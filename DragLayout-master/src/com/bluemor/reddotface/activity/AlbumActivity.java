package com.bluemor.reddotface.activity;

import com.bluemor.reddotface.R;
import com.bluemor.reddotface.view.Album;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;  
import android.net.Uri;
import android.os.Bundle;  
import android.util.Log;  
import android.widget.MediaController;  
import android.widget.VideoView;  
import android.widget.MediaController.MediaPlayerControl;  
import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;



public class AlbumActivity extends Activity{

protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.activity_album); // 设置该Activity使用的布局
	 final VideoView vv = (VideoView)this.findViewById(R.id.videoView1);
		String uri = "android.resource://" + getPackageName() + "/" + R.raw.test_i;	 
     		vv.setVideoURI(Uri.parse(uri));
     	//	vv.start();
Button button1=(Button) findViewById(R.id.button1);
Button button2=(Button) findViewById(R.id.button2);
Button button3=(Button) findViewById(R.id.button3);
Button button4=(Button) findViewById(R.id.button4);
button1.setOnClickListener(new OnClickListener() {			
	@Override
	public void onClick(View v) {
		finish();	//返回
	}
});
button2.setOnClickListener(new OnClickListener() {			
	@Override
	public void onClick(View v) {
	       		vv.start();
	}
});
button3.setOnClickListener(new OnClickListener() {			
	@Override
	public void onClick(View v) {
	       		vv.pause();
	}
});
button4.setOnClickListener(new OnClickListener() {			
	@Override
	public void onClick(View v) {
	       		vv.stopPlayback();
	       		vv.resume();
	}
});
}
// 创建一个继承Activity的内部类，用于在手机界面中，通过Activity显示详细内容
public static class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 判断是否为横屏，如果为横屏，则结束当前Activity，准备使用Fragment显示详细内容
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish(); // 结束当前Activity
			return;
		}

		if (savedInstanceState == null) { //
			// 在初始化时插入一个显示详细内容的Fragment
			Album details = new Album();// 实例化DetailFragment的对象
			details.setArguments(getIntent().getExtras()); // 设置要传递的参数
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit(); // 添加一个显示详细内容的Fragment
		}
	}
}
}
