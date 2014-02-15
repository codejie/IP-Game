package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.common.dialog.AppExitDialog;
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
		Sprite bg = game.getResources().getTextureAtlas(PackConfig.SCREEN_PLAY).createSprite(Image.BACKGROUND);
		bg.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		this.addSprite(bg);
	}
	
	private void loadScript() {
		manager.loadScript(packId, scriptId);
		if (packId == 1) {//Tutorials
			renderer.loadLesson(packId, scriptId);
		}
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
				setNextPlayScreen(packId, scriptId);
			}
			
		});		
	}
	
	private void out() {
		final ToggleGroup toggle = new ToggleGroup(this);
		toggle.out(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				//PlayScreen.this.removeActor(toggle);
				setMenuScreen();
			}
			
		});
	}
	
	private void setNextPlayScreen(final int packId, final int scriptId) {
		this.getGame().setNextPlayScreen(packId, scriptId);
	}
	
	public void setNextScreen() {
		out(packId, scriptId);
	}
	
	private void setMenuScreen() {
		this.getGame().setMenuScreen();
	}	
	
	public void returnMenuScreen() {
		out();
	}

	@Override
	protected boolean onKeyDown(int keyCode) {
		if (keyCode == Keys.BACK) {
			renderer.unloadLesson();
			AppExitDialog dlg = AppExitDialog.getInstance(this);
			if (!dlg.isShow()) {
				dlg.show();
			}
			return true;
		}
		return super.onKeyDown(keyCode);
	}

	@Override
	protected boolean onTouchDown(int x, int y, int pointer, int button) {
		if (renderer.onScreenTouchDown(x, y, pointer, button)) {
			return true;
		}
		return super.onTouchDown(x, y, pointer, button);
	}
}
