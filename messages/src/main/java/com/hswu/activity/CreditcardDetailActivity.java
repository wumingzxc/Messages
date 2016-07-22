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

public class CreditcardDetailActivity extends Activity implements OnClickListener {

    private ImageView iv_save;
    private TextView tv_save;
    private ImageView iv_cancel;
    private TextView tv_cancel;
    private EditText et_bankname;
    private EditText et_cardname;
    private EditText et_cardnumber;
    private EditText et_cvv;
    private EditText et_indate;
    private EditText et_limit;
    private DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creditcard_detail);

        //TODO 测试
        init();
        setListener();
    }

    private void init()
    {
        dbAdapter = DatabaseAdapter.getInstance(this);
        iv_save = findView(R.id.iv_save);
        tv_save = findView(R.id.tv_save);
        iv_cancel= findView(R.id.iv_cancel);
        tv_cancel= findView(R.id.tv_cancel);
        et_bankname= findView(R.id.et_bankname);
        et_cardname= findView(R.id.et_cardname);
        et_cardnumber= findView(R.id.et_cardnumber);
        et_cvv= findView(R.id.et_cvv);
        et_indate= findView(R.id.et_indate);
        et_limit= findView(R.id.et_limit);
    }


    private void setListener()
    {
        iv_save.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        iv_cancel.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    private CreditCard getCreditCard()
    {
        CreditCard card = new CreditCard(et_bankname.getText().toString(), et_cardname.getText().toString(),
                et_cardnumber.getText().toString(), et_cvv.getText().toString(), et_indate.getText().toString(), et_limit.getText().toString());

        return card;
    }


    @SuppressWarnings("unchecked")
    private <T extends View> T findView(int id) {
        return (T) this.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_cancel || v.getId() == R.id.tv_cancel) {
            CreditcardDetailActivity.this.finish();
        }

        if (v.getId() == R.id.iv_save || v.getId() == R.id.tv_save) {

            dbAdapter.insertData(URIField.CREDITCARD_URI, GetContentValues.getContentValues(getCreditCard()));
            setResult(2);
            CreditcardDetailActivity.this.finish();
        }

    }
}
