package com.hswu.fragment;

import java.util.ArrayList;

import com.hswu.activity.CategoryCreditCardActivity;
import com.hswu.activity.CreditcardDetailActivity;
import com.hswu.activity.HomePageActivity;
import com.hswu.activity.SelectCategoryActivity;
import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.database.DatabaseAdapter;
import com.hswu.safebox.R;
import com.hswu.util.URIField;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CategoryFragment extends Fragment implements OnClickListener{

	private DatabaseAdapter dbAdapter;
	private ArrayList<? extends BaseBean> cards;
	private CategoryCreditCardActivity categoryCreditCardActivity;
	private FragmentTransaction transaction;
	private Intent intent;
	private View rootView;
	private ImageView iv_add;
	private ImageView iv_left;
	private RotateAnimation rotateAnimation;
	private LinearLayout linearlayout_credit_card;
	private LinearLayout linearlayout_login;
	private LinearLayout linearlayout_note;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_category, null);
		
		init();
		setListener();
		
		return rootView;
	}

	private void init()
	{
		dbAdapter = DatabaseAdapter.getInstance(getActivity());
		transaction = this.getFragmentManager().beginTransaction();
		categoryCreditCardActivity = new CategoryCreditCardActivity();
		rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
		
		iv_add = (ImageView) rootView.findViewById(R.id.iv_add);
		iv_left = (ImageView) rootView.findViewById(R.id.iv_left);
		linearlayout_credit_card = (LinearLayout) rootView.findViewById(R.id.linearlayout_credit_card);
		linearlayout_login = (LinearLayout) rootView.findViewById(R.id.linearlayout_login);
		linearlayout_note = (LinearLayout) rootView.findViewById(R.id.linearlayout_note);
		
	}
	
	private void setListener()
	{
		iv_add.setOnClickListener(this);
		iv_left.setOnClickListener(this);
		linearlayout_credit_card.setOnClickListener(this);
		linearlayout_login.setOnClickListener(this);
		linearlayout_note.setOnClickListener(this);
		rotateAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				 ((HomePageActivity)getActivity()).openDrawerLayout();
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.iv_add:
			intent = new Intent(getActivity(), SelectCategoryActivity.class);
			getActivity().startActivity(intent);
			break;
		case R.id.iv_left:
			iv_left.startAnimation(rotateAnimation);
			break;
		case R.id.linearlayout_credit_card:
			cards = (ArrayList<CreditCard>) dbAdapter.queryData(URIField.CREDITCARD_URI);
			intent = new Intent(getActivity(), CategoryCreditCardActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("cards", (ArrayList<? extends Parcelable>) cards);
            intent.putExtra("bundle",bundle);
            getActivity().startActivity(intent);

			break;
		case R.id.linearlayout_login:

			break;
		case R.id.linearlayout_note:

			break;
			
			
			
		}
	
	}
	
}
