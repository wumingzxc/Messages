package com.hswu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hswu.bean.CreditCard;
import com.hswu.bean.Note;
import com.hswu.constant.Geneal;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.util.GetContentValues;
import com.hswu.util.URIField;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UpdateOrAddNoteActivity extends Activity {


    @BindView(R.id.et_notename) EditText et_notename;
    @BindView(R.id.et_notecontent) EditText et_notecontent;

    private boolean isUpdate;
    private Note note;
    private DatabaseAdapter dbAdapter;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.note_detail_deit);
        unbinder = ButterKnife.bind(this);
        dbAdapter = DatabaseAdapter.getInstance(this);

        isUpdate = getIntent().getExtras().getBoolean("isUpdate");
        note =(Note) getIntent().getExtras().getParcelable("note");

        if (isUpdate)
        {
            setText(note.getNoteName(),note.getNoteContent());
        }
    }

    private Note getNote()
    {
        Note note = new Note(et_notename.getText().toString(),et_notecontent.getText().toString());
        return note;
    }

    @OnClick({R.id.iv_save,R.id.tv_save,R.id.iv_cancel,R.id.tv_cancel})
    void saveOrCancel(View v)
    {
        if (v.getId() == R.id.iv_cancel || v.getId() == R.id.tv_cancel) {
            UpdateOrAddNoteActivity.this.finish();
            return;
        }

        if (v.getId() == R.id.iv_save || v.getId() == R.id.tv_save) {

            if (isUpdate)
            {
                Note newNote = getNote();
                dbAdapter.updateData(URIField.SAFEREMARK_URI, GetContentValues.getContentValues(newNote), "id = ?", new String[]{note.getId()+""});
            }else
            {
                dbAdapter.insertData(URIField.SAFEREMARK_URI, GetContentValues.getContentValues(getNote()));
            }
            Intent intent = new Intent(Geneal.ACTION_DATA_CHANGE);
            sendBroadcast(intent);
            UpdateOrAddNoteActivity.this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    public static Bundle paramNeeded( boolean isUpdate, Parcelable note)
    {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isUpdate",isUpdate);
        bundle.putParcelable("note", note);
        return bundle;
    }


    private void setText(String noteName,String noteContent)
    {
        et_notename.setText(noteName);
        et_notecontent.setText(noteContent);
    }
}
