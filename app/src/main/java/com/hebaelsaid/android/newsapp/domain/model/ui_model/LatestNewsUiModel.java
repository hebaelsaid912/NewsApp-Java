package com.hebaelsaid.android.newsapp.domain.model.ui_model;

public class LatestNewsUiModel {
    private String name;
    private String url;
    private String country;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
