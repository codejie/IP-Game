package jie.android.ip.screen.menu;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface MenuConfig {
	
	public interface Image {
		
		public static final String BACKGROUND = "background";
		
		public interface Pack {
			public static final String Item_Bg = "cmd_bg";//"pack_item_bg";
		}
		
		public interface Item {
			
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
			
		}		
	}
	
}
