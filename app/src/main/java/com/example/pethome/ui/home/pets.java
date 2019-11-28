package com.example.pethome.ui.home;

public class pets {
    public int pic;
    public String variety;
    public String category;
    public String gender;
    public String origin;
    public String color;
    public String open;
    public String mechanism;
    public String phone;
    public String address;
    public String description;

    public pets(int pic, String variety, String category, String gender, String origin, String color, String open, String mechanism, String phone, String address, String description) {
        this.pic = pic;
        this.variety = variety;
        this.category = category;
        this.gender = gender;
        this.origin = origin;
        this.color = color;
        this.open = open;
        this.mechanism = mechanism;
        this.phone = phone;
        this.address = address;
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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

    public int getPic() {
        return pic;
    }


    public void setPic(int pic) {
        this.pic = pic;
    }


}
