package jie.android.ip.screen.start;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Expo;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.IPGame;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.menu.MenuScreen;
import jie.android.ip.screen.start.StartConfig.Image;
import jie.android.ip.screen.start.StartConfig.Const;
import jie.android.ip.utils.Utils;

public class StartScreen extends BaseScreen {
	
	private final TextureAtlas textureAtlas;
	
	private ImageActor bg;
	private ImageActor i, am, a, p, p1, semi;
	private ImageActor title, ver, author;
	private ImageActor loading;
	
	private TweenCallback tweenCompleteCallback = new TweenCallback() {

		@Override
		public void onEvent(int type, BaseTween<?> source) {
//			Utils.log("tween event", "type = " + type + " source = " + source.toString());
			
			game.getResources().loadAssetManager();
			game.setScreen(new MenuScreen(game));
		}
		
	};

	public StartScreen(IPGame game) {
		super(game);
		
		textureAtlas = game.getResources().getTextureAtlas(PackConfig.SCREEN_START);

		initActors();

		initTween();
	}

	private void initActors() {
	
		i = new ImageActor(textureAtlas.findRegion(Image.I));
		am = new ImageActor(textureAtlas.findRegion(Image.AM));
		a = new ImageActor(textureAtlas.findRegion(Image.A));
		p = new ImageActor(textureAtlas.findRegion(Image.P));
		p1 = new ImageActor(textureAtlas.findRegion(Image.P1));
		semi = new ImageActor(textureAtlas.findRegion(Image.SEMI));

		bg = new ImageActor(textureAtlas.findRegion(Image.BG));
		bg.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		title = new ImageActor(textureAtlas.findRegion(Image.TITLE));
		ver = new ImageActor(textureAtlas.findRegion(Image.VER));
		author = new ImageActor(textureAtlas.findRegion(Image.AUTHOR));
		
		loading = new ImageActor(textureAtlas.findRegion(Image.LOADING));
		float x = (ScreenConfig.WIDTH - loading.getWidth()) / 2;
		loading.setPosition(x, Const.LOADING_Y);
		loading.setScale(0.0f, 0.0f);
		
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
		
		this.addActor(loading);
	}

	private void initTween() {
		
		Timeline.createSequence()
			.push(tweenSet())
			.push(tweenState1())
			.pushPause(Const.DELAY_1)
			.push(tweenState2())
			.pushPause(Const.DELAY_2)
			.push(tweenState3())
			.pushPause(Const.DELAY_3)
			.setCallback(tweenCompleteCallback)
			.start(tweenManager);
	}
	
	private Timeline tweenSet() {
		return Timeline.createParallel()
				//bg
				.push(Tween.set(bg, ImageActorAccessor.POSITION_XY).target(0, ScreenConfig.HEIGHT * 1.2f))
				//.push(Tween.set(bg, ImageActorAccessor.SCALE_TO_XY).target(ScreenConfig.WIDTH, ScreenConfig.HEIGHT))
				//i
				.push(Tween.set(i, ImageActorAccessor.POSITION_XY).target(Const.LINE1_X_1 - i.getWidth() * Const.SCALE_1, Const.LINE1_Y_1))
				.push(Tween.set(i, ImageActorAccessor.SCALE_XY).target(Const.SCALE_1, Const.SCALE_1))
				//am
				.push(Tween.set(am, ImageActorAccessor.POSITION_XY).target(Const.LINE1_X_1 - am.getWidth() * Const.SCALE_1, Const.LINE1_Y_1))
				.push(Tween.set(am, ImageActorAccessor.SCALE_XY).target(Const.SCALE_1, Const.SCALE_1))
				//a
				.push(Tween.set(a, ImageActorAccessor.POSITION_XY).target(Const.LINE2_X_1 - a.getWidth() * Const.SCALE_1, Const.LINE2_Y_1))
				.push(Tween.set(a, ImageActorAccessor.SCALE_XY).target(Const.SCALE_1, Const.SCALE_1))
				//p
				.push(Tween.set(p, ImageActorAccessor.POSITION_XY).target(Const.LINE2_X_1 - p.getWidth() * Const.SCALE_1, Const.LINE2_Y_1))
				.push(Tween.set(p, ImageActorAccessor.SCALE_XY).target(Const.SCALE_1, Const.SCALE_1))
				//p1
				.push(Tween.set(p1, ImageActorAccessor.POSITION_XY).target(Const.LINE2_X_1 - p1.getWidth() * Const.SCALE_1, Const.LINE2_Y_1 - (Const.SPACEY_1 * Const.SCALE_1)))
				.push(Tween.set(p1, ImageActorAccessor.SCALE_XY).target(Const.SCALE_1, Const.SCALE_1))
				//semicolon
				.push(Tween.set(semi, ImageActorAccessor.POSITION_XY).target(Const.LINE2_X_1 - semi.getWidth() * Const.SCALE_1, Const.LINE2_Y_1))
				.push(Tween.set(semi, ImageActorAccessor.SCALE_XY).target(Const.SCALE_1, Const.SCALE_1))
				//title, ver
				.push(Tween.set(title, ImageActorAccessor.POSITION_XY).target(Const.LINE3_X_1 - title.getWidth(), Const.LINE3_Y_1))
				.push(Tween.set(ver, ImageActorAccessor.POSITION_XY).target(Const.LINE4_X_1 - ver.getWidth(), Const.LINE4_Y_1))
				//author
				.push(Tween.set(author, ImageActorAccessor.POSITION_XY).target(Const.LINE5_X_1 + author.getWidth(), Const.LINE5_Y_1));		
	}
	
