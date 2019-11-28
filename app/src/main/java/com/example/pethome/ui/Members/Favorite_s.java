package com.example.pethome.ui.Members;

public class Favorite_s {
    private int id;
    private int pic;

    private String category;
    private String gender;
    private String origin;


    public Favorite_s(int id, int pic, String category, String gender, String origin){
        this.pic = pic;
        this.category = category;
        this.gender = gender;
        this.origin = origin;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
