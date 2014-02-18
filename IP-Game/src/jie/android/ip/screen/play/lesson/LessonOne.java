package jie.android.ip.screen.play.lesson;


import com.badlogic.gdx.graphics.Color;

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
		default:
			return false;
		}
	}

	private boolean stage0() {
		makeLabelActor(80, 680, "Target:", 70, new Color(0x243c45fe));
		makeLabelActor(110, 630, "Move all bricks in the left to the same position as the right.", 40, new Color(0x243c45fe));
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
//		makeLabelActor(100, 465, "Code Panel used to input your operation code", 38, Color.CYAN);
		makeLabelActor(70, 420, "Click here to enter EDIT mode", 36, Color.RED);
		makeImageActor(100, 360, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 280, 675, 80);
		
		return true;
	}
	
	private boolean stage1() {
		makeLabelActor(90, 400, "Click here to pick your operation code", 36, Color.RED);
		makeLabelActor(155, 370, "to control Tray action", 36, Color.RED);
		makeImageActor(135, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(100, 215, 150, 90);
		
		return true;
	}
	
	private boolean stage2() {
		makeLabelActor(210, 300, "Click the 'charge' operation icon", 36, Color.RED);
		makeImageActor(280, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 125, 90, 90);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 670, "'left': move Tray to left", 36, new Color(0x243c45ff));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 580, "'right': move Tray to right", 36, new Color(0x243c45ff));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(0x243c45ff));		
		
		return true;
	}
	
	private boolean stage3() {
		makeLabelActor(90, 360, "Click here to pick your operation code", 36, Color.RED);
		makeImageActor(135, 300, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(100, 215, 150, 90);
		
		return true;

	}
}
