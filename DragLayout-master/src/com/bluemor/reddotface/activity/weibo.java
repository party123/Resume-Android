package com.bluemor.reddotface.activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bluemor.reddotface.R;

public class weibo extends Activity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weibo);     
        init();

    }
   
    private void init(){
    	ConnectivityManager con=(ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
		boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
    	if(wifi|internet){
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
       webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
       webSettings.setAllowFileAccess(true);
        //设置支持缩放
       webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
       webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        //WebView加载web资源
       webView.loadUrl("http://weibo.com/u/1738269025");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
       webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
             view.loadUrl(url);
            return true;
        }
       });
    	}
    	else{
			Toast.makeText(getApplicationContext(),
					"请检查您的网络^_^", Toast.LENGTH_LONG)
					.show();
		}
    }
    
}
