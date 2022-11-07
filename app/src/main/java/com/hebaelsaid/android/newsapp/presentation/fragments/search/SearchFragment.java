package com.hebaelsaid.android.newsapp.presentation.fragments.search;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.hebaelsaid.android.newsapp.databinding.FragmentSearchBinding;
import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.ui_model.NewsDetailsUiModel;
import com.hebaelsaid.android.newsapp.presentation.fragments.home.latest_news.LatestNewsAdapter;
import com.hebaelsaid.android.newsapp.repository.NewsRepoImpl;
import com.hebaelsaid.android.newsapp.utils.CommonFunction;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements LatestNewsAdapter.OnItemClickListener {
    private final String TAG = "SearchFragment";
    private FragmentSearchBinding fragmentSearchBinding;
    private SearchViewModel searchViewModel;
    private LatestNewsAdapter.OnItemClickListener onItemClickListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchViewModel = new SearchViewModel(new NewsRepoImpl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        onItemClickListener = this;
        return fragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(CommonFunction.isOnline(requireContext())) {
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
        }else{
            fragmentSearchBinding.mainEmptyView.setVisibility(View.GONE);
            fragmentSearchBinding.notInternetConnectionLayout.getRoot().setVisibility(View.VISIBLE);
        }
        searchViewModel.AllNewsMutableLiveData.observe(getViewLifecycleOwner(), new Observer<NewsResponseModel>() {
            @Override
            public void onChanged(NewsResponseModel newsResponseModel) {
                ArrayList<NewsDetailsUiModel> latestNewsUiModels = new ArrayList<>();
                for (int i = 0; i < newsResponseModel.getArticles().size(); i++) {
                    NewsDetailsUiModel latestNewsUiModel = new NewsDetailsUiModel();
                    latestNewsUiModel.setName(newsResponseModel.getArticles().get(i).getTitle());
                    latestNewsUiModel.setPublishedAt(newsResponseModel.getArticles().get(i).getPublishedAt());
                    latestNewsUiModel.setUrl(newsResponseModel.getArticles().get(i).getUrlToImage());
                    latestNewsUiModel.setDescription(newsResponseModel.getArticles().get(i).getDescription());
                    latestNewsUiModels.add(latestNewsUiModel);
                }
                if (latestNewsUiModels.isEmpty()) {
                    fragmentSearchBinding.newsSearchDataRv.setVisibility(View.GONE);
                    fragmentSearchBinding.mainEmptyView.setVisibility(View.VISIBLE);
                } else {
                    LatestNewsAdapter adapter = new LatestNewsAdapter(latestNewsUiModels, onItemClickListener);
                    fragmentSearchBinding.newsSearchDataRv.setAdapter(adapter);
                    fragmentSearchBinding.newsSearchDataRv.setVisibility(View.VISIBLE);
                    fragmentSearchBinding.mainEmptyView.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public void onItemClick(View view, NewsDetailsUiModel newsDetailsUiModel) {
        Navigation.findNavController(view).navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(newsDetailsUiModel));
    }
}