package me.daylight.talk.model;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.Date;
import java.util.Objects;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.daylight.talk.bean.User;
import me.daylight.talk.http.HttpContract;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.http.RetrofitUtils;
import me.daylight.talk.utils.DialogUtil;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.HttpFeedBackUtil;
import me.daylight.talk.utils.MediaUtil;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.viewmodel.UserViewModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class InfoModel extends BaseModel {
    private UserViewModel userViewModel;

    @Override
    public void init() {
        userViewModel= ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(UserViewModel.class);
    }

    public void saveUserInfo(User user, OnHttpCallBack<RetResult> callBack) {
        RetrofitUtils.newInstance(GlobalField.url)
                .create(HttpContract.class)
                .saveUserInfo(user)
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
                        userViewModel.saveUserInfo(user);
                    }
                });
    }

    public void updateTime(User user){
        user.setUpdatetime(new Date().getTime());
        userViewModel.saveUserInfo(user);
    }

    public User loadUserInfo() {
        if (SharedPreferencesUtil.getInt(getContext(),GlobalField.USER,GlobalField.STATE)==-100) {
            User user=new User();
            user.setPhone(userViewModel.getUserPhone());
            user.setGender(0);
            user.setName("");
            user.setNicname("");
            user.setSignature("");
            user.setAddress("");
            return user;
        }
        return userViewModel.loadUserByPhone(userViewModel.getUserPhone());
    }

    public void uploadHeadImage(Uri uri,OnHttpCallBack<RetResult> callBack){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),
                Objects.requireNonNull(MediaUtil.getByteFromFile(MediaUtil.getFileFromMediaUri(getContext(), uri))));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", "", requestFile);
        QMUITipDialog loading= DialogUtil.showTipDialog(getContext(),QMUITipDialog.Builder.ICON_TYPE_LOADING,"上传头像中",false);
        RetrofitUtils.newInstance(GlobalField.url)
                .create(HttpContract.class)
                .uploadHeadImage(body,userViewModel.getUserPhone())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RetResult retResult) {
                        loading.dismiss();
                        HttpFeedBackUtil.handleRetResult(retResult,callBack);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.dismiss();
                        HttpFeedBackUtil.handleException(e,callBack);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
