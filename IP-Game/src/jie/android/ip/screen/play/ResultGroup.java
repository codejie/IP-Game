package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.dialog.ScreenGroup;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

public class ResultGroup extends ScreenGroup {

	private enum Which {
		SUCC, FAIL, FINISHED;
	}
	
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	final PlayScreenListener.RendererInternalEventListener internalListener;
	
//	private ImageActor backGround;
	private ImageActor result;
	private LabelActor labelScore;
	
	private Which current;
	
	public ResultGroup(final BaseScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.tweenManager = super.screen.getTweenManager();
		this.internalListener = internalListener;

		initStage();
	}
	
	@Override
	protected void initStage() {
		this.setBounds(Const.Result.BASE_X, Const.Result.BASE_Y, Const.Result.WIDTH, Const.Result.HEIGHT);
		
//		backGround = new ImageActor(textureAtlas.findRegion(Image.Result.BG));
//		backGround.setBounds(-Const.Result.WIDTH, ScreenConfig.HEIGHT / 2.5f/*Const.Result.BASE_Y*/, Const.Result.WIDTH, Const.Result.HEIGHT / 3.5f);
//		//this.setBounds(Const.Result.BASE_X, Const.Result.BASE_Y, Const.Result.WIDTH, Const.Result.HEIGHT);
//		this.addActor(backGround);
		
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
			//result.setBounds(Const.Result.BASE_X_SUCC, Const.Result.BASE_Y_SUCC, Const.Result.WIDTH_SUCC, Const.Result.HEIGHT_SUCC);
		} else if (which == Which.FAIL) {
			result = new ImageActor(textureAtlas.findRegion(Image.Result.FAIL));
			//result.setBounds(Const.Result.BASE_X_FAIL, Const.Result.BASE_Y_FAIL, Const.Result.WIDTH_FAIL, Const.Result.HEIGHT_FAIL);
		} else { // if (which == Which.FINISHED) {
			result = new ImageActor(textureAtlas.findRegion(Image.Result.FINISHED));
			//result.setBounds(Const.Result.BASE_X_FINISHED, Const.Result.BASE_Y_FINISHED, Const.Result.WIDTH_FINISHED, Const.Result.HEIGHT_FINISHED);
		}

		float x = (Const.Result.WIDTH - result.getWidth()) / 2;
		float y = Const.Result.BASE_Y_RESULT;
		
		result.setPosition(x, y);
		//result.setScale(0.0f);
		
		this.addActor(result);
		
		current = which;
	}
	
	public void showSuccStage(final int base_score, final int score) {
		updateResult(Which.SUCC);
		
		final float zoom = 20.0f;
		float x = (Const.Result.WIDTH - result.getWidth()) / 2;
		float y = Const.Result.BASE_Y_RESULT;
		
		this.setVisible(true);

		Timeline.createParallel()
			.push(Tween.set(result, ImageActorAccessor.OPACITY).target(0.0f))
			.push(Tween.set(result, ImageActorAccessor.SCALE_XY).target(zoom, zoom))
			.push(Tween.set(result, ImageActorAccessor.POSITION_XY).target(0, 0))
			
			.push(Tween.to(result, ImageActorAccessor.OPACITY, 0.2f).target(1.0f))
			.push(Tween.to(result, ImageActorAccessor.SCALE_XY, 0.2f).target(1.0f, 1.0f))
			.push(Tween.to(result, ImageActorAccessor.POSITION_XY, 0.2f).target(x, y))
			.setCallback(new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					final BitmapFont font = ResultGroup.this.resources.getBitmapTrueFont(95);
					labelScore = new LabelActor(String.format("%d/%d", score, base_score), font);
					labelScore.setColor(Color.RED);
					float x = (Const.Result.WIDTH - labelScore.getWidth()) / 2;
					float y = Const.Result.BASE_Y_RESULT - labelScore.getHeight();
					ResultGroup.this.addActor(labelScore);
					labelScore.setPosition(x, y);

				}				
			})
			.start(tweenManager);
	}

	public void showFailStage() {
		updateResult(Which.FAIL);
		
		final float zoom = 20.0f;
		float x = (Const.Result.WIDTH - result.getWidth()) / 2;
		float y = Const.Result.BASE_Y_RESULT;
		
		this.setVisible(true);

		Timeline.createParallel()
			.push(Tween.set(result, ImageActorAccessor.OPACITY).target(0.0f))
			.push(Tween.set(result, ImageActorAccessor.SCALE_XY).target(zoom, zoom))
			.push(Tween.set(result, ImageActorAccessor.POSITION_XY).target(0, 0))
			
			.push(Tween.to(result, ImageActorAccessor.OPACITY, 0.2f).target(1.0f))
			.push(Tween.to(result, ImageActorAccessor.SCALE_XY, 0.2f).target(1.0f, 1.0f))
			.push(Tween.to(result, ImageActorAccessor.POSITION_XY, 0.2f).target(x, y))			
			.start(tweenManager);	
	}

	public void showFinishedStage() {
		updateResult(Which.FINISHED);
		this.setVisible(true);		
		Tween.to(result, ImageActorAccessor.SCALE_XY, 0.2f).target(1.0f, 1.0f).start(tweenManager);
	}
	
	public void hideStage() {
		if (current == null) {
			return;
		}
		
		final TweenCallback callback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				ResultGroup.this.setVisible(false);
				ResultGroup.this.removeActor(labelScore);
			}			
		};
		
		if (current == Which.SUCC) {
			Timeline.createSequence()
				.push(Tween.to(result, ImageActorAccessor.SCALE_XY, 0.05f).target(0.0f, 0.0f))		
//				.push(Tween.to(backGround, ImageActorAccessor.POSITION_X, 0.05f).target(-Const.Result.WIDTH))
				.setCallback(callback)
				.start(tweenManager);
		} else {
			Tween.to(result, ImageActorAccessor.SCALE_XY, 0.1f).target(0.0f, 0.0f).setCallback(callback).start(tweenManager);			
		}
	}
}
