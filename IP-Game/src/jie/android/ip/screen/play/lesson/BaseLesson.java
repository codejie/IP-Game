package jie.android.ip.screen.play.lesson;

import java.util.ArrayList;

import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.NinePatchActor;
import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseLesson {
	
	protected final LessonGroup group;	
	protected TextureAtlas textureAtlas;
	protected TweenManager tweenManager;
	
	protected int stage = 0;
	protected NinePatchActor trapActor;
	protected ArrayList<Actor> arrayActor = new ArrayList<Actor>();
	
	public BaseLesson(final LessonGroup group, final TextureAtlas textureAltas, final TweenManager tweenMaanger) {
		this.group = group;
		this.textureAtlas = textureAtlas;
		this.tweenManager = tweenManager;
		
		loadNextStage();
	}

	private void showActors() {
		for (final Actor actor : arrayActor) {
			group.addActor(actor);
		}
	}
	
	private void clearActors() {
		for (final Actor actor : arrayActor) {
			group.removeActor(actor);
		}
		arrayActor.clear();
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
		trapActor = new NinePatchActor(textureAtlas.findRegion(Image.Lesson.FRAME), Const.Lesson.FRAME_SIZE, x, y, width, height);
		addActor(trapActor);
	}

	public final Actor getTrapActor() {
		return trapActor;
	}
	
	protected abstract boolean loadStage(int stage);
	
	
}
