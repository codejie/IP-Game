package jie.android.ip.screen;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.utils.Utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
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
	
	private final OrthographicCamera camera;
	//private final float ratioWidth, ratioHeight;
	
	public ActorStage(final OrthographicCamera camera, final Batch spriteBatch) {
		super(ScreenConfig.WIDTH, ScreenConfig.HEIGHT, true, spriteBatch);
		this.camera = camera;
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
			Vector3 pos = new Vector3(screenX, screenY, 0);
			camera.unproject(pos);
			
//			Utils.log("==", "sx = " + screenX + " sy = " + screenY + " px = " + pos.x + " py = " + pos.y + " rw = " + ratioWidth + " rh = " + ratioHeight);
			
			if (onTouchDownListener.isHandled((int)(ScreenConfig.WIDTH / 2 + pos.x), (int)(ScreenConfig.HEIGHT / 2 + pos.y), pointer, button)) {
				return true;
			}
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	public void setOnKeyDownListener(final OnKeyDownListener listener) {
		this.onKeyDownListener = listener;
	}
	
	public void setOnTouchDownListener(final OnTouchDownListener listener) {
		this.onTouchDownListener = listener;
	}

}
