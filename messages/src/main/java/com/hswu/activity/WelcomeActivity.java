package com.hswu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.hswu.constant.Geneal;
import com.hswu.messages.R;
import com.hswu.util.ActivityController;
import com.hswu.util.PreferencesUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HandsomeWu on 2016/7/29.
 */

public class WelcomeActivity extends Activity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        ActivityController.addActivity(this);

        unbinder = ButterKnife.bind(this);

        if (PreferencesUtils.hasContainKey(this,Geneal.PREFERENCE,"password"))
        {
            Bundle bundle = CheckPasswordActivity.paramNeeded(true);
            Intent intent = new Intent(this,CheckPasswordActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }


    @OnClick(R.id.button)
    void click(View view)
    {
        Intent intent = new Intent(this,MakeMasterPasswordActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
        unbinder.unbind();
    }
}
