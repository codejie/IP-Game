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
		
		public static final String CONSOLE_CODE_PANEL_BG = "console_code_panel_bg";
		
//		public static final String CONSOLE_CODE_DEFAULT_SMALL = "console_code_default_small";
//		public static final String CONSOLE_CODE_DEFAULT_BIG = "console_code_default_big";
		public static final String CONSOLE_CODE_NONE_SMALL = "console_code_none_small";
		public static final String CONSOLE_CODE_NONE_BIG = "console_code_none_big";		
		public static final String CONSOLE_CODE_IF_NONE_SMALL = "console_code_if_none_small";
		public static final String CONSOLE_CODE_IF_NONE_BIG = "console_code_if_none_big";

		public static final String CONSOLE_CODE_IF_0_SMALL = "console_code_if_0_small";
		public static final String CONSOLE_CODE_IF_0_BIG = "console_code_if_0_big";
		
		
	}
	
	public interface CmdConfig {
		public static final int BASE_X = ScreenConfig.WIDTH - 128;
		public static final int BASE_Y = 16;
	}
	
	public interface CodeConfig {
		public static final int SIZE_CODE_LINES = 4;
		public static final int SIZE_CODE_PER_LINE = 8 * 2;
		
		public static final int BASE_X_CODE_LINES = 32;
		public static final int BASE_Y_CODE_LINES = 16;
		
		public static final int WIDTH_CODE_BUTTON_SMALL = 64;
		public static final int HEIGHT_CODE_LINE_SMALL = 72;
		public static final int WIDTH_CODE_BUTTON_BIG = 128;
		public static final int HEIGHT_CODE_LINE_BIG = 144;		
		
//		public static final int WIDTH_SMALL_CODE_BUTTON = 64;
//		public static final int HEIGHT_SMALL_CODE_LINE = 72;
		
//		public static final int WIDTH_SMALL_CODE_LINE = 
		
		public static final int SPACE_X_CODE = 8;
		public static final int SPACE_Y_CODE = 8;
		
		public static final int SPACE_Y_CODE_LINES = 16;
		
		public static final int WIDTH_CODE_TITLE_SMALL = 72;
		public static final int HEIGHT_CODE_TITLE_SMALL = 72;
		public static final int WIDTH_CODE_TITLE_BIG = 144;
		public static final int HEIGHT_CODE_TITLE_BIG = 144;		
		
		public static final int WIDTH_CODE_PANEL_BUTTON = 64;
		public static final int HEIGHT_CODE_PANEL_BUTTON = 64;
		
//		public static final int SIZE_TITLE_CODE_LINE = 64;
	}
	
	public interface ConsoleGroupConfig {
		public interface Cmd {
			
		}
		
		public interface Lines {
			
			public static final int NUM_CODE_LINES = 4;
			public static final int NUM_CODE_PER_LINE = 8 * 2;
			
			public static final int BASE_X = 32;
			public static final int BASE_Y = 16;
			
			public interface Small {
				public static final int WIDTH_BG = 100;
				public static final int HEIGHT_BG = 100;
				
				public static final int WIDTH_TITLE = 100;
				public static final int HEIGHT_TITLE = 100;
				
				public static final int SPACE_X = 100;
				public static final int SPACE_Y = 100;
				
				public static final int WIDTH_BUTTON_CODE = 100;
				public static final int HEIGHT_BUTTON_CODE = 100;				
				public static final int WIDTH_BUTTON_JUDGE = 100;
				public static final int HEIGHT_BUTTON_JUDGE = 100;
			}
			
			public interface Big {
				
			}
		}
		
		public interface Panel {
			
		}
	}
	
}
