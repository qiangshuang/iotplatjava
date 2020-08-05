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
        JSONArray ja = IotUtils.getCameraEntityList(iot_url, iot_apikey, modInfo);
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
            IotUtils.postOpenCamera(algorithm_url, parm);

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
            IotUtils.postCloseCamera(algorithm_url, parm);

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
            IotUtils.postCloseCamera(algorithm_url, parm);
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
            IotUtils.postOpenCamera(algorithm_url, openparm);

            cameraDao.updateInst(jo);
            list.add(map);
        }
        return list;
    }

    /**
     * 打开算法_back
     */
//    public List<Map> algorithmOpen1(JSONArray algorithms) {
//        Set<Long> set = new HashSet<>();
//        for (int i = 0; i < algorithms.size(); i++) {
//            set.add(algorithms.getJSONObject(i).getLong("camera_id"));
//        }
//        for (Long camera_id : set) {
//            Camera camera = cameraDao.queryById(camera_id);
//            JSONObject jsonObject = new JSONObject();
//            if (StringUtils.isEmpty(camera.getCameraIndex())) {
//                jsonObject.put("camera_id", camera.getId());
//            } else {
//                jsonObject.put("camera_id", camera.getCameraIndex());
//            }
//            jsonObject.put("camera_name", camera.getTitle());
//            jsonObject.put("fps", camera.getFramerate());
//            jsonObject.put("resolution_ratio", camera.getResolution());
//            jsonObject.put("video_stream", camera.getVideoa());
//            jsonObject.put("codec", camera.getCodec());
//            jsonObject.put("target_address", algorithm_url);          //算法后端配置服务器
//            jsonObject.put("target", algorithm_resultUrl);
//
//            //待调试--算法编码
//            jsonObject.put("algorithm_api", "");    //算法服务器地址
//            List list = new ArrayList();
//            jsonObject.put("algorithm", "");
//            jsonObject.put("extras", "");
//        }
//        return null;
//    }

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
                cross_detection.put("boundary", Objects.equals("", para.getString("border")) ? new JSONArray(): JSONArray.parseArray(para.getString("border")));
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

            String result = IotUtils.postOpenAlgorithm(algorithm_url, jsonObject);
            JSONObject jsonObject1 = JSONObject.parseObject(result);
            Integer code = jsonObject1.getInteger("code");
            String msg = jsonObject1.getString("msg");
            //String data = JSONObject.parseObject(result).getString("data");

            Map resultMap = new HashMap();
            if (code == 200) {
                para.put("state", "运行中");
                para.put("opentime", System.currentTimeMillis());
                algorithmDao.addInst(para);
                resultMap.put("result", "SUCCESE");
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

            String result = IotUtils.postCloseOneAlgorithm(algorithm_url, jsonObject);
            Integer code = JSONObject.parseObject(result).getInteger("code");
            String msg = JSONObject.parseObject(result).getString("msg");

            //调试时注释下面
            //Integer code =200;
            //String msg = "调试";
            Map resultMap = new HashMap();
            if (code == 200) {
                algorithm.setState("关闭");
                algorithmDao.updateInst(JSONObject.parseObject(JSON.toJSONString(algorithm)));
                //algorithmDao.deletesInst(JSONObject.parseObject(algorithm.getId() + ""));
                resultMap.put("result", "SUCCESE");
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
                cross_detection.put("boundary", Objects.equals("", algorithm.getBorder()) ? new JSONArray(): JSONArray.parseArray(algorithm.getBorder()));
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

            String result = IotUtils.postOpenAlgorithm(algorithm_url, jsonObject);
            Integer code = JSONObject.parseObject(result).getInteger("code");
            String msg = JSONObject.parseObject(result).getString("msg");
            //String data = JSONObject.parseObject(result).getString("data");

            //调试时注释下面
            //Integer code =200;
            //String msg = "调试";
            Map resultMap = new HashMap();
            if (code == 200) {
                algorithm.setState("运行中");
                algorithmDao.updateInst(JSONObject.parseObject(JSON.toJSONString(algorithm)));
                resultMap.put("result", "SUCCESE");
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
        jsonObject.put("totality", iotDao.totality());

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
        JSONObject today_warning = new JSONObject();
        Map a = new HashMap();
        a.put("zywz", 0);
        a.put("xwwz", 0);
        a.put("pdyc", 0);
        a.put("hxyj", 0);
        a.put("qcaq", 0);
        a.put("pbwz", 0);
        a.put("xyyj", 0);
        a.put("smyj", 0);
        Map b = new HashMap();
        b.put("fypd", 0);
        b.put("wdaqm", 0);
        b.put("pp", 0);
        b.put("hx", 0);
        b.put("qc", 0);
        b.put("ryyj", 0);
        b.put("xy", 0);
        b.put("sm", 0);
        for (int i = 0; i < list.size(); i++) {
            Long algorithm_id = list.get(i).getLong("algorithm_id");
            int num = list.get(i).getInteger("num");
            if (algorithm_id == 1) {
                b.put("fypd", num);
                a.put("zywz", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 2) {
                b.put("wdaqm", num);
                a.put("xwwz", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 3) {
                b.put("pp", num);
                a.put("pdyc", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 4) {
                b.put("hx", num);
                a.put("hxyj", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 5) {
                b.put("qc", num);
                a.put("qcaq", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 6) {
                b.put("ryyj", num);
                a.put("pbwz", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 7) {
                b.put("xy", num);
                a.put("xyyj", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            } else if (algorithm_id == 8) {
                b.put("sm", num);
                a.put("smyj", new DecimalFormat("#.00").format((double) num / alarmTotal * 100));
            }
        }
        today_warning.put("a", a);
        today_warning.put("b", b);
        jsonObject.put("today_warning", today_warning);

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

    /*    *//**
     * 获取模型
     *//*
    private ModInfo saveModInfo(JSONObject jsonObject) {
        JSONObject result = JSONObject.parseObject(IotUtils.getModelInfo(iot_url, iot_apikey, jsonObject));
        ModInfo modInfo = null;
        if (result.getInteger("code") == 0) {
            JSONObject jo = result.getJSONObject("data");
            modInfoDao.addInst(jo);
            modInfo = jo.toJavaObject(ModInfo.class);
        }
        return modInfo;
    }

    */

    /**
     * 判断视频地址是否可用
     */
    public Boolean checkUrl(String url) {
        return true;
    }
}