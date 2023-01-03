package com.petstore.model;

import java.util.List;
import java.util.Map;

public class PetPojo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, ?> getCategory() {
        return category;
    }

    public void setCategory(Map<String, ?> category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getTags() {
        return tags;
    }

    public void setTags(List<?> tags) {
        this.tags = tags;
    }

    public List<?> getPhotourl() {
        return photourl;
    }

    public void setPhotourl(List<?> photourl) {
        this.photourl = photourl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int id;
    private Map<String,?> category ;
    private String name ;
    private List<?>tags;
    private List<?>photourl;
    private String status;
}