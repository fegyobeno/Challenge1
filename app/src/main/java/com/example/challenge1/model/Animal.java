package com.example.challenge1.model;

public class Animal {

    private Integer id;
    private String name;
    private Integer age;
    private String owner;

    private final String image;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public Animal(Integer id, String name, Integer age, String owner, String image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getOwner() {
        return owner;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage(){return image;}
}
