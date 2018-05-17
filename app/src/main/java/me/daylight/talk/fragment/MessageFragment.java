package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
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
import me.daylight.talk.presenter.MsgListPresenter;
import me.daylight.talk.view.MsgListView;

public class MessageFragment extends BaseFragment<MsgListPresenter> implements MsgListView {
    @BindView(R.id.topbar_message)
    QMUITopBarLayout topBar;
    @BindView(R.id.recycler_message)
    RecyclerView recyclerView;

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_message, null);
        ButterKnife.bind(this,view);
        initView();
        getPresenter().loadMessageList();
        return view;
    }

    private void initView(){
        topBar.setTitle(R.string.title_message);
    }

    @Override
    protected MsgListPresenter createPresenter() {
        return new MsgListPresenter();
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
    public void toChatFragment() {
        startFragment(new ChatFragment());
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

}
