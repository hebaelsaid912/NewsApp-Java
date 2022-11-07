package com.hebaelsaid.android.newsapp.presentation.fragments.search;

import static com.hebaelsaid.android.newsapp.utils.CommonFunction.isOnline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.hebaelsaid.android.newsapp.databinding.FragmentSearchBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.HomeFragment;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.HomeFragmentDirections;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.HomeViewModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news.LatestNewsAdapter;
import com.hebaelsaid.android.newsapp.repository.NewsRepoImpl;
import com.hebaelsaid.android.newsapp.utils.CommonFunction;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {
    private final String TAG = "SearchFragment";
    private FragmentSearchBinding fragmentSearchBinding;
    private SearchViewModel searchViewModel;
    private LatestNewsAdapter latestNewsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isOnline(requireContext())) {
            setupConnectedUi();
        } else {
            setupOfflineUi();
        }
        observeNewsData();
    }

    private void observeNewsData() {
        searchViewModel.AllNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<NewsResponseModel>() {
            @Override
            public void onChanged(NewsResponseModel newsResponseModel) {
                ArrayList<NewsDetailsUiModel> latestNewsUiModels = newsResponseModel.getUiModels();
                setupValidateUi(latestNewsUiModels);
                latestNewsAdapter.setData(latestNewsUiModels);
            }
        });
    }

    private void setupValidateUi(ArrayList<NewsDetailsUiModel> latestNewsUiModels) {
        if (latestNewsUiModels.isEmpty()) {
            fragmentSearchBinding.newsSearchDataRv.setVisibility(View.GONE);
            fragmentSearchBinding.mainEmptyView.setVisibility(View.VISIBLE);
        } else {
            setupRecyclerView();
            fragmentSearchBinding.newsSearchDataRv.setVisibility(View.VISIBLE);
            fragmentSearchBinding.mainEmptyView.setVisibility(View.GONE);

        }
    }

    private void setupOfflineUi() {
        fragmentSearchBinding.mainEmptyView.setVisibility(View.GONE);
        fragmentSearchBinding.notInternetConnectionLayout.getRoot().setVisibility(View.VISIBLE);
    }

    private void setupConnectedUi() {
        fragmentSearchBinding.newsSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s != null) {
                    searchViewModel.getLatestNewsData(s);
                    return true;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s != null) {
                    searchViewModel.getLatestNewsData(s);
                    return true;
                }
                return false;
            }
        });
    }

    private void setupRecyclerView() {
        latestNewsAdapter = new LatestNewsAdapter(SearchFragment::onNewsItemClicked);
        fragmentSearchBinding.newsSearchDataRv.setAdapter(latestNewsAdapter);
        latestNewsAdapter.notifyDataSetChanged();
    }

    private static void onNewsItemClicked(View view1, NewsDetailsUiModel newsDetailsUiModel) {
        Navigation.findNavController(view1).navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(newsDetailsUiModel));
    }

}