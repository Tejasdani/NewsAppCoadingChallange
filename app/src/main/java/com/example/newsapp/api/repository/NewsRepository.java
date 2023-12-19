package com.example.newsapp.api.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.api.response.NewsResponse;
import com.example.newsapp.api.service.NetworkModule;
import com.example.newsapp.api.service.NewsService;
import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* todo Created by Tejas Dani on 18/Dec/2023
 *   news repository to collect data from APIs and set value using Livedata
 * */
public class NewsRepository {
    private final NewsService newsService;

    public NewsRepository() {
        newsService = NetworkModule.getRetrofit().create(NewsService.class);
    }
    public NewsRepository(NewsService newsService){
        this.newsService = newsService;
    }

    public LiveData<NewsResponse> getNewsList(String country, String category, int size, String apiKey) {

        final MutableLiveData<NewsResponse> data = new MutableLiveData<>();
        newsService.getNews(country, category, size, apiKey).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.body() != null) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                data.setValue(null);

            }
        });
        return data;
    }

}
