package com.ipincloud.iotbj.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.service.IotService;
import com.ipincloud.iotbj.srv.service.AlgorithmresultService;
import com.ipincloud.iotbj.srv.service.UserService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class IotController {
    @Autowired
    private IotService iotService;
    @Autowired
    private AlgorithmresultService algorithmresultService;
    @Autowired
    private UserService userService;

    @ApiOperation("摄像机同步")
    @PostMapping("devicesync")
    public Object deviceSync(@RequestBody String bodyStr) {
        JSONObject jo = JSONObject.parseObject(bodyStr);
        String hy = jo.getString("_hy");
        List<Map> list = new ArrayList();
        if (Objects.equals("camerasync", hy)) {
            list = iotService.deviceSync(jo);
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @ApiOperation("摄像机开启")
    @PostMapping("deviceopen")
    public Object deviceOpen(@RequestBody String bodyStr) {
        JSONObject jo = JSONObject.parseObject(bodyStr);
        String hy = jo.getString("_hy");
        List<Map> list = new ArrayList();
        if (Objects.equals("cameraopen", hy)) {
            JSONArray ja = jo.getJSONArray("qps").getJSONObject(0).getJSONArray("val");
            List<Long> ids = ja.toJavaList(Long.class);
            if (ids.size() > 0) {
                list = iotService.deviceOpen(ids);
            }
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @ApiOperation("摄像机关闭")
    @PostMapping("deviceclose")
    public Object deviceClose(@RequestBody String bodyStr) {
        JSONObject jo = JSONObject.parseObject(bodyStr);
        String hy = jo.getString("_hy");
        List<Map> list = new ArrayList();
        if (Objects.equals("cameraclose", hy)) {
            JSONArray ja = jo.getJSONArray("qps").getJSONObject(0).getJSONArray("val");
            List<Long> ids = ja.toJavaList(Long.class);
            if (ids.size() > 0) {
                list = iotService.deviceClose(ids);
            }

        }
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @ApiOperation("摄像机重启")
    @PostMapping("devicerestart")
    public Object deviceRestart(@RequestBody String bodyStr) {
        JSONObject jo = JSONObject.parseObject(bodyStr);
        String hy = jo.getString("_hy");
        List<Map> list = new ArrayList();
        if (Objects.equals("camerarestart", hy)) {
            JSONArray ja = jo.getJSONArray("qps").getJSONObject(0).getJSONArray("val");
            List<Long> ids = ja.toJavaList(Long.class);
            if (ids.size() > 0) {
                list = iotService.deviceRestart(ids);
            }
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @ApiOperation("算法开启")
    @PostMapping("algorithmopen")
    public Object algorithmOpen(@RequestBody String bodyStr) {
        JSONArray ja = JSONObject.parseArray(bodyStr);
        List<Map> list = iotService.algorithmOpen(ja);
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @PostMapping("算法关闭")
    public Object algorithmClose(@RequestBody String bodyStr) {
        JSONObject jo = JSONObject.parseObject(bodyStr);
        String hy = jo.getString("_hy");
        List<Map> list = new ArrayList();
        if (Objects.equals("algorithmclose", hy)) {
            JSONArray ja = jo.getJSONArray("qps").getJSONObject(0).getJSONArray("val");
            List<Long> ids = ja.toJavaList(Long.class);
            if (ids.size() > 0) {
                list = iotService.algorithmClose(ids);
            }
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }

    @ApiOperation("算法重启")
    @PostMapping("algorithmrestart")
    public Object algorithmRestart(@RequestBody String bodyStr) {
        JSONObject jo = JSONObject.parseObject(bodyStr);
        String hy = jo.getString("_hy");
        List<Map> list = new ArrayList();
        if (Objects.equals("algorithmrestart", hy)) {
            JSONArray ja = jo.getJSONArray("qps").getJSONObject(0).getJSONArray("val");
            List<Long> ids = ja.toJavaList(Long.class);
            if (ids.size() > 0) {
                list = iotService.algorithmRestart(ids);
            }
        }
        return new ResponseBean(200, "SUCCESS", "操作成功", list);
    }


    @ApiOperation("算法结果返回")
    @PostMapping("/algorithmresult")
    public Object algorithmResult(@RequestParam("result") String result, @RequestParam("camera_id") String camera_id,
                                  @RequestParam("message") String message, @RequestParam(value = "img", required = false) MultipartFile img) {
        String path = "";
        if (img != null) {
            String originFileName = img.getOriginalFilename();
            String suffixName = originFileName.substring(originFileName.lastIndexOf("."));

            String uuId = UUID.randomUUID().toString();
            Date date = new Date();
            String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);

            String runPath = System.getProperty("user.dir") + "/classes/upload";
            // FileUtils.getRootPath();
            String retPath = String.format("/%s/%d/%s/%s%s", "algorithmresult", 0, dateStr, uuId, suffixName);
            String fullfilPath = runPath + retPath;

            // 文件对象
            //logger.debug("debug",runPath+":"+fullfilPath);
            File dest = new File(fullfilPath);
            // 判断路径是否存在，如果不存在则创建
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                // 保存到服务器中
                img.transferTo(dest);
                path = String.format("%s;%s;0;%d;;%d", originFileName, retPath, 0, System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseBean(200, "FAILED", "文件上传不成功", "");
            }
        }
        JSONObject all = new JSONObject();
        all.put("camera_id", camera_id);
        all.put("result", result);
        all.put("message", message);
        all.put("imgpath", path);
        all = algorithmresultService.addInst(all);
        return new ResponseBean(200, "SUCCESS", "操作成功", all);
    }

    @ApiOperation("算法首页")
    @PostMapping("/algorithmhome")
    public Object algorithmhome() {
        JSONObject data = iotService.algorithmhome();
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }

    @ApiOperation("报警统计")
    @PostMapping("/alarmcount")
    public Object alarmcount() {
        List<JSONObject> data = iotService.alarmcount();
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }

    @ApiOperation("报警明细")
    @PostMapping("/alarmdtl")
    public Object alarmdtl(@RequestBody String bodyStr) {
        if (bodyStr == null) {
            return new ResponseBean(200, "FAILED", "参数有误", null);
        }
        Long algorithm_id = Long.valueOf(bodyStr.trim());
        List<JSONObject> data = iotService.alarmdtl(algorithm_id);
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }

    @ApiOperation("摄像机首页")
    @PostMapping("/cameraindexlist")
    public Object cameraindexlist(@RequestBody String bodyStr) {
        JSONArray parm = JSONArray.parseArray(bodyStr);
        if (parm == null) {
            return new ResponseBean(200, "FAILED", "参数不正确", null);
        }
        List<JSONObject> data = iotService.cameraindexlist(parm);
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }

    @ApiOperation("算法预警确定")
    @PostMapping("/confirmState")
    public Object confirmState(@RequestBody String bodyStr) {
        JSONArray jsonArray = JSONArray.parseArray(bodyStr);
        iotService.confirmState(jsonArray);
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }

    @ApiOperation("算法预警误报")
    @PostMapping("/misstate")
    public Object misstate(@RequestBody String bodyStr) {
        JSONArray jsonArray = JSONArray.parseArray(bodyStr);
        iotService.missState(jsonArray);
        return new ResponseBean(200, "SUCCESS", "操作成功", null);
    }

    @ApiOperation("实时监控设备列表")
    @PostMapping("/deviceslist")
    public Object deviceslist() {
        List<JSONObject> data = iotService.deviceslist();
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }

    @ApiOperation("其他摄像机列表")
    @PostMapping("/othercameralist")
    public Object othercameralist(@RequestBody String bodyStr) {
        JSONArray jsonArray = JSONArray.parseArray(bodyStr);
        if (jsonArray == null) {
            return new ResponseBean(200, "FAILED", "参数不正确", null);
        }
        List<JSONObject> data = iotService.otherCameralist(jsonArray);
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }
	
	@ApiOperation("实时报警明细")
    @PostMapping("/realalarmdtl")
    public Object realalarmdtl(@RequestBody String bodyStr) {
        JSONObject jsonObject = JSON.parseObject(bodyStr);
        if (jsonObject == null) {
            return new ResponseBean(200, "FAILED", "参数有误", null);
        }
        //Long algorithm_id = Long.valueOf(bodyStr.trim());
        Long algorithm_id = jsonObject.getLong("algorithm_id");
        List<JSONObject> data = iotService.alarmdtl(algorithm_id);
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }
	
    @ApiOperation("实时摄像机")
    @PostMapping("/realcameraindexlist")
    public Object realcameraindexlist() {
        JSONObject data = iotService.realcameraindexlist();
        return new ResponseBean(200, "SUCCESS", "操作成功", data);
    }
	
    @ApiOperation("实时预警列表")
    @PostMapping("/realAlarmlist")
    public Object realAlarmlist(@RequestBody String bodyStr) {
        JSONObject jsonObject = JSON.parseObject(bodyStr);
        if (jsonObject == null) {
            return new ResponseBean(200, "FAILED", "参数有误", null);
        }
        return iotService.realAlarmList(jsonObject);
    }

}
