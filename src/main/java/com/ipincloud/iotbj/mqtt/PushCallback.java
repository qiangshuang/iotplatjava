package com.ipincloud.iotbj.mqtt;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class PushCallback implements MqttCallback {
    
    private static Logger log = LoggerFactory.getLogger(PushCallback.class);
    
    @Autowired
    private MqttPushClient mqttPushClient;
     
    @Override
    public void connectionLost(Throwable cause) {
        log.error("连接断开，尝试重连..");
        long reconnectTimes = 1;
        while (true) {
            try {
                if (mqttPushClient.getClient().isConnected()) {
                    log.info("mqtt reconnect success end");
                    return;
                }
                log.info("mqtt reconnect times = {} try again...", reconnectTimes++);
                mqttPushClient.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                //e1.printStackTrace();
            }
        }
    }
 
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("deliveryComplete: " + token.isComplete());
    }
 
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        log.info("接收消息主题 : " + topic);
        log.info("接收消息Qos : " + message.getQos());
        log.info("接收消息内容 : " + new String(message.getPayload()));
        com.ipincloud.iotbj.mqtt.domain.MqttMessage mqttMessage = JSONObject.parseObject(new String(message.getPayload()), com.ipincloud.iotbj.mqtt.domain.MqttMessage.class);
        System.out.println(mqttMessage);

    }

    // public static void main(String[] args) {
    //     String msg = "{\"applicationID\":\"3\",\"applicationName\":\"test\",\"deviceName\":\"tempserb02\",\"devEUI\":\"347b686900420037\",\"rxInfo\":[{\"gatewayID\":\"b827ebfffededcd1\",\"uplinkID\":\"e57e591e-6f17-4609-a696-8e4dfac0376d\",\"name\":\"xwyl-gateway-01\",\"time\":\"2020-07-25T03:10:24.04466Z\",\"rssi\":-26,\"loRaSNR\":12.2,\"location\":{\"latitude\":39.926,\"longitude\":116.16566,\"altitude\":113}}],\"txInfo\":{\"frequency\":486900000,\"dr\":1},\"adr\":false,\"fCnt\":0,\"fPort\":8,\"data\":\"JElPVEVUUwEFAEHoYKMuDQo=\"}";
    //     com.ipincloud.iotbj.domain.MqttMessage mqttMessage = JSONObject.parseObject(msg, com.ipincloud.iotbj.domain.MqttMessage.class);
    //     System.out.println(mqttMessage);
    // }
}