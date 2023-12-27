package com.example.skincure;
public class Post {
    private String postId;
    private String name;
    private String imageUrl;
    private String prevention;
    private String precautions;

    // Default constructor (empty)
    public Post() {
        // Default constructor required for Firebase database operations.
    }

    // Constructor with all fields
    public Post(String postId, String name, String imageUrl, String prevention, String precautions) {
        this.postId = postId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.prevention = prevention;
        this.precautions = precautions;
    }

    public String getPostId() {
        return postId;
    }
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPrevention() {
        return prevention;
    }

    public String getPrecautions() {
        return precautions;
    }
}
