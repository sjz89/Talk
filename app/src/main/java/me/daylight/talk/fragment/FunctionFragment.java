package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.daylight.talk.R;
import me.daylight.talk.customview.GroupListView;
import me.daylight.talk.presenter.FunctionPresenter;
import me.daylight.talk.view.FunctionView;

public class FunctionFragment extends BaseFragment<FunctionPresenter> implements FunctionView {
    @BindView(R.id.topbar_more)
    QMUITopBarLayout topBar;
    @BindView(R.id.groupList_more)
    GroupListView groupListMore;

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_more, null);
        ButterKnife.bind(this, view);
        initView();
        getPresenter().initGroupListView(groupListMore);
        return view;
    }

    private void initView() {
        topBar.setTitle(R.string.title_more);
    }

    @Override
    public void toInfoFragment() {
        startFragment(new InfoFragment());
    }

    @Override
    protected FunctionPresenter createPresenter() {
        return new FunctionPresenter();
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

}
