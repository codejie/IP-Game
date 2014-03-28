package jie.android.ip.screen.play.lesson;

import com.badlogic.gdx.graphics.Color;

import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Image;

public class LessonFour extends BaseLesson {

	public LessonFour(LessonGroup group) {
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
		default:
			return false;
		}
	}

	private boolean stage0() {
		makeLabelActor(110, 600, "Now, there is a new feature to introduce to you.", 50, new Color(COLOR_TEXT));
		
		makeLabelActor(230, 330, "Press 'MORE' button to show more system menu.", 36, new Color(COLOR_TEXT));
		makeImageActor(1080, 260, 0.5f, Image.Lesson.ARROW_RB);
		makeTrapActor(1145, 220, 140, 74);
		
		return true;
	}
	
	private boolean stage1() {
		makeLabelActor(200, 300, "OK, press this 'DEBUG' button to debug your solution.", 36, new Color(COLOR_TEXT));
		makeImageActor(1080, 210, 0.5f, Image.Lesson.ARROW_RB);
		makeTrapActor(1145, 162, 140, 74);
		
		return true;
	}
	
	private boolean stage2() {
		makeLabelActor(240, 420, "OK, press 'BACK' button to back current stage.", 36, new Color(COLOR_TEXT));
		makeImageActor(1080, 360, 0.5f, Image.Lesson.ARROW_RB);
		makeTrapActor(1145, 330, 140, 74);

		makeLabelActor(180, 270, "'DEBUG' buttion is checked, we have entered DEBUG mode now.", 36, new Color(COLOR_TEXT));
		makeImageActor(1080, 210, 0.5f, Image.Lesson.ARROW_RB);
		
		return true;
	}

	private boolean stage3() {
		makeLabelActor(320, 200, "Now, press 'RUN' button, and donot worried", 36, new Color(COLOR_TEXT));
		makeLabelActor(420, 150, "about empty solution, it is ok.", 36, new Color(COLOR_TEXT));
		makeImageActor(1050, 70, 0.5f, Image.Lesson.ARROW_RB);
		makeTrapActor(1145, 0, 140, 138);
		
		return true;
	}
	
	private boolean stage4() {
		makeLabelActor(120, 460, "This button is used to execute your solution step by step.", 36, new Color(COLOR_TEXT));
		makeImageActor(1080, 360, 0.5f, Image.Lesson.ARROW_RB);
		
		makeLabelActor(120, 320, "And this button is used to execute your solution to end.", 36, new Color(COLOR_TEXT));
		makeImageActor(1080, 240, 0.5f, Image.Lesson.ARROW_RB);
		
		makeLabelActor(320, 180, "Press 'RUN' button to stop RUN mode, and complete", 36, new Color(COLOR_TEXT));
		makeLabelActor(360, 130, "the stage by yourself now. Enjoy it!", 36, new Color(COLOR_TEXT));
		makeImageActor(1050, 70, 0.5f, Image.Lesson.ARROW_RB);
		makeTrapActor(1145, 0, 140, 138);

		return true;
	}

}
