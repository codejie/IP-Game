package jie.android.ip.screen.menu;

import com.badlogic.gdx.Input.Keys;

import jie.android.ip.IPGame;
import jie.android.ip.common.dialog.AppExitDialog;
import jie.android.ip.screen.BaseScreen;

public class MenuScreen extends BaseScreen {

	private MenuManager manager;
	private MenuRenderer renderer;
	
	public MenuScreen(IPGame game) {
		super(game);
		
		init();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	private void init() {
		manager = new MenuManager(this);
		renderer = new MenuRenderer(this);
		
		manager.setEventListener(renderer.getManagerEventListener());
		renderer.setEventListener(manager.getRendererEventListener());
		
		manager.loadPacks();
	}

	@Override
	protected boolean onKeyDown(int keyCode) {
		if (keyCode == Keys.BACK) {
			AppExitDialog dlg = AppExitDialog.getInstance(this);
			if (!dlg.isShow()) {
				dlg.show();
			}
			return true;
		}
		
		return super.onKeyDown(keyCode);
	}
	
}
