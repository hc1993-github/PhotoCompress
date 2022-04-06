package com.supore.photocompress.model;

import com.supore.photocompress.listener.LoginListener;

public class LoginModel {
    public void login(String text, LoginListener listener) {
        if("123".equals(text)){
            listener.Success("success");
        }else {
            listener.Fail("fail");
        }
    }
}
