package com.ipincloud.iotbj.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MqttPushClient {
    private static Logger log = LoggerFactory.getLogger(MqttPushClient.class);
    
    @Autowired
    private PushCallback pushCallback;
    
    private static MqttClient client;
 
    public static MqttClient getClient() {
        return client;
    }
 
    public static void setClient(MqttClient client) {
        MqttPushClient.client = client;
    }
    
    private String host;
    private String clientid;
    private String username;
    private String password;
    private int timeout;
    private int keepalive;
 
    public void getParameters(String host,String clientid,String username,String password,int timeout,int keepalive) {
        this.host = host;
        this.clientid = clientid;
        this.username = username;
        this.password = password;
        this.timeout = timeout;
        this.keepalive = keepalive;
    }
    
    /**
     * 设置mqtt连接参数
     *
     * @return
     */
    public MqttConnectOptions setMqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(timeout);
        options.setKeepAliveInterval(keepalive);
        options.setCleanSession(false);
        return options;
    }
    
    public void connect(){
        try {
            if (client == null) {
                client = new MqttClient(host, clientid, new MemoryPersistence());
                client.setCallback(pushCallback);
            }
            MqttConnectOptions mqttConnectOptions = setMqttConnectOptions();
            
            if (!client.isConnected()) {
                client.connect(mqttConnectOptions);
            } else {
                client.disconnect();
                client.connect(mqttConnectOptions);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 发布，默认qos为0，非持久化
     * @param topic
     * @param pushMessage
     */
    public static void publishMsg(int qos,boolean retained,String topic,String pushMessage){
        publish(qos, retained, topic, pushMessage);
    }
 
    /**
     * 发布
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public static void publish(int qos,boolean retained,String topic,String pushMessage){
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = MqttPushClient.getClient().getTopic(topic);
        if(null == mTopic){
            log.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 订阅某个主题，qos默认为0
     * @param topic
     */
    public static void subscribeMsg(String topic,int qos){
        subscribe(topic,qos);
    }
 
    /**
     * 订阅某个主题
     * @param topic
     * @param qos
     */
    public static void subscribe(String topic,int qos){
        try {
            MqttPushClient.getClient().subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}