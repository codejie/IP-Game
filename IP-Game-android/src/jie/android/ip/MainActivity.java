package jie.android.ip;

import jie.android.ip.setup.AndroidSetup;
import jie.android.ip.setup.Setup;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	@SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        WindowManager.LayoutParams params = getWindow().getAttributes();
	        params.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN;// | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;// | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
	        getWindow().setAttributes(params);
        }
        
        final Setup setup = new AndroidSetup(this.getApplicationContext());
        initialize(new IPGame(setup), cfg);
    }
    
}