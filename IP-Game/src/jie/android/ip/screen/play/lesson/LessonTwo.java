package jie.android.ip.screen.play.lesson;

import com.badlogic.gdx.graphics.Color;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Image;

public class LessonTwo extends BaseLesson {

	public LessonTwo(LessonGroup group) {
		super(group);
	}

	@Override
	protected boolean loadStage(int stage) {
		switch(stage) {
		case 0:
			return stage0();
		case 1:
			return stage1();
		case 2:
			return stage2();
		case 3:
			return stage3();
		case 4:
			return stage4();
		case 5:
			return stage5();
		case 6:
			return stage6();
		case 7:
			return stage7();
		case 8:
			return stage8();
		case 9:
			return stage9();
		case 10:
			return stage10();
		case 11:
			return stage11();			
		case 12:
			return stage12();			
		case 13:
			return stage13();			
		case 14:
			return stage14();			
		case 15:
			return stage15();			
		case 16:
			return stage16();			
		case 17:
			return stage17();			
		case 18:
			return stage18();			
			
		default:
			return false;
		}
	}

	private boolean stage0() {
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
		makeLabelActor(70, 345, "First,click here to add your code in 'f1' column", 36, new Color(COLOR_TEXT));
		makeImageActor(100, 275, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 191, 675, 80);		
		
		return true;
	}
	
	private boolean stage1() {
		makeLabelActor(110, 300, "Select your first code to catch the brick", 36, new Color(COLOR_TEXT));
		makeImageActor(145, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(102, 127, 142, 90);
		
		return true;
	}
	
	private boolean stage2() {
		makeLabelActor(210, 210, "Click the 'charge' operation icon", 36, new Color(COLOR_TEXT));
		makeImageActor(280, 140, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 40, 90, 88);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));		
		
		return true;		
	}
	
	private boolean stage3() {
		makeLabelActor(180, 280, "Select next code to move Tray right one step", 36, new Color(COLOR_TEXT));
		makeImageActor(285, 220, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 130, 142, 90);
		
		return true;
	}
	
	private boolean stage4() {
		makeLabelActor(130, 185, "Click the 'right' operation icon", 36, new Color(COLOR_TEXT));
		makeImageActor(200, 125, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(170, 37, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));		
		
		return true;
	}
	
	private boolean stage5() {
		makeLabelActor(280, 280, "Let's release the brick at here", 36, new Color(COLOR_TEXT));
		makeImageActor(400, 220, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(378, 130, 142, 90);
		
		return true;		
	}
	
	private boolean stage6() {
		makeLabelActor(330, 185, "Click the 'charge' operation icon again", 36, new Color(COLOR_TEXT));
		makeImageActor(460, 125, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(420, 37, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));	
		
		return true;
	}
	
	private boolean stage7() {
		makeLabelActor(120, 600, "Click anywhere except current column used to input the operation code", 36, new Color(COLOR_TEXT));
		makeLabelActor(160, 560, "to close current code column", 36, new Color(COLOR_TEXT));
		makeImageActor(1000, 520, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(0, ScreenConfig.HEIGHT / 2, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		return true;
	}
	
	private boolean stage8() {
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
		makeLabelActor(70, 420, "Second, click here to call 'f0' column", 36, new Color(COLOR_TEXT));
		makeImageActor(100, 360, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 280, 675, 80);
		
		return true;
	}	
	
	private boolean stage9() {
		makeLabelActor(90, 370, "Now, you can call other columns directly in current column", 36, new Color(COLOR_TEXT));
		makeImageActor(145, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(102, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage10() {
		makeLabelActor(290, 275, "Click 'f1' icon to call f1 colum", 36, new Color(COLOR_TEXT));
		makeImageActor(430, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(400, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_CALL_0);
		makeImageActor(180, 650, 1.0f, Image.Lines.Panel.CODE_CALL_1);
		makeImageActor(280, 650, 1.0f, Image.Lines.Panel.CODE_CALL_2);
		makeImageActor(380, 650, 1.0f, Image.Lines.Panel.CODE_CALL_3);
		makeLabelActor(100, 600, "'f0' ~ 'f3': used to call specified column", 36, new Color(COLOR_TEXT));
		
		return true;
	}
	
	private boolean stage11() {
		makeLabelActor(180, 360, "Here, Let's move Tray back right", 36, new Color(COLOR_TEXT));
		makeImageActor(285, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage12() {
		makeLabelActor(130, 270, "Click the 'left' operation icon", 36, new Color(COLOR_TEXT));
		makeImageActor(200, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(255, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));		
		
		return true;
	}	
	
	private boolean stage13() {
		makeLabelActor(280, 360, "OK, call 'f1' again to move other brick", 36, new Color(COLOR_TEXT));
		makeImageActor(400, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(378, 215, 142, 90);
		
		return true;		
	}
	
	private boolean stage14() {
		makeLabelActor(500, 270, "Click the 'f1' icon more one time", 36, new Color(COLOR_TEXT));
		makeImageActor(620, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(580, 126, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_CALL_0);
		makeImageActor(180, 650, 1.0f, Image.Lines.Panel.CODE_CALL_1);
		makeImageActor(280, 650, 1.0f, Image.Lines.Panel.CODE_CALL_2);
		makeImageActor(380, 650, 1.0f, Image.Lines.Panel.CODE_CALL_3);
		makeLabelActor(100, 600, "'f0' ~ 'f3': used to call specified column", 36, new Color(COLOR_TEXT));
		
		return true;
	}
	
	private boolean stage15() {
		makeLabelActor(120, 560, "Click anywhere except current column to ready to run", 36, new Color(COLOR_TEXT));
		makeImageActor(1000, 610, 0.5f, Image.Lesson.ARROW_RU);
		
		makeTrapActor(0, ScreenConfig.HEIGHT / 2, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		return true;
	}
	
	private boolean stage16() {
		makeLabelActor(400, 150, "Now, click 'run' button to execute your code", 36, new Color(COLOR_TEXT));
		makeImageActor(1050, 70, 0.5f, Image.Lesson.ARROW_RB);
		
		makeTrapActor(1145, 0, 140, 138);
		
		makeImageActor(80, 620, 1.0f, Image.Cmd.CLEAR_UP);
		makeLabelActor(210, 640, "'clean': removes all codes in line", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 520, 1.0f, Image.Cmd.MENU_UP);
		makeLabelActor(210, 540, "'more': shows more menu items", 36, new Color(COLOR_TEXT));
		
		return true;
	}
	
	private boolean stage17() {
		
		makeLabelActor(200, 640, "Please wait when your code is executing...", 40, new Color(COLOR_TEXT));
		
		return true;
	}	

	private boolean stage18() {
		
		makeTrapActor(1145, 334, 140, 410);
		
		makeLabelActor(340, 675, "'share': share current screen to your friends", 36, new Color(COLOR_TEXT));
		makeImageActor(1080,670, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(620, 555, "'next': go to next mission", 36, new Color(COLOR_TEXT));
		makeImageActor(1080,550, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(540, 440, "'back': back to current mission", 36, new Color(COLOR_TEXT));
		makeImageActor(1080,435, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(560, 325, "'close': return to menu stage", 36, new Color(COLOR_TEXT));
		makeImageActor(1080,320, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(200, 250, "Congratulate, you have passed Lesson Two !", 45, new Color(COLOR_TEXT));		
		
		return true;
	}	
}
