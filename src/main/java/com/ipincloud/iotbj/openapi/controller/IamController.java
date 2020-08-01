package com.ipincloud.iotbj.face.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.face.service.FaceService;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class FaceController {

    @Autowired
    FaceService faceService;

    @PostMapping("syncacsdev")
    public Object syncacsdev(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.syncAcsdev(jsonObj);
    }

    @PostMapping("gatewayadd")
    public Object gatewayadd(@RequestBody String jsonstr) {
        JSONArray jsonObj = JSON.parseArray(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.gatewayadd(jsonObj);
    }

    @PostMapping("gatewayup")
    public Object gatewayup(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.gatewayup(jsonObj);
    }

    @PostMapping("regionlist")
    public Object regionlist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.regionlist(jsonObj);
    }

    @PostMapping("gatewaylist")
    public Object gatewaylist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.gatewaylist(jsonObj);
    }

    @PostMapping("gatewaydel")
    public Object gatewaydel(@RequestBody String jsonstr) {
        JSONArray jsonObj = JSONArray.parseArray(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.gatewaydel(jsonObj);
    }

    @PostMapping("gatewayopen")
    public Object gatewayopen(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.gatewayopen(jsonObj);
    }

    @PostMapping("policyadd")
    public Object policyadd(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.policyadd(jsonObj);
    }


    @PostMapping("policylist")
    public Object policylist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.policylist(jsonObj);
    }

//    @PostMapping("policyallow")
//    public Object policyallow(@RequestBody String jsonstr) {
//        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
//        if (jsonObj == null) {
//            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
//        }
//        return faceService.policyallow(jsonObj);
//    }
//
//    @PostMapping("policyprohibit")
//    public Object policyprohibit(@RequestBody String jsonstr) {
//        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
//        if (jsonObj == null) {
//            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
//        }
//        return faceService.policyprohibit(jsonObj);
//    }

    @PostMapping("policydel")
    public Object policydel(@RequestBody String jsonstr) {
        JSONArray jsonObj = JSONArray.parseArray(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.policydel(jsonObj);
    }

    //访问记录
    @PostMapping("visithistorylist")
    public Object visithistorylist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visithistorylist(jsonObj);
    }

    //来访人员新增
    @PostMapping("visitpersonadd")
    public Object visitpersonadd(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitpersonadd(jsonObj);
    }

    //来访人员修改
    @PostMapping("visitpersonup")
    public Object visitpersonup(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitpersonup(jsonObj);
    }

    //来访人员删除
    @PostMapping("visitpersondel")
    public Object visitpersondel(@RequestBody String jsonstr) {
        JSONArray jsonObj = JSONArray.parseArray(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitpersondel(jsonObj);
    }

    //来访人员是否录入
    @PostMapping("visitpersoncheck")
    public Object visitpersoncheck(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitpersoncheck(jsonObj);
    }


    @PostMapping("userlist")
    public Object userlist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.userlist(jsonObj);
    }

    @PostMapping("visitlist")
    public Object visitlist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitList(jsonObj);
    }

    @PostMapping("visitallow")
    public Object visitallow(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitallow(jsonObj);
    }

    @PostMapping("visitprohibit")
    public Object visitprohibit(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitprohibit(jsonObj);
    }

    @PostMapping("visitresult")
    public Object visitresult(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        Long userId = jsonObj.getLong("userId");
        if (jsonstr == null || userId == null || userId == 0) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.visitresult(userId);
    }


    @PostMapping("cardadd")
    public Object cardadd(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonstr == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.cardadd(jsonObj);
    }

    @PostMapping("carddel")
    public Object carddel(@RequestBody String jsonstr) {
        JSONArray jsonArray = JSONArray.parseArray(jsonstr);
        if (jsonArray == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.carddel(jsonArray);
    }

    @PostMapping("cardstart")
    public Object cardstart(@RequestBody String jsonstr) {
        JSONArray jsonArray = JSONArray.parseArray(jsonstr);
        if (jsonstr == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.cardstart(jsonArray);
    }

    @PostMapping("cardstop")
    public Object cardstop(@RequestBody String jsonstr) {
        JSONArray jsonArray = JSONArray.parseArray(jsonstr);
        if (jsonstr == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.cardstop(jsonArray);
    }

    @PostMapping("cardlist")
    public Object cardlist(@RequestBody String jsonstr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonstr);
        if (jsonObj == null) {
            return new ResponseBean(200, "FAILED", "获取的参数不正确!", null);
        }
        return faceService.cardlist(jsonObj);
    }

    @RequestMapping("/face/img")
    public void faceUserImage(@RequestParam(value = "imgPath") String imgPath, HttpServletResponse response) {
        InputStream is = null;
        OutputStream os = null;
        try {
            String runPath = System.getProperty("user.dir") + "/classes/upload";
            String imgFullPath = runPath + imgPath;
            File file = new File(imgFullPath);
            response.setContentType("image/jpg");
            response.addHeader("Content-Length", "" + file.length());
            is = new FileInputStream(file);
            os = response.getOutputStream();
            IOUtils.copy(is, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
