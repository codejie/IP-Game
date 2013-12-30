package jie.android.ip.screen.play;

import java.util.Map.Entry;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
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
	}
	
	
	private final PlayScreen screen;
	
	private BlockGroup groupSource;
	private BlockGroup groupTarget;
	
	public BoxGroup(final PlayScreen screen) {
		this.screen = screen;

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
	
}
