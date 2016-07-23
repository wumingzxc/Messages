package com.hswu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.hswu.adapter.CreditcardListItemAdapter;
import com.hswu.bean.CreditCard;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.util.URIField;

import java.util.List;

public class ShowCreditCardsActivity extends Activity implements OnClickListener {

    private List<CreditCard> cards;
    private DatabaseAdapter dbAdapter;
    private ImageView iv_back;
    private ListView listview;
    private ImageView iv_add;
    private CreditcardListItemAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_creditcards_show);
        init();

        myAdapter = new CreditcardListItemAdapter(this, (List<CreditCard>) cards);
        listview.setAdapter(myAdapter);

        setListener();

    }

    private void init() {
        dbAdapter = DatabaseAdapter.getInstance(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        listview = (ListView) findViewById(R.id.listview);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        cards = getIntent().getBundleExtra("bundle").getParcelableArrayList("cards");

    }


    private void setListener() {
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CreditCard card = cards.get(position);
                Intent i = new Intent(ShowCreditCardsActivity.this, ShowCreditcardDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("card", card);
                i.putExtras(bundle);
                startActivityForResult(i, 0);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && (resultCode == 1 || resultCode == 2)) {
            List<CreditCard> newcards;
            newcards = (List<CreditCard>) dbAdapter.queryData(URIField.CREDITCARD_URI);
            cards.clear();
            cards.addAll(newcards);
            myAdapter.notifyDataSetChanged();
        }
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
                startActivityForResult(intent, 0);
                break;
        }
    }

}
