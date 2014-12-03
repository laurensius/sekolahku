package developer.laurensius.sekolahku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
//import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class MasterMenu extends Activity {

	WebView wv_mastermenu;
	GPSTracker myTracker;
	
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

}
