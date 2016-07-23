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

import com.hswu.adapter.BaseBeanListItemAdapter;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Note;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
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

    }

    private void init() {
        dbAdapter = DatabaseAdapter.getInstance(this);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        listview = (ListView) findViewById(R.id.listview);
        iv_add = (ImageView) findViewById(R.id.iv_add);

        notes = (List<Note>) dbAdapter.queryData(URIField.SAFEREMARK_URI,new NoteRowMapper());

    }


    private void setListener() {
        iv_back.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        listview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Note note = notes.get(position);
                Intent i = new Intent(ShowNotesActivity.this, ShowNoteDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("note", note);
                i.putExtras(bundle);
                startActivityForResult(i, 0);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && (resultCode == 1 || resultCode == 2)) {
            List<Note> newNotes;
            newNotes = (List<Note>) dbAdapter.queryData(URIField.SAFEREMARK_URI,new NoteRowMapper());
            notes.clear();
            notes.addAll(newNotes);
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
                Intent intent = new Intent(ShowNotesActivity.this, UpdateOrAddNoteActivity.class);
                Bundle bundle = UpdateOrAddCreditcardActivity.paramNeeded(false,null);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
                break;
        }
    }

}
