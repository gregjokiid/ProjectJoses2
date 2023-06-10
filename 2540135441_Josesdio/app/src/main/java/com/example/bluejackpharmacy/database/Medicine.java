package com.example.bluejackpharmacy.database;

import java.io.Serializable;

public class Medicine implements Serializable {
    private String name, price, manufacturer, description;
    private String image;

    public Medicine(String name, String price, String manufacturer, String description, String image) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
