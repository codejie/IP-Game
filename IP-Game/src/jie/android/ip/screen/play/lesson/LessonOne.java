package jie.android.ip.screen.play.lesson;


import com.badlogic.gdx.graphics.Color;

import jie.android.ip.screen.play.LessonGroup;

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
			super.makeTrapActor(0, 100, 300, 222);
			break;
		default:
			return false;
		}
		
		return true;
	}

	private boolean stage0() {
		makeLabelActor(400, 500, "make the left as shape as the right", Color.ORANGE);
		makeTrapActor(30, 280, 675, 80);
		return true;
	}
}
