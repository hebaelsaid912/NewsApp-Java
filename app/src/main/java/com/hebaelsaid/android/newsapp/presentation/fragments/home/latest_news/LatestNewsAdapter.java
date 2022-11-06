package com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hebaelsaid.android.newsapp.databinding.LatestNewListItemBinding;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.LatestNewsUiModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.GetLatestNewsHolder> {


    ArrayList<LatestNewsUiModel> data;
    Context context;

    public LatestNewsAdapter(ArrayList<LatestNewsUiModel> data) {
        this.data = data;
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

}