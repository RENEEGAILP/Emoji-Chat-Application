package edu.neu.madcourse.numad21su_vocab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserPageActivity extends AppCompatActivity {

    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_page );
        Bundle extras = getIntent().getExtras();

        if(extras !=null)
        {
            userName = extras.getString("userName");
        }
        TextView welcome_textview = findViewById( R.id.welcome_textview );
        welcome_textview.setText( "Welcome " + userName +"!!");
        welcome_textview.setVisibility( View.VISIBLE );

        displaySentMessageCount();
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.send_button:
                Intent send_intent =new Intent(getApplicationContext(), SendEmojiActivity.class);
                send_intent.putExtra( "userName", userName );
                startActivity( send_intent );
                break;
            case R.id.show_received_activity_button:
                Intent display_all_intent =new Intent(getApplicationContext(), DisplayReceivedActivity.class);
                display_all_intent.putExtra( "userName", userName );
                startActivity( display_all_intent );
                break;
            case R.id.show_sent_activity_button:
                Intent display_all_sent_intent =new Intent(getApplicationContext(), DisplayMessagesSentActivity.class);
                display_all_sent_intent.putExtra( "userName", userName );
                startActivity( display_all_sent_intent );
                break;
        }
    }

    public void displaySentMessageCount()
    {
        DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference("ChatInfo");
        Query query = db_ref.orderByChild( "senderName" ).equalTo( userName );
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TextView sentMessageCount = findViewById( R.id.sent_message_count_textview );
                sentMessageCount.setText( "You have sent a total of "+snapshot.getChildrenCount() +" mesages" );
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}