package jie.android.ip.screen.menu;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface MenuConfig {
	
	public interface Image {
		
		public static final String BACKGROUND = "background";
		public static final String TITLE = "title";
		
		public interface Button {
			public static final String BACK_UP = "back_up";//"button_back_up";
			public static final String BACK_DOWN = "back_up";//"button_back_down";
			public static final String NEXT_UP = "next_up";//"button_next";
			public static final String NEXT_DOWN = "next_up";//"button_next";
			public static final String PREV_UP = "prev_up";//"button_prev";
			public static final String PREV_DOWN = "prev_up";//"button_prev";
		}
		
		public interface Pack {
			public static final String Bg = "pack_bg";
			public static final String Bg_0 = "pack_0_bg";
		}
		
		public interface Item {
			public static final String Bg = "item_bg";
			public static final String FRAME_TOP = "frame_top";
			public static final String FRAME_BOTTOM = "frame_bottom";
			public static final String TRAY = "tray";
			public static final String BOX_0 = "box_a";
			public static final String BOX_1 = "box_b";
			public static final String BOX_2 = "box_c";
			public static final String BOX_3 = "box_d";
		}
		
		public interface Cmd {
			public static final String SHARE_DOWN = "cmd_share_down";
			public static final String SHARE_UP = "cmd_share_up";

			public static final String SETTING_UP = "cmd_setup_up";
			public static final String SETTING_DOWN = "cmd_setup_down";
			
			public static final String CLOSE_UP = "cmd_close_up";
			public static final String CLOSE_DOWN = "cmd_close_down";			
		}
		
		public interface PlayService {
			public static final String ACHIEVE_DOWN = "cmd_achieve_down";
			public static final String ACHIEVE_UP = "cmd_achieve_up";

			public static final String BOARD_UP = "cmd_board_up";
			public static final String BOARD_DOWN = "cmd_board_down";			
		}
	}
	
	public interface Const {
		
		public static final int TITLE_X = 0;
		public static final int TITLE_Y = ScreenConfig.HEIGHT - 128;
		public static final int TITLE_WIDTH = ScreenConfig.WIDTH;
		public static final int TITLE_HEIGHT = 128;
		public static final int BG_X = 0;
		public static final int BG_Y = 0;
		public static final int BG_WIDTH = ScreenConfig.WIDTH;
		public static final int BG_HEIGHT = ScreenConfig.HEIGHT - 128;
		public static final int TITLE_PACK_Y = 24;
		
		public interface Button {
			public static final int BACK_WIDTH = 64;
			public static final int BACK_HEIGHT = 128;
			public static final int BACK_X = 24;
			public static final int BACK_Y = ScreenConfig.HEIGHT - BACK_HEIGHT - TITLE_HEIGHT - 32;
			
			public static final int NEXT_WIDTH = 128;
			public static final int NEXT_HEIGHT = 64;
			public static final int NEXT_X = ScreenConfig.WIDTH - NEXT_WIDTH - 24;
			public static final int NEXT_Y = 24;

			public static final int PREV_WIDTH = 128;
			public static final int PREV_HEIGHT = 64;
			public static final int PREV_X = 24;
			public static final int PREV_Y = 24;
		}
		
		public interface Pack {
			public static final int NUM_TOTAL = 6;
			public static final int NUM_PER_LINE = 3;
			
			public static final int BASE_X = 128;
			public static final int BASE_Y = 104;
			
			public static final int SPACE_X = 32;
			public static final int SPACE_Y = 32;
			
			public static final int WIDTH = (ScreenConfig.WIDTH - SPACE_X * 2 - BASE_X * 2) / 3;
			public static final int HEIGHT = 232;			
		}
		
		public interface Item {
			public static final int NUM_PER_PAGE = 6;
			public static final int NUM_PER_LINE = 3;

			public static final int BASE_X = 128;
			public static final int BASE_Y = 104;
			
			public static final int SPACE_X = 32;
			public static final int SPACE_Y = 32;
			
			public static final int WIDTH = (ScreenConfig.WIDTH - SPACE_X * 2 - BASE_X * 2) / 3;
			public static final int HEIGHT = 232;
			
			public static final int FRAME_X = 8;
			public static final int FRAME_Y_TOP = HEIGHT - 16;
			public static final int FRAME_Y_BOTTOM = 16;
			public static final int FRAME_WIDTH = WIDTH - FRAME_X * 2;
			public static final int FRAME_HEIGHT_TOP = 2;
			public static final int FRAME_HEIGHT_BOTTOM = 16;			

			public static final int BLOCK_WIDTH = 24;
			public static final int BLOCK_HEIGHT = 24;			
			
			public static final int COL_BASE = 48;
			public static final int COL_SPACE = 4;
			public static final int ROW_BASE = 8;
			public static final int ROW_SPACE = 4;
			
			public static final int TRAY_WIDTH = 36;
			public static final float TRAY_HEIGHT = 16;
			
			public static final int TRAY_COL_BASE = 42;
			public static final int TRAY_ROW_BASE = 16;
			public static final int TRAY_SPACE = -8;
		}
		
		public interface Cmd {
			
			public static final int WIDTH_BUTTON = 128;
			public static final int HEIGHT_BUTTON = 64;
			
			public static final int BASE_X = ScreenConfig.WIDTH + 4;
			public static final int BASE_Y = 0;
			public static final int TARGET_X = ScreenConfig.WIDTH - WIDTH_BUTTON; 
			
			public static final int WIDTH = 128;
			public static final int HEIGHT = ScreenConfig.HEIGHT - 128;//72;
			
			public static final int BASE_BUTTON_X = 0;
			public static final int BASE_BUTTON_Y = 0;
			public static final int SPACE_BUTTON_Y = 48;			
			
			public static final int X_SETTING = BASE_BUTTON_X;//0;
			public static final int Y_SETTING = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 1;
			
			public static final int X_SHARE = BASE_BUTTON_X;//0;
			public static final int Y_SHARE = HEIGHT - HEIGHT_BUTTON;//80;
			
			public static final float X_CLOSE = BASE_BUTTON_X;//0;
			public static final float Y_CLOSE = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 2;			
		}
		
		public interface PlayService {
			public static final int WIDTH_BUTTON = 256;
			public static final int HEIGHT_BUTTON = 64;
			
			public static final int BASE_X = - WIDTH_BUTTON;
			public static final int BASE_Y = 0;
			public static final int TARGET_X = 0; 
			
			public static final int WIDTH = 256;
			public static final int HEIGHT = ScreenConfig.HEIGHT - 128;//72;
			
			public static final int BASE_BUTTON_X = 0;
			public static final int BASE_BUTTON_Y = 0;
			public static final int SPACE_BUTTON_Y = 48;			
			
			public static final int X_BOARD = BASE_BUTTON_X;//0;
			public static final int Y_BOARD = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 1;
			
			public static final int X_ACHIEVE = BASE_BUTTON_X;//0;
			public static final int Y_ACHIEVE = HEIGHT - HEIGHT_BUTTON;//80;			
		}

	}
	
}
