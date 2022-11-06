package com.hebaelsaid.android.newsapp.domain.model.response;

import java.util.List;

public class LatestNewsResponseModel {

    private String status;
    private List<SourcesBean> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourcesBean> getSources() {
        return sources;
    }

    public void setSources(List<SourcesBean> sources) {
        this.sources = sources;
    }

    public static class SourcesBean {

        private String id;
        private String name;
        private String description;
        private String url;
        private String category;
        private String language;
        private String country;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
