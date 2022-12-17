package com.hebaelsaid.android.newsapp.ui.feature.home;

import static com.hebaelsaid.android.newsapp.utils.CommonFunction.isOnline;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.FragmentHomeBinding;
import com.hebaelsaid.android.newsapp.data.model.NewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.ui_model.NewsDetailsUiModel;
import com.hebaelsaid.android.newsapp.ui.feature.home.latest_news.LatestNewsAdapter;
import com.hebaelsaid.android.newsapp.ui.feature.home.top_banner.TopBannerAdapter;
import com.hebaelsaid.android.newsapp.utils.Constants;

import java.util.ArrayList;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {
    private final String TAG = "HomeFragment";
    private FragmentHomeBinding fragmentHomeBinding;
    private HomeViewModel viewModel;
    private LatestNewsAdapter latestNewsAdapter;
    private final ArrayList<NewsDetailsUiModel> latestNewsUiModels = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isOnline(requireContext())) {
            showConnectedUi();
        } else {
            showOfflineUi();
        }

        observeNewsData();
        observeLatestNewsData();
        setupRecyclerView();
    }
    private void observeNewsData() {
        viewModel.getEgyptNewsMutableLiveData().observe(getViewLifecycleOwner(), newsResponseModel -> {
            setupTopBannerAdapter(newsResponseModel);
            setupViewPager();
            setupTabLayout();
            setupTabDots(newsResponseModel);
        });
    }
    private void observeLatestNewsData() {
        viewModel.getAllNewsMutableLiveData().observe(getViewLifecycleOwner(), latestNewsResponseModel -> {
            latestNewsAdapter.setData(latestNewsResponseModel.getLatestNewsUiModels(latestNewsUiModels));
        });
    }
    private void showConnectedUi() {
        viewModel.getTobBannerData(Constants.EGYPT);
        viewModel.getLatestNewsData(Constants.BBC_NEWS);
        viewModel.getLatestNewsData(Constants.THE_NEXT_WEB);
    }
    private void showOfflineUi() {
        fragmentHomeBinding.notInternetConnectionLayout.getRoot().setVisibility(View.VISIBLE);
    }
    private void setupRecyclerView() {
        latestNewsAdapter = new LatestNewsAdapter(HomeFragment::onNewsItemClicked);
        fragmentHomeBinding.latestNewsRv.setAdapter(latestNewsAdapter);
    }
    private static void onNewsItemClicked(View view1, NewsDetailsUiModel newsDetailsUiModel) {
        Navigation.findNavController(view1).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(newsDetailsUiModel));
    }
    private void setupTopBannerAdapter(NewsResponseModel newsResponseModel) {
        TopBannerAdapter topBannerAdapter = new TopBannerAdapter(requireActivity(), newsResponseModel.getArticles());
        fragmentHomeBinding.topBannerViewPager.setAdapter(topBannerAdapter);
    }

    private void setupTabDots(NewsResponseModel newsResponseModel) {
        for (int i = 0; i < newsResponseModel.getArticles().size(); i++) {
            View tab = ((ViewGroup) fragmentHomeBinding.tabsDots.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 10, 0);
            tab.requestLayout();
        }
    }

    private void setupTabLayout() {
        new TabLayoutMediator(
                fragmentHomeBinding.tabsDots,
                fragmentHomeBinding.topBannerViewPager,
                (tab, position) -> {
                    // TODO implement this one
                }
        ).attach();
    }

    private void setupViewPager() {
        float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
        fragmentHomeBinding.topBannerViewPager.setOffscreenPageLimit(3);
        fragmentHomeBinding.topBannerViewPager.setClipToPadding(false);
        fragmentHomeBinding.topBannerViewPager.setClipChildren(false);
        setupPageTransformer(pageOffset);
    }

    private void setupPageTransformer(float pageOffset) {
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
    }
}