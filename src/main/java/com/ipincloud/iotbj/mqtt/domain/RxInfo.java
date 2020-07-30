package com.ipincloud.iotbj.mqtt.domain;
import lombok.Data;

import java.util.Date;

@Data
public class RxInfo {

    private String gatewayID;
    private String uplinkID;
    private String name;
    private String time;
    private int rssi;
    private double loRaSNR;
    private Location location;

}