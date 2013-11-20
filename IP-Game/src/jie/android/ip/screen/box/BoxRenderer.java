package jie.android.ip.screen.box;

import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.box.BoxManager.Block;
import jie.android.ip.screen.box.BoxManager.Tray;

public class BoxRenderer {

	private final BoxConfig config;
	
	public BoxRenderer(final BoxConfig config) {
		this.config = config;
	}

	public void putSourceBlock(int row, int col, final Block value) {
		final String name = String.format("s.%d.%d", col, row);
		final ImageActor actor = makeActor(name, value.style, value.status);
		actor.setPosition(row + 100, col + 100);
		config.getSourceGroup().addActor(actor);
		
		
	}

	public void putTargetBlock(int row, int col, final Block value) {
		// TODO Auto-generated method stub
		
	}

	public void putTray(final Tray tray) {
		// TODO Auto-generated method stub
		
	}
		
	private final ImageActor makeActor(final String name, int style, int status) {
		return new ImageActor(name, config.getResources().getSkin().getRegion("ic"));
	}



	
	

}
