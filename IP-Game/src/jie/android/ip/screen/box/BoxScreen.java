package jie.android.ip.screen.box;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.box.BoxConfig.Image;
import jie.android.ip.screen.box.console.ConsoleGroup;
import jie.android.ip.screen.box.console.ConsoleManager;
import jie.android.ip.utils.Utils;

public class BoxScreen extends BaseScreen {

	protected static final String Tag = BoxScreen.class.getSimpleName();

	private TextureAtlas textureAtlas;
	
	private BoxGroup groupSource, groupTarget;
	private ConsoleGroup groupConsole;
	
	private BoxExecutor boxExecutor;
	private ConsoleManager consoleManager;
	
	private BoxScreenEventListener listener = new BoxScreenEventListener() {

		@Override
		public void onConsoleButtonClick(int type, int state) {
			switch (type) {
			case 1://run button
				onConsoleRunButtonClick(state);
				break;
			case 2: // stop button
				onConsoleStopButtonClick(state);
				break;
			default:
				Utils.log(Tag, "unknown command button : " + type);
			}
		}

//		@Override
//		public void onBoxMoveCompleted(boolean isType, boolean succ) {
//			if (succ) {
//				Utils.log(Tag, "succ");
//			} else {
//				Utils.log(Tag, "failed");
//			}
//		}

		@Override
		public void onScriptCompleted(boolean succ) {
			Utils.log(Tag, "Script completed : " + succ);
		}
		
	};
	
	public BoxScreen(IPGame game) {
		super(game);
		
		textureAtlas = game.getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
		
		initBackgroup();
		initGroups();
		
		initManager();
		
		boxExecutor.loadScript(".\\doc\\script.xml");
//		boxExecutor.execute(".\\doc\\test.xml");
	}

	private void initBackgroup() {
		Sprite bg = textureAtlas.createSprite(Image.BACKGROUND);
		bg.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);		
		this.addSprite(bg);		
	}

	private void initGroups() {
		groupSource = new BoxGroup(game.getResources());
		groupSource.setScale(0.5f);
		groupSource.setPosition(0, ScreenConfig.HEIGHT / 2);
		this.addActor(groupSource);

		groupTarget = new BoxGroup(game.getResources());
		groupTarget.setScale(0.5f);
		groupTarget.setPosition(ScreenConfig.WIDTH / 2, ScreenConfig.HEIGHT / 2);
		this.addActor(groupTarget);
		
		groupConsole = new ConsoleGroup(game.getResources());
//		groupConsole.setScaleY(0.5f);
		groupConsole.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		this.addActor(groupConsole);
		
	}
	
	private void initManager() {
		BoxRenderAdapter adapter = new BoxRenderAdapter();
		adapter.setSourceGroup(groupSource);
		adapter.setTargetGroup(groupTarget);
		adapter.setConsoleGroup(groupConsole);
		adapter.setResources(game.getResources());
		adapter.setTweenManager(this.tweenManager);
//		adapter.setScreenListener(listener);
		
		boxExecutor = new BoxExecutor(adapter, listener);
		consoleManager = new ConsoleManager(adapter, listener);

	}
		
	protected void onConsoleRunButtonClick(int state) {
		boxExecutor.execute(".\\doc\\test.xml");		
	}

	protected void onConsoleStopButtonClick(int state) {
		boxExecutor.reset();
		
	}
}
