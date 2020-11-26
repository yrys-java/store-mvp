package com.example.inventoryapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "inventories")
public class Inventory {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String price;
    private String quantity;
    private String manufacturer;

    private String image;

    public Inventory(String title, String price, String quantity, String manufacturer, String image) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
        this.image = image;
    }
    @Ignore
    public Inventory(String title, String price, String quantity, String manufacturer) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.manufacturer = manufacturer;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getImage() {
        return image;
    }
}
