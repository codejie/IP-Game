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
		default:
			return false;
		}
	}

	private boolean stage0() {
		makeLabelActor(80, 680, "Target:", 70, Color.DARK_GRAY);
		makeLabelActor(110, 630, "Move all bricks in the left to the same position as the right.", 40, Color.DARK_GRAY);
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
//		makeLabelActor(100, 465, "Code Panel used to input your operation code", 38, Color.CYAN);
		makeLabelActor(70, 420, "Click here to enter EDIT mode", 36, Color.RED);
		makeImageActor(100, 360, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 280, 675, 80);
		
		return true;
	}
	
	private boolean stage1() {

		makeLabelActor(90, 370, "Click here to pick your operation code", 36, Color.RED);
		makeImageActor(125, 310, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(100, 215, 150, 90);
		
		return true;
	}
}
