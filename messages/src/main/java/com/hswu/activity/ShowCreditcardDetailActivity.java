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
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShowCreditcardDetailActivity extends Activity{

	@BindView(R.id.cb_favorite) CheckBox cb_favorite;
	@BindView(R.id.tv_bankname) TextView tv_bankname;
	@BindView(R.id.tv_cardname) TextView tv_cardname;
	@BindView(R.id.tv_cardnumber) TextView tv_cardnumber;
	@BindView(R.id.tv_cvv) TextView tv_cvv;
	@BindView(R.id.tv_indate) TextView tv_indate;
	@BindView(R.id.tv_limit) TextView tv_limit;
	@BindView(R.id.imageview_right) ImageView imageview_right;

	private CreditCard card;
	private PopupMenu popupMenu;
	private DatabaseAdapter dbAdapter;
	private Unbinder unbinder;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		card =(CreditCard) getIntent().getExtras().getParcelable("card");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.creditcard_detail_show);
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

		setText(card.getBankName(),card.getCardName(),card.getCardNumber(),card.getCvv2(),card.getIndate(),card.getLimit());
	}

	private void setText(String bankName,String cardName,String cardNum,String cvv,String indate,String limit)
	{
		tv_bankname.setText(bankName);
		tv_cardname.setText(cardName);
		tv_cardnumber.setText(cardNum);
		tv_cvv.setText(cvv);
		tv_indate.setText(indate);
		tv_limit.setText(limit);
	}

	private void setListener()
	{
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				switch (item.getItemId()) {
				
				case R.id.menuDelete:
					AlertDialog.Builder builder = new AlertDialog.Builder(ShowCreditcardDetailActivity.this);
					AlertDialog dialog;
					builder.setMessage("确定删除？");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dbAdapter.deleteData(URIField.CREDITCARD_URI, "id = ?", new String[]{card.getId()+""});
							ShowCreditcardDetailActivity.this.setResult(1);
							ShowCreditcardDetailActivity.this.finish();
						}
					});
					builder.setNegativeButton("取消", null);
					dialog  = builder.create();
					dialog.show();
					break;
				case R.id.menuUpdate:
					Intent i = new Intent(ShowCreditcardDetailActivity.this, UpdateCreditCard.class);
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
		if (requestCode == 0 && resultCode ==3) {
			CreditCard newCard = data.getExtras().getParcelable("newCard"); 
			card = newCard;
			setText(card.getBankName(),card.getCardName(),card.getCardNumber(),card.getCvv2(),card.getIndate(),card.getLimit());
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}
}
