package com.hebaelsaid.android.newsapp.ui.feature.home.latest_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hebaelsaid.android.newsapp.databinding.LatestNewListItemBinding;
import com.hebaelsaid.android.newsapp.domain.ui_model.NewsDetailsUiModel;

public class GetLatestNewsHolder extends RecyclerView.ViewHolder {

    private final LatestNewListItemBinding latestNewListItemBinding;
    public static GetLatestNewsHolder create(ViewGroup parent){
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        LatestNewListItemBinding latestNewListItemBinding = LatestNewListItemBinding.inflate(layoutInflater, parent ,false);
        return new GetLatestNewsHolder(latestNewListItemBinding);
    }

    public GetLatestNewsHolder(@NonNull LatestNewListItemBinding latestNewListItemBinding) {
        super(latestNewListItemBinding.getRoot());
        this.latestNewListItemBinding = latestNewListItemBinding;

    }

    void bind(NewsDetailsUiModel latestNewsUiModel, OnItemClickListener onItemClickListener) {
        latestNewListItemBinding.setModel(latestNewsUiModel);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view, latestNewsUiModel);
            }
        });
    }
    public void recycle() {
        itemView.setOnClickListener(null);
    }
    public interface OnItemClickListener {
        void onItemClick(View view, NewsDetailsUiModel newsDetailsUiModel);
    }
}
