package com.example.frontToBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


//@Entity
//@Table(name = "items")

public class Item {


    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "item_id")
    private int itemId;

    //@Column(name = "item_name")
    //@NotBlank
    private String itemName;


    //@Entity
//@Table(name = "items")


    public Item() {
    }

    public Item(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
