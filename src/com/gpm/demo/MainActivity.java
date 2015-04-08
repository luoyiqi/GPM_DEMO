package com.gpm.demo;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gpm.demo.sdk.AdverDialog;
import com.gpm.demo.sdk.JsonObject;
import com.gpm.demo.sdk.JsonTools;
import com.gpm.demo.sdk.WebRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends ActionBarActivity {
	
	private String TAG="MainActivity";
	
	private Button bannerBtn;
	
	private Button allBtn;
	
	private Button insertBtn;
	
	private Button openBtn;
	
	private Context myContext;
	
	private Thread mThread;
	
	private String imgUrl;
	
	private ImageView mDialogImgView;
	
	private ImageView mainImageView;
	
	private ImageView bannerImageView;
	
	private ImageView allImageView;
	
	private LinearLayout btnLayout;
	
	
	private RelativeLayout allLayout;
	
	
	private RelativeLayout bannerLayout;
	
	private ImageButton bannerCloseBtn;
	
	private ImageButton allCloseBtn;
	
	private ProgressDialog progressDialog;
	
	private int btnCode;
	
	private WebView mWebView;
	
	private String redirectUrl;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		initView();
//		setActionBarContentView(R.layout.web_view);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private void initView(){
		
		this.bannerBtn = (Button)this.findViewById(R.id.bannerBtn);
		
		this.allBtn = (Button)this.findViewById(R.id.allBtn);
		
		this.insertBtn = (Button)this.findViewById(R.id.insertBtn);
		
		this.openBtn = (Button)this.findViewById(R.id.openBtn);
		
		
		this.bannerImageView = (ImageView)this.findViewById(R.id.banner_img);
		
		this.allImageView = (ImageView)this.findViewById(R.id.all_img);
		
		this.btnLayout = (LinearLayout) this.findViewById(R.id.btn_view);
		
		this.allLayout = (RelativeLayout) this.findViewById(R.id.all_view);
		
		this.bannerLayout = (RelativeLayout) this.findViewById(R.id.banner_layout);
		
		this.bannerCloseBtn = (ImageButton) this.findViewById(R.id.banner_close_btn);
		
		this.allCloseBtn = (ImageButton) this.findViewById(R.id.all_close_btn);
		
		this.myContext = this;
		
		this.bannerBtn.setOnClickListener(new MainClickListener());
		
		this.allBtn.setOnClickListener(new MainClickListener());
		
		this.insertBtn.setOnClickListener(new MainClickListener());
		
		this.openBtn.setOnClickListener(new MainClickListener());
		
		this.bannerCloseBtn.setOnClickListener(new MainClickListener());
		
		this.allCloseBtn.setOnClickListener(new MainClickListener());
		
		this.allImageView.setOnClickListener(new AdverClickListener());
		
		this.bannerImageView.setOnClickListener(new AdverClickListener());
		
		
		
		this.allImageView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				
            	btnLayout.setVisibility(View.VISIBLE);
            	allLayout.setVisibility(View.GONE);
				
				return false;
			}
		});
		
	}
	
	
	// 实现自己的oncick事件
	class MainClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			int clickId = v.getId();
			btnCode = clickId;
			
			switch (clickId) {
			case R.id.banner_close_btn:
				
				bannerLayout.setVisibility(View.GONE);
				
				
				break;
				
			case R.id.all_close_btn:
				
				allLayout.setVisibility(View.GONE);
				btnLayout.setVisibility(View.VISIBLE);
				
				break;

			default:
				
				progressDialog = ProgressDialog.show(MainActivity.this, "", "连接中,请稍后..", true, true);
				
				progressDialog.show();
				
				WebRestClient.post("http://apps.game.qq.com/client_pop/api.php?action=doMobileAdJudge",responseHandler);
				
				
				
				break;
			}

		}

	}
	
	
	
	/**
	 * 广告点击事件
	 * @author gavinwen
	 *
	 */
	class AdverClickListener implements OnClickListener {
		
		@Override
		public void onClick(View v) {
			
//			// 实例化 Bundle，设置需要传递的参数 
//	        Bundle bundle = new Bundle(); 
//	        bundle.putString("redirectUrl", redirectUrl);
	        
	        // 在某个按钮响应事件里 
	        Intent intent = new Intent(myContext, WebViewActivity.class); 
	        intent.putExtra("redirectUrl", redirectUrl);
	         
	        startActivityForResult(intent, 0); 

		}
		
	}
	
	AsyncHttpResponseHandler fileHandler = new AsyncHttpResponseHandler(){
		
		@Override  
	    public void onSuccess(int statusCode, Header[] headers,  byte[] responseBody) {  
			
			
			progressDialog.dismiss();
			switch (btnCode) {
			//banner
			case R.id.bannerBtn:
				bannerImageView.setImageBitmap(BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length));
				
				bannerLayout.setVisibility(View.VISIBLE);
				
				break;
			case R.id.allBtn:
				
				btnLayout.setVisibility(View.GONE);
				allLayout.setVisibility(View.VISIBLE);
				allImageView.setImageBitmap(BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length));
				
				break;
				
			case R.id.insertBtn:
				
				//初始化一个自定义的Dialog
	            final AdverDialog dialog=new AdverDialog(myContext, R.style.adverDialog, R.layout.adver_dialog);
	            dialog.show();
	            
	            
	            mDialogImgView = (ImageView)dialog.getWindow().getDecorView().findViewById(R.id.adver_img);

	            
	            ImageButton  closeBtn  = (ImageButton)dialog.getWindow().getDecorView().findViewById(R.id.dialog_close_btn);
	            
	            mDialogImgView.setImageBitmap(BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length));
	            
	            closeBtn.setOnClickListener(new OnClickListener(){
	            	
	            	@Override
	        		public void onClick(View v) {
	            		dialog.dismiss();
	        		}
	            	
	            });
	            
	            mDialogImgView.setOnClickListener(new AdverClickListener());
	            
	            
				break;
				
				
			//开屏，5秒之后消失	
			case R.id.openBtn:
				
				btnLayout.setVisibility(View.GONE);
				allLayout.setVisibility(View.VISIBLE);
				allImageView.setImageBitmap(BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length));
				
				Timer timer = new Timer();  
				 
				
				final Handler handler = new Handler(){
					public void handleMessage(Message msg) {  
			            switch (msg.what) {      
			            case 1:      
			                
			            	btnLayout.setVisibility(View.VISIBLE);
			            	allLayout.setVisibility(View.GONE);
			            	
			                break;      
			            }      
			            super.handleMessage(msg);  
			        }  
			          
			    };  
			    
			    
			    TimerTask task = new TimerTask(){    
			        public void run() {  
			            Message message = new Message();      
			            message.what = 1;      
			            handler.sendMessage(message);    
			        }            
			    };  
				
			    timer.schedule(task, 5000);  
			    
				break;
				
				
			default:
				
				break;
			}
			
            
	    }  
		


		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			
			Toast.makeText(myContext, "请检查网络连接", Toast.LENGTH_SHORT);
			
			WebRestClient.post(imgUrl, fileHandler);
		}
	};
	
	
	
	AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {

		@Override
		public void onFailure(int arg0, Header[] arg1, byte[] arg2,
				Throwable arg3) {
			Toast.makeText(myContext, "请检查网络连接", Toast.LENGTH_SHORT);
			
			WebRestClient.post("http://apps.game.qq.com/client_pop/api.php?action=doMobileAdJudge",responseHandler);
			
			
		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
			
			String content;
			try {
				content = new String(arg2, "UTF-8");
				JsonObject jsonObj = JsonTools.jsonToObject(content,
						JsonObject.class);
				
				
				redirectUrl = jsonObj.getAdverts().get(0).get("redirect_url");
				imgUrl = jsonObj.getAdverts().get(0).get("request_url");
				
				
				WebRestClient.post(imgUrl, fileHandler);
			} catch (UnsupportedEncodingException e) {
				Toast.makeText(myContext, "格式化json错误", Toast.LENGTH_SHORT);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		}
		

	};
	
	
	

	
	
	
}
