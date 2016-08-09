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
import android.widget.TextView;

import com.hswu.adapter.BaseBeanListItemAdapter;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Note;
import com.hswu.constant.Geneal;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.rowmapper.CreditCardRowMapper;
import com.hswu.rowmapper.NoteRowMapper;
import com.hswu.util.URIField;

import java.util.List;

public class ShowNotesActivity extends Activity implements OnClickListener {

    private List<Note> notes;
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

        myAdapter = new BaseBeanListItemAdapter(this,  notes);
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
        ((TextView)findViewById(R.id.tv)).setText("安全备注");

        notes = (List<Note>) dbAdapter.queryDatas(URIField.SAFEREMARK_URI,new NoteRowMapper());

    }


    private void setListener() {
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Note note = notes.get(position);
                myAdapter.setSelectedPosition(position);
                myAdapter.notifyDataSetChanged();
                Intent i = new Intent(ShowNotesActivity.this, ShowNoteDetailActivity.class);
                Bundle bundle = ShowNoteDetailActivity.paramNeeded(note);
                i.putExtras(bundle);
                startActivity(i);
            }
        });


    }

    private BroadcastReceiver updataBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            List<Note> newNotes;
            newNotes = (List<Note>) dbAdapter.queryDatas(URIField.SAFEREMARK_URI,new NoteRowMapper());
            notes.clear();
            notes.addAll(newNotes);
            myAdapter.notifyDataSetChanged();
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(ShowNotesActivity.this, UpdateOrAddNoteActivity.class);
                Bundle bundle = UpdateOrAddCreditcardActivity.paramNeeded(false,null);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

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
