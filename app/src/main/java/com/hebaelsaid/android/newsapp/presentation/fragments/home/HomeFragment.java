package com.hebaelsaid.android.newsapp.presentation.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.hebaelsaid.android.newsapp.databinding.FragmentHomeBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.response.LatestNewsResponseModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner.TopBannerAdapter;
import com.hebaelsaid.android.newsapp.repository.EgyptNewsRepoImpl;
import com.hebaelsaid.android.newsapp.repository.LatestNewsRepoImpl;



public class HomeFragment extends Fragment {
    private final String TAG = "HomeFragment";
    private FragmentHomeBinding fragmentHomeBinding;
    private HomeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 1");
        EgyptNewsRepoImpl egyptNewsRepo = new EgyptNewsRepoImpl();
        LatestNewsRepoImpl latestNewsRepo = new LatestNewsRepoImpl();
        viewModel = new HomeViewModel(egyptNewsRepo,latestNewsRepo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false);
        Log.d(TAG, "onCreateView: 2");
        viewModel.getTobBannerData("eg");
        viewModel.getLatestNewsData();
        Log.d(TAG, "onCreateView: 3");
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.egyptNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<EgyptNewsResponseModel>() {
            @Override
            public void onChanged(EgyptNewsResponseModel egyptNewsResponseModel) {
                Log.d(TAG, "onChanged: article size: " + egyptNewsResponseModel.getArticles().size());
                fragmentHomeBinding.mainOrderViewPager.setAdapter(new TopBannerAdapter(requireActivity(),egyptNewsResponseModel.getArticles().size()));
               new TabLayoutMediator(
                        fragmentHomeBinding.tabsDots,
                        fragmentHomeBinding.mainOrderViewPager,
                       (tab, position) -> {

                       }
                ).attach();

            }
        });
        viewModel.latestNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<LatestNewsResponseModel>() {
            @Override
            public void onChanged(LatestNewsResponseModel latestNewsResponseModel) {
                /*for( int i = 0 ; i< latestNewsResponseModel.getSources().size() ; i++){

                }*/
                Log.i(TAG, "latestNewsMutableLiveData:onChanged: sourse size: "+latestNewsResponseModel.getSources().size());
            }
        });
    }
}