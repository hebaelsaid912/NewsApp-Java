package com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;

import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<GetLatestNewsHolder> {

    private ArrayList<NewsDetailsUiModel> data = new ArrayList<>();
    private final GetLatestNewsHolder.OnItemClickListener onItemClickListener;

    public LatestNewsAdapter(GetLatestNewsHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(ArrayList<NewsDetailsUiModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GetLatestNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return GetLatestNewsHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GetLatestNewsHolder holder, int position) {
        NewsDetailsUiModel uiModel = data.get(position);
        holder.bind(uiModel,onItemClickListener);
    }
    @Override
    public void onViewRecycled(@NonNull GetLatestNewsHolder holder) {
        super.onViewRecycled(holder);
        holder.recycle();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}