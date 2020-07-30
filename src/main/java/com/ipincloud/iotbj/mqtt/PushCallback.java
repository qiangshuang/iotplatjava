package com.ipincloud.iotbj.mqtt;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.ipincloud.iotbj.srv.domain.Dataflow; 
import com.ipincloud.iotbj.srv.domain.SensorAlarm; 
import com.ipincloud.iotbj.srv.domain.SensorVal; 
import com.ipincloud.iotbj.srv.domain.Sensor; 
import com.ipincloud.iotbj.srv.domain.SensorTrigger;

import com.ipincloud.iotbj.srv.dao.DataflowDao; 
import com.ipincloud.iotbj.srv.dao.SensorAlarmDao; 
import com.ipincloud.iotbj.srv.dao.SensorValDao; 
import com.ipincloud.iotbj.srv.dao.SensorDao; 
import com.ipincloud.iotbj.srv.dao.SensorTriggerDao; 

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.*;
import java.time.LocalDateTime;
     

@EnableScheduling
@Component
public class PushCallback implements MqttCallback {
    
    private static Logger log = LoggerFactory.getLogger(PushCallback.class);

    private static List<SensorTrigger> listSensorTriggers = new ArrayList<SensorTrigger>();
    private static Map<String,JSONObject> sensorStreamLast = new HashMap<String,JSONObject>();
    @Autowired
    private MqttPushClient mqttPushClient;
    
    @Autowired
    private  DataflowDao dataflowDao;
    @Autowired
    private  SensorAlarmDao sensorAlarmDao;
    @Autowired
    private SensorValDao sensorValDao;
    @Autowired
    private SensorDao sensorDao;
    @Autowired
    private SensorTriggerDao sensorTriggerDao;

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
        

        if (mqttMessage.getData() == null || "".equals(mqttMessage.getData()) ){
            log.info("收到无效物联数据:"+ mqttMessage.toString());
            return;
        }
        String devcode = mqttMessage.getDevEUI();
        Sensor sensor = sensorDao.querybyDevcode(devcode);
        if (sensor == null || "".equals(sensor.getStreamId()) ){
            log.info("devcode无有效的设备或者数据流模版:" + devcode );
            return;
        }
		
        String sensorValStr = "";
        switch (sensor.getCategoryId()){ //category_id
            case "温度传感器":{
                byte[] btaRecv = ConvertSensorVal.base64DecodeStr(mqttMessage.getData());
                if (btaRecv == null || btaRecv.length < 14) { //初始化
                    log.info("收到无效物联数据:"+ mqttMessage.toString());
                    return;
                }
                
                byte[] btaConvert = new byte[4];
                btaConvert[0] = btaRecv[13];
                btaConvert[1] = btaRecv[12];
                btaConvert[2] = btaRecv[11];
                btaConvert[3] = btaRecv[10];
                float tempc = ConvertSensorVal.byteBE2Float(btaConvert);
                
                sensorValStr = String.format("%f",tempc);
           
                break;
            }
            case "振动传感器":{
                log.info("未处理的设备类型:"+ devcode.toString());
                return;
            }
            case "粉尘传感器":{
                log.info("未处理的设备类型:"+ devcode.toString());
                return;
            }
            case "气体传感器":{
                log.info("未处理的设备类型:"+ devcode.toString());
                return;
            }
            case "噪声传感器":{
                log.info("未处理的设备类型:"+ devcode.toString());
                return;
            }
            default:{
                log.info("未处理的设备类型:"+ devcode.toString());
                return;
            }
        }

        String sensorStreamId = sensor.getStreamId();
        List<Dataflow> sensorDataFlows = dataflowDao.queryByIds(sensorStreamId);

        if(sensorDataFlows == null || sensorDataFlows.size() < 1){
            log.info("devcode无有效的数据流模版:" + devcode );
            return;
        }
        Dataflow triggerDataflow = null;

        for(Dataflow dataflow :sensorDataFlows){
			
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("dataval",sensorValStr);
            jsonObj.put("unitname",dataflow.getCompany());
            jsonObj.put("unitcode",dataflow.getComsym());
            jsonObj.put("dataflow_title",dataflow.getDatatitle());
            jsonObj.put("device_id",sensor.getId());
            jsonObj.put("device_name",sensor.getTitel());
            jsonObj.put("dataflow_id",dataflow.getId());
            jsonObj.put("sendtime",System.currentTimeMillis());

            sensorValDao.addInst(jsonObj);
            
            for (SensorTrigger sensorTrigger : listSensorTriggers){
                
                String equIds = sensorTrigger.getEquIds();
                Long streamId = sensorTrigger.getStreamId();
                
                if (equIds == null || streamId < 1){
                    continue;
                }
                String tEquIds = ","+equIds+",";
                
                if (tEquIds.contains(","+sensor.getEquId()+",") && dataflow.getId()== streamId ) {

                    String keyLast = String.format("%s_%d",sensor.getEquId(),dataflow.getId() );
                    
                    JSONObject valLast = sensorStreamLast.get(keyLast);

                    if (TriggerCond.isTrigger(sensorValStr,sensorTrigger.getCond(),sensorTrigger.getDataval(),valLast) ){
                        
                        JSONObject alarmObj = new JSONObject();
                        alarmObj.put("sensor_id",sensor.getId());
                        alarmObj.put("sensor_title",sensor.getTitel());
                        alarmObj.put("region_id",0);
                        alarmObj.put("region_title","");
                        alarmObj.put("sensor_categary",sensor.getCategoryId());
                        alarmObj.put("dataval",sensorValStr);
                        alarmObj.put("standardval",sensorTrigger.getCond());
                        alarmObj.put("grade","0");
                        alarmObj.put("alarmtiem",System.currentTimeMillis());
                        alarmObj.put("state","未确认");
                        alarmObj.put("created",System.currentTimeMillis());
                        alarmObj.put("updated",System.currentTimeMillis());
                        // 主键id
                        sensorAlarmDao.addInst(alarmObj);
                    }

                    jsonObj.put("lasttime",System.currentTimeMillis());
                    sensorStreamLast.put(keyLast,jsonObj);
                }
            }

        }
    }
    
    
    // public static void main(String[] args) {
    //     String msg = "{\"applicationID\":\"3\",\"applicationName\":\"test\",\"deviceName\":\"tempserb02\",\"devEUI\":\"347b686900420037\",\"rxInfo\":[{\"gatewayID\":\"b827ebfffededcd1\",\"uplinkID\":\"e57e591e-6f17-4609-a696-8e4dfac0376d\",\"name\":\"xwyl-gateway-01\",\"time\":\"2020-07-25T03:10:24.04466Z\",\"rssi\":-26,\"loRaSNR\":12.2,\"location\":{\"latitude\":39.926,\"longitude\":116.16566,\"altitude\":113}}],\"txInfo\":{\"frequency\":486900000,\"dr\":1},\"adr\":false,\"fCnt\":0,\"fPort\":8,\"data\":\"JElPVEVUUwEFAEHoYKMuDQo=\"}";
    //     com.ipincloud.iotbj.domain.MqttMessage mqttMessage = JSONObject.parseObject(msg, com.ipincloud.iotbj.domain.MqttMessage.class);
    //     System.out.println(mqttMessage);
    // }
}