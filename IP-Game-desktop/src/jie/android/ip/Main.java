package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.setup.DesktopSetup;
import jie.android.ip.setup.Setup;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "IP-Game";
		cfg.useGL20 = false;
		cfg.width = (int) ((int) ScreenConfig.WIDTH * 0.33f);
		cfg.height = (int) ((int) ScreenConfig.HEIGHT * 0.33f);//320;
		
		final Setup setup = new DesktopSetup();
		
		new LwjglApplication(new IPGame(setup, null), cfg);
	}
}
