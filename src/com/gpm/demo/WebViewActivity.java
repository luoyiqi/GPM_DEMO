package com.gpm.demo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewActivity extends Activity{

	private WebView webView;
	
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.web_view);  
        webView = (WebView) findViewById(R.id.webView1);  
          
     	webView.getSettings().setJavaScriptEnabled(true);//����ʹ�ù�ִ��JS�ű�  
        webView.getSettings().setBuiltInZoomControls(true);//����ʹ֧������ 
        
        webView.setInitialScale(25);//Ϊ25%����С���ŵȼ� 
        
        Bundle extras = getIntent().getExtras(); 
        String redirectUrl = extras.getString("redirectUrl");
        
        webView.loadUrl(redirectUrl);
        
        
 
        
        webView.setWebViewClient(new WebViewClient(){
            @Override  
            public boolean shouldOverrideUrlLoading(WebView view, String url) {  
                view.loadUrl(url);// ʹ�õ�ǰWebView������ת  
                return true;//true��ʾ���¼��ڴ˴�����������Ҫ�ٹ㲥  
            }  
            @Override   //ת�����ʱ�Ĵ���  
            public void onReceivedError(WebView view, int errorCode,  
                String description, String failingUrl) {    
                Toast.makeText(WebViewActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();  
            }  
        });  
    }  
    
    /*
    @Override   //Ĭ�ϵ���˼������˳�Activity�����������������ʹ������WebView�ڷ���  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        // TODO Auto-generated method stub  
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {  
            webView.goBack();  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    } 
    */ 


}
