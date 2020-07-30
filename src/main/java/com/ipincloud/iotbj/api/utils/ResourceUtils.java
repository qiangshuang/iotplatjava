package com.ipincloud.iotbj.api.utils;


import com.alibaba.fastjson.TypeReference;
import com.ipincloud.iotbj.api.utils.hik.ApiModel;
import com.ipincloud.iotbj.api.utils.hik.ApiUtil;

import java.util.List;

public class ResourceUtils {

    public static List<ApiModel.HikOrg> getOrgList() {
        List<ApiModel.HikOrg> hikOrgs = ApiUtil.getAllData(new TypeReference<List<ApiModel.HikOrg>>() {
        }, ApiUtil.PATH_GET_ORG_LIST, null);
        return hikOrgs;
    }

    public static List<ApiModel.HikRegion> getRegionList() {
        List<ApiModel.HikRegion> hikRegions = ApiUtil.getAllData(new TypeReference<List<ApiModel.HikRegion>>() {
        }, ApiUtil.PATH_GET_REGION_LIST, null);
        return hikRegions;
    }

    public static List<ApiModel.HikPerson> getPersonList() {
        List<ApiModel.HikPerson> hikPersons = ApiUtil.getAllData(new TypeReference<List<ApiModel.HikPerson>>() {
        }, ApiUtil.PATH_GET_ORG_LIST, null);
        return hikPersons;
    }

    public static List<ApiModel.HikGateway> getGatewayList() {
        List<ApiModel.HikGateway> hikGateways = ApiUtil.getAllData(new TypeReference<List<ApiModel.HikGateway>>() {
        }, ApiUtil.PATH_GET_REGION_LIST, null);
        return hikGateways;
    }

    public static void main(String[] args) {
        List<ApiModel.HikOrg> list = getOrgList();
        System.out.println("-------------" + list.size());
    }

}
