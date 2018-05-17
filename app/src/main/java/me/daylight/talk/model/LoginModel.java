package me.daylight.talk.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.viewmodel.UserViewModel;
import me.daylight.talk.bean.User;
import me.daylight.talk.http.HttpContract;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetrofitUtils;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.HttpFeedBackUtil;

/*
 *登陆逻辑实现类
 */
public class LoginModel extends BaseModel {
    private UserViewModel userViewModel;

    @Override
    public void init() {
        userViewModel = ViewModelProviders.of((QMUIFragmentActivity) getContext()).get(UserViewModel.class);
    }

    public void login(String phone, String password, OnHttpCallBack<RetResult> callBack) {
        RetrofitUtils.newInstance(GlobalField.url)
                .create(HttpContract.class)
                .login(phone, password)
                .flatMap((Function<RetResult<Integer>, Observable<RetResult<User>>>) retResult -> {
                    HttpFeedBackUtil.handleRetResult(retResult, callBack);
                    if (retResult.getCode()== RetResult.RetCode.SUCCESS.code) {
                        SharedPreferencesUtil.putValue(getContext(),GlobalField.USER,GlobalField.STATE,retResult.getData());
                        if (retResult.getData()==100)
                            return RetrofitUtils.newInstance(GlobalField.url).create(HttpContract.class).getUserInfo(phone);
                    }
                    return null;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(userRetResult -> saveUserInfo(userRetResult.getData()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RetResult<User> userRetResult) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpFeedBackUtil.handleException(e, callBack);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void saveUserInfo(User user) {
        userViewModel.saveUserInfo(user);
    }

    public LiveData<List<String>> loadAccounts() {
        return userViewModel.getAllPhone();
    }

    public String getAccount() {
        return SharedPreferencesUtil.getString(getContext(),GlobalField.USER,GlobalField.ACCOUNT);
    }
}
