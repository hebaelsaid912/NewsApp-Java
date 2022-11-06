package com.hebaelsaid.android.newsapp.presentation.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.FragmentHomeBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.response.LatestNewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.LatestNewsUiModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news.LatestNewsAdapter;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner.TopBannerAdapter;
import com.hebaelsaid.android.newsapp.repository.EgyptNewsRepoImpl;
import com.hebaelsaid.android.newsapp.repository.LatestNewsRepoImpl;

import java.util.ArrayList;


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
                fragmentHomeBinding.topBannerViewPager.setAdapter(new TopBannerAdapter(requireActivity(),egyptNewsResponseModel.getArticles()));
               // fragmentHomeBinding.topBannerViewPager.setPadding(100,50,100,0);
                float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
                /*fragmentHomeBinding.topBannerViewPager.setPageTransformer((page, position) -> {
                    if (position == 0) {
                        page.setTranslationX(-pageOffset);
                    } else if (position <= 1) {
                        float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                        page.setTranslationX(pageOffset);
                      //  page.setTranslationX(-pageOffset);
                        page.setScaleY(scaleFactor);
                        page.setAlpha(scaleFactor);
                    } else {
                     //   page.setAlpha(0);
                        page.setTranslationX(-pageOffset);
                        page.setTranslationX(pageOffset);
                    }
                });*/
                /*fragmentHomeBinding.topBannerViewPager.setPageTransformer((page, position) -> {
                    float offset = position * -(2 * pageOffset + pageOffset);
                        float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                        page.setTranslationX(offset);
                        page.setScaleY(scaleFactor);
                       // page.setAlpha(scaleFactor);
                });*/
                fragmentHomeBinding.topBannerViewPager.setOffscreenPageLimit(3);
                fragmentHomeBinding.topBannerViewPager.setClipToPadding(false);
                fragmentHomeBinding.topBannerViewPager.setClipChildren(false);
                fragmentHomeBinding.topBannerViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
                CompositePageTransformer transformer = new CompositePageTransformer();
                transformer.addTransformer(new MarginPageTransformer(40));
                transformer.addTransformer((page, position) -> {
                    float offset = position * -(2 * pageOffset + pageOffset);
                    float scaleFactor = Math.max(0.85f, 1 - Math.abs(position - 0.14f));
                    page.setTranslationX(offset);
                    page.setScaleY(scaleFactor);
                     page.setAlpha(scaleFactor);
                });
                fragmentHomeBinding.topBannerViewPager.setPageTransformer(transformer);
               new TabLayoutMediator(
                        fragmentHomeBinding.tabsDots,
                        fragmentHomeBinding.topBannerViewPager,
                       (tab, position) -> {

                       }
                ).attach();
                for( int i = 0 ; i< egyptNewsResponseModel.getArticles().size() ; i++){
                    View tab = ((ViewGroup) fragmentHomeBinding.tabsDots.getChildAt(0)).getChildAt(i);
                    ViewGroup.MarginLayoutParams p = ( ViewGroup.MarginLayoutParams)tab.getLayoutParams();
                            p.setMargins(0,0,10,0);
                    tab.requestLayout();
                }

            }
        });
        viewModel.latestNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<LatestNewsResponseModel>() {
            @Override
            public void onChanged(LatestNewsResponseModel latestNewsResponseModel) {
                ArrayList<LatestNewsUiModel> latestNewsUiModels = new ArrayList<>();
                for( int i = 0 ; i< latestNewsResponseModel.getSources().size() ; i++){
                    if(latestNewsResponseModel.getSources().get(i).getId().equals("the-next-web")
                    || latestNewsResponseModel.getSources().get(i).getId().equals("bbc-news")) {
                        LatestNewsUiModel latestNewsUiModel = new LatestNewsUiModel();
                        latestNewsUiModel.setName(latestNewsResponseModel.getSources().get(i).getName());
                        latestNewsUiModel.setCountry(latestNewsResponseModel.getSources().get(i).getCountry());
                        latestNewsUiModel.setUrl(latestNewsResponseModel.getSources().get(i).getUrl());
                        latestNewsUiModel.setDescription(latestNewsResponseModel.getSources().get(i).getDescription());
                        latestNewsUiModels.add(latestNewsUiModel);
                    }
                }
                LatestNewsAdapter adapter = new LatestNewsAdapter(latestNewsUiModels);
                fragmentHomeBinding.latestNewsRv.setAdapter(adapter);
                Log.i(TAG, "latestNewsMutableLiveData:onChanged: sourse size: "+latestNewsResponseModel.getSources().size());
            }
        });
    }
}