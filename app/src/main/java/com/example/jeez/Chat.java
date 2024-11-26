package com.example.jeez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Chat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat); // לוודא שה-XML תואם

        // כפתור מעבר ל-ProfileActivity
        findViewById(R.id.buttonProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, Profile.class);
                startActivity(intent);
            }
        });

        // כפתור מעבר ל-SetTargetActivity
        findViewById(R.id.buttonSetTarget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, SetTarget.class);
                startActivity(intent);
            }
        });
    }
}
