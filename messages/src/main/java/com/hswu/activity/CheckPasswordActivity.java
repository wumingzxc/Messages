package com.hswu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hswu.constant.Geneal;
import com.hswu.messages.R;
import com.hswu.util.MD5Util;
import com.hswu.util.PreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HandsomeWu on 2016/7/29.
 */

public class CheckPasswordActivity extends Activity {

    @BindView(R.id.editText)EditText editText;
    @BindView(R.id.image)ImageView image;
    private Unbinder unbinder;
    private boolean isFromWelcomeActivity;
    private RotateAnimation rotateAnimation;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_checkpassword);

        isFromWelcomeActivity = getIntent().getExtras().getBoolean("isFromWelcomeActivity");
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);


        unbinder = ButterKnife.bind(this);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {

                password = MD5Util.md5(editText.getText().toString());

                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                editText.setText("解密中");


                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService( Context.INPUT_METHOD_SERVICE);

                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
                image.startAnimation(rotateAnimation);
                return true;
            }
        });


        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                if (!password.equals(PreferencesUtils.getStringPreference(CheckPasswordActivity.this, Geneal.PREFERENCE,"password","12345")))
                {
                    editText.setText("请再试一次");
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (password.equals(PreferencesUtils.getStringPreference(CheckPasswordActivity.this, Geneal.PREFERENCE,"password","12345")))
                {
                    editText.setText("已解锁");

                    if (isFromWelcomeActivity)
                    {
                        Intent intent = new Intent(CheckPasswordActivity.this,HomePageActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }else{
                    editText.setText("");
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public static Bundle paramNeeded(boolean bool)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isFromWelcomeActivity", bool);
        return bundle;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
