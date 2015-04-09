package com.gpm.demo.sdk;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.gpm.demo.R;
import com.gpm.demo.WebViewActivity;

public class AdverDialog extends Dialog {
	
	private ImageView mAdverView;
	
	private ImageButton mCloseBtn;
	
	private Dialog mDialog;

	private String mRedirectUrl;
	
	private Bitmap mBitmap;
	
	int layoutRes;//布局文件
	Context context;
	
	
    public AdverDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    public AdverDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    
    public AdverDialog(Context context, int theme,int resLayout,String redirectUrl,Bitmap bitmap){
        super(context, theme);
        this.context = context;
        this.layoutRes=resLayout;
        this.mDialog = this;
        this.mRedirectUrl = redirectUrl;
        this.mBitmap = bitmap;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adver_dialog);
        
        this.mAdverView = (ImageView)this.findViewById(R.id.adver_img);
        
        this.mAdverView.setImageBitmap(mBitmap);
        
        this.mCloseBtn = (ImageButton)this.findViewById(R.id.dialog_close_btn);
        
        this.mCloseBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
				
			}
		});
        
        
        this.mAdverView.setOnClickListener(new AdverClickListener());
        
        
        
    }
    
    
	/**
	 * 广告点击事件
	 * @author gavinwen
	 *
	 */
	class AdverClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			
	        Intent intent = new Intent(context, WebViewActivity.class); 
	        intent.putExtra("redirectUrl", mRedirectUrl);
	        
	        context.startActivity(intent);
	        

		}
		
	}
    
    
    

}