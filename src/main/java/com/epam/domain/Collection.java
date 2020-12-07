package com.epam.domain;

import java.util.List;

public class Collection {
    private Integer id;
    private String name;
    private List<Track> collection;

    public Collection(Integer id, String name, List<Track> collection) {
        this.id = id;
        this.name = name;
        this.collection = collection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Track> getCollection() {
        return collection;
    }

    public void setCollection(List<Track> collection) {
        this.collection = collection;
    }
}
