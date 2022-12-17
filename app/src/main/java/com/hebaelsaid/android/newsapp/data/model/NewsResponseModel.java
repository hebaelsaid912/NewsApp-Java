package com.hebaelsaid.android.newsapp.data.model;

import com.hebaelsaid.android.newsapp.domain.ui_model.NewsDetailsUiModel;

import java.util.ArrayList;
import java.util.List;

public class NewsResponseModel {

    private String status;
    private int totalResults;
    private List<ArticlesBean> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }
    public ArrayList<NewsDetailsUiModel> getLatestNewsUiModels(ArrayList<NewsDetailsUiModel> latestNewsUiModels) {
        for (int i = 0; i < getArticles().size(); i++) {
            NewsDetailsUiModel latestNewsUiModel = new NewsDetailsUiModel();
            latestNewsUiModel.setName(getArticles().get(i).getTitle());
            latestNewsUiModel.setPublishedAt(getArticles().get(i).getPublishedAt());
            latestNewsUiModel.setUrl(getArticles().get(i).getUrlToImage());
            latestNewsUiModel.setDescription(getArticles().get(i).getDescription());
            latestNewsUiModels.add(latestNewsUiModel);
        }
        return latestNewsUiModels;
    }
    public ArrayList<NewsDetailsUiModel> getSearchNewsUiModels() {
        ArrayList<NewsDetailsUiModel> latestNewsUiModels = new ArrayList<>();
        for (int i = 0; i < getArticles().size(); i++) {
            NewsDetailsUiModel latestNewsUiModel = new NewsDetailsUiModel();
            latestNewsUiModel.setName(getArticles().get(i).getTitle());
            latestNewsUiModel.setPublishedAt(getArticles().get(i).getPublishedAt());
            latestNewsUiModel.setUrl(getArticles().get(i).getUrlToImage());
            latestNewsUiModel.setDescription(getArticles().get(i).getDescription());
            latestNewsUiModels.add(latestNewsUiModel);
        }

        return latestNewsUiModels;
    }
    public static class ArticlesBean {

        private SourceBean source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class SourceBean {


            private String id;
            private String name;

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
        }
    }
}
