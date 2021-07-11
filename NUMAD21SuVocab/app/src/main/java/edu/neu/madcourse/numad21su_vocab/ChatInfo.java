package edu.neu.madcourse.numad21su_vocab;

public class ChatInfo {
    String senderName;
    String receiverName;
    String contentId;

    public ChatInfo()
    {    }

    public ChatInfo(String sender_name, String receiver_name, String content_id) {
        this.senderName = sender_name;
        this.receiverName = receiver_name;
        this.contentId = content_id;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getContentId() {
        return contentId;
    }
}
