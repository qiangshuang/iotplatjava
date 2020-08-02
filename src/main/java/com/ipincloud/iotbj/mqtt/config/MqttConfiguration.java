package com.ipincloud.iotbj.mqtt.config;

import com.ipincloud.iotbj.mqtt.MqttPushClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Data
//@Component
//@Configuration
//@ConfigurationProperties(MqttConfiguration.PREFIX)
public class MqttConfiguration {
    
//    @Autowired
    private MqttPushClient mqttPushClient;
    
    public static final String PREFIX = "mqtt";
    private String host;
    private String clientid;
    private String username;
    private String password;
    private String topic;
    private int timeout;
    private int keepalive;
 
    public void setClientid(String clientid) {
        this.clientid = clientid+System.currentTimeMillis();
    }
    
//    @Bean
    public MqttPushClient getMqttPushClient(){
         mqttPushClient.getParameters(host, clientid, username, password, timeout,keepalive);
         mqttPushClient.connect();
         return mqttPushClient;
    }
}