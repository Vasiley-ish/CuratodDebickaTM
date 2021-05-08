package com.example.curatoddebickatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {
    EditText emailField,nameField, passwordField;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myDataBase;
    private String USER_KEY = "Curator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
    }
    private void init() {
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        nameField = findViewById(R.id.name);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myDataBase = mFirebaseDatabase.getReference(USER_KEY);
    }

    public void Reg1(View view){
        String id = myDataBase.getKey();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String name = nameField.getText().toString();
        Curator newCurator = new Curator(id,email,password,name);
        Context context = this;
        myDataBase.push().setValue(newCurator,new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error == null) {
                    Intent intent = new Intent(context,UserActivity.class);
                    intent.putExtra("message",name + ", вы успешно зарегистрировались.");
                    startActivity(intent);

                    finish();
                }
            }
        });

    }

}