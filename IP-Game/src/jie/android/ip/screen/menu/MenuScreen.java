package jie.android.ip.screen.menu;

import jie.android.ip.IPGame;
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
	
}
