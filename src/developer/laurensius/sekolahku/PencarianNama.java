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
public class PencarianNama extends Activity {

	WebView wv_pencariannama;
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
		setContentView(R.layout.activity_pencariannama);
		WebView wv_pencariannama = (WebView)findViewById(R.id.wv_pencariannama);
		wv_pencariannama.getSettings().setJavaScriptEnabled(true);
		wv_pencariannama.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_pencariannama.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){}
        });
		JSInterface = new JavaScriptInterface(this);
		wv_pencariannama.addJavascriptInterface(JSInterface, "JSInterface");
		String uriPencarianNama = getResources().getString(R.string.uri_pencariannama).toString();
		//wv_currentlocation.loadUrl(uriCurrentLocation);
		//---------------------------------------------------------------------------------
		myTracker = new GPSTracker(PencarianNama.this);
		if(myTracker.canGetLocation()){        	
			latitude = myTracker.getLatitude();
		    longitude = myTracker.getLongitude();
		    //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
		}else{
			myTracker.showSettingsAlert();
		}
		
		if(latitude == 0 || longitude == 0){
			latitude  = -6.976000 ;
			longitude = 108.485831;
		}
		
		wv_pencariannama.loadUrl(uriPencarianNama);
	}
	
	
	public void keluarAplikasi(){
		dialogExit = new Dialog(PencarianNama.this);
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
		Intent i = new Intent(getApplicationContext(),MasterMenu.class);
		startActivity(i);
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
	    
	    public void keHasilCariNama(String nama_sekolah){
	    	Intent iKeHasilCariNama = new Intent(getApplicationContext(),HasilCariNama.class);
	    	iKeHasilCariNama.putExtra("nama_sekolah", nama_sekolah);
	    	startActivity(iKeHasilCariNama);
	    }   
	}
}
