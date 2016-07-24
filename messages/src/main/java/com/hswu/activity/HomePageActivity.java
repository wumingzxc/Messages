package com.hswu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hswu.fragment.CategoryFragment;
import com.hswu.iface.OpenDrawer;
import com.hswu.messages.R;

public class HomePageActivity extends Activity implements OpenDrawer {

	private RadioGroup rg;
	private RadioButton rb_favorite;
	private RadioButton rb_folder;
	private RadioButton rb_category;
	private DrawerLayout main_drawer_layout;
	private LinearLayout left_drawer_layout;
	private CategoryFragment categoryFragment;
	private FragmentManager fragmentManager;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_homepage);
		
		init();
		initRB();
		
		rb_category.setChecked(true);
		
		
		fragmentManager = this.getFragmentManager();
		categoryFragment =new CategoryFragment();
		fragmentManager.beginTransaction().replace(R.id.container, categoryFragment).commit();
	}
	
	
	private void init(){
		main_drawer_layout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
		left_drawer_layout = (LinearLayout) findViewById(R.id.left_drawer_layout);
		rg = (RadioGroup) findViewById(R.id.radiogroup);
		rb_favorite = (RadioButton) rg.getChildAt(0);
		rb_category = (RadioButton) rg.getChildAt(1);
		rb_folder = (RadioButton) rg.getChildAt(2);
	}
	
	
	
	@SuppressWarnings("deprecation")
	private void initRB()
	{
		Drawable drawable_favorite = getResources().getDrawable(R.drawable.favorite_selector);
		Drawable drawable_folder = getResources().getDrawable(R.drawable.folder_selector);
		Drawable drawable_category = getResources().getDrawable(R.drawable.category_selector);
		drawable_favorite.setBounds(1, 1, 60, 60);
		drawable_folder.setBounds(1, 1, 60, 60);
		drawable_category.setBounds(1, 1, 60, 60);
		rb_favorite.setCompoundDrawables(drawable_favorite, null, null, null);
		rb_folder.setCompoundDrawables(drawable_folder, null, null, null);
		rb_category.setCompoundDrawables(drawable_category, null, null, null);
	}



	@Override
	public void openDrawerLayout() {
		main_drawer_layout.openDrawer(left_drawer_layout);
	}
	
	
}
