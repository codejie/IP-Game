package jie.android.ip.screen.play;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface PlayConfig {

	public static float DELAY	=	0.05f;
	
	public interface Image {
		public static final String BACKGROUND = "background";
		
		public interface Box {
			public static final String FRAME_TOP = "box_frame_top";
			public static final String FRAME_BOTTOM = "box_frame_bottom";
			
			public static final String BOX_0 = "box_a";
			public static final String BOX_1 = "box_b";
			public static final String BOX_2 = "box_c";
			public static final String BOX_3 = "box_d";
			
			public static final String TRAY = "tray";
			public static final String COLUMN = "column";
		}
		
		public interface Cmd {
			public static final String BG = "cmd_bg";
			
			public static final String RUN_UP = "cmd_run_up";
			public static final String RUN_DOWN = "cmd_run_down";
			public static final String RUN_CHECKED = "cmd_run_checked";
			public static final String CLEAR_UP = "cmd_clean_up";
			public static final String CLEAR_DOWN = "cmd_clean_down";
			public static final String MENU_UP = "cmd_more_up";
			public static final String MENU_DOWN = "cmd_more_down";
			public static final String BACK_UP = "cmd_back_up";
			public static final String BACK_DOWN = "cmd_back_down";

			public static final String SHARE_DOWN = "cmd_share_down";
			public static final String SHARE_UP = "cmd_share_up";

			public static final String NEXT_UP = "cmd_next_up";
			public static final String NEXT_DOWN = "cmd_next_down";

			public static final String INFO_UP = "cmd_info_up";
			public static final String INFO_DOWN = "cmd_info_down";

			public static final String SETTING_UP = "cmd_setup_up";
			public static final String SETTING_DOWN = "cmd_setup_down";

			public static final String CLOSE_UP = "cmd_close_up";
			public static final String CLOSE_DOWN = "cmd_close_down";

			public static final String BACK2_UP = "cmd_back_up";
			public static final String BACK2_DOWN = "cmd_back_down";

			public static final String CLOSE2_UP = "cmd_close_up";
			public static final String CLOSE2_DOWN = "cmd_close_down";
		}
		
		public interface Lines {
			public interface Small {
				public static final String BG = "lines_small_bg";
				public static final String TITLE_0 = "lines_small_title_a";
				public static final String TITLE_1 = "lines_small_title_b";
				public static final String TITLE_2 = "lines_small_title_c";
				public static final String TITLE_3 = "lines_small_title_d";
				
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
				public static final String TITLE_0 = "lines_big_title_a";
				public static final String TITLE_1 = "lines_big_title_b";
				public static final String TITLE_2 = "lines_big_title_c";
				public static final String TITLE_3 = "lines_big_title_d";
				
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
				
				public static final String CODE_NULL = "panel_code_null";
				public static final String CODE_RIGHT = "lines_small_code_right";//"panel_code_right";
				public static final String CODE_LEFT = "lines_small_code_left";//"panel_code_left";
				public static final String CODE_ACT = "lines_small_code_act";//"panel_code_act";
				public static final String CODE_IF_NULL = "panel_code_null";//"panel_code_if_null";
				public static final String CODE_IF_0 = "panel_code_if_a";
				public static final String CODE_IF_1 = "panel_code_if_b";
				public static final String CODE_IF_2 = "panel_code_if_c";
				public static final String CODE_IF_3 = "panel_code_if_d";
				public static final String CODE_IF_ANY = "panel_code_if_any";
				public static final String CODE_IF_NONE = "panel_code_if_none";
				public static final String CODE_CALL_0 = "lines_small_code_call_a";//"panel_code_call_a";
				public static final String CODE_CALL_1 = "lines_small_code_call_b";//"panel_code_call_b";
				public static final String CODE_CALL_2 = "lines_small_code_call_c";//"panel_code_call_c";
				public static final String CODE_CALL_3 = "lines_small_code_call_d";//"panel_code_call_d";			
			}
		}
		
		public interface Result {
			public static final String BG = "result_bg";			
			public static final String SUCC = "result_succ";
			public static final String FAIL = "result_failed";
			public static final String FINISHED = "result_finished";			
		}
		
		public interface Toggle {

			public static final String BG = "cmd_bg";//"toggle_bg";
			public static final String RIGHT = "panel_order_bg";// "toggle_right";
			public static final String LEFT = "panel_order_bg";//"toggle_left";			
		}
		
		public interface Lesson {
			public static final String FRAME = "lesson_frame";
			public static final String ARROW_RU = "lesson_arrow_ru";
			public static final String ARROW_RB = "lesson_arrow_rb";
			public static final String ARROW_LU = "lesson_arrow_lu";
			public static final String ARROW_LB = "lesson_arrow_lb";
		}
		
		public interface Title {
			public static final String FRAME = "title_frame";
		}
	}
	
	public interface Const {
		
		public interface Box {
			public static final int MAX_ROW = 6;
			public static final int MAX_COL = 9;
			
			public static final int FRAME_X = 16;
			public static final int FRAME_Y_TOP = ScreenConfig.HEIGHT - 16;
			public static final int FRAME_Y_BOTTOM = 4;
			public static final int FRAME_WIDTH = ScreenConfig.WIDTH - FRAME_X * 2;
			public static final int FRAME_HEIGHT_TOP = 16;
			public static final int FRAME_HEIGHT_BOTTOM = 64;
			
			
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
			
			public static final int COLUMN_X = COL_BASE - 24 - 2;// 48;
			public static final int COLUMN_Y = 144;
			public static final int COLUMN_SPACE = 144;//48 + 96;
			public static final float COLUMN_WIDTH = 4;
			public static final float COLUMN_HEIGHT = 592;
		}	
		
		public interface Cmd {
			
			public static final int WIDTH_BUTTON = 128;
			public static final int HEIGHT_BUTTON = 64;
			
			public static final int BASE_X = ScreenConfig.WIDTH - WIDTH_BUTTON;
			public static final int BASE_Y = 0;
			
			public static final int WIDTH = 128;
			public static final int HEIGHT = ScreenConfig.HEIGHT - 32;//72;
			
			public static final int BASE_BUTTON_X = 0;
			public static final int BASE_BUTTON_Y = 0;
			public static final int SPACE_BUTTON_Y = 48;			
			
			public static final int X_RUN = BASE_BUTTON_X;//0;//ScreenConfig.WIDTH - 128;
			public static final int Y_RUN = BASE_BUTTON_Y;//16;
			public static final int X_RUN_WIDTH = WIDTH_BUTTON;
			public static final int X_RUN_HEIGHT = 128;
			
			public static final int X_CLEAR = BASE_BUTTON_X;//0;//ScreenConfig.WIDTH - 128;
			public static final int Y_CLEAR = BASE_BUTTON_Y + (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 3;//300;
			
			public static final int X_MENU = BASE_BUTTON_X;//0;//ScreenConfig.WIDTH - 128;
			public static final int Y_MENU = BASE_BUTTON_Y + (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 2;// + 198;
			
			public static final int X_SETTING = BASE_BUTTON_X;//0;
			public static final int Y_SETTING = HEIGHT - HEIGHT_BUTTON;//80;

			public static final float X_INFO = BASE_BUTTON_X;//0;
			public static final float Y_INFO = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 1;
			
			public static final float X_BACK = BASE_BUTTON_X;//0;
			public static final float Y_BACK = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 2;
			
			public static final float X_CLOSE = BASE_BUTTON_X;//0;
			public static final float Y_CLOSE = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 3;
			
			
			public static final int X_SHARE = BASE_BUTTON_X;//0;
			public static final int Y_SHARE = HEIGHT - HEIGHT_BUTTON;//80;
			
			public static final float X_NEXT = BASE_BUTTON_X;//0;
			public static final float Y_NEXT = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 1;
			
			public static final float X_BACK2 = BASE_BUTTON_X;//0;
			public static final float Y_BACK2 = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 2;
			
			public static final float X_CLOSE2 = BASE_BUTTON_X;//0;
			public static final float Y_CLOSE2 = HEIGHT - HEIGHT_BUTTON - (SPACE_BUTTON_Y + HEIGHT_BUTTON) * 3;
			
		}
		
		public interface Lines {
			
			public static final int BASE_X = 32;
			public static final int BASE_Y = 16;

			public static final int SPACE_LILNES = 8;			
			
			public interface Small {
				public static final int WIDTH_BG = 674;
				public static final int HEIGHT_BG = 80;// same as the height of small group
				
				public static final int WIDTH_TITLE = 72;
				public static final int HEIGHT_TITLE = 80;
				
				public static final int SPACE_X = 6;
				public static final int SPACE_Y = 8;
				
				public static final int WIDTH_BUTTON_ORDER = 68;
				public static final int HEIGHT_BUTTON_ORDER = 68;				
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
			
			public static final int BASE_Y_RESULT = HEIGHT / 2;

		}
		
		public interface Toggle {

			public static final int BASE_X = 0;
			public static final int BASE_Y = 0;
			public static final int WIDTH = ScreenConfig.WIDTH;
			public static final int HEIGHT = ScreenConfig.HEIGHT;
			
			public static final int BASE_X_RIGHT = ScreenConfig.WIDTH / 2;
			public static final int BASE_Y_RIGHT = 0;
			public static final int WIDTH_RIGHT = ScreenConfig.WIDTH / 2;
			public static final int HEIGHT_RIGHT = ScreenConfig.HEIGHT;
			public static final int BASE_X_LEFT = 0;
			public static final int BASE_Y_LEFT = 0;
			public static final int WIDTH_LEFT = ScreenConfig.WIDTH / 2;
			public static final int HEIGHT_LEFT = ScreenConfig.HEIGHT;			
		}
		
		public interface Lesson {
			public static final int FRAME_SIZE = 8;
		}
		
		public interface Title {
			public static final int WIDTH = 320;
			public static final int HEIGHT = 120;
			
			public static final int A_X = 760;
			public static final int A_Y = 24;			
			public static final int B_X = 900;
			public static final int B_Y = 330;
			
//			public static final int X_PACK = 50;
			public static final int Y_PACK = 88;
//			public static final int X_SCRIPT = 80;
			public static final int Y_SCRIPT = 28;			
		}
	}
}
