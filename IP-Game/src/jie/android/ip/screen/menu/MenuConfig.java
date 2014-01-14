package jie.android.ip.screen.menu;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface MenuConfig {
	
	public interface Image {
		
		public static final String BACKGROUND = "background";
		
		public interface Pack {
			public static final String Item_Bg = "cmd_bg";//"pack_item_bg";
		}
		
		public interface Item {

			public static final String Item_Bg = "cmd_bg";
			public static final String FRAME = "box_frame";
			public static final String TRAY = "tray";
			public static final String BOX_0 = "box_a";
			
		}
	}
	
	public interface Const {
		public interface Pack {
			public static final int NUM_TOTAL = 6;
			public static final int NUM_PER_LINE = 3;
			
			public static final int BASE_X = 96;
			public static final int BASE_Y = 48;
			
			public static final int SPACE_X = 48;
			public static final int SPACE_Y = 48;
			
			public static final int WIDTH = (ScreenConfig.WIDTH - SPACE_X * 2 - BASE_X * 2) / 3;
			public static final int HEIGHT = 232;			
		}
		
		public interface Item {
			public static final int NUM_TOTAL = 6;
			public static final int NUM_PER_LINE = 3;

			public static final int BASE_X = 96;
			public static final int BASE_Y = 48;
			
			public static final int SPACE_X = 48;
			public static final int SPACE_Y = 48;
			
			public static final int WIDTH = (ScreenConfig.WIDTH - SPACE_X * 2 - BASE_X * 2) / 3;
			public static final int HEIGHT = 232;
			
			public static final int FRAME_HEIGHT = 2;
			public static final int FRAME_WIDTH = WIDTH - 16;
			public static final int FRAME_TOP_X = 8;
			public static final int FRAME_TOP_Y = HEIGHT - 16;
			public static final int FRAME_BOTTOM_X = 8;
			public static final int FRAME_BOTTOM_Y = 16;

			public static final int BLOCK_WIDTH = 24;
			public static final int BLOCK_HEIGHT = 24;			
			
			public static final int COL_BASE = 48;
			public static final int COL_SPACE = 4;
			public static final int ROW_BASE = 8;
			public static final int ROW_SPACE = 4;
			
			public static final int TRAY_WIDTH = 36;
			public static final float TRAY_HEIGHT = 16;
			
			public static final int TRAY_BASE = 16;
			public static final int TRAY_SPACE = 42;		
		}		
	}
	
}
