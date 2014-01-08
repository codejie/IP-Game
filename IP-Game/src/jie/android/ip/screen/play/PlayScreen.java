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

	private final int scriptId;
	
	private PlayManager manager;
	private PlayRenderer renderer;
	
	public PlayScreen(IPGame game, int scriptId) {
		super(game);
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
	
	private void out(final int id) {
		final ToggleGroup toggle = new ToggleGroup(this);
		toggle.out(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				PlayScreen.this.removeActor(toggle);
				setScreen(id);
			}
			
		});		
	}
	
	protected void setScreen(final int id) {
		this.getGame().setScreen(new PlayScreen(this.getGame(), id));
	}

	public void setNextScreen() {
		int id = 1;
		out(id);
	}
	
}
