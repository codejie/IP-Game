package jie.android.ip.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Expo;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.IPGame;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;
import jie.android.ip.utils.Utils;

public class StartScreen extends BaseScreen {

	private static final float SCALE_1 = 1.5f;
	private static final float SCALE_2 = 6.0f;
	private static final float DURATION_1 = 0.5f;
	private static final float DURATION_2 = 0.5f;
	private static final float DURATION_3 = 1.0f;
	private static final float DURATION_4 = 0.5f;	
	private static final float DELAY_1 = 0.5f;
	private static final float DELAY_2 = -0.6f;
	private static final float DELAY_3 = 1.2f;
	
	private static final float SPACEX_1 = 15.0f;
	private static final float SPACEX_2 = 6.0f;
	private static final float SPACEY_1 = 0.0f;
	
	private static final float LINE1_X_1 = 0.0f;	
	private static final float LINE1_Y_1 = 300.0f;
	private static final float LINE1_X_2 = 80.0f;	
//	private static final float LINE1_Y_2 = 300.0f;

	private static final float LINE2_X_1 = 0.0f;
	private static final float LINE2_Y_1 = 140.0f;	
	private static final float LINE2_X_2 = 80.0f;
//	private static final float LINE2_Y_2 = 100.0f;

	private static final float LINE3_X_1 = 0.0f;
	private static final float LINE3_Y_1 = 140.0f;	
	private static final float LINE3_X_2 = ScreenConfig.WIDTH - 80.0f;
	
	private static final float LINE4_X_1 = -100.0f;
	private static final float LINE4_Y_1 = 110.0f;	
	private static final float LINE4_X_2 = ScreenConfig.WIDTH - 80.0f;

	private static final float LINE5_X_1 = ScreenConfig.WIDTH;
	private static final float LINE5_Y_1 = 60.0f;	
	private static final float LINE5_X_2 = 80.0f;
	
	
	private static final float FINAL_X_I = 200.0f;	
	private static final float FINAL_Y_I = 260.0f;
	
	private static final float FINAL_Y_OTHER = 800.0f;
	
