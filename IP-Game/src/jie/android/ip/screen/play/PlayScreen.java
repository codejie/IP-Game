package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.common.dialog.AlertDialog;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Image;

public class PlayScreen extends BaseScreen {

	private final int packId;
	private final int scriptId;
	
	private PlayManager manager;
	private PlayRenderer renderer;
	
	private float clockSpeed = 0.1f;
	
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

		setClockSpeed(super.getGame().getDBAccess().getSysDataAsInt(SystemConfig.SYS_ATTR_SPEED));
		
		manager.setEventListener(renderer.getManagerEventListener());
		renderer.setEventListener(manager.getRendererEventListener());
	}
	
	private void setClockSpeed(int level) {
		switch(level) {
		case 1:
			clockSpeed = 0.2f;
			break;
		case 3:
			clockSpeed = 0.05f;
			break;
		case 2:
		default:
			clockSpeed = 0.1f;
			break;
		}		
	}
	
	private void initBackgroup() {
		Sprite bg = game.getResources().getTextureAtlas(PackConfig.SCREEN_PLAY).createSprite(Image.BACKGROUND);
		bg.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		this.addSprite(bg);
	}
	
	public float getClockSpeed() {
		return clockSpeed;
	}
	
	private void loadScript() {
		manager.loadScript(packId, scriptId);
		if (packId == 1) {//Tutorials
			super.setOnTouchDownListener();
			super.setOnTouchUpListener();
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
			
			final AlertDialog.ButtonClickListener listener = new AlertDialog.ButtonClickListener() {
				
				@Override
				public void onClick() {
					returnMenuScreen();
				}
			};
			
			final AlertDialog dlg = new AlertDialog(PlayScreen.this, "Are you sure close and return to Menu screen ?", PlayScreen.this.getGame().getResources().getBitmapTrueFont(70), Color.YELLOW, listener, null);
			dlg.show();			
			
//			AppExitDialog dlg = AppExitDialog.getInstance(this);
//			if (!dlg.isShow()) {
//				dlg.show();
//			}
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

	
	
	@Override
	protected boolean onTouchUp(int x, int y, int pointer, int button) {
		if (renderer.onScreenTouchUp(x, y, pointer, button)) {
			return true;
		}
		return super.onTouchUp(x, y, pointer, button);
	}

	@Override
	public void onSettingChanged(int attr, int intVal, String strVal) {
		super.onSettingChanged(attr, intVal, strVal);
		
		if (attr == SystemConfig.SYS_ATTR_SPEED) {
			setClockSpeed(intVal); 
		}
		
	}
	
}
