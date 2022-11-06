package com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.FragmentTopBannerBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.TopBannerUiModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.HomeFragmentDirections;

public class TopBannerFragment extends Fragment {
    private FragmentTopBannerBinding fragmentTopBannerBinding;
    private TopBannerUiModel topBannerUiModel;

    public TopBannerFragment(TopBannerUiModel topBannerUiModel) {
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
                newsDetailsUiModel.setName(topBannerUiModel.getTitle());
                newsDetailsUiModel.setDescription(topBannerUiModel.getDescription());
                newsDetailsUiModel.setUrl(topBannerUiModel.getUrlToImage());
                newsDetailsUiModel.setCountry(topBannerUiModel.getPublishedAt());
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(newsDetailsUiModel));
            }
        });
    }
}