package com.example.onlineexamination.SQL;

public class Information {

    int id;
    String name;
    String email;
    String password;
    String value;


    public Information() {
    }

    public Information(int id, String name, String email, String password, String value) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.value = value;
    }

    public Information(String name, String email, String password,String value) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}