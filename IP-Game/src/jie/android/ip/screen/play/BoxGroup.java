package jie.android.ip.screen.play;


import java.util.Map.Entry;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quint;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.screen.play.Box.BlockArray;
import jie.android.ip.screen.play.Box.Tray;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;
import jie.android.ip.utils.Extended.Pair;

public class BoxGroup {
	
	private class BlockGroup extends BaseGroup {

		private final Resources resources;		
		private final TextureAtlas textureAtlas;
		
		public BlockGroup(final Resources resources) {
			this.resources = resources;			
			this.textureAtlas = resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
			
			initStage();
		}
		
		@Override
		protected void initStage() {
			ImageActor top = new ImageActor(textureAtlas.findRegion(Image.Box.FRAME));
			top.setBounds(4, ScreenConfig.HEIGHT - 16, ScreenConfig.WIDTH - 4, 16);
			this.addActor(top);
			
			ImageActor bottom = new ImageActor(textureAtlas.findRegion(Image.Box.FRAME));
			bottom.setBounds(4, 0, ScreenConfig.WIDTH - 4, 16);
			this.addActor(bottom);			
		}
		
		private int colToBlockX(int col) {
			return Const.Box.COL_BASE + (col - 1) * (Const.Box.BLOCK_WIDTH + Const.Box.COL_SPACE);
		}
		
		private int rowToBlockY(int row) {
			return Const.Box.ROW_BASE + (row) * (Const.Box.BLOCK_HEIGHT + Const.Box.ROW_SPACE);
		}
		
		private int colToTrayX(int col) {
			return Const.Box.TRAY_SPACE + (col - 1) * (Const.Box.TRAY_WIDTH);
		}
		
		private int rowToTrayY() {
			return Const.Box.TRAY_BASE;
		}
		
		public void loadBlock(final Box.BlockArray blockArray) {
			for (final Entry<Pair<Integer, Integer>, Box.Block> entry : blockArray.entrySet()) {
				final Box.Block block = entry.getValue();
				block.actor = makeActor(block.value, block.style);
				block.actor.setBounds(colToBlockX(entry.getKey().second()), rowToBlockY(entry.getKey().first()), Const.Box.BLOCK_WIDTH, Const.Box.BLOCK_HEIGHT);
				
				this.addActor(block.actor);
			}			
		}
		
		public void loadTray(final Box.Tray tray) {
			tray.actor = new ImageActor(textureAtlas.findRegion(Image.Box.TRAY));// adapter.getResources().getSkin().getRegion("t"));
			tray.actor.setBounds(colToTrayX(tray.posCol), rowToTrayY(), Const.Box.TRAY_WIDTH, Const.Box.TRAY_HEIGHT);
			
			this.addActor(tray.actor);
		}
		
		private final ImageActor makeActor(int value, int style) {
			if (value == 0) {
				return new ImageActor(textureAtlas.findRegion(Image.Box.BOX_0));// adapter.getResources().getSkin().getRegion("ic"));
			} else {
				return null;
			}
		}

		public void clearActors(final Box.Tray tray, final Box.BlockArray blockArray) {
			if (tray.actor != null) {
				this.removeActor(tray.actor);
			}
			
			for (final Entry<Pair<Integer, Integer>, Box.Block> entry : blockArray.entrySet()) {
				final Box.Block block = entry.getValue();
				if (block.actor != null) {
					this.removeActor(block.actor);
				}
			}
		}		
	}
	
	
	private final PlayScreen screen;
	private final TweenManager tweenManager;

	private final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private BlockGroup groupSource;
	private BlockGroup groupTarget;
	
	public BoxGroup(final PlayScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		this.screen = screen;
		this.tweenManager = this.screen.getTweenManager();
		
		this.internalListener = internalListener;

		initGroups();
	}
	
	private void initGroups() {
		groupSource = new BlockGroup(screen.getGame().getResources());
		groupSource.setScale(0.5f);
		groupSource.setPosition(0, ScreenConfig.HEIGHT / 2);
		screen.addActor(groupSource);

		groupTarget = new BlockGroup(screen.getGame().getResources());
		groupTarget.setScale(0.5f);
		groupTarget.setPosition(ScreenConfig.WIDTH / 2, ScreenConfig.HEIGHT / 2);
		screen.addActor(groupTarget);
	}

