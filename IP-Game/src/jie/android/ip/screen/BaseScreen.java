package jie.android.ip.screen;

import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.utils.Utils;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Scaling;

public class BaseScreen implements Screen {

	protected final IPGame game;
	
	private final ActorStage actorStage;
	private final ScreenCanvas screenCanvas; 
	
	protected TweenManager tweenManager = new TweenManager();	
	
	public BaseScreen(final IPGame game) {
		
		this.game = game;
		
		screenCanvas = new ScreenCanvas(game.getSpriteBatch());		
		actorStage = new ActorStage(game.getCamera(), game.getSpriteBatch());
		
		Gdx.input.setInputProcessor(actorStage.getInputProcessor());
		Gdx.input.setCatchBackKey(true);
		
		setOnKeyDownListener();
//		setOnTouchDownListener();
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
	
	public void setOnKeyDownListener() {
		actorStage.setOnKeyDownListener(new ActorStage.OnKeyHandledListener() {

			@Override
			public boolean isHandled(int keyCode) {
				return onKeyDown(keyCode);
			}
			
		});
	}
	
	public void setOnTouchDownListener() {
		actorStage.setOnTouchDownListener(new ActorStage.OnTouchHandledListener() {

			@Override
			public boolean isHandled(int x, int y, int pointer, int button) {
				return onTouchDown(x, y, pointer, button);
			}			
		});
	}
	
	public void setOnTouchUpListener() {
		actorStage.setOnTouchUpListener(new ActorStage.OnTouchHandledListener() {

			@Override
			public boolean isHandled(int x, int y, int pointer, int button) {
				return onTouchUp(x, y, pointer, button);
			}			
		});
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
		
		tweenManager.update(delta);
		
		screenCanvas.render(delta);
		actorStage.render(delta);
//		act(delta);
//		draw();
	}

	@Override
	public void resize(int width, int height) {
		//https://github.com/libgdx/libgdx/wiki/Scene2d
		Vector2 size = Scaling.fit.apply(ScreenConfig.WIDTH, ScreenConfig.HEIGHT, width, height);
	    int viewportX = (int)(width - size.x) / 2;
	    int viewportY = (int)(height - size.y) / 2;
	    int viewportWidth = (int)size.x;
	    int viewportHeight = (int)size.y;
	    Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
	    actorStage.setViewport(ScreenConfig.WIDTH, ScreenConfig.HEIGHT, true, viewportX, viewportY, viewportWidth, viewportHeight);
		
//		if (((float)width/(float)height) == ((float)ScreenConfig.WIDTH/(float)ScreenConfig.HEIGHT)) {
//			return;
//		}
//		
//		float w = width;
//		float h = height;
//		
//		float rw = w / ScreenConfig.WIDTH;
//		float rh = h / ScreenConfig.HEIGHT;
//		
//		if (rw < rh) {
//			h = h * (2 - rw);
//		} else {
//			w = w * (2 - rh);
//		}
////		Utils.log("===", "rw = " + rw + " rh = " + rh +  " width = " + width + " height = " + height + " w = " + w + " h = " + h);		
//		viewport = new Rectangle(0, 0, w, h); 
//		resized = true;
		
//		RATIO_WIDTH = width / ScreenConfig.WIDTH;
//		RATIO_HEIGHT = height / ScreenConfig.HEIGHT;
//        float aspectRatio = (float)width/(float)height;
//        float scale = 1f;
//        Vector2 crop = new Vector2(0f, 0f);
//
//        if(aspectRatio > ASPECT_RATIO)
//        {
//            scale = (float)height/(float)ScreenConfig.HEIGHT;
//            crop.x = (width - ScreenConfig.WIDTH*scale)/2f;
//        }
//        else if(aspectRatio < ASPECT_RATIO)
//        {
//            scale = (float)width/(float)ScreenConfig.WIDTH;
//            crop.y = (height - ScreenConfig.HEIGHT*scale)/2f;
//        }
//        else
//        {
//            scale = (float)width/(float)ScreenConfig.WIDTH;
//        }
//
//        float w = (float)ScreenConfig.WIDTH*scale;
//        float h = (float)ScreenConfig.HEIGHT*scale;
//        viewport = new Rectangle(crop.x, crop.y, w, h);
		
//		Utils.log("===", "width = " + width + " height = " + height + " w = " + w + " h = " + h);
//        viewport = new Rectangle(0, 0, width + 80, height + 50);
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
		
		actorStage.setOnKeyDownListener(null);
		actorStage.setOnTouchDownListener(null);
		actorStage.setOnTouchUpListener(null);
		
		tweenManager.killAll();
		actorStage.dispose();
		screenCanvas.dispose();		
	}
	
	protected boolean onKeyDown(int keyCode) { 
		return false; 
	}
	
	protected boolean onTouchDown(int x, int y, int pointer, int button) {
		return false;
	}
	
	protected boolean onTouchUp(int x, int y, int pointer, int button) {
		return false;
	}	

}
