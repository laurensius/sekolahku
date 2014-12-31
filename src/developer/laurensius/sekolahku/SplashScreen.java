package developer.laurensius.sekolahku;

import developer.laurensius.sekolahku.MasterMenu.JavaScriptInterface;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
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
}
