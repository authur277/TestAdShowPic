package com.example.testadshowpic;



import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ShowAnimationActivity extends Activity {

	private final String TAG = "ShowAnimationActivity";
	ImageView AdPic1;
	ImageView AdPic2;
	ImageView AdPic3;
	
	private int screenHeight ;
	private int screenWidth;
	private int imgWidth;
	private int imgHeight;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_show_animation);
		
		Button BeginShowOne = (Button) findViewById(R.id.BeginShowOne);
		//Button BeginShowTwo = (Button) findViewById(R.id.BeginShowTwo);
		
		AdPic1 = (ImageView) findViewById(R.id.AdPic1);
		//AdPic2 = (ImageView) findViewById(R.id.AdPic2);
		//AdPic3 = (ImageView) findViewById(R.id.AdPic3);
		
		//设置图片的位置
		MarginLayoutParams margin9 = new MarginLayoutParams(
				AdPic1.getLayoutParams());
		margin9.setMargins(200, 200, 0, 0);//在左边距400像素，顶边距10像素的位置显示图片
		RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(margin9);
		layoutParams9.height = 600;//设置图片的高度
		layoutParams9.width = 800; //设置图片的宽度
		AdPic1.setLayoutParams(layoutParams9);
		
		BeginShowOne.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				
				ShowRight();
				
				ShowLeft();
			}
		});
		/*
		BeginShowTwo.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				
				
				
				ShowLeft();
			}
		});*/
		/*
		RelativeLayout relative = ( RelativeLayout) findViewById( R.id.ShowAnimationLayout );
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( 50, 50 ); //设置ImageView的宽和高  
		params.leftMargin = 100;  //设置坐标  
		params.topMargin = 100;  
		relative.addView( AdPic1, params ); */ 
	}

	@SuppressLint("NewApi")
	private void ShowRight() {
		
		//set the showing size of the image 
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(AdPic1, "scaleX",
				0.25f, 0.5f);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(AdPic1, "scaleY",
				0.25f, 0.5f);
		
		//
		//float cy = AdPic1.getY();
		//Toast.makeText(ShowAnimationActivity.this, "cy is" + cy, 100).show();
		ObjectAnimator anim3 = ObjectAnimator.ofFloat(AdPic1,
				"translationX",  0f, -100f );
		ObjectAnimator anim4 = ObjectAnimator.ofFloat(AdPic1,
				"translationY", 0f, 100f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.setDuration(2000);
		animSet.setInterpolator(new LinearInterpolator());
		//两个动画同时执行
		animSet.playTogether(anim1, anim2);
		animSet.playTogether(anim3, anim4);
		//animSet.play(anim3);
		//animSet.play(anim4);
		//animSet.play(anim4).after(anim3);
//		animSet.playSequentially(items)
		animSet.start();
	}
	
	@SuppressLint("NewApi")
	private void ShowLeft() {
		//set the showing size of the image 
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(AdPic1, "scaleX",
				0.5f, 1f);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(AdPic1, "scaleY",
				0.5f, 1f);
		
		//
		//float cy = AdPic1.getY();
		//Toast.makeText(ShowAnimationActivity.this, "cy is" + cy, 100).show();
		ObjectAnimator anim3 = ObjectAnimator.ofFloat(AdPic1,
				"translationX",  0f, -200f );
		ObjectAnimator anim4 = ObjectAnimator.ofFloat(AdPic1,
				"translationY", 0f);
		AnimatorSet animSet = new AnimatorSet();
		animSet.setDuration(2000);
		animSet.setInterpolator(new LinearInterpolator());
		//两个动画同时执行
		animSet.playTogether(anim1, anim2);
		animSet.playTogether(anim3, anim4);
		//animSet.play(anim3);
		//animSet.play(anim4);
		//animSet.play(anim4).after(anim3);
//				animSet.playSequentially(items)
		animSet.start();
	}
}
