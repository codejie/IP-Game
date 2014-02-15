package jie.android.ip.screen.play;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.common.dialog.ScreenGroup;
import jie.android.ip.screen.play.lesson.BaseLesson;
import jie.android.ip.screen.play.lesson.LessonOne;

public class LessonGroup extends ScreenGroup {

	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private BaseLesson lesson;
	
	public LessonGroup(final PlayScreen screen, int lessonId, final PlayScreenListener.RendererInternalEventListener internalListener) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
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
	
	public void closeLesson() {
		if (internalListener != null) {
			internalListener.onLessonGroupRemoved();
		}
		screen.removeActor(this);
	}
	
	protected void onTrapActorHit(float x, float y) {
		if (lesson != null) {
			if (!lesson.loadNextStage()) {
				closeLesson();
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
