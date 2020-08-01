package com.ipincloud.iotbj.oa;

import com.alibaba.fastjson.JSON;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class OAApi {

    @Value("${oa.baseUrl}")
    String oaBaseUrl = "http://10.69.206.48";

    @Value("${oa.appId}")
    String oaAppId = "Potc5cAAaXVlQASwU233NraEEo5KXAat";

    @Value("${oa.tenantId}")
    String oaTenantId = "lfdc";

    @Value("${oa.secret}")
    String oaSecret = "79246cdd-dfa2-47ad-9252-289b78b8947e";

    @Value("${localhostUri}")
    String appHost = "http://10.69.202.101:8089";


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

    public void sendNewGuestMessage(Guest guest) {
        String token = getToken();
        NewGuestMessage newGuestMessage = NewGuestMessage.forGuest(oaAppId, token, appHost, guest);
        System.out.println("推送访客到OA工作台" + oaAppId + " " + appHost + " guest" + guest.toString());
        HttpPost httpPost = new HttpPost(oaBaseUrl + "/snap-app-im/oapi/message/sendworkmsg?tenantId=" + oaTenantId);

        try {

            List<NameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("sender",oaAppId));
            paramList.add(new BasicNameValuePair("senderName",URLEncoder.encode("访客申请")));
            paramList.add(new BasicNameValuePair("token",token));
            //paramList.add(new BasicNameValuePair("tenantId",oaTenantId));你通过以下我微信
            System.out.println(JSON.toJSONString(newGuestMessage.message));
            paramList.add(new BasicNameValuePair("message",JSON.toJSONString(newGuestMessage.message)));

            HttpEntity entity = new UrlEncodedFormEntity(paramList,"UTF-8");
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
}
