 package com.ipincloud.iotbj.api.domain;

import java.util.List;


public class DoorEvent {
    public String method;
    public Params params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public static class Params {
        public String ability;
        public List<Events> events;
        public String sendTime;

        public String getAbility() {
            return ability;
        }

        public void setAbility(String ability) {
            this.ability = ability;
        }

        public List<Events> getEvents() {
            return events;
        }

        public void setEvents(List<Events> events) {
            this.events = events;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }
    }


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

        public EventData getData() {
            return data;
        }

        public void setData(EventData data) {
            this.data = data;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public Number getEventType() {
            return eventType;
        }

        public void setEventType(Number eventType) {
            this.eventType = eventType;
        }

        public String getEventTypeName() {
            return eventTypeName;
        }

        public void setEventTypeName(String eventTypeName) {
            this.eventTypeName = eventTypeName;
        }

        public String getHappenTime() {
            return happenTime;
        }

        public void setHappenTime(String happenTime) {
            this.happenTime = happenTime;
        }

        public String getSrcIndex() {
            return srcIndex;
        }

        public void setSrcIndex(String srcIndex) {
            this.srcIndex = srcIndex;
        }

        public String getSrcName() {
            return srcName;
        }

        public void setSrcName(String srcName) {
            this.srcName = srcName;
        }

        public String getSrcParentIndex() {
            return srcParentIndex;
        }

        public void setSrcParentIndex(String srcParentIndex) {
            this.srcParentIndex = srcParentIndex;
        }

        public String getSrcType() {
            return srcType;
        }

        public void setSrcType(String srcType) {
            this.srcType = srcType;
        }

        public Number getStatus() {
            return status;
        }

        public void setStatus(Number status) {
            this.status = status;
        }

        public Number getTimeout() {
            return timeout;
        }

        public void setTimeout(Number timeout) {
            this.timeout = timeout;
        }
    }


    public static class EventData {
        public int ExtEventCardNo;
        public int ExtEventInOut;

        public int getExtEventCardNo() {
            return ExtEventCardNo;
        }

        public void setExtEventCardNo(int extEventCardNo) {
            ExtEventCardNo = extEventCardNo;
        }

        public int getExtEventInOut() {
            return ExtEventInOut;
        }

        public void setExtEventInOut(int extEventInOut) {
            ExtEventInOut = extEventInOut;
        }
    }
}
