package com.ipincloud.iotbj.mqtt.domain;
import lombok.Data;

import java.util.List;

@Data
public class MqttMessage {

    private String applicationID;
    private String applicationName;
    private String deviceName;
    private String devEUI;
    private List<RxInfo> rxInfo;
    private TxInfo txInfo;
    private boolean adr;
    private int fCnt;
    private int fPort;
    private String data;
}