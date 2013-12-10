package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.BoxScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IPGame extends Game {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Resources resources;
	
	@Override
	public void create() {
		initCamera();
		initSpriteBatch();
		initResources();
		
		//this.setScreen(new TestScreen(this));
		//this.setScreen(new StartScreen(this));
		//this.setScreen(new DDTestScreen(this));
		this.setScreen(new BoxScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		
		resources.dispose();
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
	
	public final Resources getResources() {
		return resources;
	}
	
	private void initResources() {
		resources = new Resources();
	}
	
}
