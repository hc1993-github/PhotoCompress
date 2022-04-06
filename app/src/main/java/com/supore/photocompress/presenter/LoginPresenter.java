package com.supore.photocompress.presenter;

import android.content.Context;

import com.supore.photocompress.FourActivity;
import com.supore.photocompress.listener.LoginListener;
import com.supore.photocompress.model.LoginModel;

public class LoginPresenter implements LoginListener {
    FourActivity context;
    LoginModel model;
    public LoginPresenter(Context context) {
        this.context = (FourActivity) context;
        model = new LoginModel();
    }

    public void login(String text) {
        model.login(text,this);
    }

    @Override
    public void Success(String text) {
        context.success(text);
    }

    @Override
    public void Fail(String text) {
        context.fail(text);
    }
}
