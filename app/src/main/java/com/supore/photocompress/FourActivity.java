package com.supore.photocompress;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.supore.photocompress.presenter.LoginPresenter;

public class FourActivity extends AppCompatActivity implements View.OnClickListener {
    LoginPresenter presenter;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        presenter = new LoginPresenter(this);
        editText = findViewById(R.id.edittext);
        findViewById(R.id.login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        presenter.login(editText.getText().toString());
    }
    public void success(String text){
        Toast.makeText(FourActivity.this,text,Toast.LENGTH_SHORT).show();
    }
    public void fail(String text){
        Toast.makeText(FourActivity.this,text,Toast.LENGTH_SHORT).show();
    }
}