package me.daylight.talk.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import me.daylight.talk.bean.User;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.viewmodel.UserViewModel;

public class FunctionModel extends BaseModel {
    private UserViewModel userViewModel;
    @Override
    public void init() {
        userViewModel= ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(UserViewModel.class);
    }

    public LiveData<User> getUser(){
        return userViewModel.getMe();
    }

    public void setUserPhone(){
        userViewModel.setUserPhone(SharedPreferencesUtil.getString(getContext(), GlobalField.USER,GlobalField.ACCOUNT));
    }
}
