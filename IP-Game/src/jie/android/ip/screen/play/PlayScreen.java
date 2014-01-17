package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.graphics.g2d.Sprite;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Image;

public class PlayScreen extends BaseScreen {

	private final int packId;
	private final int scriptId;
	
	private PlayManager manager;
	private PlayRenderer renderer;
	
	public PlayScreen(IPGame game, int packId, int scriptId) {
		super(game);
		this.packId = packId;
		this.scriptId = scriptId;
		
		init();
		
		enter();
		
		loadScript();		
	}

	@Override
	public void dispose() {
		manager.dispose();
		
		super.dispose();
	}

	private void init() {
		
		initBackgroup();
		
		manager = new PlayManager(this);
		renderer = new PlayRenderer(this);
		
		manager.setEventListener(renderer.getManagerEventListener());
		renderer.setEventListener(manager.getRendererEventListener());
		
	}
	
	private void initBackgroup() {
		Sprite bg = game.getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX).createSprite(Image.BACKGROUND);
		bg.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);		
		this.addSprite(bg);		
	}
	
	private void loadScript() {
		manager.loadScript(scriptId);
	}
	
	private void enter() {
		final ToggleGroup toggle = new ToggleGroup(this);
		toggle.enter(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				PlayScreen.this.removeActor(toggle);
			}			
		});	;
	}	
	
	private void out(final int packId, final int scriptId) {
		final ToggleGroup toggle = new ToggleGroup(this);
		toggle.out(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				PlayScreen.this.removeActor(toggle);
				setScreen(packId, scriptId);
			}
			
		});		
	}
	
	protected void setScreen(final int packId, final int scriptId) {
		this.getGame().setScreen(new PlayScreen(this.getGame(), packId, scriptId));
	}

	public void setNextScreen() {
		int pid = packId;
		int sid = 1;
		out(pid, sid);
	}
	
}
