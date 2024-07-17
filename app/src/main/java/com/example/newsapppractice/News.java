package com.example.newsapppractice;

public class News {
    String title,author,url,imageUrl;

    //Alt+Ins to create constructor

    public News(String title, String author, String url, String imageUrl) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    //Alt+Ins to create getter and setter methods

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
