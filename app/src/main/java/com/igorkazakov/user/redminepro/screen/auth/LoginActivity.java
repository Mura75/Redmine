package com.igorkazakov.user.redminepro.screen.auth;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.igorkazakov.user.redminepro.R;
import com.igorkazakov.user.redminepro.screen.base.BaseViewInterface;
import com.igorkazakov.user.redminepro.screen.base.LoadingDialog;
import com.igorkazakov.user.redminepro.screen.main.MainActivity;
import com.igorkazakov.user.redminepro.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @BindView(R.id.email)
    EditText mEmailView;

    @BindView(R.id.password)
    EditText mPasswordView;

    private BaseViewInterface mLoadingView;

    @InjectPresenter
    public LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        mPresenter.init();
    }

    @OnClick(R.id.email_sign_in_button)
    public void loginButtonClick() {
        String login = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        mPresenter.tryLogin(login, password);
    }

    @OnCheckedChanged(R.id.switchRememberMe)
    public void switchRememberMeClick(Switch switchRememberMe) {
        mPresenter.saveSwitchState(switchRememberMe.isChecked());
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    public void showLoginError() {
        mEmailView.setError(getString(R.string.error_invalid_email));
    }

    @Override
    public void showPasswordError() {
        mPasswordView.setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
    }

    @Override
    public void openDashboardScreen() {

        KeyboardUtils.hideKeyboard(this);
        MainActivity.start(this);
        finish();
    }
}

