package com.bluemor.reddotface.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.bluemor.reddotface.R;
public class lian extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lian);             
    Button button1=(Button) findViewById(R.id.button1);
    Button button2=(Button) findViewById(R.id.button2);
    Button button3=(Button) findViewById(R.id.button3);
    Button button4=(Button) findViewById(R.id.button4);
    Button button5=(Button) findViewById(R.id.button5);
    Button button6=(Button) findViewById(R.id.button6);
    button1.setOnClickListener(new OnClickListener() {			
    	@Override
    	public void onClick(View v) {
    		finish();	//返回
    	}
    });
    button2.setOnClickListener(new OnClickListener() {			
    	@Override
    	public void onClick(View v) {
    		Intent intent = new Intent(lian.this, weibo.class);
			startActivity(intent);
    	}
    });
    button3.setOnClickListener(new OnClickListener() {			
    	@Override
    	public void onClick(View v) {
    		Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+"18013871557"));  
    		 lian.this.startActivity(intent);
    	}
    });
    button4.setOnClickListener(new OnClickListener() {			
    	@Override
    	public void onClick(View v) {
    		String phoneNumber = "18013871557";  
    		Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));  
    	    lian.this.startActivity(sendIntent);  
      
    	}
    });
    button5.setOnClickListener(new OnClickListener() {			
    	@Override
    	public void onClick(View v) {
    		Intent intent = new Intent(lian.this, map.class);
			startActivity(intent);
    	}
    });
    button6.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {
    	 // TODO Auto-generated method stub
    	 toContacts();
    	 }

    	 });
    	 }

    	 void toContacts() {

    	 Intent it = new Intent(Intent.ACTION_INSERT, Uri.withAppendedPath(
    	 Uri.parse("content://com.android.contacts"), "contacts"));
    	 it.setType("vnd.android.cursor.dir/person");
    	 // it.setType("vnd.android.cursor.dir/contact");
    	 // it.setType("vnd.android.cursor.dir/raw_contact");
    	 // 联系人姓名
    	 it.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, "党永成");
    	it.putExtra(
    	 android.provider.ContactsContract.Intents.Insert.SECONDARY_PHONE,
    	 "18013871557");

    	startActivity(it);
    	 }
    }
