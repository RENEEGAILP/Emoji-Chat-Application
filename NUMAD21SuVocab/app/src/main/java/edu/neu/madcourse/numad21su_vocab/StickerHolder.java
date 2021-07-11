package edu.neu.madcourse.numad21su_vocab;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class StickerHolder extends RecyclerView.ViewHolder {

    private ImageView stickerImageView;
    private TextView senderTextView;

    public StickerHolder(final View view) {
        super(view);

        stickerImageView = view.findViewById(R.id.sticker_imageview );
        senderTextView = view.findViewById(R.id.sender_name_text_view);
    }

    public void setContent(String resource) {
        try {
            stickerImageView.setImageResource( getContentResourceID( resource ) );
        } catch (Exception e) {
            // _resource was not a valid file/ID
        }
    }

    public void setSenderTextView(String senderText) {
        senderTextView.setText("From: " + senderText);
    }

    private int getContentResourceID(String contentId)
    {
        switch(contentId)
        {
            case "1":
                return R.drawable.nutella;
            case "2":
                return R.drawable.turtle;
            case "3":
                return R.drawable.watermelon;
            case "4":
                return R.drawable.whale;
            case "5":
                return R.drawable.cake;
            default:
                return 0;
        }
    }
}
