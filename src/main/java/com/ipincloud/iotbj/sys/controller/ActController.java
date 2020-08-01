
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
        import com.ipincloud.iotbj.utils.ParaUtils;

        import com.ipincloud.iotbj.sys.config.JWTUtil;
        import com.ipincloud.iotbj.sys.domain.ResponseBean;
        import com.ipincloud.iotbj.sys.messinfo.MessageInfo;

        import com.ipincloud.iotbj.srv.domain.*;
        import com.ipincloud.iotbj.srv.service.*;
        import com.ipincloud.iotbj.srv.service.impl.*;
        @RestController
        public class ActController {
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


            @PostMapping("/act")
            public Object act(@RequestBody String bodyStr,
                                      HttpServletResponse response)  {

                JSONObject jsonObj =  JSON.parseObject(bodyStr);
                String hyVal = jsonObj.getString("_hy");

                if (hyVal == null || "".equals(hyVal)){
                    ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "获取的参数不正确!",null);
                    return JSON.toJSONString(retResponseBean);
                }

                // Subject currentUser = SecurityUtils.getSubject();
                // if(!currentUser.isPermitted(hyVal)){
                //     ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "权限不足,请与管理员联系",null);
                //     return JSON.toJSONString(retResponseBean);
                // }
                jsonObj.remove("_hy");
                switch(hyVal){

        
        case "algorithmdel":
            {
            Integer delNum = algorithmService.deletesAlgorithmInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmadd":
            {

            JSONObject retObj = algorithmService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmup":
            {
            algorithmService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmaccdel":
            {
            Integer delNum = algorithmaccService.deletesAlgorithmaccInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmaccadd":
            {

            JSONObject retObj = algorithmaccService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "algorithmaccup":
            {
            algorithmaccService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "alaresultadd":
            {

            JSONObject retObj = algorithmresultService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "basemandel":
            {
            Integer delNum = basemanService.deletesBasemanInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "basemanadd":
            {

            JSONObject retObj = basemanService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "basemanup":
            {
            basemanService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "brimanup":
            {
            brimanService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "brimanadd":
            {

            JSONObject retObj = brimanService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "brimandel":
            {
            Integer delNum = brimanService.deletesBrimanInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cameraadd":
            {

            JSONObject retObj = cameraService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cameradel":
            {
            Integer delNum = cameraService.deletesCameraInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cameraup":
            {
            cameraService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "dataflowdel":
            {
            Integer delNum = dataflowService.deletesDataflowInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "dataflowadd":
            {

            JSONObject retObj = dataflowService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "dataflowup":
            {
            dataflowService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "iotmanadd":
            {

            JSONObject retObj = iotmanService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "iotmanup":
            {
            iotmanService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "iotmandel":
            {
            Integer delNum = iotmanService.deletesIotmanInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "jobadd":
            {

            JSONObject retObj = jobService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "jobup":
            {
            jobService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "jobdel":
            {
            Integer delNum = jobService.deleteInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "jobdels":
            {
            Integer delNum = jobService.deletesJobInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "modcameraadd":
            {

            JSONObject retObj = modCameraService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "modadd":
            {

            JSONObject retObj = modInfoService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "deptadd":
            {

            JSONObject retObj = orgService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "deptup":
            {
            orgService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "orgdel":
            {
            Integer delNum = orgService.deletesOrgInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "useradd":
            {
            JSONObject retJson = orgService.addOrgUserInstAttr(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retJson);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userup":
            {
            //前置检查
           if (ParaUtils.hasAllColValInt(jsonObj,"id").length()>0 ) { 
                ResponseBean retResponseBean = new ResponseBean(200,"FAILED", "获取的参数不正确!",jsonObj);
                return JSON.toJSONString(retResponseBean);
            }

            orgService.updateOrgUserInstAttr(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",jsonObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userdel":
            {
            Integer delNum = orgService.deletesOrgUserUserRoleInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "promanadd":
            {

            JSONObject retObj = promanService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "promanup":
            {
            promanService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "promandel":
            {
            Integer delNum = promanService.deletesPromanInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "regiondel":
            {
            Integer delNum = regionService.deletesRegionInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "regionadd":
            {

            JSONObject retObj = regionService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "regionup":
            {
            regionService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "roleadd":
            {

            JSONObject retObj = roleService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "roleup":
            {
            roleService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "roledel":
            {
            Integer delNum = roleService.deletesRoleRolePageRoleBtnUserRoleInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "roledataset":
            {
            roleDataService.roleDataSetRelation(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "rolepagejoin":
            {
            rolePageService.rolePageMmjoin(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",jsonObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "rolepagesub":
            {
            rolePageService.rolePageMmsub(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",jsonObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "equadd":
            {

            JSONObject retObj = sensorService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "equup":
            {
            sensorService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "equdel":
            {
            Integer delNum = sensorService.deletesSensorInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "sensoradd":
            {

            JSONObject retObj = sensorcateService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "sensorup":
            {
            sensorcateService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "sensortypedel":
            {
            Integer delNum = sensorcateService.deleteInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cfqdel":
            {
            Integer delNum = sensorTriggerService.deletesSensorTriggerInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cfqadd":
            {

            JSONObject retObj = sensorTriggerService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cfqup":
            {
            sensorTriggerService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "tagsdel":
            {
            Integer delNum = tagsService.deletesTagsInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "tagsadd":
            {

            JSONObject retObj = tagsService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "tagsup":
            {
            tagsService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "personadd":
            {

            JSONObject retObj = userService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "personup":
            {
            userService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "usercardadd":
            {

            JSONObject retObj = userCardService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userdataset":
            {
            userDataService.userDataSetRelation(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userolejoin":
            {
            userRoleService.userRoleMmjoin(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",jsonObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "userrolesub":
            {
            userRoleService.userRoleMmsub(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",jsonObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "carwhitemanadd":
            {

            JSONObject retObj = vehicleService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "carwhitemanup":
            {
            vehicleService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "vehicledel":
            {
            Integer delNum = vehicleService.deletesVehicleInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cargatemandel":
            {
            Integer delNum = vehicleGateService.deletesVehicleGateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",delNum);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cargateup":
            {
            vehicleGateService.updateInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",null);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "cargateadd":
            {

            JSONObject retObj = vehicleGateService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
            
        case "visitpmanadd":
            {

            JSONObject retObj = visitpersonService.addInst(jsonObj);
            ResponseBean retResponseBean = new ResponseBean(200,"SUCCESS", "操作成功",retObj);
            return JSON.toJSONString(retResponseBean);
            }
                   
                    default:
                    break;
                }
                return new ResponseBean(200,"FAILED", "获取的参数不正确!",jsonObj);
            }
        }
        