package com.example.newsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MockResponseFileReader {

    private final String content;

    public MockResponseFileReader(String path) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + path);
        }

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        }

        content = stringBuilder.toString();
    }

    public String getContent() {
        return content;
    }
}
