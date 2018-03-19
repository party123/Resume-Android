package com.bluemor.reddotface.activity;

import java.util.ArrayList;
import java.util.List;

import com.bluemor.reddotface.R;
import com.bluemor.reddotface.fragment.OneFragment;
import com.bluemor.reddotface.fragment.ThreeFragment;
import com.bluemor.reddotface.fragment.TwoFragment;
import com.bluemor.reddotface.shark.shark;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class suzhi extends FragmentActivity {

	private ViewPager viewPager;
	private ImageView imageView;
	private TextView voiceAnswer, healthPedia, pDected;
	private List<Fragment> fragments;
	private int offset = 0;
	private int currIndex = 0;
	private int bmpW;
	private int selectedColor, unSelectedColor;
	private static final int pageSize = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabwidget);
		initView();
		Button button1=(Button) findViewById(R.id.button1);
	    button1.setOnClickListener(new OnClickListener() {			
	    	@Override
	    	public void onClick(View v) {
	    		finish();	//返回
	    	}
	    });
	}

	private void initView() {
		selectedColor = getResources()
				.getColor(R.color.tab_title_pressed_color);
		unSelectedColor = getResources().getColor(
				R.color.tab_title_normal_color);

		InitImageView();
		InitTextView();
		InitViewPager();
	}

	/**
	 */
	private void InitViewPager() {
		viewPager = (ViewPager) findViewById(R.id.vPager);
		fragments = new ArrayList<Fragment>();
		fragments.add(new ThreeFragment());
		fragments.add(new OneFragment());
		fragments.add(new TwoFragment());
		viewPager.setAdapter(new myPagerAdapter(getSupportFragmentManager(),
				fragments));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * 
	 */
	private void InitTextView() {
		voiceAnswer = (TextView) findViewById(R.id.tab_1);
		healthPedia = (TextView) findViewById(R.id.tab_2);
		pDected = (TextView) findViewById(R.id.tab_3);

		voiceAnswer.setTextColor(selectedColor);
		healthPedia.setTextColor(unSelectedColor);
		pDected.setTextColor(unSelectedColor);

		voiceAnswer.setText("兴趣爱好");
		healthPedia.setText("荣誉奖励");
		pDected.setText("社会工作");

		voiceAnswer.setOnClickListener(new MyOnClickListener(0));
		healthPedia.setOnClickListener(new MyOnClickListener(1));
		pDected.setOnClickListener(new MyOnClickListener(2));
	}

	/**

	 */

	private void InitImageView() {
		imageView = (ImageView) findViewById(R.id.cursor);
		bmpW = BitmapFactory.decodeResource(getResources(),
				R.drawable.tab_selected_bg).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (screenW / pageSize - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		imageView.setImageMatrix(matrix);
	}

	/**
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		public void onClick(View v) {

			switch (index) {
			case 0:
				voiceAnswer.setTextColor(selectedColor);
				healthPedia.setTextColor(unSelectedColor);
				pDected.setTextColor(unSelectedColor);
				break;
			case 1:
				healthPedia.setTextColor(selectedColor);
				voiceAnswer.setTextColor(unSelectedColor);
				pDected.setTextColor(unSelectedColor);
				break;
			case 2:
				pDected.setTextColor(selectedColor);
				voiceAnswer.setTextColor(unSelectedColor);
				healthPedia.setTextColor(unSelectedColor);
				break;
			}
			viewPager.setCurrentItem(index);
		}

	}

	/**
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		int one = offset * 2 + bmpW;
		int two = one * 2;

		public void onPageScrollStateChanged(int index) {
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		public void onPageSelected(int index) {
			Animation animation = new TranslateAnimation(one * currIndex, one
					* index, 0, 0);
			currIndex = index;
			animation.setFillAfter(true);
			animation.setDuration(300);
			imageView.startAnimation(animation);

			switch (index) {
			case 0:
				voiceAnswer.setTextColor(selectedColor);
				healthPedia.setTextColor(unSelectedColor);
				pDected.setTextColor(unSelectedColor);
				break;
			case 1:
				healthPedia.setTextColor(selectedColor);
				voiceAnswer.setTextColor(unSelectedColor);
				pDected.setTextColor(unSelectedColor);
				break;
			case 2:
				pDected.setTextColor(selectedColor);
				voiceAnswer.setTextColor(unSelectedColor);
				healthPedia.setTextColor(unSelectedColor);
				break;
			}
		}
	}

	/**
	 */
	class myPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragmentList;

		public myPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
			super(fm);
			this.fragmentList = fragmentList;
		}

		/**
		 */
		@Override
		public Fragment getItem(int arg0) {
			return (fragmentList == null || fragmentList.size() == 0) ? null
					: fragmentList.get(arg0);
		}

		/**
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return null;
		}

		/**
		 */
		@Override
		public int getCount() {
			return fragmentList == null ? 0 : fragmentList.size();
		}
	}

}