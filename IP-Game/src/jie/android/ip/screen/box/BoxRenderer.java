package jie.android.ip.screen.box;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quint;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.screen.box.BoxConfig.Const;
import jie.android.ip.screen.box.BoxConfig.Image;
import jie.android.ip.screen.box.BoxManager.Block;
import jie.android.ip.screen.box.BoxManager.OnRenderTweenListener;
import jie.android.ip.screen.box.BoxManager.Tray;

public class BoxRenderer {

	private final BoxRenderAdapter adapter;
	
	private final TextureAtlas textureAtlas;

	public BoxRenderer(final BoxRenderAdapter adapter) {
		this.adapter = adapter;
		this.textureAtlas = adapter.getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
	}

	private int colToBlockX(int col) {
		return Const.Box.COL_BASE + col * (Const.Box.BLOCK_WIDTH + Const.Box.COL_SPACE);
	}
	
	private int rowToBlockY(int row) {
		return Const.Box.ROW_BASE + row * (Const.Box.BLOCK_HEIGHT + Const.Box.ROW_SPACE);
	}
	
	private int colToTrayX(int col) {
		return Const.Box.TRAY_SPACE + col * (Const.Box.TRAY_WIDTH);
	}
	
	private int rowToTrayY() {
		return Const.Box.TRAY_BASE;
	}
	
	public void putSourceBlock(int row, int col, final Block block) {
		block.actor = makeActor(block.value, block.style);
		block.actor.setPosition(colToBlockX(col), rowToBlockY(row));
		adapter.getSourceGroup().addActor(block.actor);
	}

	public void putTargetBlock(int row, int col, final Block block) {
		block.actor = makeActor(block.value, block.style);
		block.actor.setPosition(colToBlockX(col), rowToBlockY(row));
		adapter.getTargetGroup().addActor(block.actor);		
	}

	public void putTray(final Tray tray) {
		tray.actor = new ImageActor(textureAtlas.findRegion(Image.Box.TRAY));// adapter.getResources().getSkin().getRegion("t"));
		tray.actor.setPosition(colToTrayX(tray.posCol), rowToTrayY());
		adapter.getSourceGroup().addActor(tray.actor);
	}

	public void clearTray(Tray tray) {
		if (tray.actor != null) {
			adapter.getSourceGroup().removeActor(tray.actor);
			tray.actor = null;
		}
	}

	public void clearSourceBlock(final Block block) {
		if (block.actor != null) {
			adapter.getSourceGroup().removeActor(block.actor);
			block.actor = null;
		}
	}
	
	private final ImageActor makeActor(int value, int style) {
		return new ImageActor(textureAtlas.findRegion(Image.Box.BOX_0));// adapter.getResources().getSkin().getRegion("ic"));
	}

	public void moveBlock(final Block block, final int srow, final int scol, final int trow, final int tcol, final OnRenderTweenListener onTweenListener) {
		if (block.actor != null) {
			Tween.to(block.actor, ImageActorAccessor.POSITION_Y, makeRowDelay(trow - srow)).target(rowToBlockY(trow)).ease(Quint.INOUT).setCallback(new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					onTweenListener.onCompleted(false, srow, scol, trow, tcol);
				}
			}).start(adapter.getTweenManager());
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
			.start(adapter.getTweenManager());
		}		
	}

	public void moveTray(final Tray tray, final int scol, final int tcol, final OnRenderTweenListener onTweenListener) {
		float ttx = colToTrayX(tcol);
		Tween.to(tray.actor, ImageActorAccessor.POSITION_X, makeColDelay(tcol - scol)).target(ttx).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				onTweenListener.onCompleted(true, 0, scol, 0, tcol);
			}
		}).start(adapter.getTweenManager());
	}
	
	private float makeRowDelay(int row) {
		return Math.abs(row) * adapter.getRenderDelay();
	}
	
	private float makeColDelay(int col) {
		return Math.abs(col) * adapter.getRenderDelay();
	}
	

}
