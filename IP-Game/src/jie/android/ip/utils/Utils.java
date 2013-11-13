package jie.android.ip.utils;

public class Utils {

	public static void logDebug(final String text) {
		Utils.log("DEBUG", text);
	}

	public static void log(final String tag, final String text) {
		System.out.println("[" + tag + "]:" + text);
	}

}
