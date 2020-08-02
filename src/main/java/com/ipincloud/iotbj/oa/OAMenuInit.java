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

//        JSONObject menu = new JSONObject();
//        menu.put("menuName", "测试菜单2");
//        menu.put("menuId", "tellowtestmenu");
//        menu.put("webURL", "http://10.69.212.11:8189/#/rolelist");
//        menu.put("displayType", "1");
//        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
//        menu.put("isWebDisplay", 1);
//        menu.put("isMobileDisplay", 0);
        JSONArray menuData = new JSONArray();
//        menuData.add(menu);
        JSONObject menu = new JSONObject();
        menu.put("menuName", "岗位权限new");
        menu.put("menuId", "image_recognition_menu_750");
        menu.put("webURL", "http://10.69.212.11:8189/#/rolelist");
        menu.put("displayType", "1");
        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);

        menu = new JSONObject();
        menu.put("menuName", "人脸识别new");
        menu.put("menuId", "image_recognition_menu_805");
        menu.put("displayType", "2");
        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "人员管理");
        menu.put("menuId", "image_recognition_menu_806");
        menu.put("webURL", "http://10.69.212.11:8189/#/pmanagement");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_805");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "门禁管理");
        menu.put("menuId", "image_recognition_menu_809");
        menu.put("webURL", "http://10.69.212.11:8189/#/acontrol");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_805");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "权限管理");
        menu.put("menuId", "image_recognition_menu_810");
        menu.put("webURL", "http://10.69.212.11:8189/#/rmanagement");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_805");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "访问记录");
        menu.put("menuId", "image_recognition_menu_812");
        menu.put("webURL", "http://10.69.212.11:8189/#/visitrecord");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_805");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "来访人员管理");
        menu.put("menuId", "image_recognition_menu_818");
        menu.put("webURL", "http://10.69.212.11:8189/#/vipman");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_805");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);

        menu = new JSONObject();
        menu.put("menuName", "车辆识别new");
        menu.put("menuId", "image_recognition_menu_807");
        menu.put("displayType", "2");
        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "车辆白名单管理");
        menu.put("menuId", "image_recognition_menu_808");
        menu.put("webURL", "http://10.69.212.11:8189/#/carwhiteman");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_807");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "车辆闸机管理");
        menu.put("menuId", "image_recognition_menu_831");
        menu.put("webURL", "http://10.69.212.11:8189/#/cargateman");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_807");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "车辆出入记录");
        menu.put("menuId", "image_recognition_menu_835");
        menu.put("webURL", "http://10.69.212.11:8189/#/timerecord");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_807");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);

        menu = new JSONObject();
        menu.put("menuName", "安全生产识别new");
        menu.put("menuId", "image_recognition_menu_823");
        menu.put("displayType", "2");
        menu.put("parentId", "6030ac57-bbef-4ba0-8793-00a77907cd26");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "实时监控");
        menu.put("menuId", "image_recognition_menu_878");
        menu.put("webURL", "http://10.69.212.11:8189/#/homepage");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_823");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "算法管理");
        menu.put("menuId", "image_recognition_menu_824");
        menu.put("webURL", "http://10.69.212.11:8189/#/algorithmm");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_823");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "摄像机管理");
        menu.put("menuId", "image_recognition_menu_826");
        menu.put("webURL", "http://10.69.212.11:8189/#/videoca");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_823");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "算法接入");
        menu.put("menuId", "image_recognition_menu_847");
        menu.put("webURL", "http://10.69.212.11:8189/#/alaccess");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_823");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
        menuData.add(menu);
        menu = new JSONObject();
        menu.put("menuName", "报警记录");
        menu.put("menuId", "image_recognition_menu_873");
        menu.put("webURL", "http://10.69.212.11:8189/#/warnlist");
        menu.put("displayType", "1");
        menu.put("parentId", "image_recognition_menu_823");
        menu.put("isWebDisplay", 1);
        menu.put("isMobileDisplay", 0);
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