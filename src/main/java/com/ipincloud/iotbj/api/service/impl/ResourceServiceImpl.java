package com.ipincloud.iotbj.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.dao.ResourceDao;
import com.ipincloud.iotbj.api.domain.AuditLog;
import com.ipincloud.iotbj.api.domain.DoorEvent;
import com.ipincloud.iotbj.api.domain.VehicleEvent;
import com.ipincloud.iotbj.api.service.ResourceService;
import com.ipincloud.iotbj.api.utils.ResourceUtils;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.srv.dao.OrgDao;
import com.ipincloud.iotbj.srv.dao.UserDao;
import com.ipincloud.iotbj.srv.domain.User;
import com.ipincloud.iotbj.sys.domain.ResponseBean;
import com.ipincloud.iotbj.utils.ParaUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    static final Set<String> AUDIT_DOOR_NAME_SET = new HashSet<String>() {
        {
            add("东二门中人脸识别出_门1");
            add("东二门人脸识别中间进_门1");
            add("东二门人脸识别北出_门1");
            add("东二门人脸识别南出_门1");
            add("东二门人脸识别南进_门1");
            add("东二门北人脸识别进_门1");
            add("人脸识别进_门1");
            add("人脸识别出_门1");
        }
    };
    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    UserDao userDao;
    @Autowired
    OrgDao orgDao;

    static void commitAuditLog2IAM(AuditLog auditLog) {
        String url = "http://10.69.206.48/snap-app-application/oapi/attendance/user/access/sync";
        //iamRequest.userId = auditLog.getUserID();
        //System.out.println(auditLog.getSrcName());
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;
        HttpClient httpClient = null;
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdfISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        try {
            Date data = sdfISO8601.parse(auditLog.getHappenTime());
            Long tim = data.getTime();
            System.out.println(tim);
            map.put("tenantId", "lfdc");
            map.put("gateName", auditLog.getGateName());
            map.put("userId", auditLog.getUserID());
            map.put("time", tim.toString());
            map.put("inOut", auditLog.getExtEventInOut().toString());
            // 创建连接
            //httpClient = HttpClientFactory.getInstance().getHttpClient();
            httpClient = HttpClients.createDefault();
            // 设置请求头和报文
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
            httpPost.setEntity(entity);
            //执行发送，获取相应结果
            httpResponse = httpClient.execute(httpPost);
            httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object syncOrg() {
        try {
            List<ApiModel.HikOrg> infoList = ResourceUtils.getOrgList();
            //orgDao.tempDeleteAllOrg();
            if (infoList != null && infoList.size() > 0) {
                JSONArray jsonArray = JSON.parseArray(infoList.toString());
                for (int i = 0; i < jsonArray.size(); i++) {
                    resourceDao.insertOrUpdateOrg(jsonArray.getJSONObject(i));
                }
            }
            resourceDao.updateOrgOther();
        } catch (RuntimeException e) {
            return new ResponseBean(200, "FAILED", "同步失败", null);
        }
        return new ResponseBean(200, "SUCCESS", "同步成功", null);
    }

    @Override
    public Object syncResion() {
        try {
            List<ApiModel.HikRegion> infoList = ResourceUtils.getRegionList();
            //orgDao.tempDeleteAllOrg();
            if (infoList != null && infoList.size() > 0) {
                JSONArray jsonArray = JSON.parseArray(infoList.toString());
                for (int i = 0; i < jsonArray.size(); i++) {
                    resourceDao.insertOrUpdateRegion(jsonArray.getJSONObject(i));
                }
            }
            resourceDao.updateResionOther();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseBean(200, "FAILED", "同步失败", null);
        }
        return new ResponseBean(200, "SUCCESS", "同步成功", null);
    }

    @Override
    public Object syncUser() {
        try {
            List<ApiModel.HikPerson> infoList = ResourceUtils.getPersonList();
            //orgDao.tempDeleteAllOrg();
            if (infoList != null && infoList.size() > 0) {
                JSONArray jsonArray = JSON.parseArray(infoList.toString());
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject.getLong("gender") == 1) {
                        jsonObject.put("gender", "男");
                    } else if (jsonObject.getLong("gender") == 2) {
                        jsonObject.put("gender", "女");
                    } else {
                        jsonObject.put("gender", "未知");
                    }
                    jsonObject.put("title", jsonObject.getString("personName"));
                    jsonObject.put("idnumber", jsonObject.getString("certificateNo"));
                    jsonObject.put("user_name", jsonObject.getString("jobNo"));
                    jsonObject.put("mobile", jsonObject.getString("phone"));
                    jsonObject.put("parent_title", jsonObject.getString("orgPathName"));

                    int count = resourceDao.exitsUserbyPersonId(jsonObject.getString("personId"));

                    if (count == 0) {
                        JSONObject jsonObjFirst = ParaUtils.copyJsonObjectCols(jsonObject, "id,type,parent_id,sort,data_scope,stop,memo,parent_title,title,orgIndexCode");
                        this.orgDao.addInst(jsonObjFirst);
                        JSONObject jsonObjSecond = ParaUtils.copyJsonObjectCols(jsonObject, "mobile,user_name,pwd,id,title,parent_id,parent_title,lastlogin,job_title,job_id,thirdin,created,updated,photo,idnumber,cardnumber,mcardno,gender,personId");
                        jsonObjSecond.put("id", jsonObjFirst.get("id"));
                        this.userDao.addInst(jsonObjSecond);
                    }

                }
            }
        } catch (RuntimeException e) {
            return new ResponseBean(200, "FAILED", "同步失败", null);
        }
        return new ResponseBean(200, "SUCCESS", "同步成功", null);
    }

    @Override
    public Object syncGateway() {
        return null;
    }

    @Override
    public Object saveEventVehicle(VehicleEvent vehicleEvent) throws ParseException {
        logger.debug("车辆识别事件返回信息:" + JSON.toJSONString(vehicleEvent));

        JSONObject vehicle_history = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date indate = simpleDateFormat.parse(vehicleEvent.params.events.get(0).happenTime);
        Date outdate = simpleDateFormat.parse(vehicleEvent.params.sendTime);
        vehicle_history.put("in_gate_id", vehicleEvent.params.events.get(0).data.gateName);
        vehicle_history.put("intime", indate.getTime());
        vehicle_history.put("inpic", "");
        vehicle_history.put("out_gate_id", vehicleEvent.params.events.get(0).data.roadwayName);
        vehicle_history.put("outtime", outdate.getTime());
        vehicle_history.put("outpic", "");
        vehicle_history.put("state", vehicleEvent.params.events.get(0).data.inResult.rlsResult.releaseAuth == 1 ? "允许通行" : "禁止通行");
        vehicle_history.put("staytime", vehicleEvent.params.events.get(0).timeout);
        vehicle_history.put("vehicle_title", vehicleEvent.params.events.get(0).data.plateNo);
        vehicle_history.put("vahicle_category", vehicleEvent.params.events.get(0).data.carAttributeName);
        vehicle_history.put("created", System.currentTimeMillis());
        vehicle_history.put("updated", System.currentTimeMillis());

        resourceDao.insertVehicleHistory(vehicle_history);
        return null;
    }

    @Override
    public JSONObject saveEventFace(DoorEvent doorEvent) {
        logger.debug("人脸识别事件返回信息:" + JSON.toJSONString(doorEvent));

        AuditLog auditLog = new AuditLog();
        auditLog.setSrcName(doorEvent.params.events.get(0).srcName);
        auditLog.setExtEventInOut(doorEvent.params.events.get(0).data.ExtEventInOut);
        auditLog.setGateName(doorEvent.params.events.get(0).srcName);
        auditLog.setHappenTime(doorEvent.params.events.get(0).happenTime);
        int eventType = doorEvent.params.events.get(0).eventType.intValue();
        if (eventType == 196893 || eventType == 197162) {
            auditLog.setResult("允许通过");
        }
        if (doorEvent.params.events.get(0).data.ExtEventCardNo != null) {
            User account = resourceDao.getAccountByCertificateNum(doorEvent.params.events.get(0).data.ExtEventCardNo.toString());
            if (account != null) {
                auditLog.setUserID(account.getPersonId());
                auditLog.setName(account.getTitle());
                auditLog.setNo(account.getUserName());
                auditLog.setMobiles(account.getMobile());
                auditLog.setCertificateNum(account.getIdnumber());
                auditLog.setUserGroup(account.getUserGroup());
                auditLog.setGender(account.getGender());
                auditLog.setCreated(System.currentTimeMillis());
                auditLog.setUpdated(System.currentTimeMillis());
            }

            resourceDao.saveAuditLog(auditLog);
            if (account != null && !account.isGuest() && AUDIT_DOOR_NAME_SET.contains(auditLog.getSrcName())) {
                commitAuditLog2IAM(auditLog);
            }
        }
        return null;
    }
}