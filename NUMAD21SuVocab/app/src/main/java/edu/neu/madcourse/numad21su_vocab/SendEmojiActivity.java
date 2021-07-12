package edu.neu.madcourse.numad21su_vocab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SendEmojiActivity extends AppCompatActivity {

    String userName;
    String selected_sticker_id;
    DatabaseReference db_ref;
    ArrayList<String> userList = new ArrayList<>();
    String selectedUser;
    ImageView sticker1;
    ImageView sticker2;
    ImageView sticker3;
    ImageView sticker4;
    ImageView sticker5;
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

        initializeSticker();
        setStickerClickListeners();

        userList.add( "Select username" );
        populateUsersDropDown();

        Spinner userListSpinner = findViewById(R.id.user_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, userList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userListSpinner.setAdapter(dataAdapter);

        userListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedUser = parentView.getItemAtPosition( position ).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    private void initializeSticker()
    {
        sticker1 = findViewById( R.id.sticker_1 );
        sticker2 = findViewById( R.id.sticker_2 );
        sticker3 = findViewById( R.id.sticker_3 );
        sticker4 = findViewById( R.id.sticker_4 );
        sticker5 = findViewById( R.id.sticker_5 );
    }
    private void setStickerClickListeners()
    {
        findViewById( R.id.sticker_1 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_2 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_3 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_4 ).setOnClickListener( this::onStickerClick );
        findViewById( R.id.sticker_5 ).setOnClickListener( this::onStickerClick );

    }
    private void populateUsersDropDown()
    {
        DatabaseReference user_db_ref = FirebaseDatabase.getInstance().getReference("UserInfo");

        user_db_ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    UserInfo userInfo = s.getValue(UserInfo.class);
                    if (!userInfo.getUserName().equals(userName)) {
                        userList.add(userInfo.getUserName());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        } );

    }

    public void onSendButtonClick(View view) {

        if(selectedUser.equals( "Select username" ))
        {
            Toast.makeText(
                    getApplicationContext(),"Please select a user.",Toast.LENGTH_LONG ).show();
            return;
        }

        if(selected_sticker_id == null)
        {
            Toast.makeText(
                    getApplicationContext(),"Please select a sticker.",Toast.LENGTH_LONG ).show();
            return;
        }

        ChatInfo chatInfo = new ChatInfo( userName,selectedUser,selected_sticker_id );

        DatabaseReference userinfo_db_ref = FirebaseDatabase.getInstance().getReference("UserInfo");
        Query query = userinfo_db_ref.orderByChild("userName").equalTo(selectedUser);

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
        sticker1.setAlpha( 1f );
        sticker2.setAlpha( 1f );
        sticker3.setAlpha( 1f );
        sticker4.setAlpha( 1f );
        sticker5.setAlpha( 1f );
        selected_sticker_id = null;
    }

    public void onStickerClick(View view)
    {
        switch(view.getId())
        {
            case R.id.sticker_1:
                selected_sticker_id = "1";
                sticker1.setAlpha( 0.5f );
                sticker2.setAlpha( 1f );
                sticker3.setAlpha( 1f );
                sticker4.setAlpha( 1f );
                sticker5.setAlpha( 1f );
                break;
            case R.id.sticker_2:
                selected_sticker_id = "2";
                sticker2.setAlpha( 0.5f );
                sticker1.setAlpha( 1f );
                sticker3.setAlpha( 1f );
                sticker4.setAlpha( 1f );
                sticker5.setAlpha( 1f );
                break;
            case R.id.sticker_3:
                selected_sticker_id = "3";
                sticker3.setAlpha( 0.5f );
                sticker2.setAlpha( 1f );
                sticker1.setAlpha( 1f );
                sticker4.setAlpha( 1f );
                sticker5.setAlpha( 1f );
                break;
            case R.id.sticker_4:
                selected_sticker_id = "4";
                sticker4.setAlpha( 0.5f );
                sticker2.setAlpha( 1f );
                sticker3.setAlpha( 1f );
                sticker1.setAlpha( 1f );
                sticker5.setAlpha( 1f );
                break;
            case R.id.sticker_5:
                selected_sticker_id = "5";
                sticker5.setAlpha( 0.5f );
                sticker2.setAlpha( 1f );
                sticker3.setAlpha( 1f );
                sticker4.setAlpha( 1f );
                sticker1.setAlpha( 1f );
                break;
        }

    }
}
