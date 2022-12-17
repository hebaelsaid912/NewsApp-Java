package com.hebaelsaid.android.newsapp.ui.feature.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hebaelsaid.android.newsapp.databinding.FragmentDetailsBinding;
import com.hebaelsaid.android.newsapp.domain.ui_model.NewsDetailsUiModel;


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentDetailsBinding.newsDetailsBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}