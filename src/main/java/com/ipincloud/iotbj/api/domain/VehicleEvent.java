package com.ipincloud.iotbj.api.domain;

import lombok.Data;

import java.util.List;

@Data
public class VehicleEvent {
    public String method;
    public Params params;

    @Data
    public static class Params {
        public String ability;
        public List<Events> events;
        public String sendTime;
    }

    @Data
    public static class Events {
        public EventData data;
        public String eventId;
        public Number eventType;
        public String eventTypeName;
        public String happenTime;
        public String srcIndex;
        public String srcName;
        public String srcParentIndex;
        public String srcType;
        public Number status;
        public Number timeout;
    }

    @Data
    public static class EventData {
        public int alarmCar;
        public String carAttributeName;
        public String cardNo;
        public int eventCmd;
        public String eventIndex;
        public String gateIndex;
        public String gateName;
        public InResult inResult;
        public int inoutType;
        public int mainLogo;
        public String parkIndex;
        public String parkName;
        public int plateBelieve;
        public int plateColor;
        public String plateNo;
        public int plateType;
        public String roadwayIndex;
        public String roadwayName;
        public int roadwayType;
        public int subLogo;
        public int subModel;
        public String time;
        public int vehicleClass;
        public int vehicleColor;
        public int vehicleType;
    }

    @Data
    public static class InResult {
        public RlsResult rlsResult;
    }

    @Data
    public static class RlsResult {
        public int releaseAuth;
        public int releaseReason;
        public int releaseResult;
        public int releaseResultEx;
        public int releaseWay;
    }
}
