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
public class DetailOther extends Activity {

	WebView wv_detailother;
	GPSTracker myTracker;
	JavaScriptInterface JSInterface;
	Dialog dialogExit;
	Button btnExitYa, btnExitTidak;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_detailother);
		wv_detailother = (WebView)findViewById(R.id.wv_detailother);
		wv_detailother.getSettings().setJavaScriptEnabled(true);
		wv_detailother.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_detailother.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            	wv_detailother.loadUrl(getResources().getString(R.string.uri_onerror).toString());
            }
        });
		JSInterface = new JavaScriptInterface(this);
		wv_detailother.addJavascriptInterface(JSInterface, "JSInterface");
		String uriDetailOther = getResources().getString(R.string.uri_detailother).toString();
		//wv_currentlocation.loadUrl(uriCurrentLocation);
		//---------------------------------------------------------------------------------
		
		String longitude, latitude, id;
		Intent i = getIntent();
		latitude = i.getStringExtra("latitude");
		longitude = i.getStringExtra("longitude");
		id = i.getStringExtra("id");
		wv_detailother.loadUrl(uriDetailOther+ "/" + latitude + "/" + longitude + "/" + id);
		
		
	}
	
	public void keluarAplikasi(){
		dialogExit = new Dialog(DetailOther.this);
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
	
	@Override
	public void onBackPressed(){
		finish();
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
	    
	    public void keTentang(){
	    	Intent iKeTentang = new Intent(getApplicationContext(),Tentang.class);
	    	startActivity(iKeTentang);
	    	finish();
	    }
	    
	    public void keLuar(){
	    	keluarAplikasi();
	    }
	}
}
