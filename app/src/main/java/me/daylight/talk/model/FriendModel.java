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
import me.daylight.talk.bean.FriendList;
import me.daylight.talk.bean.User;
import me.daylight.talk.http.HttpContract;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.http.RetrofitUtils;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.HttpFeedBackUtil;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.viewmodel.ChatViewModel;
import me.daylight.talk.viewmodel.UserViewModel;

public class FriendModel extends BaseModel {
    private UserViewModel userViewModel;
    private ChatViewModel chatViewModel;
    private String phone;

    @Override
    public void init() {
        userViewModel = ViewModelProviders.of((QMUIFragmentActivity) getContext()).get(UserViewModel.class);
        chatViewModel=ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(ChatViewModel.class);
        phone=SharedPreferencesUtil.getString(getContext(),GlobalField.USER,GlobalField.ACCOUNT);
        userViewModel.loadFriends(phone);
    }

    public void loadFriendList(OnHttpCallBack<RetResult> callBack) {
        long updateTime = userViewModel.getFriendListUpdateTime(phone);
        RetrofitUtils.newInstance(GlobalField.url).create(HttpContract.class)
                .getFriendsInfo(phone, updateTime)
                .flatMap((Function<RetResult<List<User>>, Observable<RetResult<List<FriendList>>>>) listRetResult -> {
                    for (User user : listRetResult.getData()) {
                        userViewModel.saveUserInfo(user);
                    }
                    return RetrofitUtils.newInstance(GlobalField.url).create(HttpContract.class).getFriendList(phone, updateTime);
                })
                .flatMap((Function<RetResult<List<FriendList>>, Observable<RetResult<List<User>>>>) listRetResult -> {
                    for (FriendList friend : listRetResult.getData())
                        userViewModel.addFriend(friend);
                    return RetrofitUtils.newInstance(GlobalField.url).create(HttpContract.class).updateUserInfo(phone,
                            userViewModel.getUpdateTime(phone));
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(listRetResult -> {
                    for (User user : listRetResult.getData())
                        userViewModel.saveUserInfo(user);
                    chatViewModel.emptyQuery();
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult<List<User>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RetResult<List<User>> listRetResult) {
                        HttpFeedBackUtil.handleRetResult(listRetResult, callBack);
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

    public LiveData<List<User>> getFriends() {
        return userViewModel.getFriends();
    }

    public void setFriend(User friend) {
        chatViewModel.setFriend(friend);
    }

    public void cleanMsgNumber() {
        chatViewModel.cleanMsgNumber(phone+chatViewModel.getFriend().getPhone());
    }
}
