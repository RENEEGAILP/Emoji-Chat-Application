package edu.neu.madcourse.numad21su_vocab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class StickerAdapter extends RecyclerView.Adapter<StickerHolder> {

    private ArrayList<StickerCardItem> stickerList;

    private Context context;

    public StickerAdapter(ArrayList<StickerCardItem> stickerCardItemList, Context context) {
        this.stickerList = stickerCardItemList;
        this.context = context;
    }

    @Override
    public StickerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.sticker_card, parent, false);

        return new StickerHolder(contactView);
    }

    @Override
    public void onBindViewHolder(StickerHolder holder, int position) {
        StickerCardItem stickerCardItem = stickerList.get(position);

        holder.setContent(stickerCardItem.getContentId());
        holder.setSenderTextView(stickerCardItem.getUserId());
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }
}
