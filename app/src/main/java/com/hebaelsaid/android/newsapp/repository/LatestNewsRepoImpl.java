package com.hebaelsaid.android.newsapp.repository;

import android.util.Log;


import com.hebaelsaid.android.newsapp.domain.data.remote.latest_news.LatestNewsClient;
import com.hebaelsaid.android.newsapp.domain.data.remote.latest_news.LatestNewsInterface;
import com.hebaelsaid.android.newsapp.domain.model.response.LatestNewsResponseModel;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LatestNewsRepoImpl implements LatestNewsInterface {

    @Override
    public Observable<LatestNewsResponseModel> getAllNewsData(String key) {
         return LatestNewsClient.getINSTANCE().getAllNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
