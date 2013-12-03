package jie.android.ip.screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.group.BoxGroup;
import jie.android.ip.group.CodeGroup;
import jie.android.ip.group.ConsoleGroup;
import jie.android.ip.screen.box.BoxExecutor;
import jie.android.ip.screen.console.ConsoleManager;
import jie.android.ip.utils.Utils;

public class BoxScreen extends BaseScreen {

	protected static final String Tag = BoxScreen.class.getSimpleName();

	private TextureAtlas boxTextureAtlas;
	
	private BoxGroup groupSource, groupTarget;
	private ConsoleGroup groupConsole;
	private CodeGroup groupCode;
	
	private BoxExecutor boxExecutor;
	private ConsoleManager consoleManager;
	
	private BoxScreenEventListener listener = new BoxScreenEventListener() {

		@Override
		public void onConsoleButtonClick(int type, int state) {
			boxExecutor.execute(".\\doc\\test.xml");			
		}

		@Override
		public void onBoxMoveCompleted(boolean succ) {
			if (succ) {
				Utils.log(Tag, "succ");
			} else {
				Utils.log(Tag, "failed");
			}
		}
		
	};
	
	public BoxScreen(IPGame game) {
		super(game);
		
		boxTextureAtlas = game.getResources().getAssetManager().get(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);
		
		initBackgroup();
		initGroups();
		
		initManager();
		
		boxExecutor.loadScript(".\\doc\\script.xml");
//		boxExecutor.execute(".\\doc\\test.xml");
	}

	private void initBackgroup() {
		Sprite bg = boxTextureAtlas.createSprite(ResourceConfig.BACKGROUND_NAME);
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
		groupConsole.setPosition(0, 0);
		this.addActor(groupConsole);
		
		groupCode = new CodeGroup(game.getResources());
		groupCode.setScale(0.5f);
		groupCode.setPosition(0, 0);
//		this.addActor(groupCode);
		
		
//		initBoxGroup(groupTarget);
//		initConsoleGroup(groupConsole);
	}
	
	private void initManager() {
		BoxRenderConfig config = new BoxRenderConfig();
		config.setSourceGroup(groupSource);
		config.setTargetGroup(groupTarget);
		config.setConsoleGroup(groupConsole);
		config.setCodeGroup(groupCode);
		config.setResources(game.getResources());
		config.setTweenManager(this.tweenManager);
		config.setScreenListener(listener);
		
		boxExecutor = new BoxExecutor(config);
		consoleManager = new ConsoleManager(config);

	}
		

}
