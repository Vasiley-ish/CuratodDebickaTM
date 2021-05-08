package com.example.curatoddebickatm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        text = findViewById(R.id.text);
        Intent intent = getIntent();
        text.setText(intent.getStringExtra("message"));
    }
}
