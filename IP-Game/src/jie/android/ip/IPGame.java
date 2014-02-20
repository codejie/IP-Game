package jie.android.ip;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.database.DBAccess;
import jie.android.ip.screen.menu.MenuScreen;
import jie.android.ip.screen.play.PlayScreen;
import jie.android.ip.screen.start.StartScreen;
import jie.android.ip.setup.Setup;
import jie.android.ip.utils.Utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IPGame extends Game {

	private final Setup setup;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Resources resources;
	private DBAccess dbAccess;
	private Sounder sounder;
	
	public IPGame(final Setup setup) {
		this.setup = setup;
		
		Environment.init();
	}
	
	@Override
	public void create() {
		initDBAccess();
		initResources();
		initSounder();
		
		initCamera();
		initSpriteBatch();
		
		//this.setScreen(new TestScreen(this));
		//this.setScreen(new StartScreen(this));
		//this.setScreen(new DDTestScreen(this));
		//this.setScreen(new BoxScreen(this));
		//this.setScreen(new PlayScreen(this, 2));
		//this.setScreen(new MenuScreen(this));
		
		Environment.loadAppData(dbAccess);
		
		setStartScreen();
	}

	@Override
	public void dispose() {
		super.dispose();
		
		
		sounder.dispose();
		resources.dispose();		
		batch.dispose();
		
		dbAccess.close();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}
	
	private void initCamera() {
		camera = new OrthographicCamera(ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		//camera.position.set(ScreenConfig.WIDTH / 2, ScreenConfig.HEIGHT / 2, 0);
	}

	public final Setup getSetup() {
		return setup;
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

	private void initSounder() {
		sounder = new Sounder(resources);
	}
	
	public final Sounder getSounder() {
		return sounder;
	}
	
	private void setStartScreen() {
		this.setScreen(new StartScreen(this));		
	}
	
	public void setMenuScreen() {
		this.setScreen(new MenuScreen(this));
	}
	
	public void setPlayScreen(int packId, int scriptId) {
		this.setScreen(new PlayScreen(this, packId, scriptId));
	}

	public void setNextPlayScreen(int packId, int scriptId) {
		int id = dbAccess.getNextScriptId(packId, scriptId);
		if (id != -1) {
			setPlayScreen(packId, id);
		} else {
			setMenuScreen();
		}
	}
	
}
