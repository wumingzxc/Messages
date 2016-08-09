package com.hswu.fragment;

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

import com.hswu.activity.ShowCreditCardsActivity;
import com.hswu.activity.HomePageActivity;
import com.hswu.activity.SelectCategoryActivity;
import com.hswu.activity.ShowNotesActivity;
import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.util.URIField;

import java.util.ArrayList;


/**
 *
 * 点击左侧类别切换至此fragment
 */
public class CategoryFragment extends Fragment implements OnClickListener{

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
			intent = new Intent(getActivity(), ShowCreditCardsActivity.class);
            getActivity().startActivity(intent);
			break;
		case R.id.linearlayout_login:

			break;
		case R.id.linearlayout_note:
			intent = new Intent(getActivity(), ShowNotesActivity.class);
			getActivity().startActivity(intent);
			break;
		}

	}
	
}
