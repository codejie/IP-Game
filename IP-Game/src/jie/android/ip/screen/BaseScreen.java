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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BaseScreen implements Screen {

	protected final IPGame game;
	
	private final ActorStage actorStage;
	private final ScreenCanvas screenCanvas; 
	
	protected TweenManager tweenManager = new TweenManager();	
	
	public BaseScreen(IPGame game) {
		
//		super(CommonConsts.ScreenConfig.WIDTH, CommonConsts.ScreenConfig.HEIGHT, true, game.getSpriteBatch());
		
		this.game = game;
		
		screenCanvas = new ScreenCanvas(game.getSpriteBatch());		
		actorStage = new ActorStage(game.getSpriteBatch());
		
		//this.setCamera(this.getCamera());
		
		initTweenManager();
		
		Gdx.input.setInputProcessor(actorStage.getInputProcessor());
	}
	
	private void initTweenManager() {
		Tween.registerAccessor(ImageActor.class, new ImageActorAccessor());
	}
	
	public void addActor(final Actor actor) {
		actorStage.addActor(actor);
	}
	
	public void addSprite(final Sprite sprite) {
		screenCanvas.addSprite(sprite);
	}

	@Override
	public void render(float delta) {
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.graphics.getGL10().glEnable(GL10.GL_BLEND);
		Gdx.graphics.getGL10().glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		
		tweenManager.update(delta);
		
		screenCanvas.render(delta);
		actorStage.render(delta);
//		act(delta);
//		draw();
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
		tweenManager.killAll();
		actorStage.dispose();
		screenCanvas.dispose();		
	}
	
//	@Override
//	public void act(float delta) {
//		super.act(delta);
//		tweenManager.update(delta);
//	}	

}
