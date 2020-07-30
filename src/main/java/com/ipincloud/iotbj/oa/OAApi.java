 package com.ipincloud.iotbj.oa;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class OAApi {
    @Value("oa.baseUrl")
    String oaBaseUrl;

    @Value("oa.appId")
    String oaAppId;

    @Value("oa.tenantId")
    String oaTenantId;

    @Value("oa.secret")
    String oaSecret;

    @Value("app.host")
    String appHost;

    @Value("app.port")
    String appPort;

    String getToken() {
        HttpGet httpGet = new HttpGet(oaBaseUrl + "/co/oapi/gettoken?appid=" + oaAppId + "&tenantId=" + oaTenantId + "&secret=" + oaSecret);
        String responseBody = execute(httpGet);
        if (responseBody != null) {
            OATokenResponse oaTokenResponse = JSON.parseObject(responseBody, OATokenResponse.class);
            if (oaTokenResponse != null && "0".equals(oaTokenResponse.access_token)) {
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
        NewGuestMessage newGuestMessage = NewGuestMessage.forGuest(oaAppId, token, appHost, appPort, guest);
        HttpPost httpPost = new HttpPost(oaBaseUrl + "/snap-app-im/oapi/message/sendworkmsg?tenantId=" + oaTenantId);
        StringEntity stringEntity = null;
        try {
            // 设置报文和通讯格式
            stringEntity = new StringEntity(JSON.toJSONString(newGuestMessage), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            execute(httpPost);
        } catch (Exception e) {
            if (e instanceof OAException) {
                throw e;
            }
            throw new OAException(e);
        } finally {
            try {
                EntityUtils.consume(stringEntity);
            } catch (IOException e) {
            }
        }
    }

    static String execute(HttpRequestBase requestBase) throws OAException {
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
}
