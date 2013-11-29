package jie.android.ip.screen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.group.BaseGroup;
import jie.android.ip.group.BoxGroup;
import jie.android.ip.group.ConsoleGroup;
import jie.android.ip.screen.box.BoxExecutor;
import jie.android.ip.screen.box.BoxRenderConfig;

public class BoxScreen extends BaseScreen {

	private TextureAtlas boxTextureAtlas;
	
	private BoxGroup groupSource, groupTarget;
	private ConsoleGroup groupConsole;
	
	private BoxExecutor boxExecutor;
	
	public BoxScreen(IPGame game) {
		super(game);
		
		boxTextureAtlas = game.getResources().getAssetManager().get(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);
		
		initBackgroup();
		initGroups();
		
		initExecutor();
		
		boxExecutor.loadScript(".\\doc\\script.xml");
		boxExecutor.execute(".\\doc\\test.xml");
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
		groupConsole.setScaleY(0.5f);
		groupConsole.setPosition(0, 0);
		this.addActor(groupConsole);
		
//		initBoxGroup(groupTarget);
//		initConsoleGroup(groupConsole);
	}
	
	private void initExecutor() {
		BoxRenderConfig config = new BoxRenderConfig();
		config.setSourceGroup(groupSource);
		config.setTargetGroup(groupTarget);
		config.setResources(game.getResources());
		config.setTweenManager(this.tweenManager);
		
		boxExecutor = new BoxExecutor(config);
	}
		

}
