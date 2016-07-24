package com.hswu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.hswu.bean.CreditCard;
import com.hswu.bean.Favorite;
import com.hswu.bean.Note;
import com.hswu.constant.Geneal;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.rowmapper.FavoriteRowMapper;
import com.hswu.util.GetContentValues;
import com.hswu.util.URIField;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShowNoteDetailActivity extends Activity{

	@BindView(R.id.cb_favorite) CheckBox cb_favorite;
	@BindView(R.id.tv_notename) TextView tv_notename;
	@BindView(R.id.tv_notecontent) TextView tv_notecontent;
	@BindView(R.id.imageview_right) ImageView imageview_right;

	private Note note;
	private PopupMenu popupMenu;
	private DatabaseAdapter dbAdapter;
	private Unbinder unbinder;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		note =(Note) getIntent().getExtras().getParcelable("note");

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.note_detail_show);
		unbinder = ButterKnife.bind(this);

		init();
		setListener();
	}

	private void init(){

		popupMenu = new PopupMenu(this, imageview_right);
		popupMenu.inflate(R.menu.contextmenu);
		dbAdapter = DatabaseAdapter.getInstance(this);

		Drawable drawable_favorite = getResources().getDrawable(R.drawable.rb_favorite_selector);
		drawable_favorite.setBounds(1, 1, 40, 40);
		cb_favorite.setCompoundDrawables(drawable_favorite, null, null, null);

		Favorite favorite = (Favorite) dbAdapter.queryData(URIField.FAVORITE_URI,new FavoriteRowMapper(),URIField.FAVORITE_ITEMID +" = ? and "+URIField.FAVORITE_ITEMTYPE +" = ? ", new String[]{note.getId()+"",URIField.TNAME_SAFEREMARK});

		if (favorite != null) {
			cb_favorite.setChecked(true);
		}

		setText(note.getNoteName(),note.getNoteContent());
	}

	private void setText(String noteName,String noteContent)
	{
		tv_notename.setText(noteName);
		tv_notecontent.setText(noteContent);
	}

	private void setListener()
	{
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				switch (item.getItemId()) {
				
				case R.id.menuDelete:
					AlertDialog.Builder builder = new AlertDialog.Builder(ShowNoteDetailActivity.this);
					AlertDialog dialog;
					builder.setMessage("确定删除？");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dbAdapter.deleteData(URIField.SAFEREMARK_URI, "id = ?", new String[]{note.getId()+""});
							dbAdapter.deleteData(URIField.SAFEREMARK_URI, URIField.FAVORITE_ITEMID +" = ? and "+URIField.FAVORITE_ITEMTYPE +" = ? ", new String[]{note.getId()+"",URIField.TNAME_SAFEREMARK});
							ShowNoteDetailActivity.this.setResult(1);
							ShowNoteDetailActivity.this.finish();
						}
					});
					builder.setNegativeButton("取消", null);
					dialog  = builder.create();
					dialog.show();
					break;
				case R.id.menuUpdate:
					Intent i = new Intent(ShowNoteDetailActivity.this, UpdateOrAddNoteActivity.class);
                    Bundle bundle = UpdateOrAddNoteActivity.paramNeeded(true,note);
                    i.putExtras(bundle);
                    startActivityForResult(i, 0);
					break;
				}
				return true;
			}
		});
		cb_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
				{
					dbAdapter.insertData(URIField.FAVORITE_URI, GetContentValues.getContentValues(getFavorite()));
				}else{
					dbAdapter.deleteData(URIField.FAVORITE_URI, URIField.FAVORITE_ITEMID +" = ? and "+URIField.FAVORITE_ITEMTYPE +" = ? ", new String[]{note.getId()+"",URIField.TNAME_SAFEREMARK});
				}
				Intent intent = new Intent(Geneal.ACTION_FAVORITE_CHANGE);
				sendBroadcast(intent);

			}
		});
	}


	@OnClick({R.id.imageview_right,R.id.iv_back})
	void quitOrEdit(View v)
	{
		switch (v.getId()) {
			case R.id.iv_back:
				setResult(1);
				finish();
				break;

			case R.id.imageview_right:
				popupMenu.show();
				break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode ==2) {
			Note newNote = data.getExtras().getParcelable("newNote");
			note = newNote;
			setText(note.getNoteName(),note.getNoteContent());
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	public static Bundle paramNeeded(Parcelable note)
	{
		Bundle bundle = new Bundle();
		bundle.putParcelable("note", note);
		return bundle;
	}

	private Favorite getFavorite()
	{
		Favorite favorite = new Favorite(note.getId(),URIField.TNAME_SAFEREMARK);

		return favorite;
	}
}
