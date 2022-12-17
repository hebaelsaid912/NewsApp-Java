package com.hebaelsaid.android.newsapp.ui.feature.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hebaelsaid.android.newsapp.data.model.NewsResponseModel;
import com.hebaelsaid.android.newsapp.domain.repository.NewsRepoImpl;
import com.hebaelsaid.android.newsapp.utils.CommonFunction;


import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final String TAG = "HomeViewModel";
    private MutableLiveData<NewsResponseModel> egyptNewsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<NewsResponseModel> AllNewsMutableLiveData = new MutableLiveData<>();

    public LiveData<NewsResponseModel> getEgyptNewsMutableLiveData() {
        return egyptNewsMutableLiveData;
    }

    public LiveData<NewsResponseModel> getAllNewsMutableLiveData() {
        return AllNewsMutableLiveData;
    }

    private final NewsRepoImpl egyptNewsRepo;
    @Inject
    public HomeViewModel(NewsRepoImpl egyptNewsRepo){
        this.egyptNewsRepo = egyptNewsRepo;
    }
    void getTobBannerData(String country){
        Observable<NewsResponseModel> observable = egyptNewsRepo.getEgyptNewsData(country);
        Observer<NewsResponseModel> observer = new Observer<NewsResponseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsResponseModel egyptNewsResponseModel) {
                if(egyptNewsResponseModel != null){
                    egyptNewsMutableLiveData.setValue(egyptNewsResponseModel);
                }else{
                    Log.i(TAG, "egyptNewsResponseModel = null  ");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "getTobBannerData: onError: " + e.getMessage());
                Log.i(TAG, "getTobBannerData: onError: " + CommonFunction.apisHandleError(e));
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    void getLatestNewsData(String source){
        Observable<NewsResponseModel> observable = egyptNewsRepo.getAllNewsData(source);
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
                Log.i(TAG, "getLatestNewsData: onError: " + CommonFunction.apisHandleError(e));
               // CommonFunction.apisHandleError(e);
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
}
