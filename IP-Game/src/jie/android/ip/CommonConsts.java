package jie.android.ip;

public interface CommonConsts {

	public interface ScreenConfig {
		public static int WIDTH = 1280;
		public static int HEIGHT = 768;
	}
	
	public interface BoxConfig {
		public static int MAX_ROW = 7;
		public static int MAX_COL = 6;
		
		public static int BLOCK_WIDTH = 64;
		public static int BLOCK_HEIGHT = 64;
		public static int TRAY_WIDTH = 64;
		public static int TRAY_HEIGHT = 64;
		
		public static int MAX_RENDER_WIDTH = ScreenConfig.WIDTH;
		public static int MAX_RENDER_HEIGHT = ScreenConfig.HEIGHT;
		
		public static int COL_BASE = 20;
		public static int COL_SPACE = 20;
		public static int ROW_BASE = 0;
		public static int ROW_SPACE = 20;
	}
}
