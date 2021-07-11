package edu.neu.madcourse.numad21su_vocab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    DatabaseReference db_ref;
    EditText firstname;
    EditText lastname;
    EditText username;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstname = (EditText) findViewById(R.id.fname);
        lastname = (EditText) findViewById(R.id.lname);
        username = (EditText) findViewById(R.id.username);
        register = (Button) findViewById(R.id.signup);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_ref = FirebaseDatabase.getInstance().getReference().child("UserInfo");

                String firstName = firstname.getText().toString();
                String lastName = lastname.getText().toString();
                String userName = username.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please Add All details", Toast.LENGTH_LONG).show();
                } else {
                    UserInfo user = new UserInfo(firstName, lastName, userName);

                    Query query = db_ref.orderByChild("userName").equalTo(userName);

                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if (snapshot.getChildrenCount()>0) {
                                Toast.makeText(SignUpActivity.this, "Username already exists", Toast.LENGTH_LONG).show();
                            }
                            else {
                                db_ref.push().setValue(user);
                                Toast.makeText(SignUpActivity.this, "User added", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }
            }
        });



    }
}