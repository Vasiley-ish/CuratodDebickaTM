package com.example.curatoddebickatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    private DatabaseReference myDataBase;
    private String USER_KEY = "Curator";
    private List<Curator> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
    }

    private void init() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        listData = new ArrayList<>();
        myDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    private void getData(){
        // Read from the database
        myDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (listData.size() > 0)listData.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Curator curator = ds.getValue(Curator.class);
                    assert curator!=null;
                    listData.add(curator);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public void Login(View view){
        if (listData.size() > 0){
            for (int i = 0; i<listData.size();i++){
                if(email.getText().toString().equals(listData.get(i).email) && password.getText().toString().equals(listData.get(i).password)){
                    Intent intent = new Intent(this,UserActivity.class);
                    intent.putExtra("message",listData.get(i).name + ", вы успешно вошли.");
                    startActivity(intent);

                    finish();
                }
            }
        }
    }

    public void toReg(View view){
        Intent intent = new Intent(this,RegActivity.class);
        startActivity(intent);
        finish();
    }


}