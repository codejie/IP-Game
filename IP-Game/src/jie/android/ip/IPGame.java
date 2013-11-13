package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenConfig;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IPGame extends Game {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	@Override
	public void create() {
		initCamera();
		initSpriteBatch();
	}

	@Override
	public void dispose() {
		super.dispose();
		
		batch.dispose();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
	private void initCamera() {
		camera = new OrthographicCamera();
		camera.position.set(ScreenConfig.WIDTH / 2, ScreenConfig.HEIGHT / 2, 0);		
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	private void initSpriteBatch() {
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}
}
