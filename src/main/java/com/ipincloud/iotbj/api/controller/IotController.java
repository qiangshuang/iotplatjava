package com.ipincloud.iotbj.api.controller;

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
        return new ResponseBean(200,"SUCCESS", "操作成功",list);
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
        return new ResponseBean(200,"SUCCESS", "操作成功",list);
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
        return new ResponseBean(200,"SUCCESS", "操作成功",list);
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
        return new ResponseBean(200,"SUCCESS", "操作成功",list);
    }

    @ApiOperation("算法开启")
    @PostMapping("algorithmopen")
    public Object algorithmOpen(@RequestBody String bodyStr) {
        JSONArray ja = JSONObject.parseArray(bodyStr);
        List<Map> list = iotService.algorithmOpen1(ja);
        return new ResponseBean(200,"SUCCESS", "操作成功",list);
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
        return new ResponseBean(200,"SUCCESS", "操作成功",list);
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
       return new ResponseBean(200,"SUCCESS", "操作成功",list);
    }


    @ApiOperation("算法结果返回")
    @PostMapping("/algorithmresult")
    public Object algorithmResult(@RequestParam("result") String result, @RequestParam("camera_id") String camera_id,
                                  @RequestParam("message") String message, @RequestParam(value = "img", required = false) MultipartFile img) {
        String path = "";
        if (img != null) {
            path = System.getProperty("user.dir") + "/upload/" + new Date().getTime() + img.getOriginalFilename() + ".jpg";
            File dir = new File(path);
            if (!dir.getParentFile().exists()) {// 判断目录是否存在
                dir.getParentFile().mkdir();
            }
            File newFile = new File(path);
            try {
                img.transferTo(newFile);
            } catch (Exception e) {
                e.printStackTrace();
				return new ResponseBean(200,"FAILED", "文件上传失败",null);
            }
        }
        //{"cross_detection":{"num":1,"preds":[{"sim":false,"box":[0.5680555555555555,0.48203125,0.6236111111111111,0.55390625]}]}}
        JSONObject all = new JSONObject();
        all.put("camera_id", camera_id);
        all.put("result", result);
        all.put("message", message);
        all.put("imgpath", path);
        System.out.println(all.toJSONString());
        all = algorithmresultService.addInst(all);
        return new ResponseBean(200,"SUCCESS", "操作成功",all);
    }

    @ApiOperation("算法首页")
    @PostMapping("/algorithmhome")
    public Object algorithmhome() {
        String str = "{\"base\":{\"jrls\":15,\"sfmxsl\":12,\"sxtsl\":5},\"history_alarm\":{\"by\":[1,2,3,4,5,29,10,26,15,15,1,1,1,1,1],\"day\":[1,3,5,7,9,11,13,15,17,19,1,1,1,1,1],\"sy\":[11,12,13,14,15,20,23,25,29,25,25,24,21,21,25],\"tb\":[6,7,8,9,10,12,20,16,15,35,12,18,25,18,24]},\"today_alarm\":{\"bjs\":[10,30,1,1,11],\"qrs\":[5,20,30,2,2]},\"today_warning\":{\"a\":{\"hxyj\":3.6,\"pbwz\":11.2,\"pdyc\":3.6,\"qcaq\":5.4,\"smyj\":2.2,\"xwwz\":2.1,\"xyyj\":3.3,\"zywz\":5.1},\"b\":{\"fypd\":31,\"hx\":36,\"pp\":36,\"qc\":54,\"ryyj\":50,\"sm\":20,\"wdaqm\":21,\"xy\":100}},\"totality\":{\"bjs\":100,\"fzr\":20,\"gbsl\":100,\"jrls\":400,\"kqsl\":200,\"sxtsl\":300,\"ycsl\":200,\"yxzsl\":300}}";
        JSONObject data = JSONObject.parseObject(str);
        return new ResponseBean(200,"SUCCESS", "操作成功",data);
    }

    @ApiOperation("报警统计")
    @PostMapping("/alarmcount")
    public Object alarmcount() {
        String str = "{\"data\":[{\"algorithm_id\":122,\"algorithm_name\":\"堵煤监测\",\"algorithm_num\":2},{\"algorithm_id\":124,\"algorithm_name\":\"安全帽\",\"algorithm_num\":1},{\"algorithm_id\":121,\"algorithm_name\":\"人员越界\",\"algorithm_num\":0},{\"algorithm_id\":123,\"algorithm_name\":\"皮带撕裂\",\"algorithm_num\":0},{\"algorithm_id\":125,\"algorithm_name\":\"工作服\",\"algorithm_num\":0},{\"algorithm_id\":126,\"algorithm_name\":\"明火监测\",\"algorithm_num\":0},{\"algorithm_id\":127,\"algorithm_name\":\"吸烟\",\"algorithm_num\":0}],\"msg\":\"操作已成功\",\"status\":\"SUCCESS\"}";
        JSONObject data = JSONObject.parseObject(str);
        return new ResponseBean(200,"SUCCESS", "操作成功",data);
    }

    @ApiOperation("报警明细")
    @PostMapping("/alarmdtl")
    public Object alarmdtl() {
        String str = "{\"data\":[{\"id\":1,\"alarm_time\":1594200201000,\"algorithm_id\":122,\"algorithm_name\":\"堵煤监测\",\"camera_id\":111,\"camera_name\":\"C21A2\",\"state\":\"未确认\",\"describion\":\"堵煤监测\",\"region\":\"\",\"alarm_img\":\"1594200201\",\"grade\":\"一般警告\",\"indate\":0},{\"id\":2,\"alarm_time\":1594200201000,\"algorithm_id\":122,\"algorithm_name\":\"堵煤监测\",\"camera_id\":111,\"camera_name\":\"C21A2\",\"state\":\"未确认\",\"describion\":\"堵煤监测\",\"region\":\"\",\"alarm_img\":\"1594200201\",\"grade\":\"一般警告\",\"indate\":0},{\"id\":6,\"alarm_time\":1594200976000,\"algorithm_id\":124,\"algorithm_name\":\"安全帽\",\"camera_id\":111,\"camera_name\":\"C21A1\",\"state\":\"未确认\",\"describion\":\"1\",\"region\":\"1\",\"alarm_img\":\"1594200976\",\"grade\":\"严重警告\",\"indate\":1594200976000}],\"msg\":\"操作已成功\",\"status\":\"SUCCESS\"}";
        JSONObject data = JSONObject.parseObject(str);
        return new ResponseBean(200,"SUCCESS", "操作成功",data);
    }

    @ApiOperation("摄像机首页")
    @PostMapping("/cameraindexlist")
    public Object cameraindexlist() {
        String str = "{\"data\":[{\"id\":121,\"title\":\"摄像机1\",\"videoa\":\"http://192.168.3.8:8000/live/01_192168003027_101.flv\",\"state\":\"运行中\",\"pushaddress\":\"\",\"border\":\"[[0.17666666666666667,0.16666666666666666],[0.5633333333333334,0.41333333333333333],[0.4033333333333333,0.86],[0.15,0.6066666666666667],[0.17333333333333334,0.18]]\",\"box\":\"[0.49722222222222223,0.42734375,0.6097222222222223,0.47890625]\"}],\"msg\":\"操作已成功\",\"status\":\"SUCCESS\"}";
        JSONObject data = JSONObject.parseObject(str);
        return new ResponseBean(200,"SUCCESS", "操作成功",data);
    }
}
