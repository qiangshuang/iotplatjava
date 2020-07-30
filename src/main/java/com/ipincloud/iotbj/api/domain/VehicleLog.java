 package com.ipincloud.iotbj.api.domain;

import lombok.Data;

@Data
public class VehicleLog {
    private String vehicle_id;
    private String in_gate_id;
    private String intime;
    private String inpic;
    private String out_gate_id;
    private String outtime;
    private String outpic;
    private String state = "禁止通过";
    private String created;
    private String updated;
    private String vehicle_title;
    private String vahicle_category;
    private String staytime;
}