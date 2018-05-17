package me.daylight.talk.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import java.util.Date;
import java.util.List;

import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.bean.MessageList;
import me.daylight.talk.bean.User;
import me.daylight.talk.http.MyWebSocket;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.viewmodel.ChatViewModel;
import me.daylight.talk.viewmodel.UserViewModel;

public class ChatModel extends BaseModel {
    private ChatViewModel chatViewModel;
    private UserViewModel userViewModel;
    private String phone;

    @Override
    public void init(){
        chatViewModel= ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(ChatViewModel.class);
        userViewModel= ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(UserViewModel.class);
        phone=SharedPreferencesUtil.getString(getContext(), GlobalField.USER,GlobalField.ACCOUNT);
        chatViewModel.initChatMessage(phone);
    }

    public String getFriendNicName() {
        return chatViewModel.getFriend().getNicname();
    }

    public void sendMessage(String message) {
        ChatMessage chatMessage=new ChatMessage();
        chatMessage.setSendPhone(phone);
        chatMessage.setReceivePhone(chatViewModel.getFriend().getPhone());
        chatMessage.setMsgType(GlobalField.MessageType_Send);
        chatMessage.setContext(message);
        chatMessage.setTime(new Date().getTime());
        chatViewModel.insertChatMsg(chatMessage);
        Gson gson=new Gson();
        MyWebSocket.getInstance().sendMessage(gson.toJson(chatMessage));
        MessageList messageList=new MessageList();
        messageList.setPhone(phone);
        messageList.setFriendPhone(chatViewModel.getFriend().getPhone());
        messageList.setLastMsg(message);
        messageList.setTime(chatMessage.getTime());
        messageList.setUniqueId(phone+chatViewModel.getFriend().getPhone());
        messageList.setFriendNicName(chatViewModel.getFriend().getNicname());
        chatViewModel.saveMsgList(messageList);
    }

    public LiveData<List<ChatMessage>> loadChatHistory() {
        return chatViewModel.getChatMessage();
    }

    public User getFriend() {
        return chatViewModel.getFriend();
    }

    public User getUser() {
        return userViewModel.loadUserByPhone(phone);
    }

    public void cleanMsgNumber() {
        chatViewModel.cleanMsgNumber(phone+chatViewModel.getFriend().getPhone());
    }

    public void setFriendPhone(){
        userViewModel.setUserPhone(chatViewModel.getFriend().getPhone());
    }
}
