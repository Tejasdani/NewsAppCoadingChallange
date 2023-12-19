package com.example.newsapp.ui.newslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.newsapp.R;
import com.example.newsapp.databinding.ItemLatestNewsBinding;
import com.example.newsapp.model.News;
import com.example.newsapp.utils.RVListner;

import java.util.List;

public class TopHeadlinesAdapter extends RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder> {

    List<News> newsList;
    Context mContext;
    RVListner rvListner;

    public TopHeadlinesAdapter(List<News> newsList, Context mContext, RVListner mRvListner) {
        this.newsList = newsList;
        this.mContext = mContext;
        this.rvListner = mRvListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLatestNewsBinding binding = ItemLatestNewsBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(newsList.get(position).getUrlToImage())
                .override(512,512)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.default_image)
                .into(holder.latestNewsBinding.ivNews);
        holder.latestNewsBinding.tvTitle.setText(newsList.get(position).getTitle());
        holder.latestNewsBinding.tvDescription.setText(newsList.get(position).getDescription());
        holder.latestNewsBinding.publishDate.setText(newsList.get(position).getPublishedAt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvListner.onClickedItem(holder.itemView, newsList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemLatestNewsBinding latestNewsBinding;

        public ViewHolder(ItemLatestNewsBinding binding) {
            super(binding.getRoot());
            this.latestNewsBinding = binding;
        }

    }
}
