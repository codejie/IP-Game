package jie.android.ip.screen.play;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.ActorStage.OnTouchDownListener;
import jie.android.ip.screen.play.PlayScreenListener.RendererInternalEventListener;
import jie.android.ip.screen.play.lesson.BaseLesson;
import jie.android.ip.screen.play.lesson.LessonOne;
import jie.android.ip.utils.Utils;

public class LessonGroup extends BaseGroup {

	private final PlayScreen screen;
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private BaseLesson lesson;
	
	public LessonGroup(final PlayScreen screen, int lessonId, final PlayScreenListener.RendererInternalEventListener internalListener) {
		this.screen = screen;
		this.textureAtlas = screen.getGame().getResources().getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.tweenManager = this.screen.getTweenManager();
		
		this.internalListener = internalListener;
		
		initStage();
		
		initLesson(lessonId);
	}
	@Override
	protected void initStage() {
		this.setTouchable(Touchable.disabled);
		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		screen.addActor(this);
	}

	private void initLesson(int lessonId) {
		lesson = loadLesson(lessonId);		
		if (lesson != null) {
			if (internalListener != null) {
				internalListener.onLessonGroupAdded();
			}
		}
	}
	
	protected void onTrapActorHit(float x, float y) {
		if (lesson != null) {
			if (!lesson.loadNextStage()) {
				if (internalListener != null) {
					internalListener.onLessonGroupRemoved();
				}
				screen.removeActor(this);
			}
		}
	}
	
	protected final BaseLesson loadLesson(int id) {
		switch(id) {
		case 1:
			return new LessonOne(this);
		default:
			return null;
		}
	}
	
	public final TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public final TweenManager getTweenManager() {
		return tweenManager;
	}

	public boolean hitTrap(int x, int y) {
		if (lesson != null) {
			if (lesson.hitTrap(x, y)) {
				onTrapActorHit(x, y);
				return true;
			}
		}
		return false;
	}


}
