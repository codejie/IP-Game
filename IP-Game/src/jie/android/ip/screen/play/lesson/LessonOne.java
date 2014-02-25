package jie.android.ip.screen.play.lesson;


import com.badlogic.gdx.graphics.Color;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Image;

public class LessonOne extends BaseLesson {
	
	public LessonOne(final LessonGroup group) {
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
		default:
			return false;
		}
	}

	private boolean stage0() {
		makeLabelActor(80, 680, "Target:", 70, new Color(0xFFFF00FF));
		makeLabelActor(110, 630, "Move all bricks in the left to the same position as the right.", 40, new Color(0xFFFF00FF));
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
//		makeLabelActor(100, 465, "Code Panel used to input your operation code", 38, Color.CYAN);
		makeLabelActor(70, 420, "Click here to enter EDIT mode", 36, new Color(0xFFFF00FF));
		makeImageActor(100, 360, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 280, 675, 80);
		
		return true;
	}
	
	private boolean stage1() {
		makeLabelActor(90, 405, "Click here to add your operation code", 36, new Color(0xFFFF00FF));
		makeLabelActor(155, 370, "to control Tray action", 36, new Color(0xFFFF00FF));
		makeImageActor(145, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(102, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage2() {
		makeLabelActor(180, 310, "The above list shows the usage of operation icons", 36, new Color(0xFFFF00FF));
		makeLabelActor(210, 270, "Now, click 'charge' operation icon", 36, new Color(0xFFFF00FF));
		makeImageActor(280, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(0xFFFF00FF));		
		
		return true;
	}
	
	private boolean stage3() {
		makeLabelActor(180, 360, "Click here to add the second operation code", 36, new Color(0xFFFF00FF));
		makeImageActor(285, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage4() {
		makeLabelActor(130, 270, "Click the 'right' operation icon", 36, new Color(0xFFFF00FF));
		makeImageActor(200, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(170, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(0xFFFF00FF));		
		
		return true;
	}
	
	private boolean stage5() {
		makeLabelActor(280, 360, "Click here to add the third operation code", 36, new Color(0xFFFF00FF));
		makeImageActor(400, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(378, 215, 140, 90);
		
		return true;		
	}
	
	private boolean stage6() {
		makeLabelActor(330, 260, "Click the 'charge' operation icon again", 36, new Color(0xFFFF00FF));
		makeImageActor(460, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(420, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(0xFFFF00FF));	
		
		return true;
	}
	
	private boolean stage7() {
		makeLabelActor(120, 600, "Click anywhere except the current column used to input the operation code", 36, new Color(0xFFFF00FF));
		makeLabelActor(160, 560, "to close the EDIT mode", 36, new Color(0xFFFF00FF));
		makeImageActor(1000, 650, 0.5f, Image.Lesson.ARROW_RU);
		
		makeTrapActor(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		return true;
	}
	
	private boolean stage8() {
		makeLabelActor(400, 150, "Now, click 'run' button to execute your code", 36, new Color(0xFFFF00FF));
		makeImageActor(1050, 70, 0.5f, Image.Lesson.ARROW_RB);
		
		makeTrapActor(1145, 0, 140, 138);
		
		makeImageActor(80, 620, 1.0f, Image.Cmd.CLEAR_UP);
		makeLabelActor(210, 640, "'clean': removes all codes in line", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 520, 1.0f, Image.Cmd.MENU_UP);
		makeLabelActor(210, 540, "'more': shows more menu items", 36, new Color(0xFFFF00FF));
		
		return true;
	}
	
	private boolean stage9() {
		
		makeLabelActor(200, 640, "Please wait when your code is executing...", 40, new Color(0xFFFF00FF));
		
		return true;
	}
	
	private boolean stage10() {
		
		makeTrapActor(1145, 334, 140, 410);
		
		makeLabelActor(340, 675, "'share': share current screen to your friends", 36, new Color(0xFFFF00FF));
		makeImageActor(1080,670, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(620, 555, "'next': go to next mission", 36, new Color(0xFFFF00FF));
		makeImageActor(1080,550, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(540, 440, "'back': back to current mission", 36, new Color(0xFFFF00FF));
		makeImageActor(1080,435, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(560, 325, "'close': return to menu stage", 36, new Color(0xFFFF00FF));
		makeImageActor(1080,320, 0.5f, Image.Lesson.ARROW_RU);

		makeLabelActor(200, 250, "Congratulate, you have passed Lesson One !", 45, new Color(0xFFFF00FF));		
		
		return true;
	}
	
}
