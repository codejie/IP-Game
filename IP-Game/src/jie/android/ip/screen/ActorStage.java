package jie.android.ip.screen;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.utils.Utils;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ActorStage extends Stage {

	public interface OnKeyDownListener {
		public boolean isHandled(int keyCode);
	}
	
	public interface OnTouchDownListener {
		public boolean isHandled(int x, int y, int pointer, int button);
	}

	private OnKeyDownListener onKeyDownListener;	
	private OnTouchDownListener onTouchDownListener;
	
	public ActorStage(Batch spriteBatch) {
		super(ScreenConfig.WIDTH, ScreenConfig.HEIGHT, true, spriteBatch);
	}

	public void render(float delta) {
		this.act(delta);
		this.draw();		
	}

	public InputProcessor getInputProcessor() {
		return this;
	}
	
	public void removeActor(final Actor actor) {
		super.getRoot().removeActor(actor);
	}

	@Override
	public boolean keyDown(int keyCode) {
		if (onKeyDownListener != null) {
			if (onKeyDownListener.isHandled(keyCode)) {
				return true;
			}
		}
		
		return super.keyDown(keyCode);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (onTouchDownListener != null) {
			if (!onTouchDownListener.isHandled(screenX, ScreenConfig.HEIGHT - screenY, pointer, button)) {
				return true;
			}
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	public void setKeyDownListener(final OnKeyDownListener listener) {
		this.onKeyDownListener = listener;
	}
	
	public void setTouchDownListener(final OnTouchDownListener listener) {
		this.onTouchDownListener = listener;
	}

}
