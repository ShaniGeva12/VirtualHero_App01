package com.geva.shani.virtualhero;

public class Character {
    private String name;
    private String phone;
    private String mail;
    private String sex;
    private String website;
    private boolean isHero;
    private boolean isVillain;

    public Character(String name,String sex,String phone, String mail, String website,
                     boolean isHero,  boolean isVillain)
    {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.mail = mail;
        this.website = website;
        this.isHero = isHero;
        this.isVillain = isVillain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isHero() {
        return isHero;
    }

    public void setHero(boolean hero) {
        isHero = hero;
    }

    public boolean isVillain() {
        return isVillain;
    }

    public void setVillain(boolean villain) {
        isVillain = villain;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
