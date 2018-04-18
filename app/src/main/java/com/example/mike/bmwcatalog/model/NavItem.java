package com.example.mike.bmwcatalog.model;

/**
 * Created by Mike on 26.07.2017.
 */

public class NavItem {
    private String modelName;
    private boolean isClicked;

    public NavItem(String modelName, boolean isClicked) {
        this.modelName = modelName;
        this.isClicked = isClicked;
    }

    public String getModelName() {
        return modelName;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