	public void load(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
		groupSource.loadBlock(source);
		groupSource.loadTray(tray);
		
		groupTarget.loadBlock(target);
	}
	
	public void reload(final Box.Tray tray, final Box.BlockArray source) {
		groupSource.clear();
		groupSource.loadBlock(source);
		groupSource.loadTray(tray);		
	}	

	public void clearAll() {
		groupSource.clear();
		groupTarget.clear();		
	}
	

	public void clearActors(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
		groupSource.clearActors(tray, source);
//		groupTarget.clearActors(target);
	}
	
	public void move(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
		if (tray != null) {
			if (tray.status == Box.Tray.STATUS_ATTACHED && block != null) {
				moveTrayWithBlock(tray, block, col, tcol);
			} else {
				moveTray(tray, col, tcol);
			}
		} else if (block != null) {
			moveBlock(block, col, row, tcol, trow);
		}
	}
	
	private void moveBlock(final Box.Block block, final int scol, final int srow, final int tcol, final int trow) {
		if (block.actor != null) {
			float delay = Math.abs(trow - srow) * PlayConfig.DELAY;
			Tween.to(block.actor, ImageActorAccessor.POSITION_Y, delay).target(groupSource.rowToBlockY(trow)).ease(Quint.INOUT).setCallback(new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					internalListener.onBoxMoveEnd();
				}
			}).start(tweenManager);
		}
	}

	private void moveTrayWithBlock(final Box.Tray tray, final Box.Block block, final int scol, final int tcol) {
		if (block.actor != null) {
			float tbx = groupSource.colToBlockX(tcol);
			float ttx = groupSource.colToTrayX(tcol);
			float delay = Math.abs(tcol - scol) * PlayConfig.DELAY;
			Timeline.createParallel()
				.push(Tween.to(block.actor, ImageActorAccessor.POSITION_X, delay).target(tbx))
				.push(Tween.to(tray.actor, ImageActorAccessor.POSITION_X, delay).target(ttx))
			.setCallback(new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					internalListener.onBoxMoveEnd();
				}
			})
			.start(tweenManager);
		}		
	}

	private void moveTray(final Box.Tray tray, final int scol, final int tcol) {
		float ttx = groupSource.colToTrayX(tcol);
		float delay = Math.abs(tcol - scol) * PlayConfig.DELAY;
		Tween.to(tray.actor, ImageActorAccessor.POSITION_X, delay).target(ttx).setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				internalListener.onBoxMoveEnd();
			}
		}).start(tweenManager);
	}

	public void focusSource(boolean show) {
		if (show) {
//			groupSource.setZIndex(0x0f);
			Timeline.createParallel()
				.push(Tween.to(groupSource, BaseGroupAccessor.POSITION_XY, 0.1f).target(0, ScreenConfig.HEIGHT * 0.334f))
				.push(Tween.to(groupSource, BaseGroupAccessor.SCALE_XY, 0.1f).target(0.667f, 0.667f))
				.push(Tween.to(groupTarget, BaseGroupAccessor.POSITION_XY, 0.1f).target(ScreenConfig.WIDTH * 0.667f, ScreenConfig.HEIGHT * 0.667f))
				.push(Tween.to(groupTarget, BaseGroupAccessor.SCALE_XY, 0.1f).target(0.334f, 0.334f))
				.setCallback(new TweenCallback() {
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						internalListener.onSourceFocused();
					}					
				})
				.start(tweenManager);
		} else {
			Timeline.createParallel()
				.push(Tween.to(groupSource, BaseGroupAccessor.POSITION_XY, 0.1f).target(0, ScreenConfig.HEIGHT / 2))
				.push(Tween.to(groupSource, BaseGroupAccessor.SCALE_XY, 0.1f).target(0.5f, 0.5f))
				.push(Tween.to(groupTarget, BaseGroupAccessor.POSITION_XY, 0.1f).target(ScreenConfig.WIDTH / 2, ScreenConfig.HEIGHT / 2))
				.push(Tween.to(groupTarget, BaseGroupAccessor.SCALE_XY, 0.1f).target(0.5f, 0.5f))				
				.start(tweenManager);			
		}
	}

}
