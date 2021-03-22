package com.android.alan;

public class DetailsEntity {

    //
    private String title;
    private String image;
    private String details;

    public DetailsEntity() {
    }

    public DetailsEntity(String title, String image, String details) {
        this.title = title;
        this.image = image;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
