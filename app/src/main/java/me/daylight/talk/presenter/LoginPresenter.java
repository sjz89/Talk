package me.daylight.talk.presenter;

import android.os.Handler;
import android.widget.ArrayAdapter;


import me.daylight.talk.R;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.model.LoginModel;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.view.LoginView;

/*
 *登陆视图与逻辑连接类
 * 将Model层的数据回调给View层
 */
public class LoginPresenter extends BasePresenter<LoginView,LoginModel> {

    public void login(String phone,String password) {
        getView().showProgress();
        new Handler().postDelayed(()-> getModel().login(phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                SharedPreferencesUtil.putValue(getView().getCurContext(), GlobalField.USER,GlobalField.ACCOUNT,phone);
                getView().toMain();
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().hideProgress();
                getView().showErrorMsg(errorMsg);
            }
        }),2000);
    }

    public void loadAccounts() {
        getModel().loadAccounts().observe(getView().getCurContext(), list->{
            if (list!=null&&list.size()!=0) {
                getView().showAccounts(new ArrayAdapter<>(getView().getCurContext(), R.layout.item_menu,list));
            }
        });
    }

    public void setAccount() {
        getView().setAccount(getModel().getAccount());
    }
}
