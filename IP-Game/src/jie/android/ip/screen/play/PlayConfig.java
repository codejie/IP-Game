package jie.android.ip.screen.play;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface PlayConfig {

	public static float DELAY	=	0.1f;
	
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
		
		public interface Cmd {
			public static final String BG = "cmd_bg";
			
			public static final String RUN_UP = "lines_big_code_right";//"cmd_run_up";
			public static final String RUN_DOWN = "lines_big_code_left";//"cmd_run_down";
			public static final String RUN_CHECKED = "lines_big_code_act";//"cmd_run_checked";
			public static final String CLEAR_UP = "lines_big_code_right";//"cmd_clear_up";
			public static final String CLEAR_DOWN = "lines_big_code_left";//"cmd_clear_up_down";
			public static final String MENU_UP = "lines_big_code_right";//"cmd_menu_up";
			public static final String MENU_DOWN = "lines_big_code_left";//"cmd_menu_down";
			public static final String BACK_UP = "lines_big_code_right";//"cmd_back_up";
			public static final String BACK_DOWN = "lines_big_code_left";//"cmd_back_down";

			public static final String SHARE_DOWN = "lines_big_code_right";//"cmd_share_up";
			public static final String SHARE_UP = "lines_big_code_left";//"cmd_share_down";

			public static final String NEXT_UP = "lines_big_code_right";//"cmd_next_up";
			public static final String NEXT_DOWN = "lines_big_code_left";//"cmd_next_down";
		}
		
		public interface Lines {
			public interface Small {
				public static final String BG = "lines_small_bg";
				public static final String TITLE_0 = "lines_small_title";//"lines_small_title_a";
				public static final String TITLE_1 = "lines_small_title";//"lines_big_title_b";
				public static final String TITLE_2 = "lines_small_title";//"lines_big_title_c";
				public static final String TITLE_3 = "lines_small_title";//"lines_big_title_d";
				
				public static final String CODE_NULL = "lines_small_code_null";
				public static final String CODE_RIGHT = "lines_small_code_right";
				public static final String CODE_LEFT = "lines_small_code_left";
				public static final String CODE_ACT = "lines_small_code_act";
				public static final String CODE_IF_NULL = "lines_small_code_if_null";
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
				
				public static final String CODE_NULL = "lines_big_code_null";
				public static final String CODE_RIGHT = "lines_big_code_right";
				public static final String CODE_LEFT = "lines_big_code_left";
				public static final String CODE_ACT = "lines_big_code_act";
				public static final String CODE_IF_NULL = "lines_big_code_if_null";
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
			
			public interface Panel {
				public static final String ORDER_BG = "panel_order_bg";
				public static final String JUDGE_BG = "panel_judge_bg";
				
				public static final String CODE_NULL = "panel_code_if_null";//"panel_code_null";
				public static final String CODE_RIGHT = "panel_code_right";
				public static final String CODE_LEFT = "panel_code_left";
				public static final String CODE_ACT = "panel_code_act";
				public static final String CODE_IF_NULL = "panel_code_if_null";
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
		
		public interface Result {
			public static final String BG = "result_bg";			
			public static final String SUCC = "lines_big_title";//"result_succ";
			public static final String FAIL = "lines_big_code_if_none";//"result_fail";
			public static final String FINISHED = "lines_big_code_if_any";//"result_finished";			
		}
		
		public interface Toggle {

			public static String BG = "cmd_bg";//"toggle_bg";
			public static String RIGHT = "panel_order_bg";// "toggle_right";
			public static String LEFT = "panel_order_bg";//"toggle_left";
			
		}
	}
	
	public interface Const {
		
		public interface Box {
			public static final int MAX_ROW = 6;
			public static final int MAX_COL = 9;
			
			public static final int BLOCK_WIDTH = 96;
			public static final int BLOCK_HEIGHT = 96;
			public static final int TRAY_WIDTH = 144;
			public static final int TRAY_HEIGHT = 42;
			
			public static final int COL_BASE = 72;
			public static final int COL_SPACE = 48;
			public static final int ROW_BASE = 48;
			public static final int ROW_SPACE = 4;
			public static final int TRAY_BASE = 4;
			public static final int TRAY_SPACE = 48;
			
			public static final float SOURCE_SCALE = 0.5f;
			public static final float TARGET_SCALE = 0.5f;
		}	
		
		public interface Cmd {
			public static final int BASE_X = ScreenConfig.WIDTH - 128;
			public static final int BASE_Y = 16;
			
			public static final int WIDTH = 128;
			public static final int HEIGHT = ScreenConfig.HEIGHT - 32;
			
			public static final int WIDTH_BUTTON = 128;
			public static final int HEIGHT_BUTTON = 64;
			
			public static final int BASE_BUTTON_X = 0;
			public static final int BASE_BUTTON_Y = 16;
			
			public static final int X_RUN = 0;//ScreenConfig.WIDTH - 128;
			public static final int Y_RUN = 16;
			
			public static final int X_CLEAR = 0;//ScreenConfig.WIDTH - 128;
			public static final int Y_CLEAR = 328;
			
			public static final int X_MENU = 0;//ScreenConfig.WIDTH - 128;
			public static final int Y_MENU = 198;
			
			public static final int X_BACK = 0;
			public static final int Y_BACK = HEIGHT - 80;
			
			public static final int X_SHARE = 0;
			public static final int Y_SHARE = HEIGHT - 80;
			
			public static final float X_NEXT = 0;
			public static final float Y_NEXT = HEIGHT - 80 - 64 * 2;
		}
		
		public interface Lines {
			
			public static final int BASE_X = 32;
			public static final int BASE_Y = 16;

			public static final int SPACE_LILNES = 8;			
			
			public interface Small {
				public static final int WIDTH_BG = 688;
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
			
			public interface Panel {
				
				public static final int SPACE_X = 8;
				public static final int SPACE_Y = 8;
				
				public static final int WIDTH_BUTTON = 72;
				public static final int HEIGHT_BUTTON = 72;
				
				public interface Judge {
					public static final int WIDTH_BG = 568;
					public static final int HEIGHT_BG = 88;				
				}
				
				public interface Order {
					public static final int WIDTH_BG = 648;
					public static final int HEIGHT_BG = 88;				
				}
			}			
		}
		
		public interface Result {
			public static final int BASE_X = 0;
			public static final int BASE_Y = 0;
			
			public static final int WIDTH = ScreenConfig.WIDTH;// - Cmd.WIDTH;
			public static final int HEIGHT = ScreenConfig.HEIGHT;
			
			public static final int BASE_X_SUCC = 0;
			public static final int BASE_Y_SUCC = 0;
			public static final int WIDTH_SUCC = 100;
			public static final int HEIGHT_SUCC = 100;

			public static final int BASE_X_FAIL = 0;
			public static final int BASE_Y_FAIL = 0;
			public static final int WIDTH_FAIL = 100;
			public static final int HEIGHT_FAIL = 100;
			
			public static final int BASE_X_FINISHED = 0;
			public static final int BASE_Y_FINISHED = 0;
			public static final int WIDTH_FINISHED = 100;
			public static final int HEIGHT_FINISHED = 100;
		}
		
		public interface Toggle {

			public static int BASE_X = 0;
			public static int BASE_Y = 0;
			public static int WIDTH = ScreenConfig.WIDTH;
			public static int HEIGHT = ScreenConfig.HEIGHT;
			
			public static int BASE_X_RIGHT = ScreenConfig.WIDTH / 2;
			public static int BASE_Y_RIGHT = 0;
			public static int WIDTH_RIGHT = ScreenConfig.WIDTH / 2;
			public static int HEIGHT_RIGHT = ScreenConfig.HEIGHT;
			public static int BASE_X_LEFT = 0;
			public static int BASE_Y_LEFT = 0;
			public static int WIDTH_LEFT = ScreenConfig.WIDTH / 2;
			public static int HEIGHT_LEFT = ScreenConfig.HEIGHT;			
		}		
	}
}
