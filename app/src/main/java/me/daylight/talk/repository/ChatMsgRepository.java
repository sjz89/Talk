package me.daylight.talk.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import me.daylight.talk.app.DataBase;
import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.dao.ChatMessageDao;

public class ChatMsgRepository {
    private ChatMessageDao chatMessageDao;

    public ChatMsgRepository(Context context) {
        chatMessageDao = DataBase.getDataBase(context).chatMessageDao();
    }

    public LiveData<List<ChatMessage>> queryChatHistoryByPhone(String phone, String friendPhone) {
        return chatMessageDao.queryChatMsgByPhoneAndFriendPhone(phone, friendPhone);
    }

    public void insertChatMsg(ChatMessage chatMessage) {
        chatMessageDao.insert(chatMessage);
    }
}
