package jie.android.ip;

import jie.android.ip.playservice.AndroidPlayService;
import jie.android.ip.playservice.PlayService;
import jie.android.ip.playservice.PlayServiceListener;
import jie.android.ip.setup.AndroidSetup;
import jie.android.ip.setup.Setup;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;

public class MainActivity extends AndroidApplication {
	
    private Setup setup;
    private PlayService playService;
	
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
        
        setup = new AndroidSetup(this.getApplicationContext());
        playService = new AndroidPlayService(this);
        playService.setPlayServiceListener(new PlayServiceListener() {

			@Override
			public void onSignInSucceeded() {
			}

			@Override
			public void onSignInFailed() {
				playService.showErrorDialog();
			}
        	
        });
        
        initialize(new IPGame(setup, playService), cfg);
        
        playService.create();
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
//		Log.d("======", "onActivityResult()" + " request = " + requestCode + " result = " + resultCode);
		playService.onActivityResult(requestCode, resultCode);
	}
    
}