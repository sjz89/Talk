package me.daylight.talk.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.bean.MessageList;
import me.daylight.talk.bean.User;
import me.daylight.talk.repository.ChatMsgRepository;
import me.daylight.talk.repository.MsgListRepository;

public class ChatViewModel extends AndroidViewModel {
    private User friend;
    private ChatMsgRepository chatMsgRepository;
    private MsgListRepository msgListRepository;
    private LiveData<List<ChatMessage>> chatMessage;
    private LiveData<List<MessageList>> messageList;

    public ChatViewModel(@NonNull Application application) {
        super(application);
        chatMsgRepository = new ChatMsgRepository(application);
        msgListRepository = new MsgListRepository(application);
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public void initChatMessage(String phone) {
        chatMessage = chatMsgRepository.queryChatHistoryByPhone(phone, friend.getPhone());
    }

    public LiveData<List<ChatMessage>> getChatMessage() {
        return chatMessage;
    }

    public void insertChatMsg(ChatMessage chatMessage) {
        chatMsgRepository.insertChatMsg(chatMessage);
    }

    public void saveMsgList(MessageList messageList){
        msgListRepository.saveMsgList(messageList);
    }

    public void loadMsgList(String phone){
        messageList=msgListRepository.getMsgList(phone);
    }

    public LiveData<List<MessageList>> getMessageList(){
        return messageList;
    }

    public void cleanMsgNumber(String uniqueId){
        msgListRepository.cleanMsgNumber(uniqueId);
    }

    public void emptyQuery(){
        msgListRepository.emptyQuery();
    }
}
