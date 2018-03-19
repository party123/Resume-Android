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
			 }; // ���岢��ʼ������ͼƬid������
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_album2); // ���ø�Activityʹ�õĲ���
		Toast.makeText(getApplicationContext(), "���ͼƬ���ɲ鿴��ͼ^_^", 5).show();
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish(); // ����
			}
		});
		

		GridView gridview = (GridView) findViewById(R.id.gv_img); // ��ȡGridView���

		/********************** ʹ��BaseAdapterָ��Ҫ��ʾ������ *****************************/

		BaseAdapter adapter = new BaseAdapter() {

			public View getView(int position, View convertView, ViewGroup parent) {
				ImageView imageview; // ����ImageView�Ķ���
				if (convertView == null) {
					imageview = new ImageView(AlbumActivity2.this); // ʵ����ImageView�Ķ���
					/************* ����ͼ��Ŀ�Ⱥ͸߶� ******************/
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(300);
					imageview.setMaxHeight(200);
					/**************************************************/
					imageview.setPadding(0, 0, 0, 0); // ����ImageView���ڱ߾�
					imageview.setScaleType(ImageView.ScaleType.CENTER_CROP); // �������ŷ�ʽ
				} else {
					imageview = (ImageView) convertView;
				}
				imageview.setImageResource(imageId[position]);	//ΪImageView����Ҫ��ʾ��ͼƬ
				return imageview;	//����ImageView
			}

			/*
			 * ���ܣ���õ�ǰѡ���ID
			 */
			public long getItemId(int position) {
				return position;
			}

			/*
			 * ���ܣ���õ�ǰѡ��
			 */

			public Object getItem(int position) {
				return position;
			}

			/*
			 * �������
			 */

			public int getCount() {
				return imageId.length;
			}
		};

		gridview.setAdapter(adapter); // ����������GridView����
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(AlbumActivity2.this, BigActivity.class);
				Bundle bundle = new Bundle(); // ������ʵ����һ��Bundle����
				bundle.putInt("imgId", imageId[position]); // ����ͼƬID
				bundle.putInt("position", position);// ͼƬ�ǵڼ���
//				bundle.putIntegerArrayList("imageArray", (ArrayList<Integer>) imageId[position]);//������ļ����µ�ͼƬ"����"
				intent.putExtras(bundle); // ��Bundle������ӵ�Intent������
				startActivity(intent); // �����µ�Activity

			}
		});

	}
}
