package com.example.newsapp.utils;

import android.view.View;

import com.example.newsapp.model.News;

import java.util.List;

/* todo Create by Tejas Dani on 18/Dec/2023
*   interface to click list item according to position
* */
public interface RVListner {
    void onClickedItem(View view, List<News> articlesList);
}
