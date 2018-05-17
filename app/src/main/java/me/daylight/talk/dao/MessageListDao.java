package me.daylight.talk.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import me.daylight.talk.bean.MessageList;

@Dao
public interface MessageListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMessageList(MessageList... messageLists);
    @Query("select * from messagelist where phone=:phone order by time desc")
    LiveData<List<MessageList>> findMsgListByPhone(String phone);
    @Query("update messagelist set msgNumber=msgNumber+1 where uniqueId=:uniqueId")
    void addMsgNumber(String uniqueId);
    @Query("update messagelist set msgNumber=0 where uniqueId=:uniqueId")
    void cleanMsgNumber(String uniqueId);
    @Query("select msgNumber from messagelist where uniqueId=:uniqueId")
    int getMsgNumber(String uniqueId);
    @Query("update messagelist set msgNumber=0 where msgNumber=0")
    void emptyQuery();
}
