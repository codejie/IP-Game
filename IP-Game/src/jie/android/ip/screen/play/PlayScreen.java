package jie.android.ip.screen.play;

import jie.android.ip.IPGame;
import jie.android.ip.screen.BaseScreen;

public class PlayScreen extends BaseScreen {

	private PlayManager manager;
	private PlayRenderer renderer;
	
	public PlayScreen(IPGame game) {
		super(game);
		
	}

	private void init() {
		manager = new PlayManager(this);
		renderer = new PlayRenderer(this);
		
		manager.setEventListener(renderer.getManagerEventListener());
		renderer.setEventListener(manager.getRendererEventListener());
	}
}
