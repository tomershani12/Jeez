package com.example.jeez;

public interface ChatCompletionCallback {
    void onSuccess(String content);
    void onError(String errorMessage);
}