	private Timeline tweenState1() {
		return Timeline.createSequence()
				//I
				.push(Tween.to(i, ImageActorAccessor.POSITION_X, Const.DURATION_1).target(Const.LINE1_X_2))
				//am
				.push(Tween.to(am, ImageActorAccessor.POSITION_X, Const.DURATION_1).target(Const.LINE1_X_2 + (i.getWidth() + Const.SPACEX_1) * Const.SCALE_1))
				//a
				.push(Tween.to(a, ImageActorAccessor.POSITION_X, Const.DURATION_1).target(Const.LINE2_X_2))
				.beginParallel()
				//p
				.push(Tween.to(p, ImageActorAccessor.POSITION_X, Const.DURATION_1).target(Const.LINE2_X_2 + (a.getWidth() + Const.SPACEX_1) * Const.SCALE_1))
				//p1
				.push(Tween.to(p1, ImageActorAccessor.POSITION_X, Const.DURATION_1).target(Const.LINE2_X_2 + (a.getWidth() + Const.SPACEX_1 + p.getWidth() + Const.SPACEX_2) * Const.SCALE_1))
				.end()
				//semicolon
				.push(Tween.to(semi, ImageActorAccessor.POSITION_X, Const.DURATION_1).target(Const.LINE2_X_2 + (a.getWidth() + Const.SPACEX_1 + p.getWidth() + Const.SPACEX_2 + p1.getWidth() + Const.SPACEX_2) * Const.SCALE_1));
	}
	
	
	private Timeline tweenState2() {
		return Timeline.createParallel()
				//bg
				.push(Tween.to(bg, ImageActorAccessor.POSITION_XY, Const.DURATION_3).target(0, (float) (ScreenConfig.HEIGHT * 0.382)).ease(Bounce.OUT))
				//i
				.push(Tween.to(i, ImageActorAccessor.POSITION_XY, Const.DURATION_2).target(Const.FINAL_X_I, Const.FINAL_Y_I))
				.push(Tween.to(i, ImageActorAccessor.SCALE_XY, Const.DURATION_2).target(Const.SCALE_2, Const.SCALE_2))
				//P
				.push(Tween.to(p, ImageActorAccessor.POSITION_XY, Const.DURATION_2).target(Const.FINAL_X_I + (i.getWidth() + Const.SPACEX_1 * 2 + semi.getWidth()) * Const.SCALE_2, Const.FINAL_Y_I))
				.push(Tween.to(p, ImageActorAccessor.SCALE_XY, Const.DURATION_2).target(Const.SCALE_2, Const.SCALE_2))
				//semicolon
				.push(Tween.to(semi, ImageActorAccessor.POSITION_XY, Const.DURATION_2).target(Const.FINAL_X_I + (i.getWidth() + Const.SPACEX_1)  * Const.SCALE_2, Const.FINAL_Y_I))
				.push(Tween.to(semi, ImageActorAccessor.SCALE_XY, Const.DURATION_2).target(Const.SCALE_2, Const.SCALE_2))				
				//am, a, p1
				.push(Tween.to(am, ImageActorAccessor.POSITION_Y, Const.DURATION_2).target(Const.FINAL_Y_OTHER + am.getHeight() * Const.SCALE_1).ease(Expo.OUT))
				.push(Tween.to(a, ImageActorAccessor.POSITION_Y, Const.DURATION_2).target(Const.FINAL_Y_OTHER + a.getHeight() * Const.SCALE_1).ease(Expo.OUT))
				.push(Tween.to(p1, ImageActorAccessor.POSITION_Y, Const.DURATION_2).target(Const.FINAL_Y_OTHER + p1.getHeight() * Const.SCALE_1).ease(Expo.OUT));		
	}
	
	private Timeline tweenState3() {
		return Timeline.createParallel()
				//loading
				.push(Tween.set(loading, ImageActorAccessor.SCALE_XY).target(1.0f, 1.0f))
				//title, ver
				.push(Tween.to(title, ImageActorAccessor.POSITION_X, Const.DURATION_4).target(Const.LINE3_X_2 - title.getWidth()))
				.push(Tween.to(ver, ImageActorAccessor.POSITION_X, Const.DURATION_4).target(Const.LINE4_X_2 - ver.getWidth()))
				//author
				.push(Tween.to(author, ImageActorAccessor.POSITION_X, Const.DURATION_4).target(Const.LINE5_X_2));

	}
	
}
