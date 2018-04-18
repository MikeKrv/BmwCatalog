package com.example.mike.bmwcatalog.model;

/**
 * Created by Mike on 07.09.2017.
 */

public class BmwModel {
    private String title;
    private String description;
    private String image;

    public BmwModel() {
    }

    public BmwModel(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.image = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
