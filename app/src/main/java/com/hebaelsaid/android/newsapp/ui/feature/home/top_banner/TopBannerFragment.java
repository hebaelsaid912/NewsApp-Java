package com.hebaelsaid.android.newsapp.ui.feature.home.top_banner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebaelsaid.android.newsapp.databinding.FragmentTopBannerBinding;
import com.hebaelsaid.android.newsapp.domain.ui_model.NewsDetailsUiModel;
import com.hebaelsaid.android.newsapp.ui.feature.home.HomeFragmentDirections;

public class TopBannerFragment extends Fragment {
    private FragmentTopBannerBinding fragmentTopBannerBinding;
    private NewsDetailsUiModel topBannerUiModel;

    public TopBannerFragment(NewsDetailsUiModel topBannerUiModel) {
        this.topBannerUiModel = topBannerUiModel;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTopBannerBinding = FragmentTopBannerBinding.inflate(inflater, container, false);
        fragmentTopBannerBinding.setModel(topBannerUiModel);
        return fragmentTopBannerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentTopBannerBinding.topBannerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsDetailsUiModel newsDetailsUiModel = new NewsDetailsUiModel();
                newsDetailsUiModel.setName(topBannerUiModel.getName());
                newsDetailsUiModel.setDescription(topBannerUiModel.getDescription());
                newsDetailsUiModel.setUrl(topBannerUiModel.getUrl());
                newsDetailsUiModel.setPublishedAt(topBannerUiModel.getPublishedAt());
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(newsDetailsUiModel));
            }
        });
    }
}