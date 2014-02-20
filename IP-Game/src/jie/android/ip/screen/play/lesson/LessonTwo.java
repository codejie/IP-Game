package jie.android.ip.screen.play.lesson;

import com.badlogic.gdx.graphics.Color;

import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Image;

public class LessonTwo extends BaseLesson {

	public LessonTwo(LessonGroup group) {
		super(group);
		// TODO Auto-generated constructor stub
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
//		case 3:
//			return stage3();
//		case 4:
//			return stage4();
//		case 5:
//			return stage5();
//		case 6:
//			return stage6();
//		case 7:
//			return stage7();
//		case 8:
//			return stage8();
//		case 9:
//			return stage9();
//		case 10:
//			return stage10();			
		default:
			return false;
		}
	}

	private boolean stage0() {
		
		makeLabelActor(210, 540, "LEFT", 80, Color.CYAN);
		makeLabelActor(850, 540, "RIGHT", 80, Color.CYAN);
		
		makeLabelActor(70, 345, "First,click here to pick your code in 'f1' column", 36, new Color(0xFFFF00FF));
		makeImageActor(100, 275, 0.5f, Image.Lesson.ARROW_LB);
		makeTrapActor(30, 190, 675, 80);		
		
		return true;
	}
	
	private boolean stage1() {
		makeLabelActor(110, 300, "Pick your first code to catch the brick", 36, new Color(0xFFFF00FF));
		makeImageActor(145, 210, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(102, 127, 142, 90);
		
		return true;
	}
	
	private boolean stage2() {
		makeLabelActor(210, 210, "Click the 'charge' operation icon", 36, new Color(0xFFFF00FF));
		makeImageActor(280, 140, 0.5f, Image.Lesson.ARROW_LB);
		
		makeTrapActor(240, 40, 90, 88);
		
		makeImageActor(80, 650, 1.0f, Image.Lines.Panel.CODE_RIGHT);
		makeLabelActor(150, 670, "'right': move Tray to right", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 560, 1.0f, Image.Lines.Panel.CODE_LEFT);
		makeLabelActor(150, 580, "'left': move Tray to left", 36, new Color(0xFFFF00FF));
		makeImageActor(80, 470, 1.0f, Image.Lines.Panel.CODE_ACT);
		makeLabelActor(150, 490, "'charge': catch/release the brick above/on Tray", 36, new Color(0xFFFF00FF));		
		
		return true;		
	}
}
