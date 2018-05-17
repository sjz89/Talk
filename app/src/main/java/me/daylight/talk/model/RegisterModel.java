package me.daylight.talk.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.daylight.talk.http.HttpContract;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.http.RetrofitUtils;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.HttpFeedBackUtil;

public class RegisterModel extends BaseModel {

    @Override
    public void init() {
    }

    public void register(String phone, String password, OnHttpCallBack<RetResult> callBack) {
        RetrofitUtils.newInstance(GlobalField.url)
                .create(HttpContract.class)
                .register(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RetResult<String> retResult) {
                        HttpFeedBackUtil.handleRetResult(retResult,callBack);
                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpFeedBackUtil.handleException(e,callBack);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
