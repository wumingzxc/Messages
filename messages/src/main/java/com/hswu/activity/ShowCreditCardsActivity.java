package com.hswu.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.hswu.adapter.BaseBeanListItemAdapter;
import com.hswu.bean.CreditCard;
import com.hswu.constant.Geneal;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.rowmapper.CreditCardRowMapper;
import com.hswu.util.URIField;

import java.util.List;

public class ShowCreditCardsActivity extends Activity implements OnClickListener {

    private List<CreditCard> cards;
    private DatabaseAdapter dbAdapter;
    private ImageView iv_back;
    private ListView listview;
    private ImageView iv_add;
    private BaseBeanListItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_basebeans_show);
        init();

        myAdapter = new BaseBeanListItemAdapter(this,cards);
        listview.setAdapter(myAdapter);

        setListener();
        IntentFilter filter = new IntentFilter(Geneal.ACTION_DATA_CHANGE);
        registerReceiver(updataBroadcastReceiver, filter);

    }

    private void init() {
        dbAdapter = DatabaseAdapter.getInstance(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        listview = (ListView) findViewById(R.id.listview);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        cards = (List<CreditCard>) dbAdapter.queryDatas(URIField.CREDITCARD_URI,new CreditCardRowMapper());

    }


    private void setListener() {
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CreditCard card = cards.get(position);
                myAdapter.setSelectedPosition(position);
                myAdapter.notifyDataSetChanged();
                Intent i = new Intent(ShowCreditCardsActivity.this, ShowCreditcardDetailActivity.class);
                Bundle bundle = ShowCreditcardDetailActivity.paramNeeded(card);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(ShowCreditCardsActivity.this, UpdateOrAddCreditcardActivity.class);
                Bundle bundle = UpdateOrAddCreditcardActivity.paramNeeded(false,null);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private BroadcastReceiver updataBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            List<CreditCard> newcards;
            newcards = (List<CreditCard>) dbAdapter.queryDatas(URIField.CREDITCARD_URI,new CreditCardRowMapper());
            cards.clear();
            cards.addAll(newcards);
            myAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(updataBroadcastReceiver);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        myAdapter.setSelectedPosition(-1);
        myAdapter.notifyDataSetChanged();
    }
}
