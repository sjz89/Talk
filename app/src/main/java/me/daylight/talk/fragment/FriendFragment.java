package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.daylight.talk.R;
import me.daylight.talk.adapter.CommonAdapter;
import me.daylight.talk.presenter.FriendPresenter;
import me.daylight.talk.view.FriendView;

public class FriendFragment extends BaseFragment<FriendPresenter> implements FriendView {
    @BindView(R.id.topbar_friend) QMUITopBarLayout topBar;
    @BindView(R.id.recycler_friend) RecyclerView recyclerView;
    @BindView(R.id.refresh_friend) SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_friend, null);
        ButterKnife.bind(this,view);
        initView();
        getPresenter().initFriendList();
        return view;
    }

    @Override
    protected FriendPresenter createPresenter() {
        return new FriendPresenter();
    }

    private void initView() {
        topBar.setTitle(R.string.title_friend);
        topBar.addRightImageButton(R.drawable.ic_add,R.id.add).setOnClickListener(v -> startFragment(new NewFriendFragment()));
        swipeRefreshLayout.setColorSchemeResources(R.color.aqua,R.color.grass,R.color.grapefruit);
        swipeRefreshLayout.setOnRefreshListener(()-> new Handler().postDelayed(()->getPresenter().swipeToRefresh(),1500));
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }

    @Override
    public void initRecyclerView(CommonAdapter adapter) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(getBaseFragmentActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getBaseFragmentActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void toChatFragment() {
        startFragment(new ChatFragment());
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}
