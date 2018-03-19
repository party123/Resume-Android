package com.bluemor.reddotface.activity;
import com.bluemor.reddotface.R;

import android.R.integer;
import android.app.Activity;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.graphics.Matrix;

import android.os.Bundle;

import android.util.DisplayMetrics;

import android.view.View;
import android.view.WindowManager;

import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import android.widget.LinearLayout;

import android.widget.ZoomControls;

public class look extends Activity {

    private LinearLayout layout1;

    private ZoomControls zoom;

    private ImageView img;

    private int id=0;

    private int displayWidth;

    private int displayHeight;

    private float scaleWidth = 1;

    private float scaleHeight = 1;

    private Bitmap bmp;
    private int count=5;
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.look);
        
         

        
        //ȡ����Ļ�ֱ��ʴ�С

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        displayWidth = dm.widthPixels;

        //��Ļ�߶ȼ�ȥzoomControls�ĸ߶�

        displayHeight = dm.heightPixels;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.img02);


        //zoom.hide();����zoomControls

        //zoom.show();��ʾzoomCOntrols

         

        zoom = (ZoomControls)findViewById(R.id.zoomcontrol);

        img = (ImageView)findViewById(R.id.imgview);

        zoom.setIsZoomInEnabled(true);

        zoom.setIsZoomOutEnabled(true);
 
        //ͼƬ�Ŵ�

        zoom.setOnZoomInClickListener(new OnClickListener()

        {

            public void onClick(View v)

            {
               
                int bmpWidth = bmp.getWidth();

                int bmpHeight = bmp.getHeight();
               
                if(count<5){
                //����ͼƬ�Ŵ󵫱���

                double scale = 1.25;

                //�������Ҫ�Ŵ�ı���

                scaleWidth = (float)(scaleWidth*scale);

                scaleHeight = (float)(scaleHeight*scale);
                
                count+=1;
                //�����µĴ�С��Bitmap����
                }
                else{
                	Toast.makeText(getApplicationContext(), "��ǰ�����", 5).show();
                }
                Matrix matrix = new Matrix();

                matrix.postScale(scaleWidth, scaleHeight);
             
               
                Bitmap resizeBmp = 

                Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);

                img.setImageBitmap(resizeBmp);

       
               

            }

        });

        //ͼƬ��С
        zoom.setOnZoomOutClickListener(new OnClickListener()

        {

 

            public void onClick(View v) {

                int bmpWidth = bmp.getWidth();

                int bmpHeight = bmp.getHeight();
                if(count>0){

                //����ͼƬ�Ŵ󵫱���

                double scale = 0.8;

                //�������Ҫ�Ŵ�ı���

                scaleWidth = (float)(scaleWidth*scale);

                scaleHeight = (float)(scaleHeight*scale);
                count-=1;
                //�����µĴ�С��Bitmap����
                }
                else{
                	Toast.makeText(getApplicationContext(), "��ǰ����С", 5).show();
                }
                Matrix matrix = new Matrix();

                matrix.postScale(scaleWidth, scaleHeight);

                Bitmap resizeBmp = 
                     Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);

                img.setImageBitmap(resizeBmp);
             
                

            }

             

        });

    }
}
