package com.ipincloud.iotbj.api.utils.hik;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ApiUtil {

    public static final String PATH_UPDATE_REGION = "/api/resource/v1/region/single/update";
    public static String PATH_GET_ORG_ROOT = "/api/resource/v1/org/rootOrg";
    public static String PATH_BATCH_ADD_ORG = "/api/resource/v1/org/batch/add";
    public static String PATH_GET_ORG_LIST = "/api/resource/v1/org/orgList";
    public static String PATH_UPDATE_ORG = "/api/resource/v1/org/single/update";
    public static String PATH_BATCH_DELETE_ORG = "/api/resource/v1/org/batch/delete";

    public static String PATH_GET_REGION_LIST = "/api/resource/v1/regions";
    public static String PATH_BATCH_ADD_REGION = "/api/resource/v1/region/batch/add";

    public static String PATH_GET_PERSON_LIST = "/api/resource/v2/person/personList";
    public static String PATH_GET_PERSONINFO_BY_PERSONID = "/api/resource/v1/person/personId/personInfo";
    public static String PATH_SINGLE_ADD_PERSON = "/api/resource/v1/person/single/add";
    public static String PATH_BATCH_ADD_PERSON = "/api/resource/v1/person/batch/add";
    public static String PATH_UPDATE_PERSON = "/api/resource/v1/person/single/update";
    public static String PATH_DELETE_PERSON = "/api/resource/v1/person/batch/delete";
    public static String PATH_CARD_BINDINGS = "/api/cis/v1/card/bindings";
    //public static String PATH_ADD_FACE_ = "/api/frs/v1/face/single/addition";
    public  static String PATH_GET_PERSON_LIST_BY = "/api/resource/v2/person/advance/personList";
    public static String PATH_ADD_FACE = "/api/resource/v1/face/single/add";
    public static String PATH_UPDATE_FACE = "/api/resource/v1/face/single/update";
    public static String PATH_SEARCH_FACE_GROUP = "/api/frs/v1/face/group";
    public static String PATH_ADD_FACE_GROUP = "/api/frs/v1/face/group/single/addition";
    public static String PATH_GET_PERSONINFO_BY_CERTIFICATENO = "/api/resource/v1/person/certificateNo/personInfo";
    public static String PATH_GET_PERSONINFO_BY_PHONENO = "/api/resource/v1/person/phoneNo/personInfo";
    public static String PATH_ADD_FACE_SINGLE = "/api/resource/v1/face/single/add";

    public static String PATH_GET_GATEWAY_LIST = "/api/resource/v1/acsDevice/acsDeviceList";
    public static String PATH_GET_ACSDEVICE_LIST = "/api/resource/v1/acsDevice/advance/acsDeviceList";
    public static String PATH_GET_DOOR_LIST = "/api/resource/v1/acsDoor/advance/acsDoorList";
    public static String PATH_DOOR_OPEN = "/api/acs/v1/door/doControl";
    public static String PATH_CREATE_AUTH_DOWNLOAD_TASK = "/api/acps/v1/authDownload/task/addition";
    public static String PATH_ADD_AUTH_DOWNLOAD_DATA = "/api/acps/v1/authDownload/data/addition";

    public static String PATH_START_AUTH_DOWNLOAD_TASK = "/api/acps/v1/download/configuration/task/add";
    public static String PATH_START_AUTH_DOWNLOAD_TASK_DATA = "/api/acps/v1/download/configuration/data/add";
    public static String PATH_AUTH_DOWNLOAD_TASK_START = "/api/acps/v1/authDownload/task/start";
    public static String PATH_QUERY_AUTH_DOWNLOAD_TASK_PROGRESS = "/api/acps/v1/authDownload/task/progress";
    public static String PATH_AUTH_CONFIG = "/api/acps/v1/auth_config/add";
    public static String PATH_AUTH_CONFIG_SEARCH = "/api/acps/v1/auth_config/search";
    public static String PATH_AUTH_ITEM_LIST_SEARCH = "/api/acps/v1/auth_item/list/search";
    public static String PATH_AUTH_ITEM_SINGLE_SEARCH = "/api/acps/v1/auth_item/single/search";
    public static String PATH_SEARCH_DOWNLOAD_RECORD_PERSON_DETAIL = "/api/acps/v1/download_record/person/detail/search";
    public static String PATH_EVENT_SUBSCRIPTION_BY_EVENT_TYPES = "/api/eventService/v1/eventSubscriptionByEventTypes";
    public static String PATH_ROADWAY_OPEN = "/api/pms/v1/deviceControl";
    public static String PATH_GET_ROADWAY = "/api/resource/v1/roadway/roadwayList";

    public static String PATH_GET_EVENT = "/api/eventService/v1/eventSubscriptionByEventTypes";

    public static String PATH_VEHICLE_SEARCH = "/api/resource/v1/vehicle/advance/vehicleList";
    public static String PATH_VEHICLE_ADD = "/api/resource/v1/vehicle/batch/add";
    public static String PATH_VEHICLE_UPDATE = "/api/resource/v1/vehicle/single/update";
    public static String PATH_VEHICLE_DELETE = "/api/resource/v1/vehicle/batch/delete";
    public static String PATH_VEHICLE_PARK_PARKLIST = "/api/resource/v1/park/parkList";
    public static String PATH_VEHICLE_CAR_CHARGE = "/api/pms/v1/car/charge";

    private static Logger logger = LoggerFactory.getLogger(ApiUtil.class);

    public static <T> List<T> getAllData(TypeReference<List<T>> typeReference, String url, JSONObject jsonObject) {
        List<T> list = new ArrayList<T>();
        int pageNo = 1, pageSize = 1000;
        while (true) {
            HashMap pageRequest = new HashMap();
            pageRequest.put("pageNo", pageNo);
            pageRequest.put("pageSize", pageSize);
            if (jsonObject != null && jsonObject.size() > 0) {
                for (String key : jsonObject.keySet()) {
                    pageRequest.put(key, jsonObject.getString(key));
                }
            }
            BaseResponse<GetPageData> response = post(new TypeReference<BaseResponse<GetPageData>>() {
            }, url, JSON.toJSONString(pageRequest));
            //
            if (response.data == null) {
                throw new RuntimeException(BaseResponse.ERR_NO_DATA);
            }
            GetPageData<T> data = response.data;
            int total = data.total;
            if (data.list != null) {
                list.addAll(data.list);
            }
            if (pageNo * pageSize >= total) {
                break;
            }
            pageNo++;
        }
        return list;
    }


    public static <T> BaseResponse<T> post(TypeReference<BaseResponse<T>> typeReference, String pathSegment, String body) {

        // STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
        ArtemisConfig.host = "192.168.11.11:443"; // artemis网关服务器ip端口
        ArtemisConfig.appKey = "24238186"; // 秘钥appkey
        ArtemisConfig.appSecret = "SZ9qba6gOajOjZJ4wd03";// 秘钥appSecret

        // STEP2：设置OpenAPI接口的上下文
        String ARTEMIS_PATH = "/artemis";

        // STEP3：设置PATH
        Map<String, String> path = new HashMap<>(2);

        //  STEP4：设置参数提交方式
        String CONTENT_TYPE_JSON = "application/json";

        if (pathSegment == null || !pathSegment.startsWith("/")) {
            throw new IllegalArgumentException();
        }

        path.put("https://", ARTEMIS_PATH + pathSegment);
        logger.debug("调用海康接口:" + pathSegment + " body:" + body);
        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, CONTENT_TYPE_JSON, null);
        logger.debug("调用海康接口返回result:" + result);

        //通过fastjson把【响应结果】转为对象
        BaseResponse<T> response = JSON.parseObject(result, typeReference);
        if (response == null) {
            throw new HikException(BaseResponse.ERR_NO_DATA);
        }
        if (!Objects.equals("0", response.code)) {
            throw new HikException(response.msg);
        }
        return response;
    }

}
