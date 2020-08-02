package com.ipincloud.iotbj.oa;

import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;

public class OAMenuDelete {
    public static void main(String[] args) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost("http://10.69.206.48/snap-user-org/oapi/menu/remove?appId=Potc5cAAaXVlQASwU233NraEEo5KXAat&tenantId=lfdc&menuId=tellowtestmenu");
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