package com.bluemor.reddotface.activity;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.bluemor.reddotface.R;
import com.bluemor.reddotface.shark.ShakeListener;
import com.bluemor.reddotface.shark.shark;
import com.bluemor.reddotface.shark.ShakeListener.OnShakeListener;
import com.bluemor.reddotface.view.Album;
public class jie extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jie);             
        Toast.makeText(getApplicationContext(), "当前是最后一页^_^", 5).show();
    Button button1=(Button) findViewById(R.id.button1);
    button1.setOnClickListener(new OnClickListener() {			
    	@Override
    	public void onClick(View v) {
    		finish();	//返回
    	}
    });
    }
}