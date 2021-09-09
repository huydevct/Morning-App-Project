package com.example.weatherapp;

public class DocBao {
    private String title;
    private String link;
    private String image;

    public DocBao(String title, String link, String image) {
        this.title = title;
        this.link = link;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
