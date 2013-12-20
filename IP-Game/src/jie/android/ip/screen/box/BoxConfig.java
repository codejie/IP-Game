package jie.android.ip.screen.box;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface BoxConfig {

	public interface Image {
		public static final String BACKGROUND = "background";
		
		public interface Box {
			public static final String FRAME = "box_frame";
			
			public static final String BOX_0 = "box_a";
			public static final String BOX_1 = "box_b";
			public static final String BOX_2 = "box_c";
			public static final String BOX_3 = "box_d";
			
			public static final String TRAY = "tray";
		}
		
		public interface Console {
			public interface Cmd {
				public static final String RUN = "cmd_run";
				public static final String STOP = "cmd_run";//"cmd_stop";
			}
			
			public interface Lines {
				public interface Small {
					public static final String BG = "lines_small_bg";
					public static final String TITLE_0 = "lines_small_title";//"lines_small_title_a";
					public static final String TITLE_1 = "lines_small_title";//"lines_big_title_b";
					public static final String TITLE_2 = "lines_small_title";//"lines_big_title_c";
					public static final String TITLE_3 = "lines_small_title";//"lines_big_title_d";
					
					public static final String CODE_NONE = "lines_small_code_none";
					public static final String CODE_RIGHT = "lines_small_code_right";
					public static final String CODE_LEFT = "lines_small_code_left";
					public static final String CODE_ACT = "lines_small_code_act";
					public static final String CODE_IF_0 = "lines_small_code_if_a";
					public static final String CODE_IF_1 = "lines_small_code_if_b";
					public static final String CODE_IF_2 = "lines_small_code_if_c";
					public static final String CODE_IF_3 = "lines_small_code_if_d";
					public static final String CODE_IF_ANY = "lines_small_code_if_any";
					public static final String CODE_IF_NONE = "lines_small_code_if_none";
					public static final String CODE_CALL_0 = "lines_small_code_call_a";
					public static final String CODE_CALL_1 = "lines_small_code_call_b";
					public static final String CODE_CALL_2 = "lines_small_code_call_c";
					public static final String CODE_CALL_3 = "lines_small_code_call_d";
				}
				
				public interface Big {
					public static final String BG = "lines_big_bg";
					public static final String TITLE_0 = "lines_big_title";//"lines_big_title_a";
					public static final String TITLE_1 = "lines_big_title";//"lines_big_title_b";
					public static final String TITLE_2 = "lines_big_title";//"lines_big_title_c";
					public static final String TITLE_3 = "lines_big_title";//"lines_big_title_d";
					
					public static final String CODE_NONE = "lines_big_code_none";
					public static final String CODE_RIGHT = "lines_big_code_right";
					public static final String CODE_LEFT = "lines_big_code_left";
					public static final String CODE_ACT = "lines_big_code_act";
					public static final String CODE_IF_0 = "lines_big_code_if_a";
					public static final String CODE_IF_1 = "lines_big_code_if_b";
					public static final String CODE_IF_2 = "lines_big_code_if_c";
					public static final String CODE_IF_3 = "lines_big_code_if_d";
					public static final String CODE_IF_ANY = "lines_big_code_if_any";
					public static final String CODE_IF_NONE = "lines_big_code_if_none";
					public static final String CODE_CALL_0 = "lines_big_code_call_a";
					public static final String CODE_CALL_1 = "lines_big_code_call_b";
					public static final String CODE_CALL_2 = "lines_big_code_call_c";
					public static final String CODE_CALL_3 = "lines_big_code_call_d";					
				}
			}
			
			public interface Panel {
				public static final String ORDER_BG = "panel_order_bg";
				public static final String JUDGE_BG = "panel_judge_bg";
				
				public static final String CODE_NONE = "panel_code_none";
				public static final String CODE_RIGHT = "panel_code_right";
				public static final String CODE_LEFT = "panel_code_left";
				public static final String CODE_ACT = "panel_code_act";
				public static final String CODE_IF_0 = "panel_code_if_a";
				public static final String CODE_IF_1 = "panel_code_if_b";
				public static final String CODE_IF_2 = "panel_code_if_c";
				public static final String CODE_IF_3 = "panel_code_if_d";
				public static final String CODE_IF_ANY = "panel_code_if_any";
				public static final String CODE_IF_NONE = "panel_code_if_none";
				public static final String CODE_CALL_0 = "panel_code_call_a";
				public static final String CODE_CALL_1 = "panel_code_call_b";
				public static final String CODE_CALL_2 = "panel_code_call_c";
				public static final String CODE_CALL_3 = "panel_code_call_d";			
			}
		}
	}
	
	public interface Const {
		
		public interface Box {
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
		
		public interface Console {
			public interface Cmd {
//				public static final int BASE_X = ScreenConfig.WIDTH - 128;
//				public static final int BASE_Y = 16;
				
				public static final int X_RUN = ScreenConfig.WIDTH - 128;
				public static final int Y_RUN = 16;
				
				public static final int X_STOP = ScreenConfig.WIDTH - 128;
				public static final int Y_STOP = 328;				
				
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
					
					public static final int WIDTH_BG = 1168;
					public static final int HEIGHT_BG = 144;
					
					public static final int WIDTH_TITLE = 72;
					public static final int HEIGHT_TITLE = 144;
					
					public static final int SPACE_X = 8;
					public static final int SPACE_Y = 8;
					
					public static final int WIDTH_BUTTON_ORDER = 128;
					public static final int HEIGHT_BUTTON_ORDER = 80;				
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
}
