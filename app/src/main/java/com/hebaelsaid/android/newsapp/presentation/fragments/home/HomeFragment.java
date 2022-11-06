package com.hebaelsaid.android.newsapp.presentation.fragments.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.hebaelsaid.android.newsapp.R;
import com.hebaelsaid.android.newsapp.databinding.FragmentHomeBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news.LatestNewsAdapter;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.top_banner.TopBannerAdapter;
import com.hebaelsaid.android.newsapp.repository.NewsRepoImpl;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements LatestNewsAdapter.OnItemClickListener {
    private final String TAG = "HomeFragment";
    private FragmentHomeBinding fragmentHomeBinding;
    private HomeViewModel viewModel;
    private LatestNewsAdapter.OnItemClickListener onItemClickListener;
    private ArrayList<NewsDetailsUiModel> latestNewsUiModels = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsRepoImpl egyptNewsRepo = new NewsRepoImpl();
        viewModel = new HomeViewModel(egyptNewsRepo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);
        onItemClickListener = this;
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getTobBannerData("eg");
        viewModel.getLatestNewsData("bbc-news");
        viewModel.getLatestNewsData("the-next-web");

        viewModel.egyptNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<NewsResponseModel>() {
            @Override
            public void onChanged(NewsResponseModel newsResponseModel) {
                fragmentHomeBinding.topBannerViewPager.setAdapter(new TopBannerAdapter(requireActivity(), newsResponseModel.getArticles()));
                float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
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
                for (int i = 0; i < newsResponseModel.getArticles().size(); i++) {
                    View tab = ((ViewGroup) fragmentHomeBinding.tabsDots.getChildAt(0)).getChildAt(i);
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
                    p.setMargins(0, 0, 10, 0);
                    tab.requestLayout();
                }

            }

        });

        viewModel.AllNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<NewsResponseModel>() {
            @Override
            public void onChanged(NewsResponseModel latestNewsResponseModel) {
                for (int i = 0; i < latestNewsResponseModel.getArticles().size(); i++) {
                    NewsDetailsUiModel latestNewsUiModel = new NewsDetailsUiModel();
                    latestNewsUiModel.setName(latestNewsResponseModel.getArticles().get(i).getTitle());
                    latestNewsUiModel.setPublishedAt(latestNewsResponseModel.getArticles().get(i).getPublishedAt());
                    latestNewsUiModel.setUrl(latestNewsResponseModel.getArticles().get(i).getUrlToImage());
                    latestNewsUiModel.setDescription(latestNewsResponseModel.getArticles().get(i).getDescription());
                    latestNewsUiModels.add(latestNewsUiModel);
                }
                setRecycleView();
            }
        });
    }

    private void setRecycleView() {
        LatestNewsAdapter adapter = new LatestNewsAdapter(latestNewsUiModels, onItemClickListener);
        fragmentHomeBinding.latestNewsRv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, NewsDetailsUiModel newsDetailsUiModel) {
        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(newsDetailsUiModel));
    }
}