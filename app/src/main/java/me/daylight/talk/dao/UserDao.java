package me.daylight.talk.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.daylight.talk.bean.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... user);
    @Query("select * from user where phone=:phone limit 1")
    LiveData<User> findUserByPhone(String phone);
    @Query("select * from user where phone=:phone limit 1")
    User loadUserByPhone(String phone);
    @Query("select phone from user")
    LiveData<List<String>> getAllAccount();
    @Query("select user.* from user where " +
            "(user.phone in (select friendlist.friendPhone from friendlist where friendlist.phone=:phone)) or" +
            "(user.phone in (select friendlist.phone from friendlist where friendlist.friendPhone=:phone))" +
            "order by user.nicname asc")
    LiveData<List<User>> findFriendsByPhone(String phone);
    @Query("select user.updatetime from user where user.phone in" +
            "(select friendlist.friendPhone from friendlist where friendlist.phone=:phone) or" +
            "(select friendlist.phone from friendlist where friendlist.friendPhone=:phone)" +
            "order by user.updatetime desc limit 1")
    long getUpdateTime(String phone);
    @Query("select updatetime from user where phone=:phone")
    long getUserUpdateTime(String phone);
}
