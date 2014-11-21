package developer.laurensius.sekolahku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;

@SuppressLint("SetJavaScriptEnabled")
public class MasterMenu extends Activity {

	String uriMasterMenu = getResources().getString(R.string.uri_mastermenu);
	WebView wv_mastermenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*---------------------Menghilangkan Title Bar------------------------------------*/
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		/*--------------------End of Menghilangkan Title Bar------------------------------*/
		setContentView(R.layout.activity_mastermenu);
		wv_mastermenu = (WebView)findViewById(R.id.wv_mastermenu);
		wv_mastermenu.getSettings().setJavaScriptEnabled(true);
		wv_mastermenu.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		wv_mastermenu.loadUrl(uriMasterMenu);
	}

}
