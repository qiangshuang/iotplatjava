package com.ipincloud.iotbj.oa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class OAApi {

    @Value("${oa.baseUrl}")
    String oaBaseUrl;

    @Value("${oa.appId}")
    String oaAppId;

    @Value("${oa.tenantId}")
    String oaTenantId;

    @Value("${oa.secret}")
    String oaSecret;

    @Value("${localhostUri}")
    String appHost;

    @Value("${oa.rootMenu}")
    String rootMenu;


    public static String execute(HttpRequestBase requestBase) throws OAException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求

        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(requestBase);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            String responseBody = null;
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                responseEntity.writeTo(byteArrayOutputStream);
                responseBody = new String(byteArrayOutputStream.toByteArray());
            }
            System.out.println("响应内容:" + responseBody);
            return responseBody;
        } catch (ParseException | IOException e) {
            throw new OAException(e);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    String getToken() {
        HttpGet httpGet = new HttpGet(oaBaseUrl + "/co/oapi/gettoken?appid=" + oaAppId + "&tenantId=" + oaTenantId + "&secret=" + oaSecret);
        String responseBody = execute(httpGet);
        if (responseBody != null) {
            OATokenResponse oaTokenResponse = JSON.parseObject(responseBody, OATokenResponse.class);
            if (oaTokenResponse != null && "0".equals(oaTokenResponse.errcode)) {
                String accessToken = oaTokenResponse.access_token;
                if (!StringUtils.isEmpty(accessToken)) {
                    return accessToken;
                }
            }
        }
        throw new OAException("获取Token失败");
    }

    public void sendNewGuestMessage(String msgId, Guest guest) {
        String token = getToken();
        NewGuestMessage newGuestMessage = NewGuestMessage.forGuest(oaAppId, token, appHost, msgId, guest);
        System.out.println("推送访客到OA工作台" + oaAppId + " " + appHost + " guest" + guest.toString());
        HttpPost httpPost = new HttpPost(oaBaseUrl + "/snap-app-im/oapi/message/sendworkmsg?tenantId=" + oaTenantId);

        try {

            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("sender", oaAppId));
            paramList.add(new BasicNameValuePair("senderName", URLEncoder.encode("访客申请")));
            paramList.add(new BasicNameValuePair("token", token));
            //paramList.add(new BasicNameValuePair("tenantId",oaTenantId));你通过以下我微信
            System.out.println(JSON.toJSONString(newGuestMessage.message));
            paramList.add(new BasicNameValuePair("message", JSON.toJSONString(newGuestMessage.message)));

            HttpEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
            // 设置报文和通讯格式
            //stringEntity = new StringEntity(JSON.toJSONString(newGuestMessage), "UTF-8");
            //stringEntity.setContentEncoding("UTF-8");
            //stringEntity.setContentType("application/json");
            //httpPost.setEntity(stringEntity);

//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
//            //StringBody stringBody = new StringBody(JSON.toJSONString(menuData), contentType);
//            builder.addTextBody("sender", oaAppId);
//            builder.addTextBody("senderName", URLEncoder.encode("访客申请"));
//            builder.addTextBody("token",token);
//            //builder.addTextBody("appId", "Potc5cAAaXVlQASwU233NraEEo5KXAat");
//            StringBody stringBody = new StringBody(JSON.toJSONString(newGuestMessage), contentType);
//            builder.addPart("message", stringBody);
//
//            //builder.addTextBody("tenantId", "lfdc");
//
//            HttpEntity multipart = builder.build();

            httpPost.setEntity(entity);

            execute(httpPost);
        } catch (Exception e) {
//
            e.printStackTrace();
        }
    }

    public void sendNewAlarmMessage(JSONObject alarmlog) {
        String token = getToken();
        HttpPost httpPost = new HttpPost(oaBaseUrl + "/snap-app-im/oapi/message/sendworkmsg?tenantId=" + oaTenantId);
        try {
            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("sender", oaAppId));
            paramList.add(new BasicNameValuePair("senderName", URLEncoder.encode("算法报警")));
            paramList.add(new BasicNameValuePair("token", token));
            System.out.println("推送算法报警信息到OA工作台" + JSON.toJSONString(alarmlog));
            paramList.add(new BasicNameValuePair("message", JSON.toJSONString(alarmlog)));
            HttpEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
            httpPost.setEntity(entity);
            execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeRoleMenu(String roleId, String roleName, JSONArray rolePages) throws OAException {
        try {
            if (rolePages != null && rolePages.size() > 0) {
                StringBuilder menus = new StringBuilder();
                for (int i = 0; i < rolePages.size(); i++) {
                    if (i > 0) {
                        menus.append(",");
                    }
                    menus.append("image_recognition_menu_").append(rolePages.getIntValue(i));
                }
                HttpPost httpPost = new HttpPost(oaBaseUrl + "/snap-user-org/oapi/roleMenu/remove?appId=" + oaAppId + "&tenantId=" + oaTenantId + "&position=" + URLEncoder.encode(roleName, "utf-8") + "&positionId=" + roleId + "&menuId=" + menus.toString());
                System.out.println(OAApi.execute(httpPost));
            }
        } catch (Exception e) {
            throw new OAException(e);
        }
    }

    public void saveOrUpdateRoleMenu(String roleId, String roleName, JSONArray rolePages) throws OAException {
        if (rolePages != null && rolePages.size() > 0) {
            StringBuilder menus = new StringBuilder(rootMenu);
            for (int i = 0; i < rolePages.size(); i++) {
                menus.append(",").append("image_recognition_menu_").append(rolePages.getJSONObject(i).getIntValue("page_id"));
            }
            try {
                HttpPost httpPost = new HttpPost(oaBaseUrl + "/snap-user-org/oapi/roleMenu/saveOrUpdate?appId=" + oaAppId + "&tenantId=" + oaTenantId + "&position=" + URLEncoder.encode(roleName, "utf-8") + "&positionId=" + roleId + "&menuId=" + menus.toString());
                System.out.println(OAApi.execute(httpPost));
            } catch (Exception e) {
                throw new OAException(e);
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(rootMenu);
            removeRoleMenu(roleId, roleName, jsonArray);
        }
    }
}
