package me.daylight.talk.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.daylight.talk.bean.ChatMessage;

@Dao
public interface ChatMessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ChatMessage... chatMessage);
    @Query("select * from chatmessage where (sendPhone=:phone and receivePhone=:friendPhone and msgType=1)" +
            "or (receivePhone=:phone and sendPhone=:friendPhone and msgType=2) order by time")
    LiveData<List<ChatMessage>> queryChatMsgByPhoneAndFriendPhone(String phone,String friendPhone);
}
