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
        
         

        
        //取得屏幕分辨率大小

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        displayWidth = dm.widthPixels;

        //屏幕高度减去zoomControls的高度

        displayHeight = dm.heightPixels;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.img02);


        //zoom.hide();隐藏zoomControls

        //zoom.show();显示zoomCOntrols

         

        zoom = (ZoomControls)findViewById(R.id.zoomcontrol);

        img = (ImageView)findViewById(R.id.imgview);

        zoom.setIsZoomInEnabled(true);

        zoom.setIsZoomOutEnabled(true);
 
        //图片放大

        zoom.setOnZoomInClickListener(new OnClickListener()

        {

            public void onClick(View v)

            {
               
                int bmpWidth = bmp.getWidth();

                int bmpHeight = bmp.getHeight();
               
                if(count<5){
                //设置图片放大但比例

                double scale = 1.25;

                //计算这次要放大的比例

                scaleWidth = (float)(scaleWidth*scale);

                scaleHeight = (float)(scaleHeight*scale);
                
                count+=1;
                //产生新的大小但Bitmap对象
                }
                else{
                	Toast.makeText(getApplicationContext(), "当前已最大", 5).show();
                }
                Matrix matrix = new Matrix();

                matrix.postScale(scaleWidth, scaleHeight);
             
               
                Bitmap resizeBmp = 

                Bitmap.createBitmap(bmp,0,0,bmpWidth,bmpHeight,matrix,true);

                img.setImageBitmap(resizeBmp);

       
               

            }

        });

        //图片减小
        zoom.setOnZoomOutClickListener(new OnClickListener()

        {

 

            public void onClick(View v) {

                int bmpWidth = bmp.getWidth();

                int bmpHeight = bmp.getHeight();
                if(count>0){

                //设置图片放大但比例

                double scale = 0.8;

                //计算这次要放大的比例

                scaleWidth = (float)(scaleWidth*scale);

                scaleHeight = (float)(scaleHeight*scale);
                count-=1;
                //产生新的大小但Bitmap对象
                }
                else{
                	Toast.makeText(getApplicationContext(), "当前已最小", 5).show();
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
