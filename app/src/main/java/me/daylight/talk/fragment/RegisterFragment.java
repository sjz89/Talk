package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import me.daylight.talk.R;
import me.daylight.talk.presenter.RegisterPresenter;
import me.daylight.talk.utils.DialogUtil;
import me.daylight.talk.view.RegisterView;

public class RegisterFragment extends BaseFragment<RegisterPresenter> implements RegisterView {
    @BindView(R.id.sign_up_account)
    MaterialEditText account;
    @BindView(R.id.account_input_layout)
    TextInputLayout accountInputLayout;
    @BindView(R.id.sign_up_password)
    MaterialEditText password;
    @BindView(R.id.password_input_layout)
    TextInputLayout passwordInputLayout;
    @BindView(R.id.sign_up_password_check)
    MaterialEditText checkPwd;
    @BindView(R.id.check_pwd_input_layout)
    TextInputLayout checkPwdInputLayout;
    private Unbinder unbinder;
    private QMUITipDialog tipDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_register, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @OnClick(R.id.btn_sign_up)
    public void onClick() {
        if (validInput()) {
            getPresenter().register(account.getText().toString(), password.getText().toString());
        }
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }

    @Override
    public void toLogin() {
        popBackStack();
    }

    @Override
    public void showTipDialog(int type, String msg, boolean delayDismiss) {
        tipDialog = DialogUtil.showTipDialog(getBaseFragmentActivity(), type, msg, delayDismiss);
    }

    @Override
    public void hideTipDialog() {
        tipDialog.dismiss();
    }

    private void showError(TextInputLayout textInputLayout, MaterialEditText editText, String error) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(error);
        editText.requestFocus();
    }

    private boolean validInput() {
        if (!validAccount())
            return false;
        if (password.getText().length() == 0) {
            showError(passwordInputLayout, password, "密码不能为空");
            return false;
        }
        if (!checkPwd.getText().toString().equals(password.getText().toString())) {
            showError(checkPwdInputLayout, checkPwd, "密码不一致");
            return false;
        }
        return true;
    }

    private boolean validAccount() {
        if (account.getText().length() != 11) {
            showError(accountInputLayout, account, "请输入正确的手机号码");
            return false;
        }
        return true;
    }

    @OnTextChanged(value = {R.id.sign_up_account,R.id.sign_up_password,R.id.sign_up_password_check},
            callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onTextChanged() {
        accountInputLayout.setErrorEnabled(false);
        passwordInputLayout.setErrorEnabled(false);
        checkPwdInputLayout.setErrorEnabled(false);
    }

    @Override
    public void onDestroy() {
        getBaseFragmentActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
