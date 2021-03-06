package com.hswu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.hswu.bean.CreditCard;
import com.hswu.constant.Geneal;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.util.GetContentValues;
import com.hswu.util.URIField;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UpdateOrAddCreditcardActivity extends Activity {


    @BindView(R.id.et_bankname) EditText et_bankname;
    @BindView(R.id.et_cardname) EditText et_cardname;
    @BindView(R.id.et_cardnumber) EditText et_cardnumber;
    @BindView(R.id.et_cvv) EditText et_cvv;
    @BindView(R.id.et_indate) EditText et_indate;
    @BindView(R.id.et_limit) EditText et_limit;
    private CreditCard card;
    private boolean isUpdate;
    private DatabaseAdapter dbAdapter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.creditcard_detail_deit);
        unbinder = ButterKnife.bind(this);
        dbAdapter = DatabaseAdapter.getInstance(this);

        isUpdate = getIntent().getExtras().getBoolean("isUpdate");
        card =(CreditCard) getIntent().getExtras().getParcelable("card");

        if (isUpdate)
        {
            setText(card.getBankName(),card.getCardName(),card.getCardNumber(),card.getCvv2(),card.getIndate(),card.getLimit());
        }



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
            UpdateOrAddCreditcardActivity.this.finish();
            return;
        }

        if (v.getId() == R.id.iv_save || v.getId() == R.id.tv_save) {

            if (isUpdate)
            {
                CreditCard newCard = getCreditCard();
                dbAdapter.updateData(URIField.CREDITCARD_URI, GetContentValues.getContentValues(newCard), "id = ?", new String[]{card.getId()+""});
            }else
            {
                dbAdapter.insertData(URIField.CREDITCARD_URI, GetContentValues.getContentValues(getCreditCard()));
            }
            Intent intent = new Intent(Geneal.ACTION_DATA_CHANGE);
            sendBroadcast(intent);
            UpdateOrAddCreditcardActivity.this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public static Bundle paramNeeded( boolean isUpdate, Parcelable card)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isUpdate",isUpdate);
        bundle.putParcelable("card", card);
        return bundle;
    }

    private void setText(String bankName,String cardName,String cardNum,String cvv,String indate,String limit)
    {
        et_bankname.setText(bankName);
        et_cardname.setText(cardName);
        et_cardnumber.setText(cardNum);
        et_cvv.setText(cvv);
        et_indate.setText(indate);
        et_limit.setText(limit);
    }
}
