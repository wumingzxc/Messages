package com.hswu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hswu.constant.Geneal;
import com.hswu.messages.R;
import com.hswu.util.MD5Util;
import com.hswu.util.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HandsomeWu on 2016/7/29.
 */

public class MakeMasterPasswordActivity extends Activity {

    @BindView(R.id.editText_one)EditText editText_one;
    @BindView(R.id.editText_two)EditText editText_two;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_masterpassword);

        unbinder = ButterKnife.bind(this);


    }

    @OnClick(R.id.button_commit)
    void click(View view)
    {
        String password = MD5Util.md5(editText_one.getText().toString());
        String password_again = MD5Util.md5(editText_two.getText().toString());

        if (!password.equals(password_again))
        {
            Toast.makeText(MakeMasterPasswordActivity.this,"输入密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }

        PreferencesUtils.setStringPreferences(MakeMasterPasswordActivity.this, Geneal.PREFERENCE,"password",password);
        Intent intent = new Intent(MakeMasterPasswordActivity.this,HomePageActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
