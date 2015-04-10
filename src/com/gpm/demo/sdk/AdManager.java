package com.gpm.demo.sdk;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import com.gpm.demo.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * 基础广告sdk
 * @author gavinwen
 *
 */
public class AdManager {
	
	
	private static String mAdUrl = "http://apps.game.qq.com/client_pop/api.php?action=doMobileAdJudge";
	
	
	private static AdManager mAdManager=null;
	
	private static Context mContext;
	
	private static String mImgUrl=null;
	
	private static String mRedirectUrl=null;
	
	private static Bitmap mBitmap = null;
	
	private static byte[] mImgByte ;
	
	private static ProgressDialog progressDialog;
	

	
	
	public static AdManager getInstance(Context context){
		
		mContext = context;
		
		if(mAdManager==null){
			
			return new AdManager();
		}
		
		return mAdManager;
		
	}
	
	
	
	//初始化
	public void init(){
		
		
		
	}
	
	
	/**
	 * 加载广告
	 */
	public void loadAds(){
		
//		progressDialog = ProgressDialog.show(mContext, "", "加载中..", true, true);
//		
//		progressDialog.show();
		
		WebRestClient.get(mAdUrl, jsonHttpResponseHandler);
	}
	
	
	/**
	 * 弹出广告框
	 */
	public void showAdverDialog(){
		
		//预先加载广告地址和图片地址
		if(mImgUrl==null || mRedirectUrl==null || mBitmap==null){
			loadAds();
		}
		else{
			//初始化一个自定义的Dialog
	        final AdverDialog dialog=new AdverDialog(mContext, R.style.adverDialog, R.layout.adver_dialog,mRedirectUrl,mBitmap);
	        dialog.show();
	        
		}
        
       
		
	}
	
	
	/**
	 * banner广告
	 */
	public void showBannerAdver(){
		
		
		//获取调用者的view
		View view = ((Activity)mContext).getWindow().getDecorView().findViewById(android.R.id.content);
		
		
		final LinearLayout bannerLayout = (LinearLayout)view.findViewById(R.id.bannerAdLayout);
		
		
		LayoutParams imgParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		
		
		ImageView imageView = new ImageView(mContext);
		
		imageView.setImageBitmap(mBitmap);
		
		
		ImageButton closeBtn = new ImageButton(mContext);
		
		closeBtn.setBackgroundResource(R.drawable.close_btn);
		
		LayoutParams closeParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		closeParams.gravity= Gravity.RIGHT;
		
		
	
		bannerLayout.addView(closeBtn,closeParams);
		
		
		bannerLayout.addView(imageView,imgParams);
		
		bannerLayout.setVisibility(View.VISIBLE);
		
		imageView.setOnClickListener(new AdverClickListener());
		
		
		closeBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bannerLayout.setVisibility(View.GONE);
				
			}
		});
		
	}
	
	
	//全屏广告
	public void showFullScreenAdver(){
		
		
        Intent intent = new Intent(mContext, AdverActivity.class); 
        intent.putExtra("redirectUrl", mRedirectUrl);
        intent.putExtra("imgByte", mImgByte);
        intent.putExtra("isSplash", false);
        
        mContext.startActivity(intent);
		
	}
	
	
	/**
	 * 开屏广告
	 * @param time
	 * @param splashAdver
	 */
	public void showSplashAdver(int time){
		
        Intent intent = new Intent(mContext, AdverActivity.class); 
        intent.putExtra("redirectUrl", mRedirectUrl);
        intent.putExtra("imgByte", mImgByte);
        intent.putExtra("isSplash", true);
        
        intent.putExtra("showTime", time);
        
        mContext.startActivity(intent);
		
	}
	
	
	
	JsonHttpResponseHandler jsonHttpResponseHandler = new JsonHttpResponseHandler(){
		
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        	try {
        		if(response.getInt("code")!=200){
					Toast.makeText(mContext, String.valueOf(response.get("msg")), Toast.LENGTH_SHORT).show();
        		}
        		else{
        			
        			JSONObject adverts = (JSONObject)((JSONArray)response.get("adverts")).get(0);
        			
        			//赋值广告图片链接和跳转地址
        			mImgUrl = adverts.getString("request_url");
        			
        			mRedirectUrl = adverts.getString("redirect_url");
        			
        			//请求网络图片
        			WebRestClient.get(mImgUrl, fileHandler);
        			
        		}
        		
				
				
        	} catch (JSONException e) {
				Toast.makeText(mContext, "json解析错误", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
        }
        
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
        	
        }
        
        
		@Override
		public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
			WebRestClient.get(mAdUrl, jsonHttpResponseHandler);
			
		}
		
		
	};
	
	
	/**
	 * 网络图片请求hadler
	 */
	AsyncHttpResponseHandler fileHandler = new AsyncHttpResponseHandler(){
		
		@Override  
	    public void onSuccess(int statusCode, Header[] headers,  byte[] responseBody) {  
			
//			progressDialog.dismiss();
			
			
			mImgByte = responseBody;
			
			mBitmap= BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);
            
	    }  
		


		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			WebRestClient.post(mImgUrl, fileHandler);
		}
	};
	
	
	/**
	 * 广告点击事件
	 * @author gavinwen
	 *
	 */
	public static class AdverClickListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			
			
	        Intent intent = new Intent(mContext, WebViewActivity.class); 
	        intent.putExtra("redirectUrl", mRedirectUrl);
	        
	        mContext.startActivity(intent);
	         

		}
		
	}
	 
	

}
