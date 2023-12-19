package com.example.newsapp.api.response;

import com.example.newsapp.model.News;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<News> articles = null;

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NewsResponse(String status, Integer totalResults, List<News> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> news) {
        this.articles = news;
    }
}
