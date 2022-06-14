package ru.neji69.example.api.pojo.pet;

import java.util.List;

public class Pet {

    public Pet() {
    }

    public Pet(Integer id, Category category, String name, List<String> photoUrls, List<Tag> tags, Status status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;

        Pet pet = (Pet) o;

        if (getId() != null ? !getId().equals(pet.getId()) : pet.getId() != null) return false;
        if (getCategory() != null ? !getCategory().equals(pet.getCategory()) : pet.getCategory() != null) return false;
        if (getName() != null ? !getName().equals(pet.getName()) : pet.getName() != null) return false;
        if (getPhotoUrls() != null ? !getPhotoUrls().equals(pet.getPhotoUrls()) : pet.getPhotoUrls() != null)
            return false;
        if (getTags() != null ? !getTags().equals(pet.getTags()) : pet.getTags() != null) return false;
        return getStatus() == pet.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }


    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}


