package edu.neu.madcourse.numad21su_vocab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.send_button:
                Intent send_intent =new Intent(getApplicationContext(),SendActivity.class);
                send_intent.putExtra( "userName", userName );
                startActivity( send_intent );
                break;
            case R.id.show_activity_button:
                Intent display_all_intent =new Intent(getApplicationContext(), DisplayReceivedActivity.class);
                display_all_intent.putExtra( "userName", userName );
                startActivity( display_all_intent );
                break;
        }
    }


}