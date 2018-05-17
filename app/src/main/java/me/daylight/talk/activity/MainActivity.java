package me.daylight.talk.activity;

import android.content.IntentFilter;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import me.daylight.talk.R;
import me.daylight.talk.fragment.BaseFragment;
import me.daylight.talk.fragment.MainFragment;
import me.daylight.talk.http.MyWebSocket;
import me.daylight.talk.utils.NetWorkStateReceiver;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class MainActivity extends QMUIFragmentActivity {
    private NetWorkStateReceiver netWorkStateReceiver;

    @Override
    protected int getContextViewId() {
        return R.id.main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){
            BaseFragment fragment=new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(),fragment,fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(netWorkStateReceiver,filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        SQLiteStudioService.instance().stop();
        MyWebSocket.getInstance().disconnect();
        super.onDestroy();
    }
}
