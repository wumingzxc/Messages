package com.hswu.activity;

import com.hswu.bean.CreditCard;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.util.GetContentValues;
import com.hswu.util.URIField;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddCreditcardActivity extends Activity {


    @BindView(R.id.iv_save) ImageView iv_save;
    @BindView(R.id.tv_save) TextView tv_save;
    @BindView(R.id.iv_cancel) ImageView iv_cancel;
    @BindView(R.id.tv_cancel) TextView tv_cancel;
    @BindView(R.id.et_bankname) EditText et_bankname;
    @BindView(R.id.et_cardname) EditText et_cardname;
    @BindView(R.id.et_cardnumber) EditText et_cardnumber;
    @BindView(R.id.et_cvv) EditText et_cvv;
    @BindView(R.id.et_indate) EditText et_indate;
    @BindView(R.id.et_limit) EditText et_limit;
    private DatabaseAdapter dbAdapter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creditcard_detail_deit);
        unbinder = ButterKnife.bind(this);
        dbAdapter = DatabaseAdapter.getInstance(this);

    }



    private CreditCard getCreditCard()
    {
        CreditCard card = new CreditCard(et_bankname.getText().toString(), et_cardname.getText().toString(),
                et_cardnumber.getText().toString(), et_cvv.getText().toString(), et_indate.getText().toString(), et_limit.getText().toString());

        return card;
    }

    @OnClick({R.id.iv_save,R.id.tv_save,R.id.iv_cancel,R.id.tv_cancel})
    void saveOrCancel(View v)
    {
        if (v.getId() == R.id.iv_cancel || v.getId() == R.id.tv_cancel) {
            AddCreditcardActivity.this.finish();
        }

        if (v.getId() == R.id.iv_save || v.getId() == R.id.tv_save) {

            dbAdapter.insertData(URIField.CREDITCARD_URI, GetContentValues.getContentValues(getCreditCard()));
            setResult(2);
            AddCreditcardActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
