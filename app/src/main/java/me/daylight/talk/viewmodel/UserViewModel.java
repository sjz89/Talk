package me.daylight.talk.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import me.daylight.talk.bean.FriendList;
import me.daylight.talk.bean.User;
import me.daylight.talk.repository.UserRepository;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> friends;
    private LiveData<User> me;
    private String userPhone;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        me=userRepository.findUserByPhone(SharedPreferencesUtil.getString(application, GlobalField.USER,GlobalField.ACCOUNT));
    }

    public LiveData<User> getMe() {
        return me;
    }

    public void saveUserInfo(User user) {
        userRepository.insertUser(user);
    }

    public User loadUserByPhone(String phone){
        return userRepository.loadUserByPhone(phone);
    }

    public void loadFriends(String phone){
        friends=userRepository.findFriendsByPhone(phone);
    }

    public LiveData<List<User>> getFriends() {
        return friends;
    }

    public LiveData<List<String>> getAllPhone() {
        return userRepository.getAllAccount();
    }

    public void addFriend(FriendList friend){
        userRepository.addFriend(friend);
    }

    public long getFriendListUpdateTime(String phone){
        return userRepository.getFriendListUpdateTime(phone);
    }

    public long getUpdateTime(String phone){
        return userRepository.getUpdateTime(phone);
    }

    public long getUserUpdateTime(String phone){
        return userRepository.getUserUpdateTime(phone);
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
