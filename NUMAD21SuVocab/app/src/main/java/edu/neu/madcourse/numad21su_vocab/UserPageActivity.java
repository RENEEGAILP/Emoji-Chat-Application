package edu.neu.madcourse.numad21su_vocab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.send_button:

                break;
            case R.id.show_activity_button:
                break;
        }
    }


}