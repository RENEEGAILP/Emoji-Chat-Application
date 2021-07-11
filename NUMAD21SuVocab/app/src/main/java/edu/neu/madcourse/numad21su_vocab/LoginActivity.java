package edu.neu.madcourse.numad21su_vocab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.auth.User;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    EditText userNameEdit;
    DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.log_in);
        userNameEdit = (EditText) findViewById(R.id.uname);

        dataRef= FirebaseDatabase.getInstance().getReference().child("UserInfo");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(userNameEdit.getText().toString());
            }
        });

    }

    private void signIn(String userName) {
        if (userName.isEmpty()) {
            Toast.makeText(LoginActivity.this,"Please provide username!", Toast.LENGTH_SHORT).show();
        } else {
            Query query = dataRef.orderByChild("userName").equalTo(userName);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() > 0) {
                        Toast.makeText(LoginActivity.this, "Login Success ", Toast.LENGTH_LONG).show();
//                        for (DataSnapshot user_snapshot : snapshot.getChildren()) {
//                            UserInfo user = (UserInfo) user_snapshot.getValue();
//                            Toast.makeText(LoginActivity.this, "Login Success " + user_snapshot.child("firstName").getValue(), Toast.LENGTH_LONG).show();
//
//                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
        }
    }
}