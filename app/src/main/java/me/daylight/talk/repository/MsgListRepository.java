package me.daylight.talk.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import me.daylight.talk.app.DataBase;
import me.daylight.talk.bean.MessageList;
import me.daylight.talk.dao.MessageListDao;

public class MsgListRepository {
    private MessageListDao messageListDao;
    public MsgListRepository(Context context){
        DataBase dataBase=DataBase.getDataBase(context);
        messageListDao=dataBase.messageListDao();
    }
    public void saveMsgList(MessageList messageList){
        messageListDao.saveMessageList(messageList);
    }

    public LiveData<List<MessageList>> getMsgList(String phone){
        return messageListDao.findMsgListByPhone(phone);
    }

    public void addMsgNumber(String uniqueId){
        messageListDao.addMsgNumber(uniqueId);
    }

    public void cleanMsgNumber(String uniqueId){
        messageListDao.cleanMsgNumber(uniqueId);
    }

    public void emptyQuery(){
        messageListDao.emptyQuery();
    }

    public int getMsgNumber(String uniqueId){
        return messageListDao.getMsgNumber(uniqueId);
    }
}
