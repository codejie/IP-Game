package jie.android.ip.screen;

import jie.android.ip.CommonConsts;
import jie.android.ip.IPGame;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BaseScreen extends Stage implements Screen {

	protected final IPGame game;
	
	protected TweenManager tweenManager = new TweenManager();
	
	public BaseScreen(IPGame game) {		
		super(CommonConsts.ScreenConfig.WIDTH, CommonConsts.ScreenConfig.HEIGHT, true, game.getSpriteBatch());
		
		this.game = game;
		//this.setCamera(this.getCamera());
		
		initTweenManager();
		
		Gdx.input.setInputProcessor(this);
	}
	
	private void initTweenManager() {
		Tween.registerAccessor(ImageActor.class, new ImageActorAccessor());
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
		act(delta);
		draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (tweenManager.getRunningTweensCount() > 0) {
			tweenManager.update(delta);
		}		
	}	

}
