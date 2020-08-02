package com.ipincloud.iotbj.srv.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.face.dao.FaceDao;
import com.ipincloud.iotbj.oa.OAApi;
import com.ipincloud.iotbj.srv.dao.AlgorithmresultDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.domain.Algorithmresult;
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.srv.service.AlgorithmresultService;
import com.ipincloud.iotbj.utils.FileUtils;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//(Algorithmresult)算法结果 服务实现类
//generate by redcloud,2020-07-24 19:59:20
@Service("AlgorithmresultService")
public class AlgorithmresultServiceImpl implements AlgorithmresultService {

    Logger logger = LoggerFactory.getLogger(AlgorithmresultServiceImpl.class);
    @Resource
    private AlgorithmresultDao algorithmresultDao;

    @Resource
    private FaceDao faceDao;
    @Resource
    private UserDao userDao;

    @Autowired
    OAApi oaApi;

    @Value("${localhostUri}")
    private String localhostUri;

    //@param id 主键 
    //@return 实例对象Algorithmresult 
    @Override
    public Algorithmresult queryById(Long id) {
        return this.algorithmresultDao.queryById(id);
    }

    //@param jsonObj 调用参数 
    //@return 实例对象Algorithmresult 
    @Override
    public JSONObject addInst(JSONObject jsonObj) {
        logger.debug("算法结果返回{}", JSON.toJSONString(jsonObj));
        jsonObj = ParaUtils.removeSurplusCol(jsonObj, "id,camera_id,result,message,imgpath");
        this.algorithmresultDao.addInst(jsonObj);

        if (StringUtils.isNotEmpty(jsonObj.getString("camera_id"))) {
            JSONObject camera = algorithmresultDao.queryCamera(jsonObj.getString("camera_id"));
            List<String> accesscodes = algorithmresultDao.queryAccesscode();
            JSONObject algorithmalarm = new JSONObject();
            if (camera != null) {
                algorithmalarm.put("camera_id", camera.getString("id"));
                algorithmalarm.put("camera_name", camera.getString("title"));
                algorithmalarm.put("region_id", camera.getString("region_id"));
                algorithmalarm.put("region", camera.getString("region_title"));
            }
            algorithmalarm.put("alarm_time", System.currentTimeMillis());
            algorithmalarm.put("indate", System.currentTimeMillis());
            String result = jsonObj.getString("result");
            if (accesscodes != null && accesscodes.size() > 0) {
                for (int i = 0; i < accesscodes.size(); i++) {
                    String accesscode = accesscodes.get(i);
                    if (StringUtils.isNotEmpty(accesscode) && result.contains(accesscode)) {
                        JSONObject algorithmacc = algorithmresultDao.queryAlgorithm(accesscode);
                        algorithmalarm.put("algorithm_id", algorithmacc.getString("id"));
                        algorithmalarm.put("algorithm_name", algorithmacc.getString("title"));
                        algorithmalarm.put("describion", algorithmacc.getString("describion"));
                        algorithmalarm.put("grade", algorithmacc.getString("warning_level"));

                        algorithmalarm.put("alarm_img", jsonObj.getString("imgpath"));
                        algorithmalarm.put("state", "未确认");
                        algorithmalarm.put("result_id", jsonObj.getString("id"));

                        String userId = JSON.parseObject(result).getJSONObject(accesscode).getString("user_id");
                        if (userId != null && "".equals(userId)) {
                            algorithmalarm.put("personId", userId);
                            JSONObject jsonObject = faceDao.findUserByPersonId(userId);
                            algorithmalarm.put("personName", jsonObject.getString("title"));
                        }

                        String box = "";
                        JSONArray preds = JSON.parseObject(result).getJSONObject(accesscode).getJSONArray("preds");
                        if (preds != null && preds.size() > 0 && preds.getJSONObject(0) != null) {
                            JSONArray boxObj = preds.getJSONObject(0).getJSONArray("box");
                            if (boxObj != null && boxObj.size() > 0) {
                                box = boxObj.toJSONString();
                            }
                        }
                        algorithmalarm.put("box", box);

                        this.algorithmresultDao.addInstAlgorithmalarm(algorithmalarm);

                        //查询对应的路数
                        JSONObject algorithm = algorithmresultDao.queryAlgorithmByCidAndAid(camera.getLong("id"), algorithmacc.getLong("id"));

                        JSONObject message = new JSONObject();
                        message.put("displayType", "microapp");
                        message.put("msgId", algorithmalarm.getString("id"));
                        String[] user_ids = algorithm.getString("user_ids").split(",");
                        List<String> list = new ArrayList<>();

                        String personId = "";
                        for (int j = 0; j < user_ids.length; j++) {
                            User user = userDao.queryById((long) Integer.parseInt(user_ids[j]));
                            if (user != null) {
                                list.add(user.getPersonId());
                                personId += user.getPersonId() + ",";
                            }
                        }
                        personId = personId.substring(0, personId.length() - 1);
                        message.put("recipient", personId);

                        JSONObject content = new JSONObject();
                        String msg = algorithmalarm.getString("algorithm_name") + " " + algorithmalarm.getString("camera_name") + " 出现警告！";
                        content.put("type", "text");
                        content.put("msg", msg);
                        content.put("url", "");
                        content.put("redirectUrl", "");
                        content.put("fun", "IAM");
                        content.put("title", "摄像头算法报警");
                        String imgPath = localhostUri + "/face/img?imgPath=" + FileUtils.getRealFilePath(jsonObj.getString("imgpath"));
                        content.put("avatar", imgPath);

                        message.put("message", content);
                        oaApi.sendNewAlarmMessage(message);
                    }
                }
            }

        }

        // jsonObj.put("id",genId);
        return jsonObj;


    }
}
