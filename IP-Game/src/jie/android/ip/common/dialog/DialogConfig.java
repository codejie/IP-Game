package jie.android.ip.common.dialog;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface DialogConfig {
	public interface Image {
		public static final String BACKGROUND = "bg";//"bg";
		public static final String WINDOW = "window";
		public static final String BUTTON_POSITIVE_UP = "positive_up";
		public static final String BUTTON_POSITIVE_DOWN = "positive_down";
		public static final String BUTTON_NEGATIVE_UP = "negative_up";
		public static final String BUTTON_NEGATIVE_DOWN = "negative_down";
		
		public static final String TEXT_CLEAN_CODE = "text_clean_code";
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
		
		public static int TEXT_WIDTH = WIDTH_WINDOW - 32 * 2;
		public static int TEXT_HEIGHT = HEIGHT_WINDOW - BUTTON_BASE_Y - BUTTON_HEIGHT - 16 * 2;
		public static int TEXT_BASE_X = BASE_X_WINDOW + 32;
		public static int TEXT_BASE_Y = BASE_Y_WINDOW + BUTTON_BASE_Y + BUTTON_HEIGHT + 16;
	}
}
