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
	private Button shark_button;// ҡһҡ��ť

	private Vibrator mVibrator;// ������
	private ShakeListener mShakeListener = null;
    private int on=1;
	private SoundPool soundPool;// ��Ƶ��
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
		mVibrator = (Vibrator) getApplication().getSystemService(
				VIBRATOR_SERVICE);
		// ����ָ�������ص������Ƶ����ĿΪ10��
		// ����Ʒ��Ϊ5��ҿ����� �����Ը�����Ч��
		soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
		// ������Ƶ��
		hitOkSfx = soundPool.load(this, R.raw.shake, 0);

		initView();
		 
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
	private void initView() {
		shark_button = (Button) findViewById(R.id.button);
		mShakeListener = new ShakeListener(this);

		setListener();
	}

	/**
	 * ���ü�����
	 */
	private void setListener() {
		// ҡһҡ��ť
		shark_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setShark();
			}
		});
		// ����ҡ��
		mShakeListener.setOnShakeListener(new OnShakeListener() {

			@Override
			public void onShake() {
				setShark();
			}
		});
	}

	/**
	 * ҡһҡ
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
			Toast.makeText(getApplicationContext(), "��ǰ�����һҳ^_^", 5).show();
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
	 * ��������
	 */
	private void setAnim() {
		Animation operatingAnim = AnimationUtils.loadAnimation(this,
				R.anim.shark_anim);
		
	}

	/**
	 * ������
	 */
	public void startVibrato() {
		// ��һ�����������ǽ������飬 �ڶ����������ظ�������-1Ϊ���ظ�����-1���մ�pattern��ָ���±꿪ʼ�ظ�
		mVibrator.vibrate(new long[] { 500, 200, 500, 200 }, -1);
		// ������Ƶ�����Զ����������ֱ����ã��������������ȼ���ѭ�������Լ�����
		// �������0.5���Ϊ2��1���� �����ٶ�
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
