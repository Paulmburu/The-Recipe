package com.app.sample.recipe.model;

import java.io.Serializable;

public class Category implements Serializable {
// long id;   long id;
    String name;
    String photo;
    String icon;
    String desc;
   int recipes;

    public Category(){}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getRecipes() {
        return recipes;
    }

    public void setRecipes(int recipes) {
        this.recipes = recipes;
    }
}
