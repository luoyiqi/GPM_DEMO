package com.gpm.demo.sdk;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.gpm.demo.R;

/**
 * 广告activity
 * @author gavinwen
 *
 */
public class AdverActivity extends Activity{
	
	
	private Context mContext;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.mContext = this;
        
        Bundle extras = getIntent().getExtras(); 
        String redirectUrl = extras.getString("redirectUrl");
        
        byte[] imgByte = extras.getByteArray("imgByte");
        
        boolean isSplash = extras.getBoolean("isSplash");
        

        
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        
        LinearLayout linearLayout = new LinearLayout(this);
		
        
        LayoutParams lineParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
        
		LayoutParams imgParams = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
		
		
		ImageView imageView = new ImageView(this);
		
		imageView.setImageBitmap(bitmap);
		
		
		ImageButton closeBtn = new ImageButton(this);
		
		closeBtn.setBackgroundResource(R.drawable.close_btn);
		
		LayoutParams closeParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		closeParams.gravity= Gravity.RIGHT;
		
		
	
		linearLayout.addView(closeBtn,closeParams);
		
		
		linearLayout.addView(imageView,imgParams);
		
		
		linearLayout.setVisibility(View.VISIBLE);
		
		
		imageView.setOnClickListener(new AdManager.AdverClickListener());
		
		closeBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Activity)mContext).finish();
			}
		});
        
        
        this.addContentView(linearLayout, lineParams);
        
        
        //如果是开屏，那么定时任务开始
        if(isSplash){
        	
        	int showTime = extras.getInt("showTime");
        	
        	Timer timer = new Timer();  
			 
			
			final Handler handler = new Handler(){
				public void handleMessage(Message msg) {  
		            switch (msg.what) {      
		            case 1:      
		            	((Activity)mContext).finish();
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
			
		    timer.schedule(task, showTime*1000);  
		    
        }
        
        
       
    }  
    


}
