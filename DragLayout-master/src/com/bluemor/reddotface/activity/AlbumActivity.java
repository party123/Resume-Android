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
	setContentView(R.layout.activity_album); // ���ø�Activityʹ�õĲ���
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
		finish();	//����
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
// ����һ���̳�Activity���ڲ��࣬�������ֻ������У�ͨ��Activity��ʾ��ϸ����
public static class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �ж��Ƿ�Ϊ���������Ϊ�������������ǰActivity��׼��ʹ��Fragment��ʾ��ϸ����
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish(); // ������ǰActivity
			return;
		}

		if (savedInstanceState == null) { //
			// �ڳ�ʼ��ʱ����һ����ʾ��ϸ���ݵ�Fragment
			Album details = new Album();// ʵ����DetailFragment�Ķ���
			details.setArguments(getIntent().getExtras()); // ����Ҫ���ݵĲ���
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit(); // ���һ����ʾ��ϸ���ݵ�Fragment
		}
	}
}
}
