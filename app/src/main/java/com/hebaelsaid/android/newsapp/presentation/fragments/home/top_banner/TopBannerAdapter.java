package com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;

import java.util.ArrayList;
import java.util.List;

public class TopBannerAdapter extends FragmentStateAdapter {
    private List<NewsResponseModel.ArticlesBean> articlesBeanList = new ArrayList<>();

    public TopBannerAdapter(@NonNull FragmentActivity fragmentActivity,List<NewsResponseModel.ArticlesBean> articlesBeanList) {
        super(fragmentActivity);
        this.articlesBeanList = articlesBeanList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        NewsDetailsUiModel model  = new NewsDetailsUiModel();
        model.setName(articlesBeanList.get(position).getTitle());
        model.setPublishedAt(articlesBeanList.get(position).getPublishedAt());
        model.setUrl(articlesBeanList.get(position).getUrlToImage());
        model.setDescription(articlesBeanList.get(position).getDescription());
        return new TopBannerFragment(model);
    }

    @Override
    public int getItemCount() {
        return articlesBeanList.size();
    }
}
