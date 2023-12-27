package com.example.skincure;

public class Users
{
    private String Uid;
    private  String Name;
    private String Email;

    private String Password;
    private int usertype;



    public int getUsertype() {
        return usertype;
    }

    public Users(String uid, String name, String email, String password, int usertype) {
        Uid = uid;
        Name = name;
        Email = email;
        Password = password;
        this.usertype = usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }


    public Users()
    {
    }
    public Users(String uid, String email, String password, int usertype) {
        Uid = uid;
        Email = email;
        Password = password;
        usertype = usertype;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


}
