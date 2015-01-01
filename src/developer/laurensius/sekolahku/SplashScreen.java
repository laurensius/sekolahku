package developer.laurensius.sekolahku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_splashscreen);
		
		WebView wv_splashscreen = (WebView)findViewById(R.id.wv_splashscreen);
		wv_splashscreen.getSettings().setJavaScriptEnabled(true);
		wv_splashscreen.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_splashscreen.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){
				Thread timer = new Thread() {
					public void run() {
						try {
							sleep(3500);
							finish();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} finally {
							Intent i = new Intent(getApplicationContext(),MasterMenu.class);
							startActivity(i);
							finish();
						}
					}
				};
				timer.start();
			}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){}
        });
		String uriSplashScreen = getResources().getString(R.string.uri_splashscreen).toString();
		wv_splashscreen.loadUrl(uriSplashScreen);
	}
}
