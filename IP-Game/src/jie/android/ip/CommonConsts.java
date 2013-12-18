package jie.android.ip;

public interface CommonConsts {

	public interface ScreenConfig {
		public static final int WIDTH = 1280;
		public static final int HEIGHT = 768;
	}
	
	public interface BoxConfig {
		public static final int MAX_ROW = 6;
		public static final int MAX_COL = 6;
		
		public static final int BLOCK_WIDTH = 64;
		public static final int BLOCK_HEIGHT = 64;
		public static final int TRAY_WIDTH = 96;
		public static final int TRAY_HEIGHT = 48;
		
		public static final int MAX_RENDER_WIDTH = ScreenConfig.WIDTH;
		public static final int MAX_RENDER_HEIGHT = ScreenConfig.HEIGHT;
		
		public static final int COL_BASE = 16;
		public static final int COL_SPACE = 32;
		public static final int ROW_BASE = 50;
		public static final int ROW_SPACE = 4;
		public static final int TRAY_BASE = 0;
		public static final int TRAY_SPACE = 0;
		
		public static final float SOURCE_SCALE = 0.5f;
		public static final float TARGET_SCALE = 1.0f;
	}
	
	public interface ResourceConfig {
		public static final String BOX_PACK_NAME = "data/box.pack";
		public static final String FONT_18_NAME = "data/font/arial-18.fnt";
		public static final String CONSOLE_PACK_NAME = "data/console.pack";
		
		public static final String BACKGROUND_NAME = "background";
		public static final String FRANE_NAME = "frame";
		
		public static final String RUN_BUTTON_NAME = "run";
		
		public static final String CONSOLE_CODE_LINE_BG_SMALL = "console_code_line_bg_small";
		public static final String CONSOLE_CODE_LINE_BG_BIG = "console_code_line_bg_big";
		public static final String CONSOLE_CODE_LINE_TITLE_SMALL = "console_code_line_title_small";
		public static final String CONSOLE_CODE_LINE_TITLE_BIG = "console_code_line_title_big";
		
		public static final String CONSOLE_CODE_PANEL_JUDGE_BG = "console_code_panel_judge_bg";
		public static final String CONSOLE_CODE_PANEL_ORDER_BG = "console_code_panel_order_bg";
		
//		public static final String CONSOLE_CODE_DEFAULT_SMALL = "console_code_default_small";
//		public static final String CONSOLE_CODE_DEFAULT_BIG = "console_code_default_big";
		public static final String CONSOLE_CODE_NONE_SMALL = "console_code_none_small";
		public static final String CONSOLE_CODE_NONE_BIG = "console_code_none_big";		
		public static final String CONSOLE_CODE_IF_NONE_SMALL = "console_code_if_none_small";
		public static final String CONSOLE_CODE_IF_NONE_BIG = "console_code_if_none_big";

		public static final String CONSOLE_CODE_IF_0_SMALL = "console_code_if_0_small";
		public static final String CONSOLE_CODE_IF_0_BIG = "console_code_if_0_big";
		
		public interface Cmd {
			
		}
		
		public interface Lines {
			public interface Small {
				
			}
			
			public interface Big {
				
			}
		}
		
		public interface Panel {
			public interface Judge {
			}
			
			public interface Order {
			}			
		}
		
	}
	
	public interface ConsoleGroupConfig {
		public interface Cmd {
			public static final int BASE_X = ScreenConfig.WIDTH - 128;
			public static final int BASE_Y = 16;
		}
		
		public interface Lines {
			
			public static final int BASE_X = 32;
			public static final int BASE_Y = 16;

			public static final int SPACE_LILNES = 8;			
			
			public interface Small {
				public static final int WIDTH_BG = 684;
				public static final int HEIGHT_BG = 80;// same as the height of small group
				
				public static final int WIDTH_TITLE = 72;
				public static final int HEIGHT_TITLE = 80;
				
				public static final int SPACE_X = 4;
				public static final int SPACE_Y = 4;
				
				public static final int WIDTH_BUTTON_ORDER = 72;
				public static final int HEIGHT_BUTTON_ORDER = 72;				
				public static final int WIDTH_BUTTON_JUDGE = 36;
				public static final int HEIGHT_BUTTON_JUDGE = 36;
			}
			
			public interface Big {
				//public static final int BASE_Y_TOP = 100;
				
				public static final int WIDTH_BG = 1160;
				public static final int HEIGHT_BG = 144;
				
				public static final int WIDTH_TITLE = 64;
				public static final int HEIGHT_TITLE = 144;
				
				public static final int SPACE_X = 8;
				public static final int SPACE_Y = 8;
				
				public static final int WIDTH_BUTTON_CODE = 128;
				public static final int HEIGHT_BUTTON_CODE = 80;				
				public static final int WIDTH_BUTTON_JUDGE = 128;
				public static final int HEIGHT_BUTTON_JUDGE = 48;
			}
		}
		
		public interface Panel {
			
			public static final int SPACE_X = 8;
			public static final int SPACE_Y = 8;
			
			public static final int WIDTH_BUTTON = 72;
			public static final int HEIGHT_BUTTON = 72;
			
			public interface Judge {
				public static final int WIDTH_BG = 488;
				public static final int HEIGHT_BG = 88;				
			}
			
			public interface Order {
				public static final int WIDTH_BG = 648;
				public static final int HEIGHT_BG = 88;				
			}
		}
	}
	
}
