package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

public class ResultGroup extends BaseGroup {

	private enum Which {
		SUCC, FAIL, FINISHED;
	}
	
	private final PlayScreen screen;
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private ImageActor backGround;
	private ImageActor result;
	
	private Which current;
	
	public ResultGroup(final PlayScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		this.screen = screen;
		this.textureAtlas = screen.getGame().getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
		this.tweenManager = this.screen.getTweenManager();
		this.internalListener = internalListener;

		initStage();
	}
	
	@Override
	protected void initStage() {
		backGround = new ImageActor(textureAtlas.findRegion(Image.Result.BG));
		backGround.setBounds(-Const.Result.WIDTH, Const.Result.BASE_Y, Const.Result.WIDTH, Const.Result.HEIGHT);
		this.setBounds(Const.Result.BASE_X, Const.Result.BASE_Y, Const.Result.WIDTH, Const.Result.HEIGHT);
		this.addActor(backGround);

//		result = new ImageActor(textureAtlas.findRegion(Image.Result.SUCC));
//		result.setBounds(Const.Result.BASE_X_RESULT, Const.Result.BASE_Y_RESULT, Const.Result.WIDTH_RESULT, Const.Result.HEIGHT_RESULT);
//		result.setScale(0.0f);
//		this.addActor(result);		
		
		this.setBounds(Const.Result.BASE_X, Const.Result.BASE_Y, Const.Result.WIDTH, Const.Result.HEIGHT);
		this.setVisible(false);
		screen.addActor(this);		
	}

	private void updateResult(final Which which) {
		
		if (current != null && current == which) {
			return;
		}
		
		if (result != null) {
			this.removeActor(result);
		}
		
		if (which == Which.SUCC) {
			result = new ImageActor(textureAtlas.findRegion(Image.Result.SUCC));
			result.setBounds(Const.Result.BASE_X_SUCC, Const.Result.BASE_Y_SUCC, Const.Result.WIDTH_SUCC, Const.Result.HEIGHT_SUCC);
		} else if (which == Which.FAIL) {
			result = new ImageActor(textureAtlas.findRegion(Image.Result.FAIL));
		} else { // if (which == Which.FINISHED) {
			result = new ImageActor(textureAtlas.findRegion(Image.Result.FINISHED));
		}
		
		//result.setBounds(0, 0, width, height)
		result.setScale(0.0f);
		
		this.addActor(result);
		
		current = which;
	}
	
	public void showSuccStage() {
		updateResult(Which.SUCC);
		this.setVisible(true);
		Timeline.createSequence()
			.push(Tween.to(backGround, ImageActorAccessor.POSITION_X, 0.05f).target(Const.Result.BASE_X))
			.push(Tween.to(result, ImageActorAccessor.SCALE_XY, 0.05f).target(1.0f, 1.0f))
			.start(tweenManager);
	}

	public void showFailStage() {
		updateResult(Which.FAIL);
		Tween.to(result, ImageActorAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f).start(tweenManager);
	}

	public void showFinishedStage() {
		updateResult(Which.FINISHED);
		Tween.to(result, ImageActorAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f).start(tweenManager);
	}
	
	public void hideStage() {
		if (current == null) {
			return;
		}
		
		final TweenCallback callback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				ResultGroup.this.setVisible(false);
			}			
		};
		
		if (current == Which.SUCC) {
			Timeline.createSequence()
				.push(Tween.to(result, ImageActorAccessor.SCALE_XY, 0.05f).target(0.0f, 0.0f))		
				.push(Tween.to(backGround, ImageActorAccessor.POSITION_X, 0.05f).target(-Const.Result.WIDTH))
				.setCallback(callback)
				.start(tweenManager);
		} else {
			Tween.to(result, ImageActorAccessor.SCALE_XY, 0.1f).target(0.0f, 0.0f).setCallback(callback).start(tweenManager);			
		}
	}
}
