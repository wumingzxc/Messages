package com.hswu.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hswu.activity.HomePageActivity;
import com.hswu.adapter.BaseBeanListItemAdapter;
import com.hswu.bean.BaseBean;
import com.hswu.bean.CreditCard;
import com.hswu.bean.Favorite;
import com.hswu.bean.Note;
import com.hswu.constant.Geneal;
import com.hswu.database.DatabaseAdapter;
import com.hswu.messages.R;
import com.hswu.rowmapper.CreditCardRowMapper;
import com.hswu.rowmapper.FavoriteRowMapper;
import com.hswu.rowmapper.NoteRowMapper;
import com.hswu.util.URIField;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FavoriteFragment extends Fragment{

	@BindView(R.id.iv_add)
	ImageView iv_add;
	@BindView(R.id.iv_left)
	ImageView iv_left;
	@BindView(R.id.tv_title)
	TextView tv_title;

	private View rootView;
	private ListView listView;
	private Unbinder unbinder;
	private RotateAnimation rotateAnimation;
	private DatabaseAdapter databaseAdapter;
	private List<Favorite> favorites;
	private List<BaseBean> beans = new ArrayList<BaseBean>();
	private BaseBeanListItemAdapter baseBeanListItemAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_favorite, null);
		unbinder = ButterKnife.bind(this,rootView);
		rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
		listView = (ListView) rootView.findViewById(R.id.listview);
		iv_add.setVisibility(View.INVISIBLE);
		tv_title.setText("收藏夹");

		databaseAdapter = DatabaseAdapter.getInstance(getActivity());

		favorites = (List<Favorite>)databaseAdapter.queryDatas(URIField.FAVORITE_URI,new FavoriteRowMapper());

		updateData(favorites);

		baseBeanListItemAdapter = new BaseBeanListItemAdapter(getActivity(),beans);
		listView.setAdapter(baseBeanListItemAdapter);

		setListener();

		IntentFilter filter = new IntentFilter(Geneal.ACTION_FAVORITE_CHANGE);
		getActivity().registerReceiver(updataBroadcastReceiver,filter);

		return rootView;
	}



	private void setListener()
	{
		rotateAnimation.setAnimationListener(new Animation.AnimationListener() {

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


	@OnClick(R.id.iv_left)
	void openDrawer(View view)
	{
		iv_left.startAnimation(rotateAnimation);
	}



	private void updateData(List<Favorite> favorites)
	{
		beans.clear();
		for (Favorite favorite: favorites) {

			if (favorite.getItemType().equals(URIField.TNAME_CREDITCARD))
			{
				CreditCard card = (CreditCard) databaseAdapter.queryData(URIField.CREDITCARD_URI,new CreditCardRowMapper()," id = ?",new String[]{favorite.getItemId()+""});
				beans.add(card);
			}
			if (favorite.getItemType().equals(URIField.TNAME_SAFEREMARK))
			{
				Note note = (Note) databaseAdapter.queryData(URIField.SAFEREMARK_URI,new NoteRowMapper()," id = ?",new String[]{favorite.getItemId()+""});
				beans.add(note);
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getActivity().unregisterReceiver(updataBroadcastReceiver);
		unbinder.unbind();
	}


	private BroadcastReceiver updataBroadcastReceiver = new  BroadcastReceiver()
	{
		@Override
		public void onReceive(Context context, Intent intent) {

			favorites = (List<Favorite>)databaseAdapter.queryDatas(URIField.FAVORITE_URI,new FavoriteRowMapper());
			updateData(favorites);
			baseBeanListItemAdapter.notifyDataSetChanged();
		}
	};


}
