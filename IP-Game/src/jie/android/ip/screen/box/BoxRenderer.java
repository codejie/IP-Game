package jie.android.ip.screen.box;

import aurelienribon.tweenengine.Tween;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;
import jie.android.ip.screen.box.BoxManager.Block;
import jie.android.ip.screen.box.BoxManager.Tray;

public class BoxRenderer {

	private final BoxConfig config;
	
	public BoxRenderer(final BoxConfig config) {
		this.config = config;
	}

	private float colToX(int col) {
		return config.getColBase() + col * (config.getBlockWidth() + config.getColSpace());
	}
	
	private float rowToY(int row) {
		return config.getRowBase() + row * (config.getBlockHeight() + config.getRowSpace());
	}
	
	public void putSourceBlock(int row, int col, final Block block) {
		final String name = String.format("s.%d.%d", col, row);
		final ImageActor actor = makeActor(name, block.style, block.status);
//		actor.setPosition(config.getRowBase() + row * config.getBlockHeight() + , col + 100);
		actor.setPosition(config.getColBase() + col * (config.getBlockWidth() + config.getColSpace())
				, config.getRowBase() + row * (config.getBlockHeight() + config.getRowSpace()));
		config.getSourceGroup().addActor(actor);
		
		
	}

	public void putTargetBlock(int row, int col, final Block block) {
		// TODO Auto-generated method stub
		
	}

	public void putTray(final Tray tray) {
		// TODO Auto-generated method stub
		
	}
		
	private final ImageActor makeActor(final String name, int style, int status) {
		return new ImageActor(name, config.getResources().getSkin().getRegion("ic"));
	}

	public void moveBlock(int srow, int scol, int trow, int tcol) {
		final String name = String.format("s.%d.%d", srow, scol);
		final ImageActor actor = (ImageActor) config.getSourceGroup().findActor(name);
		if (actor != null) {
			float tx = config.getColBase() + tcol * (config.getBlockWidth() + config.getColSpace());
			float ty = config.getRowBase() + trow * (config.getBlockHeight() + config.getRowSpace());
			Tween.to(actor, ImageActorAccessor.POSITION_Y, 1.0f).target(ty).start(config.getTweenManager());
		}
	}
	
	



	
	

}
