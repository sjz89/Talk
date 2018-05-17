package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.daylight.talk.R;
import me.daylight.talk.presenter.ChatPresenter;
import me.daylight.talk.view.ChatView;

public class ChatFragment extends BaseFragment<ChatPresenter> implements ChatView{
    @BindView(R.id.recycler_chat) RecyclerView recyclerView;
    @BindView(R.id.topbar_chat) QMUITopBarLayout topBar;
    @BindView(R.id.chat_editor) EditText text;

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_chat, null);
        ButterKnife.bind(this,view);
        getPresenter().initTopBar();
        getPresenter().loadChatHistory();
        return view;
    }

    @Override
    protected ChatPresenter createPresenter() {
        return new ChatPresenter();
    }

    @Override
    public void initTopBar(String nicName) {
        topBar.setTitle(nicName);
        topBar.addLeftBackImageButton().setOnClickListener(v -> popBackStack());
    }

    @Override
    public RecyclerView initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    @Override
    public void toInfoFragment() {
        startFragment(new InfoFragment());
    }

    @OnClick(R.id.chat_send)
    public void onSendClick() {
        getPresenter().sendMessage(text.getText().toString());
        text.setText("");
    }

    @Override
    protected void popBackStack() {
        super.popBackStack();
        getPresenter().cleanMsgNumber();
    }
}
