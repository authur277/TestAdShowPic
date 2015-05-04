package com.example.testadshowpic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

        private WebView webView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
                this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.activity_web_view);
                webView = (WebView) findViewById(R.id.web_view);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setLoadWithOverviewMode(true);
                webView.getSettings().setUseWideViewPort(true);
                webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                // 根据传入的参数再去加载新的网页
                                view.loadUrl(url);
                                // 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器
                                return true;
                        }
                });
                webView.loadUrl("http://114.215.168.231/pingan/baby.php?channel=123456");
        }

}
