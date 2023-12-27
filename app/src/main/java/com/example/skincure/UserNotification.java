package com.example.skincure;

public class UserNotification {
    private String userEmailAddress;
    private String message;

    public UserNotification() {
        // Default constructor required for Firebase
    }

    public UserNotification(String userEmailAddress, String message) {
        this.userEmailAddress = userEmailAddress;
        this.message = message;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
