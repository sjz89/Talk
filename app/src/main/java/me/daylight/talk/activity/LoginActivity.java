package me.daylight.talk.activity;

import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import me.daylight.talk.R;
import me.daylight.talk.fragment.BaseFragment;
import me.daylight.talk.fragment.LoginFragment;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class LoginActivity extends QMUIFragmentActivity {
    @Override
    protected int getContextViewId() {
        return R.id.login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteStudioService.instance().start(this);
        if (savedInstanceState==null){
            BaseFragment fragment=new LoginFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(),fragment,fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
