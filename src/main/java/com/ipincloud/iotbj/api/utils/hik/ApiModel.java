package com.ipincloud.iotbj.api.utils.hik;

public class ApiModel {

    public static class HikRegion {
        public String indexCode;
        public String name;
        public String parentIndexCode;
        public String treeCode;
    }

    public static class HikGateway {
        public String acsDevIndexCode;
        public String acsDevName;
        public String acsDevTypeDesc;
        public String acsDevTypeCode;
        public String acsDevTypeName;
        public String acsDevIp;
        public String acsDevPort;
        public String acsDevCode;
        public String regionIndexCode;
        public String treatyType;
        public String createTime;
        public String updateTime;
        public String firm;
        public String doorIndexCode;
        public String doorNo;
        public String channelType;
        public String channelNo;
    }

    public static class HikDoor {
        public String doorIndexCode;
        public String acsDevName;
        public String doorName;
        public String doorNo;
        public String acsDevIndexCode;
        public String regionIndexCode;
        public String channelType;
        public String channelNo;
        public String installLocation;
        public String remark;
        public String createTime;
        public String updateTime;
    }

    public static class HikOrg {
        public String orgIndexCode;
        public String orgNo;
        public String orgName;
        public String orgPath;
        public String parentOrgIndexCode;
        public String parentOrgName;
        public String updateTime;
    }

    public static class HikPerson {
        public String personId;
        public String personName;
        public String orgIndexCode;
        public String gender;
        public String birthday;
        public String phoneNo;
        public String email;
        public String certificateType;
        public String certificateNo;
        public String jobNo;
    }

    public static class HikDevice {
        public String indexCode;
        public String name;
        public String regionIndexCode;
    }


}
