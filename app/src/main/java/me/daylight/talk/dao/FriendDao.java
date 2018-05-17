package me.daylight.talk.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.daylight.talk.bean.FriendList;

@Dao
public interface FriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFriend(FriendList... friend);
    @Query("select friendPhone from friendlist where phone=:phone or friendPhone=:phone")
    LiveData<List<String>> queryFriendPhoneByPhone(String phone);
    @Query("select time from friendlist where phone=:phone or friendPhone=:phone order by time desc limit 1")
    long findUpdateTimeByPhone(String phone);
}
