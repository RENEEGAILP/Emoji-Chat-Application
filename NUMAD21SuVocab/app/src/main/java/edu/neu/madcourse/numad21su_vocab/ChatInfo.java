package edu.neu.madcourse.numad21su_vocab;

public class ChatInfo {
    private  final String sender_name;
    private final String receiver_name;
    private final String content_id;

    public ChatInfo(String sender_name, String receiver_name, String content_id) {
        this.sender_name = sender_name;
        this.receiver_name = receiver_name;
        this.content_id = content_id;
    }

    public String getSenderName() {
        return sender_name;
    }

    public String getReceiverName() {
        return receiver_name;
    }

    public String getContentId() {
        return content_id;
    }
}
