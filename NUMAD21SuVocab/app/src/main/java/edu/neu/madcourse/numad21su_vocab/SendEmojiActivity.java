package edu.neu.madcourse.numad21su_vocab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SendEmojiActivity extends AppCompatActivity {

    String userName;
    String selected_sticker_id;
    DatabaseReference db_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_send );

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            userName = extras.getString( "userName" );
        }
        db_ref = FirebaseDatabase.getInstance().getReference().child("ChatInfo");

        findViewById( R.id.sticker_1 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_2 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_3 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_4 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_5 ).setOnClickListener( this::onStickerClick );

    }
    public void onSendButtonClick(View view) {
        EditText receiver_name_edittext = findViewById( R.id.editTextTextPersonName );
        String receiver_name = receiver_name_edittext.getText().toString();


        ChatInfo chatInfo = new ChatInfo( userName,receiver_name,selected_sticker_id );

        DatabaseReference userinfo_db_ref = FirebaseDatabase.getInstance().getReference("UserInfo");
        Query query = userinfo_db_ref.orderByChild("userName").equalTo(receiver_name);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount()<=0) {
                    Toast.makeText(getApplicationContext(), "This receiver does not exist. \n Please add valid username", Toast.LENGTH_LONG).show();
                }
                else {
                    db_ref.push().setValue(chatInfo);
                    Toast.makeText( getApplicationContext(),"Message sent successfully!", Toast.LENGTH_LONG ).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        NotificationCompat.Builder builder = new NotificationCompat.Builder(SendEmojiActivity.this)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Notification").setContentText("You got a new message")
                .setAutoCancel(true);

    }

    public void onStickerClick(View view)
    {
        switch(view.getId())
        {
            case R.id.sticker_1:
                selected_sticker_id = "1";
                break;
            case R.id.sticker_2:
                selected_sticker_id = "2";
                break;
            case R.id.sticker_3:
                selected_sticker_id = "3";
                break;
            case R.id.sticker_4:
                selected_sticker_id = "4";
                break;
            case R.id.sticker_5:
                selected_sticker_id = "5";
                break;
        }

    }
}