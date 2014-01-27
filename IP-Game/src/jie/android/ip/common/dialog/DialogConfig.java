package jie.android.ip.common.dialog;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface DialogConfig {
	public interface Image {
		public static final String BACKGROUND = "result_bg";//"bg";
		public static final String WINDOW = "box_a";//"window";
		public static final String BUTTON_POSITIVE_UP = "lines_big_code_right";
		public static final String BUTTON_POSITIVE_DOWN = "lines_big_code_left";
		public static final String BUTTON_NEGATIVE_UP = "lines_big_code_right";
		public static final String BUTTON_NEGATIVE_DOWN = "lines_big_code_left";
		
	}
	
	public interface Const {
		public static int WIDTH = ScreenConfig.WIDTH;
		public static int HEIGHT = ScreenConfig.HEIGHT;
		public static int BASE_X = 0;
		public static int BASE_Y = 0;
		
		public static int WIDTH_WINDOW = ScreenConfig.WIDTH - 128 * 2;
		public static int HEIGHT_WINDOW = ScreenConfig.HEIGHT - 128 * 2;
		public static int BASE_X_WINDOW = (ScreenConfig.WIDTH - WIDTH_WINDOW) / 2;
		public static int BASE_Y_WINDOW = 128;
		
		
//		public static int BUTTON_BASE_X = 0;
		public static int BUTTON_BASE_Y = BASE_Y_WINDOW + 32;
		public static int BUTTON_WIDTH = 128 * 2;
		public static int BUTTON_HEIGHT = 96;
		public static int BUTTON_SPACE_X = 128;
	}
}
