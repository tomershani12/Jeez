package com.example.jeez;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.jeez.OpenAIHelper;
public class Chat extends AppCompatActivity {
    EditText message;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat); // לוודא שה-XML תואם
        message = findViewById(R.id.editTextMessage);
        responseTextView = findViewById(R.id.textViewResponse);

        // כפתור מעבר ל-ProfileActivity
        findViewById(R.id.buttonProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, Profile.class);
                startActivity(intent);
            }
        });

        // כפתור מעבר ל-SetTargetActivity
        findViewById(R.id.buttonSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAIHelper.callChatCompletionAPIAsync(message.getText().toString(), new ChatCompletionCallback() {
                    @Override
                    public void onSuccess(String content) {
                        // If you're on Android, any UI update must happen on the main thread.
                        // So use runOnUiThread(...) or similar to update your UI.
                        System.out.println(content);
                        runOnUiThread(() -> responseTextView.setText(content));

                    }

                    @Override
                    public void onError(String errorMessage) {
                        runOnUiThread(() -> responseTextView.setText(errorMessage));

                    }
                });
                message.setText(null);
            }
        });
    }
}

