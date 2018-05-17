package me.daylight.talk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

import java.lang.ref.WeakReference;

import me.daylight.talk.presenter.BasePresenter;
import me.daylight.talk.view.BaseView;

public abstract class BaseFragment<P extends BasePresenter> extends QMUIFragment implements BaseView{
    private P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
        if (mPresenter != null) {
            mPresenter.attach(new WeakReference(this));
        }
    }

    // 由子类去实现创建
    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter==null) return;
        mPresenter.detach();
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }

    public P getPresenter() {
        return mPresenter;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

}
