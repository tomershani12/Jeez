package com.example.jeez;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class OpenAIHelper {
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    /**
     * Makes a call to the OpenAI Completion API.
     *
     * @param prompt The input prompt for the API.
     * @return The API response as a string.
     */
    public static String callChatCompletionAPI(String prompt) {

        System.out.println("callChatCompletionAPI() >> " + prompt);
        StringBuilder response = new StringBuilder();
        String contentToReturn = "";

        try {
            // Set up connection
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);

            // Create JSON payload for chat-based models
            String payload = "{"
                    + "\"model\": \"gpt-4o-mini\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]"
                    + "}";

            System.out.println("callChatCompletionAPI() >> " + payload);

            // Write payload to output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get response
            int responseCode = connection.getResponseCode();
            System.out.println("callChatCompletionAPI() >> responseCode:" + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Build the response from the input stream
                try (Scanner scanner = new Scanner(connection.getInputStream())) {
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                }

                // Now parse JSON to extract only the 'content'
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray choices = jsonObject.getJSONArray("choices");
                    JSONObject firstChoice = choices.getJSONObject(0);
                    JSONObject message = firstChoice.getJSONObject("message");
                    contentToReturn = message.getString("content");
                } catch (Exception parseEx) {
                    // If parsing fails for some reason, fallback to returning raw JSON
                    contentToReturn = response.toString();
                }

            } else {
                // Read error stream if not 200
                System.out.println("callChatCompletionAPI() ERROR >> responseCode:" + responseCode);
                response.append("Error: ").append(responseCode).append(" - ");
                try (Scanner scanner = new Scanner(connection.getErrorStream())) {
                    while (scanner.hasNextLine()) {
                        response.append(scanner.nextLine());
                    }
                }
                contentToReturn = response.toString();
            }

        } catch (Exception e) {
            // Catch any exception and put the message in the response
            System.out.println("callChatCompletionAPI() << Exc: " + e.getMessage());
            contentToReturn = "Error: " + e.getMessage();
        }

        // Return only the 'content' or error fallback
        return contentToReturn;
    }

    public static void callChatCompletionAPIAsync(String prompt, ChatCompletionCallback callback) {
        new Thread(() -> {
            try {
                // Actual network call (synchronous)
                String content = callChatCompletionAPI(prompt);

                // On success, call callback onSuccess
                // (You may or may not need to switch threads again, e.g. if you
                // need to update UI on the main thread, do it in the Activity/Fragment.)
                callback.onSuccess(content);
            } catch (Exception e) {
                // In case callChatCompletionAPI() itself throws an exception
                callback.onError(e.getMessage());
            }
        }).start();
    }

}
