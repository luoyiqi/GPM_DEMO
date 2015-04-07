package com.gpm.demo;

import org.apache.http.Header;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.gpm.demo.sdk.JsonObject;
import com.gpm.demo.sdk.JsonTools;
import com.gpm.demo.sdk.WebRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends ActionBarActivity {
	
	private String TAG="MainActivity";
	
	private Button topBtn;
	
	private Button midBtn;
	
	private Button bottomBtn;
	
	private Context myContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		initView();
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
		
		this.topBtn = (Button)this.findViewById(R.id.topBtn);
		
		this.midBtn = (Button)this.findViewById(R.id.midBtn);
		
		this.bottomBtn = (Button)this.findViewById(R.id.bottomBtn);
		
		this.myContext = this;
		
		this.topBtn.setOnClickListener(new MainClickListener());
		
		this.midBtn.setOnClickListener(new MainClickListener());
		
		this.bottomBtn.setOnClickListener(new MainClickListener());
		
	}
	
	
	//记载广告
	private void loadAdverts(String position){
		
		WebRestClient.post("http://gavinwen.me/misc/gpm/index.php",responseHandler);
		
		if(position.equals("top")){
			
			
		}
		else if(position.equals("mid")){
			
			
		}
		else if(position.equals("bottom")){
			
			
		}
		else{
			
			
		}
		
		
		
		
		
	}
	
	
	
	// 实现自己的oncick事件
	class MainClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int clickId = v.getId();
			switch (clickId) {
			case R.id.topBtn:
				break;
			case R.id.midBtn:

				break;
				
			case R.id.bottomBtn:

				break;
			default:
				
				break;
			}

		}

	}
	
	
	
	AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {

		@Override
		@Deprecated
		public void onFailure(Throwable error, String content) {
			Log.i(TAG, content);
			super.onFailure(error, content);
			

		}

		@Override
		@Deprecated
		public void onSuccess(int statusCode, Header[] headers, String content) {
			Log.i(TAG, content);
			JsonObject jsonObj = JsonTools.jsonToObject(content,
					JsonObject.class);
			

		}

	};
}
