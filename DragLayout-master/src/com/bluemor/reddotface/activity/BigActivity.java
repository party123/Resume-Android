package com.bluemor.reddotface.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bluemor.reddotface.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class BigActivity extends Activity {
//	private ImageView iv;
	private ImageSwitcher imageSwitcher; // 声明一个图像切换器对象
	private final int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,R.drawable.img09
			 }; // 定义并初始化保存图片id的数组

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.big);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish(); // 返回
			}
		});


		final Intent intent=getIntent();	//获取Intent对象
		Bundle bundle=intent.getExtras();	//获取传递过来的数据包
		String imgId=bundle.getString("imgId");//传递过来的图片，让imageview显示
		int position=bundle.getInt("position");//传递过来的图片在第几个
		int imgId1=bundle.getInt("imgId");

		Gallery gallery = (Gallery) findViewById(R.id.gallery1); // 获取Gallery组件
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1); // 获取图像切换器
		


		
		// 设置动画效果
				imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
						android.R.anim.fade_in)); // 设置淡入动画
				imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
						android.R.anim.fade_out)); // 设置淡出动画
				imageSwitcher.setFactory(new ViewFactory() {

					@Override
					public View makeView() {
						ImageView imageView = new ImageView(BigActivity.this); // 实例化一个ImageView类的对象
						imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // 设置保持纵横比居中缩放图像
						imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
								LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
						return imageView; // 返回imageView对象
					}

				});
		/********************** 使用BaseAdapter指定要显示的内容 *****************************/
		BaseAdapter adapter = new BaseAdapter() {
			private Context mContext;
		    private LayoutInflater mInflater;
		
		    private int mSelectedItemPos = -1;

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // 声明ImageView的对象
				if (convertView == null) {
					imageview = new ImageView(BigActivity.this); // 实例化ImageView的对象
					imageview.setScaleType(ImageView.ScaleType.FIT_XY); // 设置缩放方式
					imageview
							.setLayoutParams(new Gallery.LayoutParams(180, 135));
					TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
					imageview.setBackgroundResource(typedArray.getResourceId(
							R.styleable.Gallery_android_galleryItemBackground,
							0));
					imageview.setPadding(5, 0, 5, 0); // 设置ImageView的内边距
				} else {
					imageview = (ImageView) convertView;
				}
				
				imageview.setImageResource(imageId[position]);	//为ImageView设置要显示的图片
				return imageview; // 返回ImageView
			}

			/*
			 * 功能：获得当前选项的ID (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItemId(int)
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}

			/*
			 * 功能：获得当前选项 (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItem(int)
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}

			/*
			 * 获得数量 (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getCount()
			 */
		
			public int getCount() {
				return imageId.length;
			}
			 
		};
		gallery.setAdapter(adapter); // 将适配器与Gallery关联
		/*********************************************************************************/
		gallery.setSelection(position); // 从当前图片开始选
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageSwitcher.setImageResource(imageId[position]);// 显示选中的图片
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

	}
}