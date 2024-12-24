package com.example.jeez;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SetTargetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target); // התאמת ה-XML לפעילות

        // בדיקת תקינות הכפתור
        View button = findViewById(R.id.buttonBackToChat);
        if (button == null) {
            Log.e("SetTargetActivity", "buttonBackToChat not found!");
            return; // עצירה אם הכפתור לא נמצא
        }

        // הגדרת לחצן חזרה ל-ChatActivity
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SetTargetActivity", "Back to Chat button clicked");
                Intent intent = new Intent(SetTargetActivity.this, Chat.class);
                startActivity(intent);
                //finish(); // סוגר את הפעילות הנוכחית
            }
        });
    }
}
