package jie.android.ip.screen.play.lesson;

import java.util.ArrayList;

import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.actor.NinePatchActor;
import jie.android.ip.screen.play.LessonGroup;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseLesson {
	
	protected final LessonGroup group;
	protected final TextureAtlas textureAtlas;
	
	protected int stage = 0;
	protected NinePatchActor trapActor;
	protected ArrayList<Actor> arrayActor = new ArrayList<Actor>();
	
	public BaseLesson(final LessonGroup group) {
		this.group = group;
		this.textureAtlas = this.group.getTextureAtlas();
		
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
		trapActor = new NinePatchActor(textureAtlas.findRegion(Image.Lesson.FRAME), Const.Lesson.FRAME_SIZE, x, y, width, height);
		addActor(trapActor);
	}
	
	protected void makeImageActor(int x, int y, float scale, final String textRes) {
		final ImageActor actor = new ImageActor(textureAtlas.findRegion(textRes));
		actor.setPosition(x, y);
		actor.setScale(scale);
		addActor(actor);		
	}
	
	protected void makeLabelActor(int x, int y, final String text, int size, final Color color) {
		final BitmapFont font = this.group.getResources().getBitmapTrueFont(size);
		final LabelActor actor = new LabelActor(text, font);
		actor.setColor(color);
		actor.setPosition(x, y);
		addActor(actor);
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
