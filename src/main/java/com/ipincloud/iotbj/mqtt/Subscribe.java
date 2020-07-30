package com.ipincloud.iotbj.mqtt;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class Subscribe implements ApplicationRunner{

    /**
     * 默认订阅的主题
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String msg = "{\"applicationID\":\"3\",\"applicationName\":\"test\",\"deviceName\":\"tempserb02\",\"devEUI\":\"347b686900420037\",\"rxInfo\":[{\"gatewayID\":\"b827ebfffededcd1\",\"uplinkID\":\"e57e591e-6f17-4609-a696-8e4dfac0376d\",\"name\":\"xwyl-gateway-01\",\"time\":\"2020-07-25T03:10:24.04466Z\",\"rssi\":-26,\"loRaSNR\":12.2,\"location\":{\"latitude\":39.926,\"longitude\":116.16566,\"altitude\":113}}],\"txInfo\":{\"frequency\":486900000,\"dr\":1},\"adr\":false,\"fCnt\":0,\"fPort\":8,\"data\":\"JElPVEVUUwEFAEHoYKMuDQo=\"}";

        //MqttPushClient.subscribe("application/3/device/347b686900420037/rx");
        // MqttPushClient.publishMsg(0,false,"application/3/device/347b686900420037/rx",msg);
        // Thread.sleep(4000);
        MqttPushClient.subscribeMsg("application/3/device/347b686900420037/rx",0);

    }
}