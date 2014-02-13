package jie.android.ip.screen.play.lesson;

import java.util.ArrayList;

import jie.android.ip.common.actor.NinePatchActor;
import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseLesson {
	
	protected final LessonGroup group;	
	
	protected int stage = 0;
	protected NinePatchActor trapActor;
	protected ArrayList<Actor> arrayActor = new ArrayList<Actor>();
	
	public BaseLesson(final LessonGroup group) {
		this.group = group;
		
		loadNextStage();
	}

	private void showActors() {
		for (final Actor actor : arrayActor) {
			group.addActor(actor);
			onActorAdded(actor);
		}
		onAddActorsEnd();
	}
	
	private void clearActors() {
		for (final Actor actor : arrayActor) {
			group.removeActor(actor);
		}
		arrayActor.clear();
		trapActor = null;
	}
	
	protected void addActor(final Actor actor) {
		arrayActor.add(actor);
	}
	
	public boolean loadNextStage() {
		
		clearActors();
		
		if (!loadStage(stage)) {
			return false;
		}
		
		++ stage;
		
		showActors();
		
		return true;
	}
	
	protected void makeTrapActor(int x, int y, int width, int height) {
		trapActor = new NinePatchActor(group.getTextureAtlas().findRegion(Image.Lesson.FRAME), Const.Lesson.FRAME_SIZE, x, y, width, height);
		addActor(trapActor);
	}

	public boolean hitTrap(int x, int y) {
		return ((x >= trapActor.getX() && x <= (trapActor.getX() + trapActor.getWidth()))
				&& (y >= trapActor.getY() && y <= (trapActor.getY() + trapActor.getHeight())));
//		Utils.log("===", "hitTrap : x = " + x + " y = " + y + " ret = " + ret);
	}
	
	protected abstract boolean loadStage(int stage);
	protected void onActorAdded(final Actor actor) {}
	protected void onAddActorsEnd() {}

}
