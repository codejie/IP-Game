package jie.android.ip;

public interface CommonConsts {

	public interface SystemConfig {
		public static final String DATABASE_FILE = "ip.db3";
		
		public static final int SYS_ATTR_VERSION = 1;
	}
	
	public interface ScreenConfig {
		public static final int WIDTH = 1280;
		public static final int HEIGHT = 768;
	}	
	
	public interface PackConfig {
		public static final String SCREEN_START = "data/screen_start.pack";
		public static final String SCREEN_PLAY = "data/screen_box.pack";
		public static final String SCREEN_MENU = "data/screen_box.pack";//"data/screen_menu.pack";		
		public static final String DIALOG = "data/screen_box.pack";//"data/dialog.pack";
	}
	
	public interface FontConfig {
		public static final String FONT_18 = "data/font/arial-18.fnt";
		public static final String FONT_20 = "data/font/arial-20.fnt";
		public static final String FONT_24 = "data/font/arial-24.fnt";
	}

	public interface SoundConfig {
		public static final String MENU_CLICK = "data/sound/menu_click.mp3";
	}

}
