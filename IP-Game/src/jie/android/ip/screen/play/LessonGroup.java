package jie.android.ip.screen.play;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.play.lesson.BaseLesson;

public class LessonGroup extends BaseGroup {

	private final PlayScreen screen;
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	private BaseLesson lesson;
	
	public LessonGroup(final PlayScreen screen, int lessonId) {
		this.screen = screen;
		this.textureAtlas = screen.getGame().getResources().getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.tweenManager = this.screen.getTweenManager();
		
		initStage();
		
		lesson = loadLesson(lessonId);
	}
	
	@Override
	protected void initStage() {
		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		screen.addActor(this);
		this.setZIndex(0x1f);
		
//		final Group base = new Group() {
//
//			@Override
//			public Actor hit(float x, float y, boolean touchable) {
//				Actor actor = super.hit(x, y, touchable);
//				if (actor == trapActor) {
//					onTrapActorHit(x, y, touchable);
//					return null;
//				}
//				return actor;
//			}
//			
//		};
//		base.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
//		
//		screen.addActor(base);
	}
	
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		Actor actor = super.hit(x, y, touchable);
		if (lesson != null) {
			if (actor == lesson.getTrapActor()) {
				onTrapActorHit(x, y, touchable);
				return null;
			}
		}
		return actor;
	}

	protected void onTrapActorHit(float x, float y, boolean touchable) {
		if (lesson != null) {
			if (!lesson.loadNextStage()) {
				screen.removeActor(this);
			}
		}
	}
	
	protected final BaseLesson loadLesson(int id) {
		switch(id) {
		case 1:
			return new LessonOne(this, textureAtlas, tweenManager);
		default:
			return null;
		}
	}

}
