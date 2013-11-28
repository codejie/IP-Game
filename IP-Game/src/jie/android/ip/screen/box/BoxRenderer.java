package jie.android.ip.screen.box;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Expo;
import aurelienribon.tweenengine.equations.Quint;
import jie.android.ip.CommonConsts.BoxConfig;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;
import jie.android.ip.screen.box.BoxManager.Block;
import jie.android.ip.screen.box.BoxManager.OnRenderTweenListener;
import jie.android.ip.screen.box.BoxManager.Tray;

public class BoxRenderer {

	private final BoxRenderConfig config;
	
	private final TextureAtlas textureAtlas;

	public BoxRenderer(final BoxRenderConfig config) {
		this.config = config;
		this.textureAtlas = config.getResources().getAssetManager().get(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);;
	}

	private int colToBlockX(int col) {
		return BoxConfig.COL_BASE + col * (BoxConfig.BLOCK_WIDTH + BoxConfig.COL_SPACE);
	}
	
	private int rowToBlockY(int row) {
		return BoxConfig.ROW_BASE + row * (BoxConfig.BLOCK_HEIGHT + BoxConfig.ROW_SPACE);
	}
	
	private int colToTrayX(int col) {
		return BoxConfig.TRAY_SPACE + col * (BoxConfig.TRAY_WIDTH);
	}
	
	private int rowToTrayY() {
		return BoxConfig.TRAY_BASE;
	}
	
	public void putSourceBlock(int row, int col, final Block block) {
		block.actor = makeActor(block.value, block.style);
		block.actor.setPosition(colToBlockX(col), rowToBlockY(row));
		config.getSourceGroup().addActor(block.actor);
	}

	public void putTargetBlock(int row, int col, final Block block) {
		block.actor = makeActor(block.value, block.style);
		block.actor.setPosition(colToBlockX(col), rowToBlockY(row));
		config.getTargetGroup().addActor(block.actor);		
	}

	public void putTray(final Tray tray) {
		tray.actor = new ImageActor(textureAtlas.findRegion("t"));// config.getResources().getSkin().getRegion("t"));
		tray.actor.setPosition(colToTrayX(tray.posCol), rowToTrayY());
		config.getSourceGroup().addActor(tray.actor);
	}
		
	private final ImageActor makeActor(int value, int style) {
		return new ImageActor(textureAtlas.findRegion("ic"));// config.getResources().getSkin().getRegion("ic"));
	}

	public void moveBlock(final Block block, final int srow, final int scol, final int trow, final int tcol, final OnRenderTweenListener onTweenListener) {
		if (block.actor != null) {
			Tween.to(block.actor, ImageActorAccessor.POSITION_Y, makeRowDelay(trow - srow)).target(rowToBlockY(trow)).ease(Quint.INOUT).setCallback(new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					onTweenListener.onCompleted(false, srow, scol, trow, tcol);
				}
			}).start(config.getTweenManager());
		}
	}

	public void moveTrayWithBlock(final Tray tray, final Block block, final int scol, final int tcol, final OnRenderTweenListener onTweenListener) {
		if (block.actor != null) {
			float tbx = colToBlockX(tcol);
			float ttx = colToTrayX(tcol);
			float delay = makeColDelay(tcol - scol);
			Timeline.createParallel()
				.push(Tween.to(block.actor, ImageActorAccessor.POSITION_X, delay).target(tbx))
				.push(Tween.to(tray.actor, ImageActorAccessor.POSITION_X, delay).target(ttx))
			.setCallback(new TweenCallback() {

				@Override
				public void onEvent(int type, BaseTween<?> source) {
					onTweenListener.onCompleted(true, 0, scol, 0, tcol);
				}
			})
			.start(config.getTweenManager());
		}		
	}

	public void moveTray(final Tray tray, final int scol, final int tcol, final OnRenderTweenListener onTweenListener) {
		float ttx = colToTrayX(tcol);
		Tween.to(tray.actor, ImageActorAccessor.POSITION_X, makeColDelay(tcol - scol)).target(ttx).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				onTweenListener.onCompleted(true, 0, scol, 0, tcol);
			}
		}).start(config.getTweenManager());
	}
	
	private float makeRowDelay(int row) {
		return Math.abs(row) * config.getRenderDelay();
	}
	
	private float makeColDelay(int col) {
		return Math.abs(col) * config.getRenderDelay();
	}



	
	

}
