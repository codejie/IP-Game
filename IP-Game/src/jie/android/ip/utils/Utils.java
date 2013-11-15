package jie.android.ip.utils;

import com.badlogic.gdx.Gdx;

public class Utils {

	public static void logDebug(final String text) {
		Utils.log("DEBUG", text);
	}

	public static void log(final String tag, final String text) {
		Gdx.app.log(tag, text);
//		System.out.println("[" + tag + "]:" + text);
	}

}
