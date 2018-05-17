package me.daylight.talk.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import me.daylight.talk.app.DataBase;
import me.daylight.talk.bean.FriendList;
import me.daylight.talk.bean.User;
import me.daylight.talk.dao.FriendDao;
import me.daylight.talk.dao.UserDao;

public class UserRepository {
    private UserDao userDao;
    private FriendDao friendDao;

    public UserRepository(Context context) {
        DataBase dataBase = DataBase.getDataBase(context);
        userDao = dataBase.userDao();
        friendDao=dataBase.friendDao();
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public LiveData<User> findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    public LiveData<List<User>> findFriendsByPhone(String phone){
        return userDao.findFriendsByPhone(phone);
    }

    public User loadUserByPhone(String phone){
        return userDao.loadUserByPhone(phone);
    }

    public LiveData<List<String>> getAllAccount() {
        return userDao.getAllAccount();
    }

    public void addFriend(FriendList friend){
        friendDao.addFriend(friend);
    }

    public long getFriendListUpdateTime(String phone){
        return friendDao.findUpdateTimeByPhone(phone);
    }

    public long getUpdateTime(String phone){
        return userDao.getUpdateTime(phone);
    }

    public long getUserUpdateTime(String phone){
        return userDao.getUserUpdateTime(phone);
    }
}
