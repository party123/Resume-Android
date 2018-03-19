package com.bluemor.reddotface.shark;

import com.bluemor.reddotface.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bluemor.reddotface.activity.AlbumActivity2;
import com.bluemor.reddotface.activity.MainActivity;
import com.bluemor.reddotface.activity.jie;
import com.bluemor.reddotface.shark.ShakeListener.OnShakeListener;
import com.bluemor.reddotface.view.Album;
public class shark extends Activity {
	private Button shark_button;// 摇一摇按钮

	private Vibrator mVibrator;// 开启震动
	private ShakeListener mShakeListener = null;
    private int on=1;
	private SoundPool soundPool;// 音频池
	private int hitOkSfx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
    
		setContentView(R.layout.shark);
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
		mVibrator = (Vibrator) getApplication().getSystemService(
				VIBRATOR_SERVICE);
		// 这里指定声音池的最大音频流数目为10，
		// 声音品质为5大家可以自 己测试感受下效果
		soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		// 载入音频流
		hitOkSfx = soundPool.load(this, R.raw.shake, 0);

		initView();
		 
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
	private void initView() {
		shark_button = (Button) findViewById(R.id.button);
		mShakeListener = new ShakeListener(this);

		setListener();
	}

	/**
	 * 设置监听器
	 */
	private void setListener() {
		// 摇一摇按钮
		shark_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setShark();
			}
		});
		// 监听摇晃
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake() {
				setShark();
			}
		});
	}

	/**
	 * 摇一摇
	 */
	private void setShark() {
		setAnim();
		mShakeListener.stop();
		startVibrato();
		if(on==1){
		Intent intent = new Intent(shark.this, jie.class);
				startActivity(intent);
				on=0;
		}
		else {
			Toast.makeText(getApplicationContext(), "当前是最后一页^_^", 5).show();
		}
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mVibrator.cancel();
				mShakeListener.start();
			}
		}, 1000);
	}

	/**
	 * 动画设置
	 */
	private void setAnim() {
		Animation operatingAnim = AnimationUtils.loadAnimation(this,
				R.anim.shark_anim);
		
	}

	/**
	 * 定义震动
	 */
	public void startVibrato() {
		// 第一个｛｝里面是节奏数组， 第二个参数是重复次数，-1为不重复，非-1俄日从pattern的指定下标开始重复
		mVibrator.vibrate(new long[] { 500, 200, 500, 200 }, -1);
		// 播放音频，可以对左右音量分别设置，还可以设置优先级，循环次数以及速率
		// 速率最低0.5最高为2，1代表 正常速度
		soundPool.play(hitOkSfx, 1, 1, 0, 0, 1);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mShakeListener != null) {
			mShakeListener.stop();
		}
	}

}
