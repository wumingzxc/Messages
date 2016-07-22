package com.hswu.activity;

import com.hswu.bean.CreditCard;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.util.URIField;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

public class CreditcardShowActivity extends Activity implements OnClickListener{

	private CheckBox cb_favorite;
	private CreditCard card;
	private TextView tv_bankname;
	private TextView tv_cardname;
	private TextView tv_cardnumber;
	private TextView tv_cvv;
	private TextView tv_indate;
	private TextView tv_limit;
	private ImageView iv_back;
	private ImageView imageview_right;
	private PopupMenu popupMenu;
	private DatabaseAdapter dbAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		card =(CreditCard) getIntent().getExtras().getParcelable("card");

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.creditcard_detail_show);

		init();
		initRB();
		setText();
		setListener();
	}




	private void init(){
		cb_favorite =findView(R.id.cb_favorite);
		tv_bankname =findView(R.id.tv_bankname);
		tv_cardname =findView(R.id.tv_cardname);
		tv_cardnumber =findView(R.id.tv_cardnumber);
		tv_cvv=findView(R.id.tv_cvv);
		tv_indate =findView(R.id.tv_indate);
		tv_limit =findView(R.id.tv_limit);
		iv_back = findView(R.id.iv_back);
		imageview_right = findView(R.id.imageview_right);
		popupMenu = new PopupMenu(this, imageview_right);
		popupMenu.inflate(R.menu.contextmenu);
		dbAdapter = DatabaseAdapter.getInstance(this);
	}

	private void setText()
	{
		tv_bankname.setText(card.getBankName());
		tv_cardname.setText(card.getCardName());
		tv_cardnumber.setText(card.getCardNumber());
		tv_cvv.setText(card.getCvv2());
		tv_indate.setText(card.getIndate());
		tv_limit.setText(card.getLimit());
	}

	private void setListener()
	{
		iv_back.setOnClickListener(this);
		imageview_right.setOnClickListener(this);

		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				switch (item.getItemId()) {
				
				case R.id.menuDelete:
					AlertDialog.Builder builder = new AlertDialog.Builder(CreditcardShowActivity.this);
					AlertDialog dialog;
					builder.setMessage("确定删除？");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dbAdapter.deleteData(URIField.CREDITCARD_URI, "id = ?", new String[]{card.getId()+""});
							CreditcardShowActivity.this.setResult(1);
							CreditcardShowActivity.this.finish();
						}
					});
					builder.setNegativeButton("取消", null);
					dialog  = builder.create();
					dialog.show();
					break;
				case R.id.menuUpdate:
					Intent i = new Intent(CreditcardShowActivity.this, UpdateCreditCard.class);
					Bundle bundle = new Bundle();
					bundle.putParcelable("card", card);
					i.putExtras(bundle);
					startActivityForResult(i, 0);
					break;
				}
				return true;
			}
		});


	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0 && resultCode ==3) {
			CreditCard newCard = data.getExtras().getParcelable("newCard"); 
			card = newCard;
			tv_bankname.setText(newCard.getBankName());
			tv_cardname.setText(newCard.getCardName());
			tv_cardnumber.setText(newCard.getCardNumber());
			tv_cvv.setText(newCard.getCvv2());
			tv_indate.setText(newCard.getIndate());
			tv_limit.setText(newCard.getLimit());
		}
	}
	

	@SuppressWarnings("deprecation")
	private void initRB()
	{
		Drawable drawable_favorite = getResources().getDrawable(R.drawable.rb_favorite_selector);
		drawable_favorite.setBounds(1, 1, 40, 40);
		cb_favorite.setCompoundDrawables(drawable_favorite, null, null, null);
	}


	@SuppressWarnings("unchecked")
	private <T extends View> T findView(int id) {
		return (T) this.findViewById(id);
	}

	@Override
	public void onClick(View v) {
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

}
