package com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.TopBannerUiModel;

import java.util.ArrayList;
import java.util.List;

public class TopBannerAdapter extends FragmentStateAdapter {
    private List<EgyptNewsResponseModel.ArticlesBean> articlesBeanList = new ArrayList<>();

    public TopBannerAdapter(@NonNull FragmentActivity fragmentActivity,List<EgyptNewsResponseModel.ArticlesBean> articlesBeanList) {
        super(fragmentActivity);
        this.articlesBeanList = articlesBeanList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        TopBannerUiModel model  = new TopBannerUiModel();
        model.setTitle(articlesBeanList.get(position).getTitle());
        model.setPublishedAt(articlesBeanList.get(position).getPublishedAt());
        model.setUrlToImage(articlesBeanList.get(position).getUrlToImage());
        return new TopBannerFragment(model);
    }

    @Override
    public int getItemCount() {
        return articlesBeanList.size();
    }
}
