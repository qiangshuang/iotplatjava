package com.ipincloud.iotbj.api.service;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.domain.DoorEvent;
import com.ipincloud.iotbj.api.domain.VehicleEvent;

import java.text.ParseException;

public interface ResourceService {

    Object syncOrg();

    Object syncResion();

    Object syncUser();

    Object syncGateway();

    Object saveEventFace(DoorEvent doorEvent);

    Object saveEventVehicle(VehicleEvent vehicleEvent) throws ParseException;
}