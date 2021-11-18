package com.chen.fy.wisdomscenicspot.activities;


public class Item {

    public boolean isChecked;

    private String name;


    public Item( String name) {

        this.name   = name;

    }



    public void setName(String name) {
        this.name   = name;
    }

    public String getName() {
        return name;
    }



    public String toString() {
        return "Item[" +  name + "]";
    }

}
