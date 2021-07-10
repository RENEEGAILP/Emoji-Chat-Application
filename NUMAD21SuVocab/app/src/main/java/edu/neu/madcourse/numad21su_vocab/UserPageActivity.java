package edu.neu.madcourse.numad21su_vocab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserPageActivity extends AppCompatActivity {

    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_page );
        Bundle extras = getIntent().getExtras();

        if(extras !=null)
        {
            user_name = extras.getString("user_name");
        }
        TextView welcome_textview = findViewById( R.id.welcome_textview );
        welcome_textview.setText( "Welcome " +user_name +"!!");
        welcome_textview.setVisibility( View.VISIBLE );
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.send_button:
                Intent send_intent =new Intent(getApplicationContext(),SendActivity.class);
                send_intent.putExtra( "user_name",user_name );
                startActivity( send_intent );
                break;
            case R.id.show_activity_button:
                Intent display_all_intent =new Intent(getApplicationContext(),DisplayAllActivity.class);
                display_all_intent.putExtra( "user_name",user_name );
                startActivity( display_all_intent );
                break;
        }
    }


}