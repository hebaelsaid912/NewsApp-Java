package com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hebaelsaid.android.newsapp.databinding.LatestNewListItemBinding;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.LatestNewsUiModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.GetLatestNewsHolder> {

    private ArrayList<LatestNewsUiModel> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public LatestNewsAdapter(ArrayList<LatestNewsUiModel> data,OnItemClickListener onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public GetLatestNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LatestNewListItemBinding latestNewListItemBinding = LatestNewListItemBinding.inflate(LayoutInflater.from(parent.getContext()) , parent ,false);
        context  = parent.getContext();
        return new GetLatestNewsHolder(latestNewListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestNewsAdapter.GetLatestNewsHolder holder, int position) {
        holder.bind(data.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailsUiModel newsDetailsUiModel = new NewsDetailsUiModel();
                newsDetailsUiModel.setName(data.get(position).getName());
                newsDetailsUiModel.setDescription(data.get(position).getDescription());
                newsDetailsUiModel.setCountry(data.get(position).getCountry());
                newsDetailsUiModel.setUrl(data.get(position).getUrl());
                onItemClickListener.onItemClick(view,newsDetailsUiModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class GetLatestNewsHolder extends RecyclerView.ViewHolder {

        private LatestNewListItemBinding latestNewListItemBinding;

        public GetLatestNewsHolder(@NonNull LatestNewListItemBinding latestNewListItemBinding) {
            super(latestNewListItemBinding.getRoot());
            this.latestNewListItemBinding = latestNewListItemBinding;

        }

        void bind(LatestNewsUiModel latestNewsUiModel) {
            latestNewListItemBinding.setModel(latestNewsUiModel);
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View view, NewsDetailsUiModel newsDetailsUiModel);
    }
}