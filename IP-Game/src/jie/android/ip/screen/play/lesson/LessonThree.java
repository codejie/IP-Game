package jie.android.ip.screen.play.lesson;

import com.badlogic.gdx.graphics.Color;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Image;

public class LessonThree extends BaseLesson {

	public LessonThree(LessonGroup group) {
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
		
		makeLabelActor(70, 420, "Click here'", 36, new Color(COLOR_TEXT));
		makeImageActor(100, 360, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 280, 675, 80);
		
		return true;
	}
	
	private boolean stage1() {
		makeLabelActor(90, 370, "Put the first code to catch the Green brick", 36, new Color(COLOR_TEXT));
		makeImageActor(145, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(102, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage2() {
		makeLabelActor(210, 270, "Click the 'charge' icon to catch it", 36, new Color(COLOR_TEXT));
		makeImageActor(280, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));		
		
		return true;
	}
	
	private boolean stage3() {
		makeLabelActor(180, 360, "Then, move them right together", 36, new Color(COLOR_TEXT));
		makeImageActor(285, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage4() {
		makeLabelActor(130, 270, "Click the 'right' icon", 36, new Color(COLOR_TEXT));
		makeImageActor(200, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(170, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));		
		
		return true;
	}
	
	private boolean stage5() {
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
		makeLabelActor(180, 390, "As what RIGHT shows, if the brick is Blue,", 36, new Color(COLOR_TEXT));
		makeLabelActor(200, 350, "we need move them right again", 36, new Color(COLOR_TEXT));
		makeImageActor(390, 300, 0.4f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(378, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage6() {
		makeLabelActor(180, 270, "So, let's click the 'right' icon again", 36, new Color(COLOR_TEXT));
		makeImageActor(300, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(260, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(COLOR_TEXT));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(COLOR_TEXT));		
		
		return true;
	}	
	
	private boolean stage7() {
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);		
		
		makeLabelActor(200, 510, "Now, if the brick is Green, we should release it,", 36, new Color(COLOR_TEXT));
		makeLabelActor(210, 470, "but if it is Blue, we have to move it right again before release it.", 36, new Color(COLOR_TEXT));
		makeLabelActor(270, 430, "So let's click here to check it is Green or Blue", 36, new Color(COLOR_TEXT));
		makeImageActor(420, 365, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(379, 296, 136, 54);
		
		return true;		
	}	
	
	private boolean stage8() {
		
		makeLabelActor(180, 280, "Ok, click here to indicate if the brick on Tray is Blue, release it;", 36, new Color(COLOR_TEXT));
		makeLabelActor(210, 240, "else do not move Tray right", 36, new Color(COLOR_TEXT));
		makeImageActor(250, 310, 0.4f, Image.Lesson.ARROW_RU);
		
		makeTrapActor(260, 360, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_IF_0);
		makeImageActor(180, 650, 1.0f, Image.Lines.Panel.CODE_IF_1);
		makeImageActor(280, 650, 1.0f, Image.Lines.Panel.CODE_IF_2);
		makeImageActor(380, 650, 1.0f, Image.Lines.Panel.CODE_IF_3);
		
		makeLabelActor(80, 610, "The different icons indicate", 36, new Color(COLOR_TEXT));
		makeLabelActor(100, 570, "the different color bricks", 36, new Color(COLOR_TEXT));

		makeImageActor(720, 650, 1.0f, Image.Lines.Panel.CODE_IF_ANY);
		makeLabelActor(800, 670, "Indicates any color brick", 36, new Color(COLOR_TEXT));
		makeImageActor(720, 560, 1.0f, Image.Lines.Panel.CODE_IF_NONE);
		makeLabelActor(800, 580, "Indicates NO any brick", 36, new Color(COLOR_TEXT));
		
		return true;
	}
	
	private boolean stage9() {
		makeLabelActor(340, 370, "Then, we need relase the brick on Tray", 36, new Color(COLOR_TEXT));
		makeImageActor(540, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(510, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage10() {
		makeLabelActor(450, 270, "Click the 'charge' icon to catch it", 36, new Color(COLOR_TEXT));
		makeImageActor(540, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(512, 125, 90, 90);
		
		return true;
	}	
	
	private boolean stage11() {
		makeLabelActor(440, 370, "Now, let's move Tray back left", 36, new Color(COLOR_TEXT));
		makeImageActor(670, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(646, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage12() {
		makeLabelActor(450, 270, "Click the 'left' icon to move it", 36, new Color(COLOR_TEXT));
		makeImageActor(570, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(525, 125, 90, 90);
		
		return true;
	}	
	
	private boolean stage13() {
		makeLabelActor(360, 370, "Finally, let's call the previous steps to move the Blue brick", 36, new Color(COLOR_TEXT));
		makeImageActor(810, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(783, 215, 142, 90);
		
		return true;
	}
	
	private boolean stage14() {
		makeLabelActor(420, 270, "Yes, we just need call current column again, click 'f0'", 36, new Color(COLOR_TEXT));
		makeImageActor(810, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(773, 125, 90, 90);
		
		return true;
	}	
	
	private boolean stage15() {
		makeLabelActor(120, 560, "Click anywhere except current column to ready to run", 36, new Color(COLOR_TEXT));
		makeImageActor(1000, 460, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(0, ScreenConfig.HEIGHT / 2, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		return true;
	}
	
	private boolean stage16() {
		makeLabelActor(400, 150, "Now, click 'run' button to execute your code", 36, new Color(COLOR_TEXT));
		makeImageActor(1050, 70, 0.5f, Image.Lesson.ARROW_RB);
		
		makeTrapActor(1145, 0, 140, 138);
		
		return true;
	}		
	
	private boolean stage17() {
		
		makeLabelActor(200, 640, "Please wait when your code is executing...", 40, new Color(COLOR_TEXT));
		
		return true;
	}
	
	private boolean stage18() {

		makeLabelActor(160, 560, "Congratulate, you have finished all lessons !", 45, new Color(COLOR_TEXT));		
		makeLabelActor(350, 500, "Enjoy it yourself now !", 45, new Color(COLOR_TEXT));

		makeTrapActor(0, ScreenConfig.HEIGHT / 2, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		return true;
	}	
}
