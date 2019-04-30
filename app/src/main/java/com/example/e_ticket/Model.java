package com.example.e_ticket;

public class Model {

    private String title, image, description; //the names in firebase database

    //construotor
    public Model(){}

    //getter and setters press Alt+Insert

    String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title + ", " + description;
    }
}
