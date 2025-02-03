package com.example.jeez;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.bumptech.glide.Glide;

public class Profile extends AppCompatActivity {

    FirebaseAuth auth;
    ShapeableImageView imageView;
    TextView name, mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_profile);

        imageView = findViewById(R.id.profileImage);
        name = findViewById(R.id.nameTV);
        mail = findViewById(R.id.mailTv);

        Glide.with(Profile.this).load(auth.getCurrentUser().getPhotoUrl()).into(imageView);
        name.setText(auth.getCurrentUser().getDisplayName());
        mail.setText(auth.getCurrentUser().getEmail());
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
