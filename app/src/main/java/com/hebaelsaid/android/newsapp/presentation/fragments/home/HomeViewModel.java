package com.hebaelsaid.android.newsapp.presentation.fragments.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hebaelsaid.android.newsapp.domain.model.response.NewsResponseModel;
import com.hebaelsaid.android.newsapp.repository.NewsRepoImpl;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends ViewModel {
    private final String TAG = "HomeViewModel";
    MutableLiveData<NewsResponseModel> egyptNewsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<NewsResponseModel> AllNewsMutableLiveData = new MutableLiveData<>();
    private NewsRepoImpl egyptNewsRepo;
    public HomeViewModel(NewsRepoImpl egyptNewsRepo){
        this.egyptNewsRepo = egyptNewsRepo;
    }
    void getTobBannerData(String country){
        Observable<NewsResponseModel> observable = egyptNewsRepo.getEgyptNewsData(country,"14e5e0dc7d9049daaf1b8fa74a5838fd");
        Observer<NewsResponseModel> observer = new Observer<NewsResponseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsResponseModel egyptNewsResponseModel) {
                if(egyptNewsResponseModel != null){
                    Log.d(TAG, "onNext: 4");
                    Log.d(TAG, "onNext: status: "+ egyptNewsResponseModel.getStatus());
                        Log.d(TAG, "onNext: 5");
                        egyptNewsMutableLiveData.setValue(egyptNewsResponseModel);

                }else{
                    Log.i(TAG, "egyptNewsResponseModel = null  ");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "getTobBannerData: onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    void getLatestNewsData(String source){
        Observable<NewsResponseModel> observable = egyptNewsRepo.getAllNewsData(source,"14e5e0dc7d9049daaf1b8fa74a5838fd");
        Observer<NewsResponseModel> observer = new Observer<NewsResponseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsResponseModel latestNewsResponseModel) {
                if(latestNewsResponseModel != null){
                    if(latestNewsResponseModel.getStatus().equals("ok")) {
                        AllNewsMutableLiveData.setValue(latestNewsResponseModel);
                    }
                }else{
                    Log.i(TAG, "latestNewsResponseModel = null  ");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "getLatestNewsData: onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
