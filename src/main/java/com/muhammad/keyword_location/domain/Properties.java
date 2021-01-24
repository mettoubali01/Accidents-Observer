package com.muhammad.keyword_location.domain;

public class Properties {
    private String title;
    private String description;

    public Properties(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "type: '" + title + '\'' +
                ", description: '" + description + '\'' +
                '}';
    }
}
