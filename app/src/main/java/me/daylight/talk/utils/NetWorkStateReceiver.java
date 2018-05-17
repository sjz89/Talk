package me.daylight.talk.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Objects;

import me.daylight.talk.http.MyWebSocket;

public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("NetWorkState","网络状态发生变化");
        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取ConnectivityManager对象对应的NetworkInfo对象
        //获取WIFI连接的信息
        NetworkInfo wifiNetworkInfo = Objects.requireNonNull(connMgr).getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //获取移动数据连接的信息
        NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
            Log.d("NetWorkState","WIFI&MOBILE");
            MyWebSocket.getInstance().connect();
        } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
            Log.d("NetWorkState","WIFI");
            MyWebSocket.getInstance().connect();
        } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
            Log.d("NetWorkState","MOBILE");
            MyWebSocket.getInstance().connect();
        }
    }
}