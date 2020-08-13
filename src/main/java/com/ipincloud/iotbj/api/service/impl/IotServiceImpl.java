package com.ipincloud.iotbj.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.dao.IotDao;
import com.ipincloud.iotbj.api.service.IotService;
import com.ipincloud.iotbj.api.utils.IotUtils;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.domain.Algorithm;
import com.ipincloud.iotbj.srv.domain.Algorithmalarm;
import com.ipincloud.iotbj.srv.domain.Camera;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.utils.ParaUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;


/**
 * 服务接口
 *
 * @author lcc
 * @since 2020-06-29 17:21:01
 */
@Service
@Transactional
public class IotServiceImpl implements IotService {

    @Value("${algorithm.url}")
    private String algorithm_url;
    @Value("${algorithm.resultUrl}")
    private String algorithm_resultUrl;
    @Value("${iot.url}")
    private String iot_url;
    @Value("${iot.apikey}")
    private String iot_apikey;
    @Value("${iotEnable}")
    private Boolean iotEnable;

    @Autowired
    private CameraDao cameraDao;
    @Autowired
    private AlgorithmDao algorithmDao;
    @Autowired
    private AlgorithmaccDao algorithmaccDao;
    @Autowired
    private AlgorithmresultDao algorithmresultDao;
    @Autowired
    private AlgorithmalarmDao algorithmalarmDao;
    @Autowired
    private ModInfoDao modInfoDao;
    @Autowired
    private ModCameraDao modCameraDao;
    @Autowired
    private IotDao iotDao;

    /**
     * 同步摄像机
     * 调用北向接口
     */
    @Override
    public List<Map> deviceSync(JSONObject jsonObject) {
        JSONObject param = new JSONObject();
        param.put("modName", "摄像头");
        param.put("userName", "iotadmin");
        JSONObject modInfo = iotDao.selectModInfo(param);
        if (modInfo == null) {
            modInfo = IotUtils.getModInfo(iot_url, iot_apikey, param);
            modInfoDao.addInst(modInfo);
        }
        modInfo.put("pageNum", jsonObject.getInteger("cp"));
        modInfo.put("pageSize", jsonObject.getInteger("rop"));
        JSONArray ja = null;
        if (iotEnable) {
            ja = IotUtils.getCameraEntityList(iot_url, iot_apikey, modInfo);
        }
        for (int i = 0; i < ja.size(); i++) {
            int count = iotDao.existByIndex(ja.getJSONObject(i).getString("cameraIndex"));
            if (count > 0) {
                break;
            } else
                modCameraDao.addInst(ja.getJSONObject(i));
        }

        return ja.toJavaList(Map.class);
    }

    /**
     * 开启摄像机
     */
    @Override
    public List<Map> deviceOpen(List<Long> ids) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Long camera_id = ids.get(i);
            Camera camera = cameraDao.queryById(camera_id);
            JSONObject jo = new JSONObject();
            jo.put("id", camera_id);
            jo.put("state", "运行中");
            Map map = new HashMap();
            if (!checkUrl(camera.getUrl())) {
                map.put("result", "FAILD");
                map.put("msg", "视频地址无效！");
                map.put("id", camera_id);
                map.put("date", System.currentTimeMillis());
            } else {
                map.put("result", "SUCCESS");
                map.put("msg", "启动成功");
                map.put("id", camera_id);
                map.put("date", System.currentTimeMillis());
            }
            JSONObject parm = new JSONObject();

