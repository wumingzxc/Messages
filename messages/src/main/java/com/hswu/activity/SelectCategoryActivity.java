package com.hswu.activity;

import com.hswu.messages.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;

public class SelectCategoryActivity extends Activity implements OnClickListener {


	private LinearLayout linearlayout_credit_card;
	private LinearLayout linearlayout_login;
	private LinearLayout linearlayout_note;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_select_category);
		
		init();
	

	}
	
	private void init()
	{
		
		linearlayout_credit_card = (LinearLayout) findViewById(R.id.linearlayout_credit_card);
		linearlayout_login = (LinearLayout) findViewById(R.id.linearlayout_login);
		linearlayout_note = (LinearLayout) findViewById(R.id.linearlayout_note);

		linearlayout_credit_card.setOnClickListener(this);
		linearlayout_login.setOnClickListener(this);
		linearlayout_note.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.linearlayout_credit_card:
			intent = new Intent(this, AddCreditcardActivity.class);
			startActivity(intent);
			this.finish();
			break;
		case R.id.linearlayout_login:

			break;
		case R.id.linearlayout_note:

			break;

		}
	}


}
