package jie.android.ip;

public interface CommonConsts {

	public interface SystemConfig {
		public static final String DATABASE_FILE = "ip.db3";
		
		public static final int SYS_ATTR_VERSION = 1;
		public static final int SYS_ATTR_SPEED = 2;
		public static final int SYS_ATTR_MUSIC = 3;
		public static final int SYS_ATTR_SOUND = 4;
	}
	
	public interface ScreenConfig {
		public static final int WIDTH = 1280;//1296;
		public static final int HEIGHT = 768;//832;
	}	
	
	public interface PackConfig {
		public static final String SCREEN_START = "data/screen_start.pack";
		public static final String SCREEN_PLAY = "data/screen_box.pack";
		public static final String SCREEN_MENU = "data/screen_menu.pack";		
		public static final String SCREEN_DIALOG = "data/screen_dialog.pack";
	}
	
	public interface FontConfig {
//		public static final String FONT_18 = "data/font/arial-18.fnt";
//		public static final String FONT_20 = "data/font/arial-20.fnt";
//		public static final String FONT_24 = "data/font/arial-24.fnt";
//		
		public static final String FONT_TRUE = "data/font/segoeuib.ttf";
//		public static final String FONT_TRUE = "data/font/simli.ttf";
		public static final String FONT_TRUE_CHARS = null;
	}

	public interface AudioConfig {
		public static final String MENU_CLICK = "data/sound/menu_click.mp3";
		public static final String TRAY_CATCH = "data/sound/Pickup_Coin106.wav";
		public static final String TRAY_RELEASE = "data/sound/Pickup_Coin119.wav";
		
		public static final String RESULT_SUCC = "data/sound/Pickup_Coin106.wav";
		public static final String RESULT_FAIL = "data/sound/Pickup_Coin119.wav";
		
		public static final String MUSIC_BACKGROUND = "data/sound/Gaiaires.mp3";//DST-DarkFuture.mp3";
	}

}
