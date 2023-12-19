package com.example.newsapp.ui.newslist.viewmodel;

import static com.example.newsapp.utils.Constants.CATEGORY;
import static com.example.newsapp.utils.Constants.COUNTRY;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.api.repository.NewsRepository;
import com.example.newsapp.api.response.NewsResponse;
import com.example.newsapp.utils.Constants;

/* todo Created by Tejas Dani on 18/Dec/2023
 *   VM to get data from and perform NewsRepository and perform logical operation to set response to UI
 * */

public class NewsListViewModel extends AndroidViewModel {

    private NewsRepository newsRepository;
    private LiveData<NewsResponse> newsResponseLiveData;

    public NewsListViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository();
        this.newsResponseLiveData = newsRepository.getNewsList(COUNTRY,CATEGORY,100, Constants.API_KEY);

    }

    public LiveData<NewsResponse> getNewsData() {
        return newsResponseLiveData;
    }
}
