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
        //����WebView���ԣ��ܹ�ִ��Javascript�ű�
       webSettings.setJavaScriptEnabled(true);
        //���ÿ��Է����ļ�
       webSettings.setAllowFileAccess(true);
        //����֧������
       webSettings.setBuiltInZoomControls(true);
        //������Ҫ��ʾ����ҳ
       webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        //WebView����web��Դ
       webView.loadUrl("http://weibo.com/u/1738269025");
        //����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
       webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
             view.loadUrl(url);
            return true;
        }
       });
    	}
    	else{
			Toast.makeText(getApplicationContext(),
					"������������^_^", Toast.LENGTH_LONG)
					.show();
		}
    }
    
}
