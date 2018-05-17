package me.daylight.talk.presenter;

import android.os.Handler;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.model.RegisterModel;
import me.daylight.talk.view.RegisterView;

public class RegisterPresenter extends BasePresenter<RegisterView,RegisterModel> {

    public void register(String phone,String password) {
        getView().showTipDialog(QMUITipDialog.Builder.ICON_TYPE_LOADING,"注册中",false);
        new Handler().postDelayed(()-> getModel().register(phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                getView().hideTipDialog();
                getView().showTipDialog(QMUITipDialog.Builder.ICON_TYPE_SUCCESS,retResult.getMsg(),true);
                getView().toLogin();
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().hideTipDialog();
                getView().showTipDialog(QMUITipDialog.Builder.ICON_TYPE_FAIL,errorMsg,true);
            }
        }),2000);
    }
}
