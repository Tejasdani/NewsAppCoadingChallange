package com.example.newsapp.ui.newsdetails;

import static com.example.newsapp.utils.Constants.NEWS_DATA;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ActivityDescriptionBinding;
import com.example.newsapp.model.News;

import timber.log.Timber;

public class NewsDetailsActivity extends AppCompatActivity {
private ActivityDescriptionBinding descriptionBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        descriptionBinding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        View view = descriptionBinding.getRoot();
        setContentView(view);
        News newDetails = getIntent().getParcelableExtra(NEWS_DATA);
        Timber.d("DATA: " + newDetails);

        if (newDetails != null) {
            setDescData(newDetails);
        }

    }

    private void setDescData(News newDetails) {
        descriptionBinding.tvNewsTitle.setText(newDetails.getTitle());
        descriptionBinding.tvNewsContent.setText(newDetails.getContent());
        descriptionBinding.tvNewsURL.setText(newDetails.getUrl());
        Glide.with(this)
                .load(newDetails.getUrlToImage())
                .override(512,512)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.default_image)
                .into(descriptionBinding.newsIV);
    }
}
