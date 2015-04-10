package com.gpm.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gpm.demo.sdk.AdManager;

public class MainActivity extends Activity {
	
	private String TAG="MainActivity";
	
	private Button bannerBtn;
	
	private Button allBtn;
	
	private Button insertBtn;
	
	private Button openBtn;
	
	private Context myContext;

	
	
	private ProgressDialog progressDialog;
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		
		initView();
		
		//提前加载数据
		AdManager.getInstance(myContext).loadAds();
		
//		setActionBarContentView(R.layout.web_view);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	
	
	private void initView(){
		
		this.bannerBtn = (Button)this.findViewById(R.id.bannerBtn);
		
		this.allBtn = (Button)this.findViewById(R.id.allBtn);
		
		this.insertBtn = (Button)this.findViewById(R.id.insertBtn);
		
		this.openBtn = (Button)this.findViewById(R.id.openBtn);

		
		this.myContext = this;
		
		this.bannerBtn.setOnClickListener(new MainClickListener());
		
		this.allBtn.setOnClickListener(new MainClickListener());
		
		this.insertBtn.setOnClickListener(new MainClickListener());
		
		this.openBtn.setOnClickListener(new MainClickListener());
		

		
	}
	
	
	// 实现自己的oncick事件
	class MainClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			int clickId = v.getId();
			
			switch (clickId) {
			
			//banner
			case R.id.bannerBtn:
				
				AdManager.getInstance(myContext).showBannerAdver();
				
				break;
			
			//插屏
			case R.id.insertBtn:
				
				AdManager.getInstance(myContext).showAdverDialog();
				
				break;
			//全屏
			case R.id.allBtn:
				
				AdManager.getInstance(myContext).showFullScreenAdver();
				
				break;
			//开屏	
			case R.id.openBtn:
				
				
				AdManager.getInstance(myContext).showSplashAdver(3);
				
				break;
				

			default:

				
				
				break;
			}

		}

	}
	
	
	
	

	
	
	

	
	
	
}
