package com.example.bloodhub;

public class Users {
    private String etName;
    private String etGender;
    private String etBlood;
    private String etPhone;

    public Users() {
    }

    public Users(String etName, String etGender, String etBlood, String etPhone) {
        this.etName = etName;
        this.etGender = etGender;
        this.etBlood = etBlood;
        this.etPhone = etPhone;
    }

    public String getEtName() {
        return etName;
    }

    public void setEtName(String etName) {
        this.etName = etName;
    }

    public String getEtGender() {
        return etGender;
    }

    public void setEtGender(String etGender) {
        this.etGender = etGender;
    }

    public String getEtBlood() {
        return etBlood;
    }

    public void setEtBlood(String etBlood) {
        this.etBlood = etBlood;
    }

    public String getEtPhone() {
        return etPhone;
    }

    public void setEtPhone(String etPhone) {
        this.etPhone = etPhone;
    }
}



