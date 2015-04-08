package com.gpm.demo.sdk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.gpm.demo.R;

public class AdverDialog extends Dialog {

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
    
    public AdverDialog(Context context, int theme,int resLayout){
        super(context, theme);
        this.context = context;
        this.layoutRes=resLayout;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.adver_dialog);
    }

}