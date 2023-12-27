package com.example.skincure;

public class Posts {
    private String name;
    private String image;
    private String prevention;
    // Add other fields and constructors as needed

    public Posts() {
        // Default constructor required for Firebase
    }

    public Posts(String name, String image, String prevention) {
        this.name = name;
        this.image = image;
        this.prevention = prevention;
        // Initialize other fields as needed
    }

    // Define getter and setter methods for your fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrevention() {
        return prevention;
    }

    public void setPrevention(String prevention) {
        this.prevention = prevention;
    }
}
