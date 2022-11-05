package com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.FragmentTopBannerBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;

public class TopBannerFragment extends Fragment {
    private FragmentTopBannerBinding fragmentTopBannerBinding;
    private TopBannerViewModel topBannerViewModel;
    public TopBannerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topBannerViewModel = new TopBannerViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTopBannerBinding = FragmentTopBannerBinding.inflate(inflater,container,false);
        fragmentTopBannerBinding.setViewModel(topBannerViewModel);
        return fragmentTopBannerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}