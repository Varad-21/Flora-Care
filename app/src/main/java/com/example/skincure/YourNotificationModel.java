package com.example.skincure;

public class YourNotificationModel {

    private String message;

    // Default constructor required for Firebase
    public YourNotificationModel() {
    }

    public YourNotificationModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
