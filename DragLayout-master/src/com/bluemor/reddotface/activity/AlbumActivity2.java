package com.bluemor.reddotface.activity;

import com.bluemor.reddotface.R;
import com.bluemor.reddotface.view.Album;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivity2 extends Activity {
	private int[] imageId = new int[] { R.drawable.img01, R.drawable.img02,
			R.drawable.img03, R.drawable.img04, R.drawable.img05,
			R.drawable.img06, R.drawable.img07, R.drawable.img08,R.drawable.img09
			 }; // 定义并初始化保存图片id的数组
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_album2); // 设置该Activity使用的布局
		Toast.makeText(getApplicationContext(), "点击图片即可查看大图^_^", 5).show();
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); // 返回
			}
		});
		

		GridView gridview = (GridView) findViewById(R.id.gv_img); // 获取GridView组件

		/********************** 使用BaseAdapter指定要显示的内容 *****************************/

		BaseAdapter adapter = new BaseAdapter() {

			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // 声明ImageView的对象
				if (convertView == null) {
					imageview = new ImageView(AlbumActivity2.this); // 实例化ImageView的对象
					/************* 设置图像的宽度和高度 ******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(300);
					imageview.setMaxHeight(200);
					/**************************************************/
					imageview.setPadding(0, 0, 0, 0); // 设置ImageView的内边距
					imageview.setScaleType(ImageView.ScaleType.CENTER_CROP); // 设置缩放方式
				} else {
					imageview = (ImageView) convertView;
				}
				imageview.setImageResource(imageId[position]);	//为ImageView设置要显示的图片
				return imageview;	//返回ImageView
			}

			/*
			 * 功能：获得当前选项的ID
			 */
			public long getItemId(int position) {
				return position;
			}

			/*
			 * 功能：获得当前选项
			 */

			public Object getItem(int position) {
				return position;
			}

			/*
			 * 获得数量
			 */

			public int getCount() {
				return imageId.length;
			}
		};

		gridview.setAdapter(adapter); // 将适配器与GridView关联
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(AlbumActivity2.this, BigActivity.class);
				Bundle bundle = new Bundle(); // 创建并实例化一个Bundle对象
				bundle.putInt("imgId", imageId[position]); // 保存图片ID
				bundle.putInt("position", position);// 图片是第几个
//				bundle.putIntegerArrayList("imageArray", (ArrayList<Integer>) imageId[position]);//保存该文件夹下的图片"缓存"
				intent.putExtras(bundle); // 将Bundle对象添加到Intent对象中
				startActivity(intent); // 启动新的Activity

			}
		});

	}
}
