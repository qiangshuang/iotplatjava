 package com.ipincloud.iotbj.oa;

import java.net.URLEncoder;

public class NewGuestMessage {
    public String sender;
    public String senderName;
    public String token;
    public Message message;

    public static class Message {
        public String recipient;
        public String displayType;
        public String msgId;
        public Content message;
    }

    public static class Content {
        public String type;
        public String msg;
        public String url;
        public String redirectUrl;
        public String mobileUrl;
        public String mobileRedirectUrl;
        public String fun;
        public String title;
        public String avatar;
        public String businessType;
        public String businessStatus;
    }

    public static NewGuestMessage forGuest(String appId, String token, String appHost, String appPort, Guest guest) {
        NewGuestMessage newGuestMessage = new NewGuestMessage();
        newGuestMessage.sender = appId;
        newGuestMessage.senderName = URLEncoder.encode("访客申请");
        newGuestMessage.token = token;

        Message message = new Message();
        message.recipient = guest.targetUserId;
        message.displayType = "microapp";
        message.msgId = guest.id;

        Content content = new Content();
        content.type = "text";
        content.msg = guest.name + "申请访问您, 请及时处理";
        content.url = "http://" + appHost + ":" + appPort + "/face/visitor-apply-review/?id=" + guest.id;
        content.redirectUrl = "";
        content.fun = "IAM";
        content.title = "您有新访客";

        message.message = content;

        newGuestMessage.message = message;
        return newGuestMessage;
    }
}
