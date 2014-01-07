package jie.android.ip.screen.toggle;

import jie.android.ip.IPGame;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.BaseScreen;

public class ToggleScreen extends BaseScreen {

	private final int scriptId;
	
	private ImageActor right, left;
	private ImageActor bg;
	
	public ToggleScreen(final IPGame game, int scriptId) {
		super(game);
		
		this.scriptId = scriptId;
		
		initActors();
		
		tweenActors();
	}

	private void initActors() {
		// TODO Auto-generated method stub
		
	}

	private void tweenActors() {
		// TODO Auto-generated method stub
		
	}	

	private void setScreen(int id) {
		
	}
}
