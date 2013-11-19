package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenConfig;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "IP-Game";
		cfg.useGL20 = false;
		cfg.width = (int) ((int) ScreenConfig.WIDTH * 0.4f);
		cfg.height = (int) ((int) ScreenConfig.HEIGHT * 0.4f);//320;
		
		new LwjglApplication(new IPGame(), cfg);
	}
}
