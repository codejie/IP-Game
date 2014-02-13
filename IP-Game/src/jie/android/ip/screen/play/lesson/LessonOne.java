package jie.android.ip.screen.play.lesson;

import jie.android.ip.screen.play.LessonGroup;

public class LessonOne extends BaseLesson {

	public LessonOne(final LessonGroup group) {
		super(group);
	}

	@Override
	protected boolean loadStage(int stage) {
		switch(stage) {
		case 0:
			super.makeTrapActor(0, 0, 300, 222);
			break;
		case 1:
			super.makeTrapActor(0, 100, 300, 222);
			break;
		default:
			return false;
		}
		
		return true;
	}

}
