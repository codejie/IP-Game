package jie.android.ip.screen.play;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.Resources;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.screen.play.lesson.BaseLesson;
import jie.android.ip.screen.play.lesson.LessonFour;
import jie.android.ip.screen.play.lesson.LessonOne;
import jie.android.ip.screen.play.lesson.LessonThree;
import jie.android.ip.screen.play.lesson.LessonTwo;

public class LessonGroup extends ScreenGroup {

	private final Resources resources;
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private BaseLesson lesson;
	
	public LessonGroup(final PlayScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		super(screen);
		this.resources = super.resources;
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.tweenManager = this.screen.getTweenManager();
		
		this.internalListener = internalListener;
		
		initStage();
	}
	@Override
	protected void initStage() {
//		this.setColor(1.0f, 1.0f, 1.0f, 0.4f);
		this.setTouchable(Touchable.disabled);
		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		screen.addActor(this);
	}
	
	private void loadNextStage() {
		if (lesson != null) {
			if (!lesson.loadNextStage()) {
				closeLesson();
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
		loadNextStage();
	}
	
	public boolean loadLesson(int id) {
		switch(id) {
		case 1:
			lesson = new LessonOne(this);
			break;
		case 2:
			lesson = new LessonTwo(this);
			break;
		case 3:
			lesson = new LessonThree(this);
			break;
		case 4:
			lesson = new LessonFour(this);
			break;
		default:
			return false;
		}
		
		if (internalListener != null) {
			internalListener.onLessonGroupAdded();
		}
		return true;
	}
	
	public final Resources getResources() {
		return resources;
	}

	public final TextureAtlas getTextureAtlas() {
		return textureAtlas;
	}
	
	public final TweenManager getTweenManager() {
		return tweenManager;
	}

	public boolean hitTrap(int x, int y, boolean up) {
		if (lesson != null) {
			if (lesson.hitTrap(x, y)) {
				if (up) {
					onTrapActorHit(x, y);
				}
				return true;
			}
		}
		return false;
	}
	public void onExecuteEnd(boolean succ) {
		loadNextStage();
	}
}
