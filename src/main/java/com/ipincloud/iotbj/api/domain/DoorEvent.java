package com.ipincloud.iotbj.api.domain;

import lombok.Data;

import java.util.List;

@Data
public class DoorEvent {
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
        public int ExtEventCardNo;
        public int ExtEventInOut;
    }
}
