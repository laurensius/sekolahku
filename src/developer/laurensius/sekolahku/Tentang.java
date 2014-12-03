package developer.laurensius.sekolahku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
//import android.widget.Toast;
import android.webkit.WebViewClient;
import android.widget.Button;

@SuppressLint("SetJavaScriptEnabled")
public class Tentang extends Activity {

	WebView wv_tentang;
	GPSTracker myTracker;
	JavaScriptInterface JSInterface;
	Dialog dialogExit;
	Button btnExitYa, btnExitTidak;
	
	double longitude, latitude; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_tentang);
		WebView wv_tentang = (WebView)findViewById(R.id.wv_tentang);
		wv_tentang.getSettings().setJavaScriptEnabled(true);
		wv_tentang.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_tentang.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){}
        });
		JSInterface = new JavaScriptInterface(this);
		wv_tentang.addJavascriptInterface(JSInterface, "JSInterface");
		String uriTentang = getResources().getString(R.string.uri_tentang).toString();
		wv_tentang.loadUrl(uriTentang);
		//---------------------------------------------------------------------------------
		myTracker = new GPSTracker(Tentang.this);
		if(myTracker.canGetLocation()){        	
			latitude = myTracker.getLatitude();
		    longitude = myTracker.getLongitude();
		    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
		}else{
			myTracker.showSettingsAlert();
		}
	}
	
	@Override
	public void onBackPressed(){
		dialogExit = new Dialog(Tentang.this);
		dialogExit.setContentView(R.layout.activity_dialogexit);
		dialogExit.setTitle("Konfirmasi Keluar");
		btnExitYa = (Button) dialogExit.findViewById(R.id.btnExitYa);
		btnExitTidak = (Button) dialogExit.findViewById(R.id.btnExitTidak);
		dialogExit.show();
		
		btnExitYa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btnExitTidak.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogExit.dismiss();
			}
		});
	}
	
	//-----------------------JS Interface------------------------------------
	public class JavaScriptInterface {
	    Context mContext;
	    JavaScriptInterface(Context c) {
	        mContext = c;
	    }
	    public void kePencarian(){
	    	Intent iKePencarian = new Intent(getApplicationContext(),MasterMenu.class);
	    	startActivity(iKePencarian);
	    	finish();
	    }
	    public void keTentang(){}
	    public void keLuar(){}
	}

}
