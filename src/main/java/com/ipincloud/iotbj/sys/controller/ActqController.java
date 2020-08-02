
        package com.ipincloud.iotbj.sys.controller;
        import java.io.UnsupportedEncodingException;
        import java.util.List;
        import java.util.Map;
        import java.util.ArrayList;
        import javax.servlet.http.HttpServletResponse;

        import org.apache.shiro.authz.UnauthorizedException;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.ResponseStatus;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestBody;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import com.alibaba.fastjson.JSONObject;
        import com.alibaba.fastjson.JSON;

        import org.apache.shiro.SecurityUtils;
        import org.apache.shiro.subject.Subject;

        import com.ipincloud.iotbj.utils.FileUtils;
        import com.ipincloud.iotbj.utils.RsaUtils;

        import com.ipincloud.iotbj.sys.config.JWTUtil;
        import com.ipincloud.iotbj.sys.domain.ResponseBean;
        import com.ipincloud.iotbj.sys.messinfo.MessageInfo;

        import com.ipincloud.iotbj.srv.domain.*;
        import com.ipincloud.iotbj.srv.service.*;
        import com.ipincloud.iotbj.srv.service.impl.*;
        @RestController
        public class ActqController {
            private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
            
            @Autowired
            private AccesslogService accesslogService;

            @Autowired
            private AlgorithmService algorithmService;

            @Autowired
            private AlgorithmaccService algorithmaccService;

            @Autowired
            private AlgorithmalarmService algorithmalarmService;

            @Autowired
            private AlgorithmresultService algorithmresultService;

            @Autowired
            private BasemanService basemanService;

            @Autowired
            private BrimanService brimanService;

            @Autowired
            private BtnService btnService;

            @Autowired
            private CameraService cameraService;

            @Autowired
            private DataflowService dataflowService;

            @Autowired
            private IotmanService iotmanService;

            @Autowired
            private JobService jobService;

            @Autowired
            private ModCameraService modCameraService;

            @Autowired
            private ModInfoService modInfoService;

            @Autowired
            private OrgService orgService;

            @Autowired
            private PageService pageService;

            @Autowired
            private PromanService promanService;

            @Autowired
            private RegionService regionService;

            @Autowired
            private RoleService roleService;

            @Autowired
            private RoleDataService roleDataService;

            @Autowired
            private RolePageService rolePageService;

            @Autowired
            private SensorService sensorService;

            @Autowired
            private SensorcateService sensorcateService;

            @Autowired
            private SensorTriggerService sensorTriggerService;

            @Autowired
            private TagsService tagsService;

            @Autowired
            private UserService userService;

            @Autowired
            private UserCardService userCardService;

            @Autowired
            private UserDataService userDataService;

            @Autowired
            private UserRoleService userRoleService;

            @Autowired
            private VehicleService vehicleService;

            @Autowired
            private VehicleGateService vehicleGateService;

            @Autowired
            private VehicleHistoryService vehicleHistoryService;

            @Autowired
            private VisitpersonService visitpersonService;


            @PostMapping("/actq")
            public Object actq(@RequestBody String bodyStr,
                                      HttpServletResponse response)  {

                JSONObject jsonObj =  JSON.parseObject(bodyStr);
                String hyVal = jsonObj.getString("_hy");

                if (hyVal == null || "".equals(hyVal)){
                    ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "获取的参数不正确!",null);
                    return JSON.toJSONString(retResponseBean);
                }


//                Subject currentUser = SecurityUtils.getSubject();
//                if(!currentUser.isPermitted(hyVal)){
//                    ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
//                    return JSON.toJSONString(retResponseBean);
//                }

                jsonObj.remove("_hy");
                switch(hyVal){

        
        case "/face_history":
            {
            List<Map> retMap = accesslogService.accesslogQuery(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmlist":
            {
            Map retMap = algorithmService.algorithmList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmacclist":
            {
            Map retMap = algorithmaccService.algorithmaccList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "alarmlist":
            {
            Map retMap = algorithmalarmService.algorithmalarmList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "basemanlist":
            {
            Map retMap = basemanService.basemanList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "brimanlist":
            {
            Map retMap = brimanService.brimanList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "btnlist":
            {
            Map retMap = btnService.btnList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cameralist":
            {
            Map retMap = cameraService.cameraList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "dataflowlist":
            {
            Map retMap = dataflowService.dataflowList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "datafllist":
            {
            Map retMap = dataflowService.dataflowList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "iotmanlist":
            {
            Map retMap = iotmanService.iotmanList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "joblist":
            {
            Map retMap = jobService.jobList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "orgtt":
            {
            List<Map> retMap = orgService.orgTree(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "departlist":
            {
            List<Map> retMap = orgService.orgQuery(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "orglist":
            {
            Map retMap = orgService.orgList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "orgdeptlist":
            {
            List<Map> retMap = orgService.orgQuery(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "rolepagemmlist":
            {
            Map retMap = pageService.pageRolePageMmlist(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "promanlist":
            {
            Map retMap = promanService.promanList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "regiontt":
            {
            List<Map> retMap = regionService.regionTree(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "rolelist":
            {
            Map retMap = roleService.roleList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "rolesellist":
            {
            List<Map> retMap = roleService.roleQuery(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userrolelist":
            {
            Map retMap = roleService.roleUserRoleMmlist(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "roledatalist":
            {
            List<Map> retMap = roleDataService.roleDataQuery(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "equlist":
            {
            Map retMap = sensorService.sensorList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "equulist":
            {
            Map retMap = sensorService.sensorList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "sensortypelist":
            {
            Map retMap = sensorcateService.sensorcateList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cfqlist":
            {
            Map retMap = sensorTriggerService.sensorTriggerList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "tagslist":
            {
            Map retMap = tagsService.tagsList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userlist":
            {
            Map retMap = userService.userList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "pushuserlist":
            {
            Map retMap = userService.userList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "roleuserlist":
            {
            Map retMap = userService.userUserRoleListJoin(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "usercardlist":
            {
            Map retMap = userCardService.userCardList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userdatalist":
            {
            List<Map> retMap = userDataService.userDataQuery(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "vehiclelist":
            {
            Map retMap = vehicleService.vehicleList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cargatemanlist":
            {
            Map retMap = vehicleGateService.vehicleGateList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "vehicle_historylist":
            {
            Map retMap = vehicleHistoryService.vehicleHistoryList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "visitpersonlist":
            {
            Map retMap = visitpersonService.visitpersonList(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retMap);
            return JSON.toJSONString(retResponseBean);
            }
                   
                    default:
                    break;
                }
                return new ResponseBean(200,"FAILED", "获取的参数不正确!",jsonObj);
            }
        }
        