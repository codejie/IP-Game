package jie.android.ip.screen;

import jie.android.ip.IPGame;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseScreen implements Screen {

	protected final IPGame game;
	
	private final ActorStage actorStage;
	private final ScreenCanvas screenCanvas; 
	
	protected TweenManager tweenManager = new TweenManager();	
	
	public BaseScreen(final IPGame game) {
		
		this.game = game;
		
		screenCanvas = new ScreenCanvas(game.getSpriteBatch());		
		actorStage = new ActorStage(game.getSpriteBatch());
		
		Gdx.input.setInputProcessor(actorStage.getInputProcessor());
	}
	
	public final IPGame getGame() {
		return game;
	}
	
	public final TweenManager getTweenManager() {
		return tweenManager;
	}
	
	public void addActor(final Actor actor) {
		actorStage.addActor(actor);
	}
	
	public void removeActor(final Actor actor) {
		actorStage.removeActor(actor);
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
