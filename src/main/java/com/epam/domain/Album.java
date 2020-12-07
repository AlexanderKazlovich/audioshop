package com.epam.domain;

import java.util.List;

public class Album {
    private Integer id;
    private String name;
    private List<Track> album;

    public Album(Integer id, String name, List<Track> album) {
        this.id = id;
        this.name = name;
        this.album = album;
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

    public List<Track> getList() {
        return album;
    }

    public void setList(List<Track> album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", album=" + album +
                '}';
    }
}
