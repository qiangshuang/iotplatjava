package com.ipincloud.iotbj.oa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class OAMenuInit {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        List<BasicNameValuePair> pairs = new ArrayList<>();
//        pairs.add(new BasicNameValuePair("appId", "Potc5cAAaXVlQASwU233NraEEo5KXAat"));
//        pairs.add(new BasicNameValuePair("tenantId", "lfdc"));

        JSONObject menu = new JSONObject();
        menu.put("menuName", "测试菜单2");
        menu.put("menuId", "tellowtestmenu");
        menu.put("webURL", "http://10.69.202.101:8089/menu");
        menu.put("displayType", "1");
        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        JSONArray menuData = new JSONArray();
        menuData.add(menu);

//        menu = new JSONObject();
//        menu.put("menuName", "人脸识别2");
//        menu.put("menuId", "face_menu");
//        menu.put("displayType", "2");
//        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
//        menu.put("isWebDisplay", 1);
//        menu.put("isMobileDisplay", 0);
//        menuData.add(menu);
//
//        menu = new JSONObject();
//        menu.put("menuName", "人员管理");
//        menu.put("menuId", "face_menu_person");
//        menu.put("webURL", "http://10.69.202.101:8089/#/pmanagement");
//        menu.put("displayType", "1");
//        menu.put("parentId", "face_menu");
//        menu.put("isWebDisplay", 1);
//        menu.put("isMobileDisplay", 0);
//        menuData.add(menu);
//        pairs.add(new BasicNameValuePair("menuData", JSON.toJSONString(menuData)));

//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
        StringBody stringBody = new StringBody(JSON.toJSONString(menuData), contentType);
        builder.addPart("menuData", stringBody);
        builder.addTextBody("appId", "Potc5cAAaXVlQASwU233NraEEo5KXAat");
        builder.addTextBody("tenantId", "lfdc");

        HttpEntity multipart = builder.build();

        HttpPost httpPost = new HttpPost("http://10.69.206.48/snap-user-org/oapi/menu/saveOrUpdate");
        try {
            // 设置报文和通讯格式
//            entity.setContentType("multipart/form-data;boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
            httpPost.setEntity(multipart);
            System.out.println(OAApi.execute(httpPost));
        } catch (Exception e) {
            if (e instanceof OAException) {
                throw e;
            }
            throw new OAException(e);
        } finally {
            try {
                EntityUtils.consume(multipart);
            } catch (IOException e) {
            }
        }
    }

}