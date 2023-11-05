package com.example.japanese_app_android.model.response;

import java.util.Date;

public class LoginResponseDto {
    private String token;
    private Integer id;
    private String mail;
    private String lastName;
    private String firstName;
    private String phone;
    private Date dob;
    private String avatar;

    public LoginResponseDto(String token, Integer id, String mail, String lastName,
                            String firstName, String phone, Date dob, String avatar) {
        this.token = token;
        this.id = id;
        this.mail = mail;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.dob = dob;
        this.avatar = avatar;
    }

    public LoginResponseDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
