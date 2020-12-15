package com.epam.domain.audio;

import com.epam.domain.Entity;

public class Track extends Entity {
    private Integer id;
    private String name;
    private String genre;
    private Integer price;
    private String fullPath;
    private String demoPath;

    public Track(Integer id, String name, String genre, Integer price, String fullPath, String demoPath) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.fullPath = fullPath;
        this.demoPath = demoPath;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getDemoPath() {
        return demoPath;
    }

    public void setDemoPath(String demoPath) {
        this.demoPath = demoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (!id.equals(track.id)) return false;
        if (!name.equals(track.name)) return false;
        if (!genre.equals(track.genre)) return false;
        if (!price.equals(track.price)) return false;
        if (!fullPath.equals(track.fullPath)) return false;
        return demoPath.equals(track.demoPath);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + fullPath.hashCode();
        result = 31 * result + demoPath.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", fullPath='" + fullPath + '\'' +
                ", demoPath='" + demoPath + '\'' +
                '}';
    }
}
