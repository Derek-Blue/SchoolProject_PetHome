package com.example.pethome.ui.Members;

import android.graphics.Bitmap;

public class found {
    public int id;
    Bitmap img;
    String date,place,feature;

    public found(int id, Bitmap img, String date, String place, String feature) {
        this.id = id;
        this.img = img;
        this.date = date;
        this.place = place;
        this.feature = feature;
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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
