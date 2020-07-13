package com.ipincloud.iotbj.api.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.service.IotService;
import com.ipincloud.iotbj.api.utils.IotUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import com.ipincloud.iotbj.srv.dao.*;
import com.ipincloud.iotbj.srv.domain.Algorithm;
import com.ipincloud.iotbj.srv.domain.Algorithmalarm;
import com.ipincloud.iotbj.srv.domain.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 同步摄像机
     * 调用北向接口
     */
    @Override
    public List<Map> deviceSync(JSONObject jsonObject) {
        JSONObject param = new JSONObject();
        param.put("modName", "摄像头");
        param.put("userName", "iotadmin");
        JSONObject modInfo = modInfoDao.selectModInfo(param);
        if (modInfo == null) {
            modInfo = IotUtils.getModInfo(iot_url, iot_apikey, param);
            modInfoDao.addInst(modInfo);
        }
        modInfo.put("pageNum", jsonObject.getInteger("cp"));
        modInfo.put("pageSize", jsonObject.getInteger("rop"));
        JSONArray ja = IotUtils.getCameraEntityList(iot_url, iot_apikey, modInfo);
        for (int i = 0; i < ja.size(); i++) {
            int count = modCameraDao.existByIndex(ja.getJSONObject(i).getString("cameraIndex"));
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
            //["object_detection","cross_detection","helmet_detection","call_smoke_detection","belt_mask_detection"]
            JSONArray ja = new JSONArray();
            ja.add(para.getString("accesscode"));
            jsonObject.put("algorithm", ja.toString());
            JSONObject jo = new JSONObject();
            JSONObject jo1 = new JSONObject();
            jo1.put("boundary", para.getString("border"));
            jo1.put("region", Objects.equals("内部", para.getString("direction")) ? 0 : 1);
            jo.put("cross_detection", jo1.toJSONString());
            jsonObject.put("extras", para.getString("border"));

            String result = IotUtils.postOpenAlgorithm(algorithm_url, jsonObject);
            Integer code = JSONObject.parseObject(result).getInteger("code");
            String msg = JSONObject.parseObject(result).getString("msg");
            String data = JSONObject.parseObject(result).getString("data");

            //调试时注释下面
            //Integer code =200;
            //String msg = "调试";
            Map resultMap = new HashMap();
            if (code == 200) {
                para.put("state", "运行中");
                para.put("opentime", System.currentTimeMillis());
                algorithmDao.addInst(para);
                resultMap.put("result", "SUCCESE");
                resultMap.put("msg", msg);
                resultMap.put("date", System.currentTimeMillis());
                resultMap.put("algorithm_title", para.getString(""));
                resultMap.put("camera_title", para.getString(""));
                resultMap.put("puser_title", para.getString(""));
            } else {
                resultMap.put("result", "FAILD");
                resultMap.put("msg", msg);
                resultMap.put("date", System.currentTimeMillis());
                resultMap.put("algorithm_title", para.getString(""));
                resultMap.put("camera_title", para.getString(""));
                resultMap.put("puser_title", para.getString(""));
            }

            resultList.add(resultMap);
        }
        return resultList;
    }

    /**
     * 打开算法_back
     */
    public List<Map> algorithmOpen1(JSONArray algorithms) {
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < algorithms.size(); i++) {
            set.add(algorithms.getJSONObject(i).getLong("camera_id"));
        }
        System.out.println("========================= " + set.size());
        for (Long camera_id : set) {
            Camera camera = cameraDao.queryById(camera_id);
            JSONObject jsonObject = new JSONObject();
            if (StringUtils.isEmpty(camera.getCameraIndex())) {
                jsonObject.put("camera_id", camera.getId());
            } else {
                jsonObject.put("camera_id", camera.getCameraIndex());
            }
            jsonObject.put("camera_name", camera.getTitle());
            jsonObject.put("fps", camera.getFramerate());
            jsonObject.put("resolution_ratio", camera.getResolution());
            jsonObject.put("video_stream", camera.getVideoa());
            jsonObject.put("codec", camera.getCodec());
            jsonObject.put("target_address", algorithm_url);          //算法后端配置服务器
            jsonObject.put("target", algorithm_resultUrl);

            //待调试--算法编码
            jsonObject.put("algorithm_api", "");    //算法服务器地址
            List list = new ArrayList();
            jsonObject.put("algorithm", "");
            jsonObject.put("extras", "");
        }
        return null;
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
            jsonObject.put("algorithm", algorithm.getAlgorithmTitle());
            String result = IotUtils.postCloseOneAlgorithm(algorithm_url, jsonObject);
            Integer code = JSONObject.parseObject(result).getInteger("code");
            String msg = JSONObject.parseObject(result).getString("msg");
            //String data = JSONObject.parseObject(result).getString("data");

            //调试时注释下面
            //Integer code =200;
            //String msg = "调试";
            Map resultMap = new HashMap();
            if (code == 200) {
                algorithmDao.deletesInst(JSONObject.parseObject(algorithm.getId() + ""));
                resultMap.put("result", "SUCCESE");
                resultMap.put("msg", msg);
            } else {
                resultMap.put("result", "FAILD");
                resultMap.put("msg", msg);
            }
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
            jsonObject.put("algorithm", algorithm.getAlgorithmTitle());
            jsonObject.put("extras", algorithm.getBorder());

            String result = IotUtils.postOpenAlgorithm(algorithm_url, jsonObject);
            Integer code = JSONObject.parseObject(result).getInteger("code");
            String msg = JSONObject.parseObject(result).getString("msg");
            String data = JSONObject.parseObject(result).getString("data");

            //调试时注释下面
            //Integer code =200;
            //String msg = "调试";
            Map resultMap = new HashMap();
            if (code == 200) {
                algorithm.setState("运行中");
                algorithmDao.updateInst((JSONObject) JSONObject.toJSON(algorithm));
                resultMap.put("result", "SUCCESE");
                resultMap.put("msg", msg);
            } else {
                resultMap.put("result", "FAILD");
                resultMap.put("msg", msg);
            }
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
        System.out.println("aaaaaaaaaaaa" + a);
    }

    @Override
    public void alarmSave(Algorithmalarm algorithmalarm) {
        this.algorithmalarmDao.addInst((JSONObject) JSONObject.toJSON(algorithmalarm));
    }


    /**
     * 判断视频地址是否可用
     */
    public Boolean checkUrl(String url) {
        return true;
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

    *//**
     * 获取实体
     *//*
    private ModInfo getModInfo(JSONObject jsonObject) {
        JSONObject result = IotUtils.getModelInfo(iot_url, iot_apikey, jsonObject);
        ModInfo modInfo = null;
        if (result != null) {
            modInfoDao.addInst(result);
            modInfo = result.toJavaObject(ModInfo.class);
        }
        return modInfo;
    }*/

}