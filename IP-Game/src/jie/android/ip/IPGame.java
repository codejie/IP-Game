package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.database.DBAccess;
import jie.android.ip.screen.menu.MenuScreen;
import jie.android.ip.screen.play.PlayScreen;
import jie.android.ip.setup.Setup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IPGame extends Game {

	private final Setup setup;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Resources resources;
	private DBAccess dbAccess;
	
	public IPGame(final Setup setup) {
		this.setup = setup;
		
		Environment.init();
	}
	
	@Override
	public void create() {
		initDBAccess();
		initResources();
		
		initCamera();
		initSpriteBatch();
		
		//this.setScreen(new TestScreen(this));
		//this.setScreen(new StartScreen(this));
		//this.setScreen(new DDTestScreen(this));
		//this.setScreen(new BoxScreen(this));
		//this.setScreen(new PlayScreen(this, 2));
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		
		resources.dispose();
		batch.dispose();
		dbAccess.close();
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
	
	public final Resources getResources() {
		return resources;
	}

	public final DBAccess getDBAccess() {
		return dbAccess;
	}
	
	private void initDBAccess() {
		dbAccess = new DBAccess(setup.getDatabaseConnection());
		
		dbAccess.check();
	}
	
	private void initResources() {
		resources = new Resources();
	}

	private void initSpriteBatch() {
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
	}
	
}