            if (StringUtils.isNotEmpty(camera.getCameraIndex())) {
                parm.put("camera_id", camera.getCameraIndex());
            } else {
                parm.put("camera_id", camera.getId().toString());
            }
            parm.put("camera_name", camera.getTitle());
            parm.put("fps", camera.getFramerate());
            parm.put("codec", camera.getCodec());
            parm.put("resolution_ratio", camera.getResolution());
            parm.put("video_stream", camera.getVideoa());
            parm.put("target_address", algorithm_url);
            if (iotEnable) {
                IotUtils.postOpenCamera(algorithm_url, parm);
            }
            cameraDao.updateInst(jo);
            list.add(map);
        }
        return list;
    }

    /**
     * 关闭摄像机
     */
    @Override
    public List<Map> deviceClose(List<Long> ids) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Long camera_id = ids.get(i);
            Camera camera = cameraDao.queryById(camera_id);
            JSONObject jo = new JSONObject();
            jo.put("id", camera_id);
            jo.put("state", "关闭");
            Map map = new HashMap();
            if (!checkUrl(camera.getUrl())) {
                map.put("result", "FAILD");
                map.put("msg", "视频地址无效！");
                map.put("id", camera_id);
                map.put("date", System.currentTimeMillis());
            } else {
                map.put("result", "SUCCESS");
                map.put("msg", "关闭成功");
                map.put("id", camera_id);
                map.put("date", System.currentTimeMillis());
            }

            JSONObject parm = new JSONObject();
            if (StringUtils.isNotEmpty(camera.getCameraIndex())) {
                parm.put("camera_id", camera.getCameraIndex());
            } else {
                parm.put("camera_id", camera.getId().toString());
            }
            if (iotEnable) {
                IotUtils.postCloseCamera(algorithm_url, parm);
            }
            cameraDao.updateInst(jo);
            list.add(map);
        }
        return list;
    }

    /**
     * 重启摄像机
     */
    @Override
    public List<Map> deviceRestart(List<Long> ids) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Long camera_id = ids.get(i);
            Camera camera = cameraDao.queryById(camera_id);
            JSONObject jo = new JSONObject();
            jo.put("id", camera_id);
            jo.put("state", "运行中");
            Map map = new HashMap();
            if (!checkUrl(camera.getUrl())) {
                map.put("result", "FAILD");
                map.put("msg", "视频地址无效！");
                map.put("id", camera_id);
                map.put("date", System.currentTimeMillis());
            } else {
                map.put("result", "SUCCESS");
                map.put("msg", "启动成功");
                map.put("id", camera_id);
                map.put("date", System.currentTimeMillis());
            }

            JSONObject parm = new JSONObject();
            if (StringUtils.isNotEmpty(camera.getCameraIndex())) {
                parm.put("camera_id", camera.getCameraIndex());
            } else {
                parm.put("camera_id", camera.getId().toString());
            }
            if (iotEnable) {
                IotUtils.postCloseCamera(algorithm_url, parm);
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            JSONObject openparm = new JSONObject();
            if (StringUtils.isNotEmpty(camera.getCameraIndex())) {
                openparm.put("camera_id", camera.getCameraIndex());
            } else {
                openparm.put("camera_id", camera.getId().toString());
            }
            openparm.put("camera_name", camera.getTitle());
            openparm.put("fps", camera.getFramerate());
            openparm.put("codec", camera.getCodec());
            openparm.put("resolution_ratio", camera.getResolution());
            openparm.put("video_stream", camera.getVideoa());
            openparm.put("target_address", algorithm_url);
            if (iotEnable) {
                IotUtils.postOpenCamera(algorithm_url, openparm);
            }
            cameraDao.updateInst(jo);
            list.add(map);
        }
        return list;
    }

    /**
     * 打开算法
     */
    @Override
    public List<Map> algorithmOpen(JSONArray algorithms) {
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < algorithms.size(); i++) {
            JSONObject para = algorithms.getJSONObject(i);
            Camera camera = cameraDao.queryById(para.getLong("camera_id"));
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(camera.getCameraIndex())) {
                jsonObject.put("camera_id", para.getString("camera_id"));
            } else {
                jsonObject.put("camera_id", camera.getCameraIndex());
            }
            jsonObject.put("camera_name", para.getString("camera_title"));
            jsonObject.put("fps", para.getString("framerate"));
            jsonObject.put("resolution_ratio", para.getString("resolution"));
            jsonObject.put("video_stream", para.getString("videoa"));
            jsonObject.put("codec", para.getString("codec"));
            jsonObject.put("target_address", algorithm_url);          //算法后端配置服务器
            jsonObject.put("target", algorithm_resultUrl);          //算法结果发送得服务器
            jsonObject.put("algorithm_api", para.getString("algorithm_url"));    //算法服务器地址
            //待调试--算法编码
            String algorithm = para.getString("accesscode");
            jsonObject.put("algorithm", algorithm);

            int apriori = para.getInteger("relation_face");

            JSONObject extras = new JSONObject();
            if (Objects.equals("object_detection", algorithm)) {
                Map object_detection = new HashMap();
                object_detection.put("width_threshold", 80);
                object_detection.put("length_threshold", 90);
                object_detection.put("apriori", apriori);
                extras.put("object_detection", object_detection);
            } else if (Objects.equals("cross_detection", algorithm)) {
                Map cross_detection = new HashMap();
                cross_detection.put("boundary", Objects.equals("", para.getString("border")) ? new JSONArray() : JSONArray.parseArray(para.getString("border")));
                cross_detection.put("region", Objects.equals("内部", para.getString("direction")) ? 0 : 1);
                cross_detection.put("apriori", apriori);
                extras.put("cross_detection", cross_detection);
            } else if (Objects.equals("helmet_detection", algorithm)) {
                Map helmet_detection = new HashMap();
                helmet_detection.put("apriori", apriori);
                extras.put("helmet_detection", helmet_detection);
            } else if (Objects.equals("call_smoke_detection", algorithm)) {
                Map call_smoke_detection = new HashMap();
                call_smoke_detection.put("apriori", apriori);
                extras.put("call_smoke_detection", call_smoke_detection);
            } else if (Objects.equals("belt_mask_detection", algorithm)) {
                Map belt_mask_detection = new HashMap();
                belt_mask_detection.put("camera_name", para.getString("camera_title"));
                belt_mask_detection.put("apriori", apriori);
                extras.put("belt_mask_detection", belt_mask_detection);
            }
            jsonObject.put("extras", JSON.toJSONString(extras));

            int code = 200;
            String msg = "";
            if (iotEnable) {
                String result = IotUtils.postOpenAlgorithm(algorithm_url, jsonObject);
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                code = jsonObject1.getInteger("code");
                msg = jsonObject1.getString("msg");
            }

            Map resultMap = new HashMap();
            if (code == 200) {
                para.put("state", "运行中");
                para.put("opentime", System.currentTimeMillis());
                algorithmDao.addInst(para);
                resultMap.put("result", "SUCCESS");
            } else {
                resultMap.put("result", "FAILD");
            }
            resultMap.put("msg", msg);
            resultMap.put("date", System.currentTimeMillis());
            resultMap.put("algorithm_title", para.getString("algorithm_title"));
            resultMap.put("camera_title", para.getString("camera_title"));
            resultMap.put("puser_title", para.getString("puser_title"));
            resultList.add(resultMap);
        }
        return resultList;
    }

    /**
     * 关闭算法
     */
    @Override
    public List<Map> algorithmClose(List<Long> ids) {
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Algorithm algorithm = algorithmDao.queryById(ids.get(i));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("camera_id", algorithm.getCameraId());
            jsonObject.put("algorithm", algorithm.getAccesscode());

            int code = 200;
            String msg = "";
            if (iotEnable) {
                String result = IotUtils.postCloseOneAlgorithm(algorithm_url, jsonObject);
                code = JSONObject.parseObject(result).getInteger("code");
                msg = JSONObject.parseObject(result).getString("msg");
            }

            Map resultMap = new HashMap();
            if (code == 200) {
                algorithm.setState("关闭");
                algorithmDao.updateInst(JSONObject.parseObject(JSON.toJSONString(algorithm)));
                //algorithmDao.deletesInst(JSONObject.parseObject(algorithm.getId() + ""));
                resultMap.put("result", "SUCCESS");
            } else {
                resultMap.put("result", "FAILD");
            }
            resultMap.put("msg", msg);
            resultMap.put("date", System.currentTimeMillis());
            resultMap.put("algorithm_title", algorithm.getAlgorithmTitle());
            resultMap.put("camera_title", algorithm.getCameraTitle());
            resultMap.put("puser_title", algorithm.getUserTitles());
            resultList.add(resultMap);
        }
        return resultList;
    }

    /**
     * 重启算法
     *
     * @param ids
     * @return
     */
    @Override
    public List<Map> algorithmRestart(List<Long> ids) {
        List<Map> resultList = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Algorithm algorithm = algorithmDao.queryById(ids.get(i));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("camera_id", algorithm.getCameraId());
            jsonObject.put("camera_name", algorithm.getCameraTitle());
            jsonObject.put("fps", algorithm.getFramerate());
            jsonObject.put("resolution_ratio", algorithm.getResolution());
            jsonObject.put("video_stream", algorithm.getVideoa());
            jsonObject.put("codec", algorithm.getCodec());
            jsonObject.put("target_address", algorithm_url);          //算法后端配置服务器
            jsonObject.put("target", algorithm_resultUrl);          //算法结果发送得服务器
            jsonObject.put("algorithm_api", algorithm.getAlgorithmUrl());    //算法服务器地址
            //待调试--算法编码

            jsonObject.put("algorithm", algorithm.getAccesscode());
            int apriori = StringUtils.isNotEmpty(algorithm.getRelation_face()) ? Integer.parseInt(algorithm.getRelation_face()) : 0;

            JSONObject extras = new JSONObject();
            if (Objects.equals("object_detection", algorithm.getAccesscode())) {
                Map object_detection = new HashMap();
                object_detection.put("width_threshold", 80);
                object_detection.put("length_threshold", 90);
                object_detection.put("apriori", apriori);
                extras.put("object_detection", object_detection);
            } else if (Objects.equals("cross_detection", algorithm.getAccesscode())) {
                Map cross_detection = new HashMap();
                cross_detection.put("boundary", Objects.equals("", algorithm.getBorder()) ? new JSONArray() : JSONArray.parseArray(algorithm.getBorder()));
                cross_detection.put("region", Objects.equals("内部", algorithm.getDirection()) ? 0 : 1);
                cross_detection.put("apriori", apriori);
                extras.put("cross_detection", cross_detection);
            } else if (Objects.equals("helmet_detection", algorithm.getAccesscode())) {
                Map helmet_detection = new HashMap();
                helmet_detection.put("apriori", apriori);
                extras.put("helmet_detection", helmet_detection);
            } else if (Objects.equals("call_smoke_detection", algorithm.getAccesscode())) {
                Map call_smoke_detection = new HashMap();
                call_smoke_detection.put("apriori", apriori);
                extras.put("call_smoke_detection", call_smoke_detection);
            } else if (Objects.equals("belt_mask_detection", algorithm.getAccesscode())) {
                Map belt_mask_detection = new HashMap();
                belt_mask_detection.put("camera_name", algorithm.getCameraTitle());
                belt_mask_detection.put("apriori", apriori);
                extras.put("belt_mask_detection", belt_mask_detection);
            }
            jsonObject.put("extras", JSON.toJSONString(extras));

            int code = 200;
            String msg = "";
            if (iotEnable) {
                String result = IotUtils.postOpenAlgorithm(algorithm_url, jsonObject);
                code = JSONObject.parseObject(result).getInteger("code");
                msg = JSONObject.parseObject(result).getString("msg");
            }

            Map resultMap = new HashMap();
            if (code == 200) {
                algorithm.setState("运行中");
                algorithmDao.updateInst(JSONObject.parseObject(JSON.toJSONString(algorithm)));
                resultMap.put("result", "SUCCESS");
            } else {
                resultMap.put("result", "FAILD");
                resultMap.put("msg", msg);
            }
            resultMap.put("msg", msg);
            resultMap.put("date", System.currentTimeMillis());
            resultMap.put("algorithm_title", algorithm.getAlgorithmTitle());
            resultMap.put("camera_title", algorithm.getCameraTitle());
            resultMap.put("puser_title", algorithm.getUserTitles());
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    public void algorithmResult(JSONObject jo, String path) {
        jo.put("imgpath", path);
        long a = algorithmresultDao.addInst(jo);
    }

    @Override
    public void alarmSave(Algorithmalarm algorithmalarm) {
        this.algorithmalarmDao.addInst((JSONObject) JSONObject.toJSON(algorithmalarm));
    }

    @Override
    public void confirmState(JSONArray jsonArray) {
        iotDao.confirmState(jsonArray);
    }

    @Override
    public void missState(JSONArray jsonArray) {
        iotDao.misstate(jsonArray);
    }

    @Override
    public JSONObject algorithmhome() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("base", iotDao.base());
        List<String> fzrs = iotDao.queryFzrTotal();
        Set<String> fzrSet = new HashSet<>();
        for (String fzr : fzrs) {
            if (StringUtils.isNotEmpty(fzr)) {
                String[] list = fzr.split(",");
                for (int i = 0; i < list.length; i++) {
                    if (StringUtils.isNotEmpty(list[i])) {
                        fzrSet.add(list[i]);
                    }
                }
            }
        }
        JSONObject totality = iotDao.totality();
        totality.put("fzr", fzrSet.size());
        jsonObject.put("totality", totality);

        Map history_alarm = new HashMap();
        List<Integer> days = new ArrayList<>();
        List<Integer> by = new ArrayList<>();
        List<Integer> sy = new ArrayList<>();
        List<String> tb = new ArrayList<>();
        List<JSONObject> bys = iotDao.by();
        List<JSONObject> sys = iotDao.sy();

        for (JSONObject byJson : bys) {
            days.add(byJson.getInteger("days"));
        }
        List<Integer> syDays = new ArrayList<>();
        for (JSONObject syJson : sys) {
            syDays.add(syJson.getInteger("days"));
        }
        if (days != null) {
            for (int i = 0; i < days.size(); i++) {
                Integer flagDay = days.get(i);
                Integer bytmp = 0;
                for (int j = 0; j < bys.size(); j++) {
                    if (flagDay == bys.get(j).getInteger("days")) {
                        bytmp = bys.get(j).getInteger("num");
                        by.add(bytmp);
                        break;
                    }
                }
                Integer sytmp = 0;
                if (sys.size() == 0) {
                    sy.add(sytmp);
                } else {
                    for (int n = 0; n < sys.size(); n++) {
                        if (syDays.contains(flagDay)) {
                            if (flagDay == sys.get(n).getInteger("days")) {
                                sytmp = sys.get(n).getInteger("num");
                                sy.add(sytmp);
                                break;
                            }
                        } else {
                            sy.add(sytmp);
                        }
                    }
                }
                if (sytmp == 0) {
                    tb.add(0 + "");
                } else {
                    tb.add(new DecimalFormat("#.00").format((double) (bytmp - sytmp) / sytmp * 100));
                }
            }
        }
        history_alarm.put("day", days);
        history_alarm.put("by", by);
        history_alarm.put("sy", sy);
        history_alarm.put("tb", tb);
        jsonObject.put("history_alarm", history_alarm);

        int alarmTotal = iotDao.alarmTotal();
        List<JSONObject> list = iotDao.alarmGroup();
        List<JSONObject> recList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jo = list.get(i);
            if (jo != null) {
                float num = jo.getFloat("num");
                String rate = "0.0";
                if (alarmTotal != 0) {
                    rate = String.format("%.1f", num / alarmTotal * 100);
                }
                jo.put("rate", rate);
            }
            recList.add(jo);
        }
        jsonObject.put("today_warning", recList);

        List<JSONObject> today_alarm = iotDao.bjsAndQrsGroup();
        jsonObject.put("today_alarm", today_alarm);

        return jsonObject;
    }

    @Override
    public List<JSONObject> cameraindexlist(JSONArray parm) {
        String region_id = parm.getString(0);
        String algorithm_id = parm.getString(1);
        String title = parm.getString(2);

        return iotDao.cameraindexlist(region_id, algorithm_id, title);
    }

    @Override
    public List<JSONObject> alarmcount() {
        return iotDao.alarmcount();
    }

    @Override
    public List<JSONObject> alarmdtl(Long algorithm_id) {
        return iotDao.alarmdtl(algorithm_id);
    }

    @Override
    public List<JSONObject> deviceslist() {
        List<JSONObject> regions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JSONObject region = new JSONObject();
            region.put("region_id", "1");
            region.put("region_title", "位置1");
            List<JSONObject> ways = new ArrayList<>();
            JSONObject way1 = new JSONObject();
            way1.put("camera_id", "10");
            way1.put("camera_title", "A摄像头");
            way1.put("algorithm_id", "100");
            way1.put("algorithm_title", "蒸汽跑冒监测");
            JSONObject way2 = new JSONObject();
            way2.put("camera_id", "20");
            way2.put("camera_title", "B摄像头");
            way2.put("algorithm_id", "100");
            way2.put("algorithm_title", "蒸汽跑冒监测");
            ways.add(way1);
            ways.add(way2);
            region.put("ways", ways);
            regions.add(region);
        }
        return regions;
    }

    @Override
    public List<JSONObject> otherCameralist(JSONArray jsonArray) {
        List<JSONObject> allList = iotDao.allCameraList();
        List<JSONObject> liveList = jsonArray.toJavaList(JSONObject.class);
        List<JSONObject> list = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            String camera_id = allList.get(i).getString("camera_id");
            String algorithm_id = allList.get(i).getString("algorithm_id");
            if (liveList == null || liveList.size() < 1) {
                list.add(allList.get(i));
            } else {
                for (int j = 0; j < liveList.size(); j++) {
                    String l_camera_id = liveList.get(j).getString("id");
                    String l_algorithm_id = liveList.get(j).getString("algorithm_id");
                    if (Objects.equals(camera_id, l_camera_id) && Objects.equals(algorithm_id, l_algorithm_id)) {
                        continue;
                    } else {
                        list.add(allList.get(i));
                        break;
                    }
                }
            }
        }
        return list;
    }

    @Override
    public JSONObject realcameraindexlist() {

        JSONObject jsonObject = new JSONObject();
        List<JSONObject> realCameraList = iotDao.realCameraIndexlist();
        if (realCameraList != null && realCameraList.size() > 0) {
            jsonObject.put("total", realCameraList.size());
            jsonObject.put("list", realCameraList);
        }
        return jsonObject;
    }

    @Override
    public Object realAlarmList(JSONObject jsonObj) {
        int totalRec = iotDao.countRealAlarmList(jsonObj);
        jsonObj = ParaUtils.checkStartIndex(jsonObj, totalRec);
        List<JSONObject> list = iotDao.realAlarmList(jsonObj);
        jsonObj.put("pageData", list);
        jsonObj.put("totalRec", totalRec);

        return new ResponseBean(200, "SUCCESS", "操作成功", jsonObj);
    }

    @Override
    public Object spatiotemporalDataTracking(JSONObject jsonObject) {
        JSONObject data = new JSONObject();
        //算法接入情况
        List<JSONObject> algorithmData = iotDao.algorithmList();
        data.put("algorithmData", algorithmData);
        //预警情况
        List<JSONObject> alarmData = iotDao.alarmList();
        data.put("alarmData", alarmData);
        //预警变化趋势
        List<JSONObject> alarmChangeData = iotDao.alarmChangeList();
        data.put("alarmChangeData", alarmChangeData);

        //预警处理情况
        JSONObject alarmHandleData = iotDao.alarmHandList();
        if (alarmHandleData != null) {
            int total = alarmHandleData.getInteger("total");
            float untreated = alarmHandleData.getInteger("untreated");
            String processRate = total == 0 ? "0" : String.format("%.2f", (total - untreated) / total * 100);
            alarmHandleData.put("processRate", processRate);
        }
        data.put("alarmHandleData", alarmHandleData);

        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }

    /**
     * 判断视频地址是否可用
     */
    public Boolean checkUrl(String url) {
        return true;
    }
}