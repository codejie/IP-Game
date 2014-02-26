package jie.android.ip.common.dialog;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface DialogConfig {
	public interface Image {
		public static final String BACKGROUND = "bg";//"bg";
		public static final String WINDOW = "window";
		public static final String BUTTON_YES_UP = "positive_up";
		public static final String BUTTON_YES_DOWN = "positive_down";
		public static final String BUTTON_NO_UP = "negative_up";
		public static final String BUTTON_NO_DOWN = "negative_down";
		
		public static final String BUTTON_OK_UP = "positive_up";
		public static final String BUTTON_OK_DOWN = "positive_down";		
		
		public static final String TEXT_CLEAN_CODE = "text_clean_code";
		
		public interface Setting {
			public static final String SLOW_UP = "setting_normal_up";//"setting_slow_up";
			public static final String SLOW_DOWN = "setting_normal_down";//"setting_slow_down";
			public static final String SLOW_CHECK = "setting_normal_check";//"setting_slow_check";

			public static final String NORMAL_UP = "setting_normal_up";
			public static final String NORMAL_DOWN = "setting_normal_down";
			public static final String NORMAL_CHECK = "setting_normal_check";
			
			public static final String FAST_UP = "setting_normal_up";//"setting_fast_up";
			public static final String FAST_DOWN = "setting_normal_down";//"setting_fast_down";
			public static final String FAST_CHECK = "setting_normal_check";//"setting_fast_check";
			
			public static final String OPEN_UP = "setting_normal_up";//"setting_open_check";
			public static final String OPEN_DOWN = "setting_normal_down";//"setting_open_check";
			public static final String OPEN_CHECK = "setting_normal_check";//"setting_open_check";
			
			public static final String CLOSE_UP = "setting_normal_up";//"setting_close_check";
			public static final String CLOSE_DOWN = "setting_normal_down";//"setting_close_check";
			public static final String CLOSE_CHECK = "setting_normal_check";//"setting_close_check";
		}
	}
	
	public interface Const {
		public static final int WIDTH = ScreenConfig.WIDTH;
		public static final int HEIGHT = ScreenConfig.HEIGHT;
		public static final int BASE_X = 0;
		public static final int BASE_Y = 0;
		
		public static final int WIDTH_WINDOW = ScreenConfig.WIDTH - 128 * 2;
		public static final int HEIGHT_WINDOW = ScreenConfig.HEIGHT - 128 * 2;
		public static final int BASE_X_WINDOW = (ScreenConfig.WIDTH - WIDTH_WINDOW) / 2;
		public static final int BASE_Y_WINDOW = 128;
		
//		public static final int BUTTON_BASE_X = 0;
		public static final int BUTTON_BASE_Y = BASE_Y_WINDOW + 24;
		public static final int BUTTON_WIDTH = 128 * 2;
		public static final int BUTTON_HEIGHT = 96;
		public static final int BUTTON_SPACE_X = 128;
		
		public static final int TEXT_WIDTH = WIDTH_WINDOW - 32 * 2;
		public static final int TEXT_HEIGHT = HEIGHT_WINDOW - BUTTON_BASE_Y - BUTTON_HEIGHT - 16 * 2;
		public static final int TEXT_BASE_X = BASE_X_WINDOW + 32;
		public static final int TEXT_BASE_Y = BASE_Y_WINDOW + BUTTON_BASE_Y + BUTTON_HEIGHT + 16;
		
		public interface Setting {
			public static final int WIDTH_BUTTON = 128;
			public static final int HEIGHT_BUTTON = 72;
			public static final int SPACE_BUTTON = 72;
			
			public static final int X_SPEED_TITLE = BASE_X_WINDOW + 64;
			public static final int Y_SPEED_TITLE = 560;
			
			public static final int X_SPEED_BUTTON_SLOW = X_SPEED_TITLE + 360;
			public static final int Y_SPEED_BUTTON_SLOW = Y_SPEED_TITLE - 32;
			public static final int X_SPEED_BUTTON_NORMAL = X_SPEED_BUTTON_SLOW + WIDTH_BUTTON + SPACE_BUTTON;
			public static final int Y_SPEED_BUTTON_NORMAL = Y_SPEED_BUTTON_SLOW;
			public static final int X_SPEED_BUTTON_FAST = X_SPEED_BUTTON_NORMAL + WIDTH_BUTTON + SPACE_BUTTON;
			public static final int Y_SPEED_BUTTON_FAST = Y_SPEED_BUTTON_SLOW;
			
			public static final int X_MUSIC_TITLE = BASE_X_WINDOW + 225;
			public static final int Y_MUSIC_TITLE = 440;
			
			public static final int X_SPEED_BUTTON_MUSIC_OPEN = X_SPEED_TITLE + 360;
			public static final int Y_SPEED_BUTTON_MUSIC_OPEN = Y_MUSIC_TITLE - 36;
			public static final int X_SPEED_BUTTON_MUSIC_CLOSE = X_SPEED_BUTTON_MUSIC_OPEN + 2 * WIDTH_BUTTON + 2 * SPACE_BUTTON;
			public static final int Y_SPEED_BUTTON_MUSIC_CLOSE = Y_SPEED_BUTTON_MUSIC_OPEN;

			public static final int X_SOUND_TITLE = BASE_X_WINDOW + 222;
			public static final int Y_SOUND_TITLE = 310;
			
			public static final int X_SPEED_BUTTON_SOUND_OPEN = X_SPEED_TITLE + 360;
			public static final int Y_SPEED_BUTTON_SOUND_OPEN = Y_SOUND_TITLE - 36;
			public static final int X_SPEED_BUTTON_SOUND_CLOSE = X_SPEED_BUTTON_SOUND_OPEN + 2 * WIDTH_BUTTON + 2 * SPACE_BUTTON;
			public static final int Y_SPEED_BUTTON_SOUND_CLOSE = Y_SPEED_BUTTON_SOUND_OPEN;
			
		}
	}
}
