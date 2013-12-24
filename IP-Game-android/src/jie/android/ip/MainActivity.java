package jie.android.ip;

import jie.android.ip.setup.AndroidSetup;
import jie.android.ip.setup.Setup;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        final Setup setup = new AndroidSetup(this.getApplicationContext());
        
        initialize(new IPGame(setup), cfg);
    }
}