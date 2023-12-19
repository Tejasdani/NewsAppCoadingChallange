package com.example.newsapp.api.service;


import com.example.newsapp.api.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    String DEFAULT_PAGE_SIZE = "100"; // Assuming DEFAULT_PAGE_SIZE is a constant

    @GET("v2/top-headlines")
    Call<NewsResponse> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("size") int size,
            @Query("apiKey") String apiKey
    );
}
