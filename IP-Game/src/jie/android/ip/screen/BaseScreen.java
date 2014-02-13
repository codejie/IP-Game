package jie.android.ip.screen;

import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.setup.Setup;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BaseScreen implements Screen {

	protected final IPGame game;
	
	private final ActorStage actorStage;
	private final ScreenCanvas screenCanvas; 
	
	private static final float ASPECT_RATIO = (float)ScreenConfig.WIDTH/(float)ScreenConfig.HEIGHT;
	private Rectangle viewport;
	
	protected TweenManager tweenManager = new TweenManager();	
	
	public BaseScreen(final IPGame game) {
		
		this.game = game;
		
		screenCanvas = new ScreenCanvas(game.getSpriteBatch());		
		actorStage = new ActorStage(game.getSpriteBatch());
		
		Gdx.input.setInputProcessor(actorStage.getInputProcessor());
		Gdx.input.setCatchBackKey(true);
		
		setDefaultKeyDownListener();
	}
	
	private void setDefaultKeyDownListener() {
		this.setOnKeyDownListener(new DefaultStageKeyDownListener(this ));
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
	
	public void setOnKeyDownListener(final ActorStage.OnKeyDownListener listener) {
		actorStage.setKeyDownListener(listener);
	}
	
	public void setOnTouchDownListener(final ActorStage.OnTouchDownListener listener) {
		actorStage.setTouchDownListener(listener);
	}
	
	public int screenToStageX(int screenX) {
		return screenX;
	}
	
	public int screenToStageY(int screenY) {
		return ScreenConfig.HEIGHT - screenY;
	}

	@Override
	public void render(float delta) {
	
		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.graphics.getGL10().glEnable(GL10.GL_BLEND);
		Gdx.graphics.getGL10().glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
		
		tweenManager.update(delta);
		
		screenCanvas.render(delta);
		actorStage.render(delta);
//		act(delta);
//		draw();
	}

	@Override
	public void resize(int width, int height) {
		
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);

        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)ScreenConfig.HEIGHT;
            crop.x = (width - ScreenConfig.WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)ScreenConfig.WIDTH;
            crop.y = (height - ScreenConfig.HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)ScreenConfig.WIDTH;
        }

        float w = (float)ScreenConfig.WIDTH*scale;
        float h = (float)ScreenConfig.HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
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

}
