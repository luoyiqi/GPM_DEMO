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
          
     	webView.getSettings().setJavaScriptEnabled(true);//设置使用够执行JS脚本  
        webView.getSettings().setBuiltInZoomControls(true);//设置使支持缩放 
        
        webView.setInitialScale(25);//为25%，最小缩放等级 
        
        Bundle extras = getIntent().getExtras(); 
        String redirectUrl = extras.getString("redirectUrl");
        
        webView.loadUrl(redirectUrl);
        
        
 
        
        webView.setWebViewClient(new WebViewClient(){
            @Override  
            public boolean shouldOverrideUrlLoading(WebView view, String url) {  
                view.loadUrl(url);// 使用当前WebView处理跳转  
                return true;//true表示此事件在此处被处理，不需要再广播  
            }  
            @Override   //转向错误时的处理  
            public void onReceivedError(WebView view, int errorCode,  
                String description, String failingUrl) {    
                Toast.makeText(WebViewActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();  
            }  
        });  
    }  
    
    /*
    @Override   //默认点回退键，会退出Activity，需监听按键操作，使回退在WebView内发生  
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
