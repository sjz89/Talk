package me.daylight.talk.presenter;

import android.support.v7.widget.RecyclerView;

import com.qmuiteam.qmui.util.QMUIKeyboardHelper;

import java.util.ArrayList;
import java.util.List;

import me.daylight.talk.adapter.ChatAdapter;
import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.model.ChatModel;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.view.ChatView;

public class ChatPresenter extends BasePresenter<ChatView,ChatModel> {
    private List<ChatMessage> chatMessageList;

    public ChatPresenter(){
        chatMessageList=new ArrayList<>();
    }

    public void initTopBar() {
        getView().initTopBar(getModel().getFriendNicName());
    }

    public void loadChatHistory() {
        ChatAdapter adapter=new ChatAdapter(getView().getCurContext(),
                GlobalField.url+"user/headImage/"+getModel().getUser().getPhone()+"?time="+getModel().getUser().getUpdatetime(),
                GlobalField.url+"user/headImage/"+getModel().getFriend().getPhone()+"?time="+getModel().getFriend().getUpdatetime());
        adapter.setOnFriendAvatarClickListener((view,position)->{
            getModel().setFriendPhone();
            getView().toInfoFragment();
        });
        RecyclerView recyclerView=getView().initRecyclerView();
        getModel().loadChatHistory().observe(getView().getCurContext(),chatMessages -> {
            if (chatMessages!=null&&chatMessages.size()!=0){
                chatMessageList=chatMessages;
                adapter.setData(chatMessages);
                recyclerView.scrollToPosition(chatMessages.size()-1);
            }
        });

        QMUIKeyboardHelper.setVisibilityEventListener(getView().getCurContext(), isOpen -> {
            if (isOpen)
                recyclerView.scrollToPosition(chatMessageList.size()-1);
        });
        recyclerView.setAdapter(adapter);
    }

    public void sendMessage(String message) {
        getModel().sendMessage(message);
    }

    public void cleanMsgNumber() {
        getModel().cleanMsgNumber();
    }
}
