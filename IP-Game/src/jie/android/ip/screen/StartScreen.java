package jie.android.ip.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Expo;
import jie.android.ip.IPGame;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;
import jie.android.ip.utils.Utils;

public class StartScreen extends BaseScreen {

	private ImageActor i, am, a, p, p1, p2;
	private TweenCallback callback = new TweenCallback() {

		@Override
		public void onEvent(int type, BaseTween<?> source) {
			Utils.log("tween event", "type = " + type + " source = " + source.toString());
			//game.setScreen(new TestScreen(game));
		}
		
	};
	private TweenCallback pScaleCallback = new TweenCallback() {

		@Override
		public void onEvent(int type, BaseTween<?> source) {
			Utils.log("scale tween event", "type = " + type + " source = " + source.toString());			
		}
		
	};

	public StartScreen(IPGame game) {
		super(game);

		initActors();

		initAction();
	}

	private void initActors() {
		i = new ImageActor("i", game.getResources().getTextureAtlas().findRegion("I"));
		am = new ImageActor("am", game.getResources().getTextureAtlas().findRegion("am"));
		a = new ImageActor("a", game.getResources().getTextureAtlas().findRegion("a"));
		p = new ImageActor("p", game.getResources().getTextureAtlas().findRegion("p"));
		p1 = new ImageActor("p1", game.getResources().getTextureAtlas().findRegion("p1"));
		p2 = new ImageActor("p2", game.getResources().getTextureAtlas().findRegion("p2"));
		this.addActor(i);
		this.addActor(am);
		this.addActor(a);
		this.addActor(p);
		this.addActor(p1);
		this.addActor(p2);
	}

	private void initAction() {
//		i.setPosition(-1100, 0);
//		am.setPosition(-1050, 0);
		
		Timeline.createSequence()// .createParallel()
		.push(Tween.set(i, ImageActorAccessor.POSITION_XY).target(-1100, 100))
		.push(Tween.set(am, ImageActorAccessor.POSITION_XY).target(-1050, 100))
		.push(Tween.set(p, ImageActorAccessor.POSITION_XY).target(-1000, 0))
		.push(Tween.set(p1, ImageActorAccessor.POSITION_XY).target(-990, 0))
		.push(Tween.set(p2, ImageActorAccessor.POSITION_XY).target(-200, 0))
//		.beginParallel()
		.push(Tween.to(i, ImageActorAccessor.POSITION_X, 1.0f).target(10))
		.push(Tween.to(am, ImageActorAccessor.POSITION_X, 1.0f).target(60))
		.push(Tween.to(p, ImageActorAccessor.POSITION_X, 1.0f).target(200))
		.push(Tween.to(p1, ImageActorAccessor.POSITION_X, 1.0f).target(300))
		.push(Tween.to(p2, ImageActorAccessor.POSITION_X, 1.0f).target(1100))
		.pushPause(1.0f)
		.beginSequence()
//		.beginParallel()
			//.push(Tween.to(am, ImageActorAccessor.POSITION_Y, 0.2f).target(800).ease(Expo.OUT))
			.push(Tween.to(p1, ImageActorAccessor.POSITION_Y, 0.2f).target(800).ease(Expo.OUT))
		//.push(Tween.to(p2, ImageActorAccessor.POSITION_Y, 1.0f).target(800))
		.end()
		.pushPause(-0.5f)
		.beginParallel()
			.push(Tween.to(p, ImageActorAccessor.SCALE_XY, 1.0f).target(20, 20).repeat(4, 0.5f).setCallbackTriggers(TweenCallback.BEGIN | TweenCallback.START).setCallback(pScaleCallback))
			.push(Tween.to(p, ImageActorAccessor.OPACITY, 1.0f).target(1.0f))
			.push(Tween.to(p, ImageActorAccessor.POSITION_XY, 0.1f).target(0, 0))
			.push(Tween.to(p, ImageActorAccessor.TINT, 1.0f).target(0.9f, 0.9f, 0.1f))
		.end()
		.pushPause(1.0f)
		.setCallback(callback )
		.start(tweenManager);
	}
}
