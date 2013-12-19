package jie.android.ip.screen.start;

import jie.android.ip.CommonConsts.ScreenConfig;

public interface StartConfig {
	public interface Image {
		
		public static final String BG = "s-bg";
		
		public static final String I = "I";
		public static final String AM = "am";
		public static final String A = "a";
		public static final String P = "p";
		public static final String P1 = "p1";
		public static final String SEMI = "semi";
		
		public static final String TITLE = "title";
		public static final String VER = "ver";
		public static final String AUTHOR = "author";
	}
	
	public interface Const {
		public static final float SCALE_1 = 1.5f;
		public static final float SCALE_2 = 6.0f;
		public static final float DURATION_1 = 0.5f;
		public static final float DURATION_2 = 0.5f;
		public static final float DURATION_3 = 1.0f;
		public static final float DURATION_4 = 0.5f;	
		public static final float DELAY_1 = 0.5f;
		public static final float DELAY_2 = -0.6f;
		public static final float DELAY_3 = 1.0f;
		
		public static final float SPACEX_1 = 15.0f;
		public static final float SPACEX_2 = 6.0f;
		public static final float SPACEY_1 = 0.0f;
		
		public static final float LINE1_X_1 = 0.0f;	
		public static final float LINE1_Y_1 = 300.0f;
		public static final float LINE1_X_2 = 80.0f;	
//		public static final float LINE1_Y_2 = 300.0f;

		public static final float LINE2_X_1 = 0.0f;
		public static final float LINE2_Y_1 = 140.0f;	
		public static final float LINE2_X_2 = 80.0f;
//		public static final float LINE2_Y_2 = 100.0f;

		public static final float LINE3_X_1 = 0.0f;
		public static final float LINE3_Y_1 = 140.0f;	
		public static final float LINE3_X_2 = ScreenConfig.WIDTH - 80.0f;
		
		public static final float LINE4_X_1 = -100.0f;
		public static final float LINE4_Y_1 = 110.0f;	
		public static final float LINE4_X_2 = ScreenConfig.WIDTH - 80.0f;

		public static final float LINE5_X_1 = ScreenConfig.WIDTH;
		public static final float LINE5_Y_1 = 60.0f;	
		public static final float LINE5_X_2 = 80.0f;
		
		
		public static final float FINAL_X_I = 200.0f;	
		public static final float FINAL_Y_I = 260.0f;
		
		public static final float FINAL_Y_OTHER = 800.0f;		
	}
}
