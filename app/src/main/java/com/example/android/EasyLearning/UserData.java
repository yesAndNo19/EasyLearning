package com.example.android.EasyLearning;

public class UserData {

    String user_name;
    String pass;
    boolean isDoctor;

    public UserData(String user_name, String pass, boolean isDoctor) {
        this.user_name = user_name;
        this.pass = pass;
        this.isDoctor = isDoctor;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}
