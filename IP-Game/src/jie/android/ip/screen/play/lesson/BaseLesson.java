package jie.android.ip.screen.play.lesson;

import java.util.ArrayList;

import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.play.LessonGroup;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseLesson {
	
	protected final LessonGroup group;	
	protected TextureAtlas textureAtlas;
	protected TweenManager tweenManager;
	
	protected int stage = 0;
	protected ArrayList<ImageActor> arrayActor = new ArrayList<ImageActor>();
	
	public BaseLesson(final LessonGroup group, final TextureAtlas textureAltas, final TweenManager tweenMaanger) {
		this.group = group;
		this.textureAtlas = textureAtlas;
		this.tweenManager = tweenManager;
		
		loadNextStage();
	}

	private void showActors() {
		for (final ImageActor actor : arrayActor) {
			group.addActor(actor);
		}
	}
	
	private void clearActors() {
		for (final ImageActor actor : arrayActor) {
			group.removeActor(actor);
		}
		arrayActor.clear();
	}
	
	protected void addActor(final ImageActor actor) {
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
	
	protected abstract boolean loadStage(int stage);
	public abstract Actor getTrapActor();
	
	
}
