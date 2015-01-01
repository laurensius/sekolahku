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
public class HasilCariNama extends Activity {

	WebView wv_hasilcarinama;
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
		setContentView(R.layout.activity_hasilcarinama);
		wv_hasilcarinama = (WebView)findViewById(R.id.wv_hasilcarinama);
		wv_hasilcarinama.getSettings().setJavaScriptEnabled(true);
		wv_hasilcarinama.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_hasilcarinama.setWebViewClient(new WebViewClient() {
			public void onPageFinished(WebView view, String url){}
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            	wv_hasilcarinama.loadUrl(getResources().getString(R.string.uri_onerror).toString());
            }
        });
		JSInterface = new JavaScriptInterface(this);
		wv_hasilcarinama.addJavascriptInterface(JSInterface, "JSInterface");
		String uriHasilCariNama = getResources().getString(R.string.uri_hasilcarinama).toString();
		//wv_currentlocation.loadUrl(uriCurrentLocation);
		//---------------------------------------------------------------------------------
		myTracker = new GPSTracker(HasilCariNama.this);
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
		
		Intent i = getIntent();
		String nama_sekolah = i.getStringExtra("nama_sekolah");
		
		wv_hasilcarinama.loadUrl(uriHasilCariNama+"/"+latitude+"/"+longitude+"/"+nama_sekolah);
		
		
	}
	
	public void keluarAplikasi(){
		dialogExit = new Dialog(HasilCariNama.this);
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
	    
	    public void keDetailHasilCariNama(String id,String lat,String lng){
	    	Intent iKeDetailHasilCariNama = new Intent(getApplicationContext(),DetailHasilCariNama.class);
	    	iKeDetailHasilCariNama.putExtra("latitude", lat);
	    	iKeDetailHasilCariNama.putExtra("longitude", lng);
	    	iKeDetailHasilCariNama.putExtra("id", id);
	    	startActivity(iKeDetailHasilCariNama);
	    }
	}

}
