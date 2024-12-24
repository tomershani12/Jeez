package com.example.jeez;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // הגדרת כפתור חזרה לעמוד Chat
        findViewById(R.id.buttonBackToChat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Chat.class);
                startActivity(intent);
                //finish(); // סוגר את הפעילות הנוכחית
            }
        });
    }
}
