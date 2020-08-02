package com.ipincloud.iotbj.api.utils.hik;

import com.alibaba.fastjson.*;
import com.ipincloud.iotbj.api.utils.IotUtils;
import com.ipincloud.iotbj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ApiService {
    private static Logger logger = LoggerFactory.getLogger(ApiService.class);

    // 同步门禁设备
    public static JSONObject ascDevSync(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_GET_DOOR_LIST, JSON.toJSONString(jsonObject));
        return result.data;
    }

    // 同步车道
    public static JSONObject deviceSync() {

        String apikey = "KWrgSTHd36CHMa3wlNSECTkckMntDK6U";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", "iotadmin");
        jsonObject.put("modName", "车辆道闸");



        String url = "https://10.69.206.70:10443/restapi/iot/iotinfomgr/modelInfo";
        String result = IotUtils.doPost(url, apikey, jsonObject);

        JSONObject retJson = (JSONObject) JSONObject.parse(result);
        if (retJson.getInteger("code") != 0) {
            return retJson;
        }
        /////
        JSONObject step2Json = retJson.getJSONObject("data");
        if (step2Json == null) {
            return retJson;
        }

        step2Json.remove("modName");
        String url2 = "https://10.69.206.70:10443/restapi/iot/iotinfomgr/modelKey";
        String result2 = IotUtils.doGet(url2, apikey, step2Json);
        JSONObject retJson2 = (JSONObject) JSONObject.parse(result2);
        if (retJson2.getInteger("code") != 0) {
            return retJson2;
        }
        /////
        JSONObject step3Json = retJson2.getJSONObject("data");
        if (step3Json == null) {
            return retJson2;
        }
        step3Json.remove("userName");
        step3Json.put("pageNum", 1);
        step3Json.put("pageSize", 1000);

        String url3 = "https://10.69.206.70:10443/restapi/iot/iotinfomgr/entityList";
        String result3 = IotUtils.doPost(url3, apikey, step3Json);
        JSONObject retJson3 = (JSONObject) JSONObject.parse(result3);
        return retJson3;
    }

    //车道开闸
    public static JSONObject deviceOpen(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_ROADWAY_OPEN, JSON.toJSONString(jsonObject));
        return result.data;
    }

    //设备开闸
    public static boolean gatewayOpen(String acsDevIndexCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("controlType", 2);
        jsonObject.put("doorIndexCodes", new ArrayList<>().add(acsDevIndexCode));
        BaseResponse<Map> maps = ApiUtil.post(new TypeReference<BaseResponse<Map>>() {
        }, ApiUtil.PATH_DOOR_OPEN, JSON.toJSONString(jsonObject));
        if (maps == null) {
            throw new RuntimeException(BaseResponse.ERR_NO_DATA);
        }
        return true;
    }

    //配置权限
    public static boolean authconf(JSONObject jsonObject) {
        BaseResponse<Map> maps = ApiUtil.post(new TypeReference<BaseResponse<Map>>() {
        }, ApiUtil.PATH_AUTH_CONFIG, JSON.toJSONString(jsonObject));
        if (maps == null) {
            throw new RuntimeException(BaseResponse.ERR_NO_DATA);
        }

        JSONObject createtask = new JSONObject();
        createtask.put("taskType", 4);
        BaseResponse<JSONObject> jo = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_START_AUTH_DOWNLOAD_TASK, JSON.toJSONString(createtask));

        String taskId = jo.data.get("taskId").toString();

        JSONObject task = new JSONObject();
        task.put("taskId", taskId);
        task.put("resourceInfos", jsonObject.getJSONArray("resourceInfos"));

        BaseResponse<JSONObject> jodata = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_START_AUTH_DOWNLOAD_TASK_DATA, JSON.toJSONString(task));
        if (jodata == null) {
            throw new RuntimeException(BaseResponse.ERR_NO_DATA);
        }

        task = new JSONObject();
        task.put("taskId", taskId);

        return queryAuthDownloadTaskStartAndProgress(task);
    }

    static boolean queryAuthDownloadTaskStartAndProgress(JSONObject task) {
        BaseResponse<JSONObject> jo = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_AUTH_DOWNLOAD_TASK_START, JSON.toJSONString(task));
        boolean isDownloadFinished = false;
        long start = System.currentTimeMillis();
        do {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BaseResponse<JSONObject> jo1 = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
            }, ApiUtil.PATH_QUERY_AUTH_DOWNLOAD_TASK_PROGRESS, JSON.toJSONString(task));
            if (jo1 == null) {
                throw new RuntimeException(BaseResponse.ERR_NO_DATA);
            }
            isDownloadFinished = jo1.data.getBoolean("isDownloadFinished");

        } while (!isDownloadFinished || System.currentTimeMillis() - start > 3600 * 1000);
        return isDownloadFinished;
    }

    // 获取根组织
    public static ApiModel.HikOrg getOrgRoot() {
        BaseResponse<ApiModel.HikOrg> result = ApiUtil.post(new TypeReference<BaseResponse<ApiModel.HikOrg>>() {
        }, ApiUtil.PATH_GET_ORG_ROOT, null);
        return result.data;
    }

    // 批量添加组织
    public static JSONObject addBatchOrg(List<JSONObject> list) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_BATCH_ADD_ORG, JSON.toJSONString(list));
        return result.data;
    }

    //修改组织
    public static JSONObject updateOrg(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_UPDATE_ORG, JSON.toJSONString(jsonObject));
        return result.data;
    }

    // 批量删除组织
    public static JSONObject deleteBatchOrg(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_BATCH_DELETE_ORG, JSON.toJSONString(jsonObject));
        return result.data;
    }

    // 批量添加区域
    public static JSONObject addBatchRegion(List<JSONObject> jsonArray) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_BATCH_ADD_REGION, JSON.toJSONString(jsonArray));
        return result.data;
    }

    //修改区域
    public static JSONObject updateRegion(JSONObject region) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_UPDATE_REGION, JSON.toJSONString(region));
        return result.data;
    }


    // 批量添加人员
    public static JSONObject addBatchPerson(JSONArray jsonArray) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_BATCH_ADD_PERSON, JSON.toJSONString(jsonArray));
        return result.data;
    }

    // 添加人员(人脸)
    public static String addPerson(JSONObject jsonObject) {
        BaseResponse<String> result = ApiUtil.post(new TypeReference<BaseResponse<String>>() {
        }, ApiUtil.PATH_SINGLE_ADD_PERSON, JSON.toJSONString(jsonObject));
        String personId = result.data;

//        JSONObject face = new JSONObject();
//        face.put("personId", personId);
//        face.put("faceData", jsonObject.getJSONArray("faces").getJSONObject(0).getString("faceData"));
//        BaseResponse<JSONObject> faceResult = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
//        }, ApiUtil.PATH_ADD_FACE_SINGLE, JSON.toJSONString(face));

        return personId;
    }

    // 修改人员
    public static JSONObject updatePerson(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_UPDATE_PERSON, JSON.toJSONString(jsonObject));
        //修改人脸信息
        updateFace(jsonObject);
        return result.data;
    }
    //修改人臉
    public static JSONObject updateFace(JSONObject jsonObject) {
        JSONObject face = new JSONObject();
        face.put("personId", jsonObject.getString("personId"));
        face.put("faceData", jsonObject.getString("faces"));
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_ADD_FACE, JSON.toJSONString(face));
        return result.data;
    }

    // 删除人员
    public static JSONArray deletePerson(JSONObject jsonObject) {
        BaseResponse<JSONArray> result = ApiUtil.post(new TypeReference<BaseResponse<JSONArray>>() {
        }, ApiUtil.PATH_DELETE_PERSON, JSON.toJSONString(jsonObject));
        return result.data;
    }

    //更加身份证查询人员
    public static JSONObject getPersonbycertificateno(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_GET_PERSONINFO_BY_CERTIFICATENO, JSON.toJSONString(jsonObject));
        return result.data;
    }


    //创建人脸分组
    public static JSONObject addSingleFaceGroup(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_ADD_FACE_GROUP, JSON.toJSONString(jsonObject));
        return result.data;
    }

    //门禁订阅事件
    public static void getEventFace() {
        JSONObject jsonObject = new JSONObject();
        List<Number> eventTypes = new ArrayList<>();

        eventTypes.add(196893);
        eventTypes.add(197151);
        eventTypes.add(197160);
//        eventTypes.add(197162);
//        eventTypes.add(197163);

        String eventDest = "http://10.69.202.101:8089/eventRcvFace";
        jsonObject.put("eventTypes", eventTypes);
        jsonObject.put("eventDest", eventDest);
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_GET_EVENT, JSON.toJSONString(jsonObject));

        logger.debug("门禁订阅事件返回信息:" + JSON.toJSONString(result));
    }

    public static void getEventVehicle() {
        JSONObject jsonObject = new JSONObject();
        List<Number> eventTypes = new ArrayList<>();

        eventTypes.add(771760131);
        eventTypes.add(771760134);
        String eventDest = "http://10.69.202.101:8089/eventRcvVehicle";
        jsonObject.put("eventTypes", eventTypes);
        jsonObject.put("eventDest", eventDest);
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_GET_EVENT, JSON.toJSONString(jsonObject));

        logger.debug("车辆订阅事件返回信息:" + JSON.toJSONString(result));
    }

    public static String createAuthDownloadTask(int taskType) {
        JSONObject addTaskRequest = new JSONObject();
        addTaskRequest.put("taskType", taskType);
        BaseResponse<JSONObject> response = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_CREATE_AUTH_DOWNLOAD_TASK, JSON.toJSONString(addTaskRequest));
        return response.data.getString("taskId");
    }

    public static void addAuthDownloadData(JSONObject authDownloadData) {
        ApiUtil.post(new TypeReference<BaseResponse<Object>>() {
        }, ApiUtil.PATH_ADD_AUTH_DOWNLOAD_DATA, JSON.toJSONString(authDownloadData));
    }

    public static void startAuthDownloadTask(JSONObject authDownloadRequest) {
        ApiUtil.post(new TypeReference<BaseResponse<Object>>() {
        }, ApiUtil.PATH_START_AUTH_DOWNLOAD_TASK, JSON.toJSONString(authDownloadRequest));
    }

    public static JSONObject addVehicle(List<JSONObject> list) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_VEHICLE_ADD, JSON.toJSONString(list));
        fixed(list.get(0).getString("plateNo"));
        return result.data;
    }

    public static void fixed(String plateNo) {
        BaseResponse<List<JSONObject>> parkList = ApiUtil.post(new TypeReference<BaseResponse<List<JSONObject>>>() {
        }, ApiUtil.PATH_VEHICLE_PARK_PARKLIST, JSON.toJSONString(new JSONObject()));
        String parkIndexCode = null;
        for (JSONObject jsonObject : parkList.data) {
            parkIndexCode = jsonObject.getString("parkIndexCode");
            if (StringUtils.isNotEmpty(parkIndexCode)) {
                break;
            }
        }
        JSONObject param = new JSONObject();
        param.put("parkSyscode", parkIndexCode);
        param.put("plateNo", plateNo);
        param.put("fee", "0");
        param.put("startTime", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        param.put("endTime", "2037-12-30");
        ApiUtil.post(new TypeReference<BaseResponse<Object>>() {
        }, ApiUtil.PATH_VEHICLE_CAR_CHARGE, JSON.toJSONString(param));
    }

    public static void updateVehicle(JSONObject jsonObject) {
        ApiUtil.post(new TypeReference<BaseResponse<Object>>() {
        }, ApiUtil.PATH_VEHICLE_UPDATE, JSON.toJSONString(jsonObject));
    }

    public static void deleteVehicle(JSONObject jsonObject) {
        ApiUtil.post(new TypeReference<BaseResponse<Object>>() {
        }, ApiUtil.PATH_VEHICLE_DELETE, JSON.toJSONString(jsonObject));
    }

    public static JSONObject searchVehicle(JSONObject jsonObject) {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_VEHICLE_SEARCH, JSON.toJSONString(jsonObject));
        return result.data;
    }

    // 配置员工权限
    public static void authDownload(Set<JSONObject> resourceInfos, Set<JSONObject> personInfos, Boolean addOrUpdate) {
        int taskTypeCard = 1;
        String taskIdCard = createAuthDownloadTask(taskTypeCard);
        JSONObject jsonObjectCard = new JSONObject();
        jsonObjectCard.put("taskId", taskIdCard);
        jsonObjectCard.put("resourceInfos", resourceInfos);
        jsonObjectCard.put("personInfos", personInfos);
        addAuthDownloadData(jsonObjectCard);

        if (addOrUpdate) {
            JSONObject taskCard = new JSONObject();
            taskCard.put("taskId", taskIdCard);
            queryAuthDownloadTaskStartAndProgress(taskCard);
        }

        int taskTypeFace = 4;
        String taskIdface = createAuthDownloadTask(taskTypeFace);
        JSONObject jsonObjectFace = new JSONObject();
        jsonObjectFace.put("taskId", taskIdface);
        jsonObjectFace.put("resourceInfos", resourceInfos);
        jsonObjectFace.put("personInfos", personInfos);
        addAuthDownloadData(jsonObjectFace);

        if (addOrUpdate) {
            JSONObject taskFace = new JSONObject();
            taskFace.put("taskId", taskIdface);
            queryAuthDownloadTaskStartAndProgress(taskFace);
        }

    }

    //查询单个人员权限配置
    public static JSONObject authDownloadSearch() {
        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_AUTH_ITEM_SINGLE_SEARCH, JSON.toJSONString(new JSONObject()));
        return null;
    }

    public static JSONObject authDownloadSearchList(Set<JSONObject> resourceInfos, Set<String> personIds) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNo", 1);
        jsonObject.put("pageSize", 1000);
        jsonObject.put("personIds", personIds);
        jsonObject.put("resourceInfos", resourceInfos);
        jsonObject.put("queryType", "door");
        jsonObject.put("personStatus", new int[]{3});
        jsonObject.put("cardStatus", new int[]{3});
        jsonObject.put("personStatus", new int[]{3});

        BaseResponse<JSONObject> result = ApiUtil.post(new TypeReference<BaseResponse<JSONObject>>() {
        }, ApiUtil.PATH_AUTH_ITEM_LIST_SEARCH, JSON.toJSONString(jsonObject));
        return result.data;

    }

}
