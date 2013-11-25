package jie.android.ip;

public interface CommonConsts {

	public interface ScreenConfig {
		public static int WIDTH = 1280;
		public static int HEIGHT = 768;
	}
	
	public interface BoxConfig {
		public static int MAX_ROW = 6;
		public static int MAX_COL = 6;
		
		public static int BLOCK_WIDTH = 64;
		public static int BLOCK_HEIGHT = 64;
		public static int TRAY_WIDTH = 96;
		public static int TRAY_HEIGHT = 48;
		
		public static int MAX_RENDER_WIDTH = ScreenConfig.WIDTH;
		public static int MAX_RENDER_HEIGHT = ScreenConfig.HEIGHT;
		
		public static int COL_BASE = 16;
		public static int COL_SPACE = 32;
		public static int ROW_BASE = 50;
		public static int ROW_SPACE = 4;
		public static int TRAY_BASE = 0;
		public static int TRAY_SPACE = 0;
		
		public static float SOURCE_SCALE = 0.5f;
		public static float TARGET_SCALE = 1.0f;
	}
}
