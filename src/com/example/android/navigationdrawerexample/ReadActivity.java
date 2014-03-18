package com.example.android.navigationdrawerexample;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ReadActivity extends Activity {

	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		final String message = intent.getStringExtra("bookName");
		
		
		final WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.addJavascriptInterface(this, "Android");
        myWebView.loadUrl("file:///android_asset/read.html");
        
		myWebView.setWebViewClient(new WebViewClient(){
		    public void onPageFinished(WebView view, String url){  
		    	
		    	myWebView.loadUrl("javascript:setName('"+message+"')");
		    }           
		});
	}

	

}
