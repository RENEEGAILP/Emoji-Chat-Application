package edu.neu.madcourse.numad21su_vocab;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText userNameEdit;
    DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        userNameEdit=findViewById(R.id.usernameText);
        loginButton=findViewById(R.id.loginButton);
        dataRef= FirebaseDatabase.getInstance().getReference("users");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(userNameEdit.getText().toString());
            }
        });


    }

    private void signIn(String userName) {
        if (userName.isEmpty()) {
            Toast.makeText(MainActivity.this,"Please provide username!", Toast.LENGTH_SHORT).show();
        } else {
            dataRef.orderByChild("name").equalTo(userName).get()
                    .addOnCompleteListener((OnCompleteListener<DataSnapshot>) task -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"Connection Issue", Toast.LENGTH_SHORT).show();
                        } else {
                            if(task.getResult().getValue() == null) {
                                Toast.makeText(MainActivity.this,"Invalid user! Please register!", Toast.LENGTH_SHORT).show();
                            } else {
                                //Intent UserLandingPage = new Intent(this, UserLandingPageActivity.class);
                                //UserLandingPage.putExtra("sender_name", userName);
                                //startActivity(UserLandingPage);
                                Toast.makeText(MainActivity.this,"Login success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
   }

    public void registerUser(View view) {
        //Intent intent = new Intent(this, SignUpActivity.class);
        //startActivity(intent);
    }


}