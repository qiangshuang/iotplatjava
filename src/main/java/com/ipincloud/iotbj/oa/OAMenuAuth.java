package com.ipincloud.iotbj.oa;

import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OAMenuAuth {
    public static void main(String[] args) throws UnsupportedEncodingException {

        HttpPost httpPost = new HttpPost("http://10.69.206.48/snap-user-org/oapi/roleMenu/saveOrUpdate?appId=Potc5cAAaXVlQASwU233NraEEo5KXAat&tenantId=lfdc&menuId=image_recognition_menu_873&positionId=18c023e523075821862caec2565754d3&position=" + URLEncoder.encode("通用岗位", "utf-8"));
        try {
            System.out.println(OAApi.execute(httpPost));
        } catch (Exception e) {
            if (e instanceof OAException) {
                throw e;
            }
            throw new OAException(e);
        }
//        httpPost = new HttpPost("http://10.69.206.48/snap-user-org/oapi/roleMenu/saveOrUpdate?appId=Potc5cAAaXVlQASwU233NraEEo5KXAat&tenantId=lfdc&menuId=face_menu_person&positionId=18c023e523075821862caec2565754d3&position=" + URLEncoder.encode("通用岗位", "utf-8"));
//        try {
//            System.out.println(OAApi.execute(httpPost));
//        } catch (Exception e) {
//            if (e instanceof OAException) {
//                throw e;
//            }
//            throw new OAException(e);
//        }
    }

}