	private ImageActor bg;
	private ImageActor i, am, a, p, p1, semi;
	private ImageActor title, ver, author;
	private TweenCallback tweenCompleteCallback = new TweenCallback() {

		@Override
		public void onEvent(int type, BaseTween<?> source) {
			Utils.log("tween event", "type = " + type + " source = " + source.toString());
			game.setScreen(new TestScreen(game));
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

		initTween();
	}

	private void initActors() {
	
		i = new ImageActor("i", game.getResources().getTextureAtlas().findRegion("I"));
		am = new ImageActor("am", game.getResources().getTextureAtlas().findRegion("am"));
		a = new ImageActor("a", game.getResources().getTextureAtlas().findRegion("a"));
		p = new ImageActor("p", game.getResources().getTextureAtlas().findRegion("p"));
		p1 = new ImageActor("p1", game.getResources().getTextureAtlas().findRegion("p1"));
		semi = new ImageActor("semi", game.getResources().getTextureAtlas().findRegion("semi"));

		bg = new ImageActor("bg", game.getResources().getTextureAtlas().findRegion("s-bg"));		
		title = new ImageActor("title", game.getResources().getTextureAtlas().findRegion("title"));
		ver = new ImageActor("ver", game.getResources().getTextureAtlas().findRegion("ver"));
		author = new ImageActor("author", game.getResources().getTextureAtlas().findRegion("author"));
		
		this.addActor(bg);
		
		this.addActor(i);
		this.addActor(am);
		this.addActor(a);
		this.addActor(p);
		this.addActor(p1);
		this.addActor(semi);
		
		this.addActor(title);
		this.addActor(ver);
		this.addActor(author);
	}

	private void initTween() {
//		i.setPosition(-1100, 0);
//		am.setPosition(-1050, 0);
		
//		Timeline.createSequence()// .createParallel()
//		.push(Tween.set(i, ImageActorAccessor.POSITION_XY).target(-1100, 100))
//		.push(Tween.set(am, ImageActorAccessor.POSITION_XY).target(-1050, 100))
//		.push(Tween.set(p, ImageActorAccessor.POSITION_XY).target(-1000, 0))
//		.push(Tween.set(p1, ImageActorAccessor.POSITION_XY).target(-990, 0))
//		.push(Tween.set(p2, ImageActorAccessor.POSITION_XY).target(-200, 0))
//		.push(Tween.to(i, ImageActorAccessor.POSITION_X, 1.0f).target(10))
//		.push(Tween.to(am, ImageActorAccessor.POSITION_X, 1.0f).target(60))
//		.push(Tween.to(p, ImageActorAccessor.POSITION_X, 1.0f).target(200))
//		.push(Tween.to(p1, ImageActorAccessor.POSITION_X, 1.0f).target(300))
//		.push(Tween.to(p2, ImageActorAccessor.POSITION_X, 1.0f).target(1100))
//		.pushPause(1.0f)
//		.beginSequence()
//			//.push(Tween.to(am, ImageActorAccessor.POSITION_Y, 0.2f).target(800).ease(Expo.OUT))
//			.push(Tween.to(p1, ImageActorAccessor.POSITION_Y, 0.2f).target(800).ease(Expo.OUT))
//		//.push(Tween.to(p2, ImageActorAccessor.POSITION_Y, 1.0f).target(800))
//		.end()
//		.pushPause(-0.5f)
//		.beginParallel()
//			.push(Tween.to(p, ImageActorAccessor.SCALE_XY, 1.0f).target(20, 20).repeat(4, 0.5f).setCallbackTriggers(TweenCallback.BEGIN | TweenCallback.START).setCallback(pScaleCallback))
//			.push(Tween.to(p, ImageActorAccessor.OPACITY, 1.0f).target(1.0f))
//			.push(Tween.to(p, ImageActorAccessor.POSITION_XY, 0.1f).target(0, 0))
//			.push(Tween.to(p, ImageActorAccessor.TINT, 1.0f).target(0.9f, 0.9f, 0.1f))
//		.end()
//		.pushPause(1.0f)
//		.setCallback(callback )
//		.start(tweenManager);
		
		Timeline.createSequence()
			.push(tweenSet())
			.push(tweenState1())
			.pushPause(DELAY_1)
			.push(tweenState2())
			.pushPause(DELAY_2)
			.push(tweenState3())
			.pushPause(DELAY_3)
			.setCallback(tweenCompleteCallback)
			.start(tweenManager);
		
//		Timeline.createParallel()
//				.push(Tween.set(i, ImageActorAccessor.POSITION_XY).target(LINE1_X_1, LINE1_Y_1))
//				.push(Tween.set(i, ImageActorAccessor.SCALE_XY).target(SCALE_1))
//				.beginSequence()
//					.push(Tween.to(i, ImageActorAccessor.POSITION_X, DURATIN_1).target(LINE1_X_2))
//				.end()
//				.start(tweenManager);
	}
	
	private Timeline tweenSet() {
		return Timeline.createParallel()
				//bg
				.push(Tween.set(bg, ImageActorAccessor.POSITION_XY).target(0, ScreenConfig.HEIGHT))
				.push(Tween.set(bg, ImageActorAccessor.SCALE_TO_XY).target(ScreenConfig.WIDTH, ScreenConfig.HEIGHT))
				//i
				.push(Tween.set(i, ImageActorAccessor.POSITION_XY).target(LINE1_X_1 - i.getWidth() * SCALE_1, LINE1_Y_1))
				.push(Tween.set(i, ImageActorAccessor.SCALE_XY).target(SCALE_1, SCALE_1))
				//am
				.push(Tween.set(am, ImageActorAccessor.POSITION_XY).target(LINE1_X_1 - am.getWidth() * SCALE_1, LINE1_Y_1))
				.push(Tween.set(am, ImageActorAccessor.SCALE_XY).target(SCALE_1, SCALE_1))
				//a
				.push(Tween.set(a, ImageActorAccessor.POSITION_XY).target(LINE2_X_1 - a.getWidth() * SCALE_1, LINE2_Y_1))
				.push(Tween.set(a, ImageActorAccessor.SCALE_XY).target(SCALE_1, SCALE_1))
				//p
				.push(Tween.set(p, ImageActorAccessor.POSITION_XY).target(LINE2_X_1 - p.getWidth() * SCALE_1, LINE2_Y_1))
				.push(Tween.set(p, ImageActorAccessor.SCALE_XY).target(SCALE_1, SCALE_1))
				//p1
				.push(Tween.set(p1, ImageActorAccessor.POSITION_XY).target(LINE2_X_1 - p1.getWidth() * SCALE_1, LINE2_Y_1 - (SPACEY_1 * SCALE_1)))
				.push(Tween.set(p1, ImageActorAccessor.SCALE_XY).target(SCALE_1, SCALE_1))
				//semicolon
				.push(Tween.set(semi, ImageActorAccessor.POSITION_XY).target(LINE2_X_1 - semi.getWidth() * SCALE_1, LINE2_Y_1))
				.push(Tween.set(semi, ImageActorAccessor.SCALE_XY).target(SCALE_1, SCALE_1))
				//title, ver
				.push(Tween.set(title, ImageActorAccessor.POSITION_XY).target(LINE3_X_1 - title.getWidth(), LINE3_Y_1))
				.push(Tween.set(ver, ImageActorAccessor.POSITION_XY).target(LINE4_X_1 - ver.getWidth(), LINE4_Y_1))
				//author
				.push(Tween.set(author, ImageActorAccessor.POSITION_XY).target(LINE5_X_1 + author.getWidth(), LINE5_Y_1));		
	}
	
	private Timeline tweenState1() {
		return Timeline.createSequence()
				//I
				.push(Tween.to(i, ImageActorAccessor.POSITION_X, DURATION_1).target(LINE1_X_2))
				//am
				.push(Tween.to(am, ImageActorAccessor.POSITION_X, DURATION_1).target(LINE1_X_2 + (i.getWidth() + SPACEX_1) * SCALE_1))
				//a
				.push(Tween.to(a, ImageActorAccessor.POSITION_X, DURATION_1).target(LINE2_X_2))
				.beginParallel()
				//p
				.push(Tween.to(p, ImageActorAccessor.POSITION_X, DURATION_1).target(LINE2_X_2 + (a.getWidth() + SPACEX_1) * SCALE_1))
				//p1
				.push(Tween.to(p1, ImageActorAccessor.POSITION_X, DURATION_1).target(LINE2_X_2 + (a.getWidth() + SPACEX_1 + p.getWidth() + SPACEX_2) * SCALE_1))
				.end()
				//semicolon
				.push(Tween.to(semi, ImageActorAccessor.POSITION_X, DURATION_1).target(LINE2_X_2 + (a.getWidth() + SPACEX_1 + p.getWidth() + SPACEX_2 + p1.getWidth() + SPACEX_2) * SCALE_1));
	}
	
	
	private Timeline tweenState2() {
		return Timeline.createParallel()
				//bg
				.push(Tween.to(bg, ImageActorAccessor.POSITION_XY, DURATION_3).target(0, (float) (ScreenConfig.HEIGHT * 0.382)).ease(Bounce.OUT))
				//i
				.push(Tween.to(i, ImageActorAccessor.POSITION_XY, DURATION_2).target(FINAL_X_I, FINAL_Y_I))
				.push(Tween.to(i, ImageActorAccessor.SCALE_XY, DURATION_2).target(SCALE_2, SCALE_2))
				//P
				.push(Tween.to(p, ImageActorAccessor.POSITION_XY, DURATION_2).target(FINAL_X_I + (i.getWidth() + SPACEX_1 * 2 + semi.getWidth()) * SCALE_2, FINAL_Y_I))
				.push(Tween.to(p, ImageActorAccessor.SCALE_XY, DURATION_2).target(SCALE_2, SCALE_2))
				//semicolon
				.push(Tween.to(semi, ImageActorAccessor.POSITION_XY, DURATION_2).target(FINAL_X_I + (i.getWidth() + SPACEX_1)  * SCALE_2, FINAL_Y_I))
				.push(Tween.to(semi, ImageActorAccessor.SCALE_XY, DURATION_2).target(SCALE_2, SCALE_2))				
				//am, a, p1
				.push(Tween.to(am, ImageActorAccessor.POSITION_Y, DURATION_2).target(FINAL_Y_OTHER + am.getHeight() * SCALE_1).ease(Expo.OUT))
				.push(Tween.to(a, ImageActorAccessor.POSITION_Y, DURATION_2).target(FINAL_Y_OTHER + a.getHeight() * SCALE_1).ease(Expo.OUT))
				.push(Tween.to(p1, ImageActorAccessor.POSITION_Y, DURATION_2).target(FINAL_Y_OTHER + p1.getHeight() * SCALE_1).ease(Expo.OUT));		
	}
	
	private Timeline tweenState3() {
		return Timeline.createParallel()
				//title, ver
				.push(Tween.to(title, ImageActorAccessor.POSITION_X, DURATION_4).target(LINE3_X_2 - title.getWidth()))
				.push(Tween.to(ver, ImageActorAccessor.POSITION_X, DURATION_4).target(LINE4_X_2 - ver.getWidth()))
				//author
				.push(Tween.to(author, ImageActorAccessor.POSITION_X, DURATION_4).target(LINE5_X_2));

	}
	
}
