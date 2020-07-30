package com.ipincloud.iotbj.mqtt.domain;

import lombok.Data;

@Data
public class Location {

    private double latitude;
    private double longitude;
    private int altitude;
}