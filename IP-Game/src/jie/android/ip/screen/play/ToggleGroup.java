package jie.android.ip.screen.play;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

public class ToggleGroup extends BaseGroup {

	private final PlayScreen screen;
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
//	private final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private ImageActor right, left;
	private ImageActor bg;
	
	public ToggleGroup(final PlayScreen screen) {
		this.screen = screen;
		this.textureAtlas = this.screen.getGame().getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
		this.tweenManager = this.screen.getTweenManager();
//		this.internalListener = internalListener;
		
		initStage();
	}
	
	@Override
	protected void initStage() {
		this.setBounds(Const.Toggle.BASE_X, Const.Toggle.BASE_Y, Const.Toggle.WIDTH, Const.Toggle.HEIGHT);
		
		bg = new ImageActor(textureAtlas.findRegion(Image.Toggle.BG));
		bg.setBounds(Const.Toggle.BASE_X, Const.Toggle.BASE_Y, Const.Toggle.WIDTH, Const.Toggle.HEIGHT);
//		bg.setColor(0, 0, 0, 1);
		this.addActor(bg);

		
		right = new ImageActor(textureAtlas.findRegion(Image.Toggle.RIGHT));
		right.setBounds(Const.Toggle.BASE_X_RIGHT, Const.Toggle.BASE_Y_RIGHT, Const.Toggle.WIDTH_RIGHT, Const.Toggle.HEIGHT_RIGHT);
//		right.setPosition(Const.Toggle.BASE_X_RIGHT, Const.Toggle.BASE_Y_RIGHT);
		this.addActor(right);

		left = new ImageActor(textureAtlas.findRegion(Image.Toggle.LEFT));
		left.setBounds(Const.Toggle.BASE_X_LEFT, Const.Toggle.BASE_Y_LEFT, Const.Toggle.WIDTH_LEFT, Const.Toggle.HEIGHT_LEFT);
		this.addActor(left);
		
		screen.addActor(this);
		
//		this.setZIndex(0x64);
	}
	
	public void enter(final TweenCallback tweenCallback) {
		Timeline.createParallel()
			.push(Tween.set(bg, ImageActorAccessor.OPACITY).target(1))
			.push(Tween.set(bg, ImageActorAccessor.TINT).target(0, 0, 0))
			.push(Tween.to(bg, ImageActorAccessor.OPACITY, 0.5f).target(0))
			.push(Tween.to(bg, ImageActorAccessor.TINT, 0.5f).target(1, 1, 1))
			.push(Tween.to(right, ImageActorAccessor.POSITION_X, 0.5f).targetRelative(Const.Toggle.WIDTH_RIGHT))
			.push(Tween.to(left, ImageActorAccessor.POSITION_X, 0.5f).targetRelative(-Const.Toggle.WIDTH_LEFT))
			.setCallback(tweenCallback)
			.start(tweenManager);
	}
	
	public void out(final TweenCallback tweenCallback) {
		Timeline.createParallel()	
		.push(Tween.set(bg, ImageActorAccessor.OPACITY).target(0))
		.push(Tween.set(right, ImageActorAccessor.POSITION_X).target(Const.Toggle.WIDTH))
		.push(Tween.set(left, ImageActorAccessor.POSITION_X).target(-Const.Toggle.WIDTH_LEFT))
		.push(Tween.to(bg, ImageActorAccessor.OPACITY, 0.5f).target(1))
		.push(Tween.to(bg, ImageActorAccessor.TINT, 0.5f).target(0, 0, 0))
		.push(Tween.to(right, ImageActorAccessor.POSITION_X, 0.5f).targetRelative(-Const.Toggle.WIDTH_RIGHT))
		.push(Tween.to(left, ImageActorAccessor.POSITION_X, 0.5f).targetRelative(Const.Toggle.WIDTH_LEFT))
		.setCallback(tweenCallback)
		.start(tweenManager);		
	}

	
}
