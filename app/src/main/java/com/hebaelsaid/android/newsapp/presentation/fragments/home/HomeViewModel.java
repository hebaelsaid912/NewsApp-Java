package com.hebaelsaid.android.newsapp.presentation.fragments.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hebaelsaid.android.newsapp.domain.model.response.EgyptNewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.model.response.LatestNewsResponseModel;
import com.hebaelsaid.android.newsapp.repository.EgyptNewsRepoImpl;
import com.hebaelsaid.android.newsapp.repository.LatestNewsRepoImpl;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends ViewModel {
    private final String TAG = "HomeViewModel";
    MutableLiveData<EgyptNewsResponseModel> egyptNewsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<LatestNewsResponseModel> latestNewsMutableLiveData = new MutableLiveData<>();
    private EgyptNewsRepoImpl egyptNewsRepo;
    private LatestNewsRepoImpl latestNewsRepo;
    public HomeViewModel(EgyptNewsRepoImpl egyptNewsRepo , LatestNewsRepoImpl latestNewsRepo){
        this.egyptNewsRepo = egyptNewsRepo;
        this.latestNewsRepo = latestNewsRepo;
    }
    void getTobBannerData(String country){
        Observable<EgyptNewsResponseModel> observable = egyptNewsRepo.getEgyptNewsData(country,"14e5e0dc7d9049daaf1b8fa74a5838fd");
        Observer<EgyptNewsResponseModel> observer = new Observer<EgyptNewsResponseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(EgyptNewsResponseModel egyptNewsResponseModel) {
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
    void getLatestNewsData(){
        Observable<LatestNewsResponseModel> observable = latestNewsRepo.getAllNewsData("14e5e0dc7d9049daaf1b8fa74a5838fd");
        Observer<LatestNewsResponseModel> observer = new Observer<LatestNewsResponseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LatestNewsResponseModel latestNewsResponseModel) {
                if(latestNewsResponseModel != null){
                    if(latestNewsResponseModel.getStatus().equals("200")) {
                        latestNewsMutableLiveData.setValue(latestNewsResponseModel);
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
