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
import java.net.URLEncoder;

public class OAMenuAuth {
    public static void main(String[] args) throws UnsupportedEncodingException {

        HttpPost httpPost = new HttpPost("http://10.69.206.48/snap-user-org/oapi/roleMenu/saveOrUpdate?appId=Potc5cAAaXVlQASwU233NraEEo5KXAat&tenantId=lfdc&menuId=face_menu&positionId=18c023e523075821862caec2565754d3&position=" + URLEncoder.encode("通用岗位", "utf-8"));
        try {
            System.out.println(OAApi.execute(httpPost));
        } catch (Exception e) {
            if (e instanceof OAException) {
                throw e;
            }
            throw new OAException(e);
        }
        httpPost = new HttpPost("http://10.69.206.48/snap-user-org/oapi/roleMenu/saveOrUpdate?appId=Potc5cAAaXVlQASwU233NraEEo5KXAat&tenantId=lfdc&menuId=face_menu_person&positionId=18c023e523075821862caec2565754d3&position=" + URLEncoder.encode("通用岗位", "utf-8"));
        try {
            System.out.println(OAApi.execute(httpPost));
        } catch (Exception e) {
            if (e instanceof OAException) {
                throw e;
            }
            throw new OAException(e);
        }
    }

}