package com.example.testadshowpic;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	public static int[] image = { R.drawable.img1, 
								  R.drawable.img2, 
								  R.drawable.img3,  
								   };  
	private Context context;  

	public ImageAdapter(Context context) {  
		this.context = context;  
	}  

	@Override  
	public int getCount() {  
		// TODO Auto-generated method stub  
		return image.length;  
	}  

	@Override  
	public Object getItem(int arg0) {  
		// TODO Auto-generated method stub  
		return arg0;  
	}  

	@Override  
	public long getItemId(int arg0) {  
		// TODO Auto-generated method stub  
		return arg0;  
	}  

	@Override  
	public View getView(int arg0, View arg1, ViewGroup arg2) {  
		// TODO Auto-generated method stub  
	    ImageView imageView = new ImageView(context);  
	    imageView.setImageResource(image[arg0]);  
	    // 保持宽高比,不设置则gallery显示一张图片  
	    imageView.setAdjustViewBounds(true);  
	    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);  
	    imageView.setLayoutParams(new Gallery.LayoutParams(500/2, 500/2));  
	    return imageView;  
	}  
}