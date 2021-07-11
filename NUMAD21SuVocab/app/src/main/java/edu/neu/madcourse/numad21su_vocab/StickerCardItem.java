package edu.neu.madcourse.numad21su_vocab;

public class StickerCardItem {
    private String contentId;
    private String userId;

    public StickerCardItem(String contentId, String userId) {
        this.contentId = contentId;
        this.userId = userId;
    }

    public String getContentId() {
        return contentId;
    }

    public String getUserId() {
        return userId;
    }

}
