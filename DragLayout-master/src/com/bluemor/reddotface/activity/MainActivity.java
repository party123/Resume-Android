package com.bluemor.reddotface.activity;

import java.io.IOException;
import java.util.Random;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import com.bluemor.reddotface.R;
import com.bluemor.reddotface.shark.shark;
import com.bluemor.reddotface.view.Album;
import com.bluemor.reddotface.view.DragLayout;
import com.bluemor.reddotface.view.DragLayout.DragListener;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends Activity {

	private DragLayout dl;
	private GridView gv_img;
	private ListView lv;
	private TextView tv_noimg;
	private ImageView iv_icon, iv_bottom;
	private TextView text;
	private MediaPlayer mediaPlayer;
	private static int IsMusicOn = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  Button button1=(Button) findViewById(R.id.button1);
		Toast.makeText(getApplicationContext(), "��ӭ�ۿ��ҵļ������󻬿ɼ��˵���^_^", 5).show();
		if(IsMusicOn==0) IsMusicOn=1;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		 button1.setOnClickListener(new OnClickListener() {			
		    	@Override
		    	public void onClick(View v) {
		    		mediaPlayer.stop();	
		    		IsMusicOn=0;
		    	}
		    });
		if(IsMusicOn==1)
		{

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setLooping(true);
		mediaPlayer = MediaPlayer.create(this, R.raw.test);
		 try{
         mediaPlayer.prepare();
		 }
		 catch (IllegalArgumentException e)
		 {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		 }
		 catch (IllegalStateException e)
		 {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		 }
		 catch (IOException e)
		 {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		 }
		  
         mediaPlayer.start();
         IsMusicOn = 0;
		}
		initDragLayout();
		initView();
	}
   
	private void initDragLayout() {
		dl = (DragLayout) findViewById(R.id.dl);
		dl.setDragListener(new DragListener() {
			@Override
			public void onOpen() {
				lv.smoothScrollToPosition(new Random().nextInt(30));
			}

			@Override
			public void onClose() {
			}

			@Override
			public void onDrag(float percent) {
				animate(percent);
			}
		});
	}

	private void animate(float percent) {
		ViewGroup vg_left = dl.getVg_left();
		ViewGroup vg_main = dl.getVg_main();

		float f1 = 1 - percent * 0.3f;
		ViewHelper.setScaleX(vg_main, f1);
		ViewHelper.setScaleY(vg_main, f1);
		ViewHelper.setTranslationX(vg_left, -vg_left.getWidth() / 2.2f
				+ vg_left.getWidth() / 2.2f * percent);
		ViewHelper.setScaleX(vg_left, 0.5f + 0.5f * percent);
		ViewHelper.setScaleY(vg_left, 0.5f + 0.5f * percent);
		ViewHelper.setAlpha(vg_left, percent);
		ViewHelper.setAlpha(iv_icon, 1 - percent);

		
	}

	private void initView() {
		iv_icon = (ImageView) findViewById(R.id.iv_icon);
		iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
		gv_img = (GridView) findViewById(R.id.gv_img);
		tv_noimg = (TextView) findViewById(R.id.iv_noimg);
		text = (TextView) findViewById(R.id.text);
		lv = (ListView) findViewById(R.id.lv);
		final String[] s=new String[] { "   ��������","   �������","   ���չʾ","   ���ҽ���", "   ��ϵ��ʽ","   ��  ��"};
		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.item_text,s));
		iv_bottom.setOnClickListener(new OnClickListener() {
			   
			   @Override
			   public void onClick(View arg0) {
			    // TODO Auto-generated method stub
				   Toast.makeText(getApplicationContext(), "����Ŵ�^_^", 5).show();
				   Intent intent = new Intent(MainActivity.this, look.class);
							startActivity(intent);
			   }
			  });
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
//				Toast.makeText(getApplicationContext(), "click " + position,Toast.LENGTH_LONG).show();
				if(s[position]=="   ��������"){dl.close();}
				else if(s[position]=="   �������"){
				Intent intent = new Intent(MainActivity.this, AlbumActivity2.class);
		//		Bundle bundle = new Bundle();
		//		bundle.putString("index", s[position]);
		//		intent.putExtras(bundle);
				startActivity(intent);
				}
				
				else if(s[position]=="   ���չʾ"){
					Intent intent = new Intent(MainActivity.this, suzhi.class);
					Bundle bundle = new Bundle();
					bundle.putString("intent", s[position]);
					intent.putExtras(bundle);
					startActivity(intent);
					}
				else if(s[position]=="   ���ҽ���"){
					Intent intent = new Intent(MainActivity.this, shark.class);
					Bundle bundle = new Bundle();
					bundle.putString("intent", s[position]);
					intent.putExtras(bundle);
					startActivity(intent);
					}
				else if(s[position]=="   ��ϵ��ʽ"){
					Intent intent = new Intent(MainActivity.this, lian.class);
					Bundle bundle = new Bundle();
					bundle.putString("intent", s[position]);
					intent.putExtras(bundle);
					startActivity(intent);
					}
				else if(s[position]=="   ��  ��"){
					AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);  
					builder.setIcon(R.drawable.ic_launcher);  
					builder.setTitle("��ȷ��Ҫ�˳���");  
					builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
					    public void onClick(DialogInterface dialog, int whichButton) {  
					        // ������ӵ��ȷ������߼�  
					    	mediaPlayer.stop();
					    	System.exit(0);
					        //finish();
					    }  
					});  
					builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {  
					    public void onClick(DialogInterface dialog, int whichButton) {  
					        // ������ӵ��ȷ������߼�  
					    }  
					});  
					builder.create().show(); 

				}
			}
		});
		
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {       
			mediaPlayer.stop();
	        finish();  
			return false;        
			}
		else {             
			return super.onKeyDown(keyCode, event);       
			}             
		}   
	

}
