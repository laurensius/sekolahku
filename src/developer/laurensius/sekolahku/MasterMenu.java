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
public class MasterMenu extends Activity {

	WebView wv_mastermenu;
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
		setContentView(R.layout.activity_mastermenu);
		WebView wv_mastermenu = (WebView)findViewById(R.id.wv_mastermenu);
		wv_mastermenu.getSettings().setJavaScriptEnabled(true);
		wv_mastermenu.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_mastermenu.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){}
        });
		JSInterface = new JavaScriptInterface(this);
		wv_mastermenu.addJavascriptInterface(JSInterface, "JSInterface");
		String uriMasterMenu = getResources().getString(R.string.uri_mastermenu).toString();
		wv_mastermenu.loadUrl(uriMasterMenu);
		//---------------------------------------------------------------------------------
		myTracker = new GPSTracker(MasterMenu.this);
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
		dialogExit = new Dialog(MasterMenu.this);
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
	    public void kePencarian(){}
	    
	    public void keTentang(){
	    	Intent iKeTentang = new Intent(getApplicationContext(),Tentang.class);
	    	startActivity(iKeTentang);
	    	finish();
	    }
	    
	    public void keLuar(){}
	    
	    public void keCurrent(){
	    	Intent iKeCurrent = new Intent(getApplicationContext(),CurrentLocation.class);
	    	startActivity(iKeCurrent);
	    	finish();
	    }
	    
	    public void keDetail(){
	    	
	    }
	}

}
