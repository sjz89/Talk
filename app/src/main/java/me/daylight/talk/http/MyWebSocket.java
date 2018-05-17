package me.daylight.talk.http;

import android.util.Log;

import com.google.gson.Gson;

import me.daylight.talk.app.MyApplication;
import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.bean.MessageList;
import me.daylight.talk.repository.ChatMsgRepository;
import me.daylight.talk.repository.MsgListRepository;
import me.daylight.talk.repository.UserRepository;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MyWebSocket extends WebSocketListener {
    private static MyWebSocket mInstance;
    private WebSocket webSocket;
    private ChatMsgRepository chatMsgRepository;
    private MsgListRepository msgListRepository;
    private UserRepository userRepository;
    private String phone;

    private MyWebSocket() {
        chatMsgRepository = new ChatMsgRepository(MyApplication.getApplication().getApplicationContext());
        msgListRepository = new MsgListRepository(MyApplication.getApplication().getApplicationContext());
        userRepository = new UserRepository(MyApplication.getApplication().getApplicationContext());
        phone=SharedPreferencesUtil.getString(MyApplication.getApplication().getApplicationContext(),
                GlobalField.USER,GlobalField.ACCOUNT);
    }

    public static MyWebSocket getInstance() {
        if (mInstance == null) {
            synchronized (MyWebSocket.class) {
                if (mInstance == null) {
                    mInstance = new MyWebSocket();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .build();
        String url="ws://"+SharedPreferencesUtil.getString(MyApplication.getApplication().getApplicationContext(),
                GlobalField.SETTING,GlobalField.URL)+"/webSocketService?phone="+phone;
        Request request = new Request.Builder()
                .url(url)
                .build();
        webSocket=client.newWebSocket(request, this);
        client.dispatcher().executorService().shutdown();
    }

    public void sendMessage(String msg) {
        Log.d("sendMessage",""+webSocket.send(msg));
    }

    public void connect(){
        if (webSocket!=null) {
            webSocket.close(1000, null);
            return;
        }
        init();
    }

    public void disconnect() {
        mInstance = null;
        if (webSocket != null)
            webSocket.close(1000, "close");
    }

    private void handleMessage(String msg) {
        Gson gson = new Gson();
        ChatMessage chatMessage = gson.fromJson(msg, ChatMessage.class);
        chatMessage.setMsgType(GlobalField.MessageType_Receive);
        chatMsgRepository.insertChatMsg(chatMessage);
        MessageList messageList = new MessageList();
        messageList.setPhone(chatMessage.getReceivePhone());
        messageList.setFriendPhone(chatMessage.getSendPhone());
        messageList.setUniqueId(chatMessage.getReceivePhone() + chatMessage.getSendPhone());
        messageList.setTime(chatMessage.getTime());
        messageList.setLastMsg(chatMessage.getContext());
        messageList.setFriendNicName(userRepository.loadUserByPhone(chatMessage.getSendPhone()).getNicname());
        messageList.setMsgNumber(msgListRepository.getMsgNumber(chatMessage.getReceivePhone() + chatMessage.getSendPhone()));
        msgListRepository.saveMsgList(messageList);
        msgListRepository.addMsgNumber(messageList.getUniqueId());
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        Log.d("onOpen","");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        handleMessage(text);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Log.d("onClosing", reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.d("onClosed", reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.d("onFailure", t+"");
        init();
    }

}
