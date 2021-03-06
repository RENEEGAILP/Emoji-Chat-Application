package edu.neu.madcourse.numad21su_vocab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayMessagesSentActivity extends AppCompatActivity {

    ArrayList<StickerCardItem> sentStickerList;
    DatabaseReference db_ref;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_messages_sent);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getString("userName");
        }

        sentStickerList = new ArrayList<>();

        final RecyclerView stickersRV = findViewById(R.id.sent_sticker_recycler_view);
        final StickerAdapter stickersAdapter = new StickerAdapter(sentStickerList, "DisplayMessagesSentActivity");

        stickersRV.setAdapter(stickersAdapter);
        stickersRV.setLayoutManager(new LinearLayoutManager(this));

        db_ref = FirebaseDatabase.getInstance().getReference("ChatInfo");
        Query query = db_ref.orderByChild( "senderName" ).equalTo( userId );

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sentStickerList.clear();
                for(DataSnapshot s : snapshot.getChildren())
                {
                    ChatInfo chatInfo = s.getValue(ChatInfo.class);
                    StickerCardItem sticker = new StickerCardItem( chatInfo.getContentId(),chatInfo.getReceiverName() );
                    sentStickerList.add( sticker );
                }
                stickersAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}