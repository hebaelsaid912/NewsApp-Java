package com.hebaelsaid.android.newsapp.presentation.fragments.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.FragmentDetailsBinding;
import com.hebaelsaid.android.newsapp.databinding.FragmentDetailsBindingImpl;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;


public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding fragmentDetailsBinding;
    NewsDetailsUiModel newsDetailsUiModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsDetailsUiModel = getArguments().getParcelable("news_data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        fragmentDetailsBinding.setModel(newsDetailsUiModel);
        return fragmentDetailsBinding.getRoot();
    }


}