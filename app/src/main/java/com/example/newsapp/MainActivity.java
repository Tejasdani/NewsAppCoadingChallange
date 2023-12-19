package com.example.newsapp;

import static com.example.newsapp.utils.Constants.NEWS_DATA;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.databinding.ActivityMainBinding;

import com.example.newsapp.model.News;
import com.example.newsapp.ui.newsdetails.NewsDetailsActivity;
import com.example.newsapp.ui.newslist.adapter.TopHeadlinesAdapter;
import com.example.newsapp.ui.newslist.viewmodel.NewsListViewModel;
import com.example.newsapp.utils.RVListner;

import java.util.ArrayList;
import java.util.List;

/* todo Created by Tejas Dani on 18/Dec/2023
 *  Class which will show news list and widget from layout is set by using viewbinding
 * */

public class MainActivity extends AppCompatActivity {

    List<News> newsList;
    NewsListViewModel newsListViewModel;
    TopHeadlinesAdapter topHeadlinesAdapter;
    ActivityMainBinding activityMainBinding;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        mContext = this;
        initializeData();
        getNews();
    }

    //Set data to RV
    private void initializeData() {
        activityMainBinding.rvNewsList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        activityMainBinding.rvNewsList.hasFixedSize();

        newsList = new ArrayList<>();
        topHeadlinesAdapter = new TopHeadlinesAdapter(newsList, this, rvInterface);
        activityMainBinding.rvNewsList.setAdapter(topHeadlinesAdapter);

        newsListViewModel = new ViewModelProvider(this).get(NewsListViewModel.class);

    }

    //Get data from viewmodel and set to adapter
    private void getNews() {
        newsListViewModel.getNewsData().observe(this, response -> {
            if (response != null && response.getArticles() != null && !response.getArticles().isEmpty()) {
                activityMainBinding.rvNewsList.setVisibility(View.VISIBLE);
                activityMainBinding.tvNoEvents.setVisibility(View.GONE);
                List<News> newsArticleList = response.getArticles();
                newsList.addAll(newsArticleList);
                topHeadlinesAdapter.notifyDataSetChanged();
            } else {
                activityMainBinding.rvNewsList.setVisibility(View.GONE);
                activityMainBinding.tvNoEvents.setVisibility(View.VISIBLE);
            }
        });
    }

    //Send data to NewsDetailsScreen using bundle
    RVListner rvInterface = new RVListner() {
        @Override
        public void onClickedItem(View v, List<News> articlesList) {
            int index = activityMainBinding.rvNewsList.getChildAdapterPosition(v);
            if (index >= 0) {
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra(NEWS_DATA, newsList.get(activityMainBinding.rvNewsList.getChildAdapterPosition(v)));
                startActivity(intent);
            }
        }
    };

}
