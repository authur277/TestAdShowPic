package com.example.testadshowpic;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity  {
	
	private ImageAdapter imgAdapter;			// 声明图片资源对象
	private Gallery gallery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		Button button_ShowAd = (Button) findViewById(R.id.button_ShowAd);
		Button button_ShowPopupWindow = (Button) findViewById(R.id.button_ShowPopupWindow);
		Button button_ShowAnimation = (Button) findViewById(R.id.button_ShowAnimation);
		
		button_ShowAd.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				// 以下拉方式显示。
//				popup.showAsDropDown(v);
				//将PopupWindow显示在指定位置
				//popup.showAtLocation(findViewById(R.id.button_ShowAd), Gravity.CENTER, 20,
					//20);
				openThreePicGalleryDialog();
			}
		});
		
		button_ShowPopupWindow.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				openAdDialog();
			}
		});
		
		button_ShowAnimation.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, ShowAnimationActivity.class);
				startActivity(intent);
			}
		});
	}

	private void openThreePicGalleryDialog() {
		View DialogView = View.inflate(this, R.layout.gallery, null);
		// 创建AlertDialog
		final AlertDialog menuDialog = new AlertDialog.Builder(this).create();
		menuDialog.setView(DialogView);
		imgAdapter = new ImageAdapter(MainActivity.this);
		//gallery = new GalleryFlow(MainActivity.this);
		
		gallery = (Gallery) DialogView.findViewById(R.id.gallery);
		gallery.setAdapter(imgAdapter); 					// 设置图片资源
		//gallery.setGravity(Gravity.CENTER_HORIZONTAL);		// 设置水平居中显示
		//gallery.setSelection(imgAdapter.imgs.length * 100);		// 设置起始图片显示位置（可以用来制作gallery循环显示效果）
		
		gallery.setOnItemClickListener(clickListener); 			// 设置点击图片的监听事件（需要用手点击才触发，滑动时不触发）
		gallery.setOnItemSelectedListener(selectedListener);		// 设置选中图片的监听事件（当图片滑到屏幕正中，则视为自动选中）
		gallery.setUnselectedAlpha(0.3f);					// 设置未选中图片的透明度
		gallery.setSpacing(40);							// 设置图片之间的间距
		/*
		menuGrid = (GridView) menuView.findViewById(R.id.gridview);
		menuGrid.setAdapter(getMenuAdapter(menu_name_array, menu_image_array));
		menuGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == 11) {
					menuDialog.cancel();
				}
			}
		});*/
		menuDialog.show();
	}
	
	// 点击图片的监听事件
	AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			//Toast.makeText(MainActivity.this, "点击图片 " + (position + 1), 100).show();
			Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
			startActivity(intent);
		}
	};
		
	// 选中图片的监听事件
	AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			//Toast.makeText(MainActivity.this, "选中图片 " + (position + 1), 20).show();
			ImageView imageview = (ImageView)view;
			//((ImageView) view).setImageDrawable((Drawable) product_image_list.get(position));
	        view.setLayoutParams(new Gallery.LayoutParams(500, 500));
	        //title.setText((String)product_title.get(position));
	        //info.setText((String)product_info.get(position));
	        
	        for(int i=0; i<parent.getChildCount();i++){
		        //缩小选中图片旁边的图片
		        ImageView local_imageview = (ImageView)parent.getChildAt(i);
		        if(local_imageview!=imageview){
		        	local_imageview.setLayoutParams(new Gallery.LayoutParams(500/2, 500/2));
		            local_imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
		        }
	    	}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	};
	
	
	private void openAdDialog() {
		
		View DialogView = View.inflate(this, R.layout.dialog, null);
		// 创建AlertDialog
		final AlertDialog Dialog = new AlertDialog.Builder(this).create();
		Dialog.setView(DialogView);
		final ImageView Adpic = (ImageView) DialogView.findViewById(R.id.AdPic);
		Dialog.show();
	}
}
