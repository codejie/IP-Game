package jie.android.ip.screen.box;

import java.util.HashMap;
import java.util.Map.Entry;

import jie.android.ip.Resources;
import jie.android.ip.group.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.script.Script;
import jie.android.ip.script.Script.BlockData;
import jie.android.ip.utils.Extended.Pair;

public class BoxManager {
	
	public enum Direction {
		DOWN, UP, LEFT, RIGHT;
	}
	
	public class Block {
		
		public static final int STATUS_EMPTY = 0;
		
		public int style = -1;
		public int status = STATUS_EMPTY;
		
		public Block(int style) {
			this.style = style;
		}
	}
	
	private class BlockArray extends HashMap<Pair<Integer, Integer>, Block> {
		
		private int maxRow, maxCol;
		
		public BlockArray(int maxrow, int maxcol) {
			this.maxRow = maxrow;
			this.maxCol = maxcol;
		}
		
		public final Block get(int row, int col) {
			return super.get(new Pair<Integer, Integer>(Integer.valueOf(row), Integer.valueOf(col)));
		}
		
		public boolean put(int row, int col, final Block block) {
			return super.put(new Pair<Integer, Integer>(Integer.valueOf(row), Integer.valueOf(col)), block) == null;
		}
		
		public boolean update(int srow, int scol, int trow, int tcol) {

			final Pair<Integer, Integer> tkey = new Pair<Integer, Integer>(Integer.valueOf(srow), Integer.valueOf(scol));
			if (super.get(tkey) != null) {
				return false;
			}
			
			final Pair<Integer, Integer> skey = new Pair<Integer, Integer>(Integer.valueOf(srow), Integer.valueOf(scol));
			final Block block = super.remove(skey);
			if (block == null) {
				return false;
			}
			
			return super.put(tkey, block) == null;
		}
		
		public final Block getByRow(int col) {
			for (int i = 1; i < maxRow; ++ i) {//start from 1
				Block block = get(i, col);
				if (block != null) {
					return block;
				}
			}
			return null;
		}
	}
	
	public class Tray {

		public static final int STATUS_EMPTY = 0;
		public static final int STATUS_ATTACHED = 1;
		
		public int style = -1;
		public int status = STATUS_EMPTY;
		
		public int posCol = 0;
		
		public Tray(int style, int col) {
			this.style = style;
			this.posCol = col;
		}		
	}
	

	private final BoxConfig config;
	
	private BoxRenderer renderer;
	
	private BlockArray blockSource;
	private BlockArray blockTarget;
	private Tray tray;
	
	public BoxManager(final BoxConfig config) {
		this.config = config;
	}
	
	public boolean loadScript(final Script script) {
		
		blockSource = new BlockArray(config.getMaxRow(), config.getMaxCol());
		if (script.getSource() != null) {
			for (final Script.BlockData data : script.getSource()) {
				inflateBlock(blockSource, data);
			}
		}
		
		blockTarget = new BlockArray(config.getMaxRow(), config.getMaxCol());
		if (script.getTarget() != null) {
			for (final Script.BlockData data : script.getTarget()) {
				inflateBlock(blockTarget, data);
			}
		}
		
		final Script.TrayData td = script.getTray();
		tray = new Tray(td.style, td.col);
		
		initRenderer();
		
		return true;
	}

	private void inflateBlock(BlockArray block, BlockData unit) {
		block.put(unit.row,  unit.col, new Block(unit.style));
	}

	private void initRenderer() {
		renderer = new BoxRenderer(config);
		
		for (final Entry<Pair<Integer, Integer>, Block> entry : blockSource.entrySet()) {
			renderer.putSourceBlock(entry.getKey().first(), entry.getKey().second(), entry.getValue());
		}		

		for (final Entry<Pair<Integer, Integer>, Block> entry : blockTarget.entrySet()) {
			renderer.putTargetBlock(entry.getKey().first(), entry.getKey().second(), entry.getValue());
		}
		
		renderer.putTray(tray);
	}	

	public void moveBlock(int row, Direction direction) {
		//updata block
		renderer.moveBlock(row, 2, row - 1, 2);
	}
}
