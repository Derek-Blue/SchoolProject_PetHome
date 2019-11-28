package com.example.pethome.ui.dashboard;

import android.graphics.Bitmap;

public class Pet {
    public int id;
    Bitmap img;
    public String name,date,place,phone,feature,info;

    public Pet(int id, Bitmap img, String name, String date, String place, String phone, String feature, String info) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.date = date;
        this.place = place;
        this.phone = phone;
        this.feature = feature;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
