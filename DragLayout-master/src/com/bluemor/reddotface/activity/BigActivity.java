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
	private ImageSwitcher imageSwitcher; // ����һ��ͼ���л�������
	private final int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,R.drawable.img09
			 }; // ���岢��ʼ������ͼƬid������

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.big);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish(); // ����
			}
		});


		final Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();	//��ȡ���ݹ��������ݰ�
		String imgId=bundle.getString("imgId");//���ݹ�����ͼƬ����imageview��ʾ
		int position=bundle.getInt("position");//���ݹ�����ͼƬ�ڵڼ���
		int imgId1=bundle.getInt("imgId");

		Gallery gallery = (Gallery) findViewById(R.id.gallery1); // ��ȡGallery���
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1); // ��ȡͼ���л���
		


		
		// ���ö���Ч��
				imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
						android.R.anim.fade_in)); // ���õ��붯��
				imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
						android.R.anim.fade_out)); // ���õ�������
				imageSwitcher.setFactory(new ViewFactory() {

					@Override
					public View makeView() {
						ImageView imageView = new ImageView(BigActivity.this); // ʵ����һ��ImageView��Ķ���
						imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); // ���ñ����ݺ�Ⱦ�������ͼ��
						imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
								LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
						return imageView; // ����imageView����
					}

				});
		/********************** ʹ��BaseAdapterָ��Ҫ��ʾ������ *****************************/
		BaseAdapter adapter = new BaseAdapter() {
			private Context mContext;
		    private LayoutInflater mInflater;
		
		    private int mSelectedItemPos = -1;

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // ����ImageView�Ķ���
				if (convertView == null) {
					imageview = new ImageView(BigActivity.this); // ʵ����ImageView�Ķ���
					imageview.setScaleType(ImageView.ScaleType.FIT_XY); // �������ŷ�ʽ
					imageview
							.setLayoutParams(new Gallery.LayoutParams(180, 135));
					TypedArray typedArray = obtainStyledAttributes(R.styleable.Gallery);
					imageview.setBackgroundResource(typedArray.getResourceId(
							R.styleable.Gallery_android_galleryItemBackground,
							0));
					imageview.setPadding(5, 0, 5, 0); // ����ImageView���ڱ߾�
				} else {
					imageview = (ImageView) convertView;
				}
				
				imageview.setImageResource(imageId[position]);	//ΪImageView����Ҫ��ʾ��ͼƬ
				return imageview; // ����ImageView
			}

			/*
			 * ���ܣ���õ�ǰѡ���ID (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItemId(int)
			 */
			@Override
			public long getItemId(int position) {
				return position;
			}

			/*
			 * ���ܣ���õ�ǰѡ�� (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getItem(int)
			 */
			@Override
			public Object getItem(int position) {
				return position;
			}

			/*
			 * ������� (non-Javadoc)
			 * 
			 * @see android.widget.Adapter#getCount()
			 */
		
			public int getCount() {
				return imageId.length;
			}
			 
		};
		gallery.setAdapter(adapter); // ����������Gallery����
		/*********************************************************************************/
		gallery.setSelection(position); // �ӵ�ǰͼƬ��ʼѡ
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				imageSwitcher.setImageResource(imageId[position]);// ��ʾѡ�е�ͼƬ
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

	}
}