package jie.android.ip.screen.box;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Actor;

import jie.android.ip.CommonConsts.BoxConfig;
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
		public Actor actor;
		
		public Block(int style) {
			this.style = style;
		}
	}
	
	private class BlockArray extends HashMap<Pair<Integer, Integer>, Block> {
		
		private static final long serialVersionUID = 1L;
		
		private int maxRow;
		private int maxCol;
		
		public BlockArray(int maxrow, int maxcol) {
			this.maxRow = maxrow;
			this.maxCol = maxcol;
		}
		
		public final Block get(int row, int col) {
			return super.get(new Pair<Integer, Integer>(Integer.valueOf(row), Integer.valueOf(col)));
		}
		
		public final Block add(int row, int col, final Block block) {
			return add(new Pair<Integer, Integer>(Integer.valueOf(row), Integer.valueOf(col)), block);
		}
		
		private final Block add(final Pair<Integer, Integer> key, final Block block) {
			super.put(key, block);
			return block;
		}
		
		public final Block update(int srow, int scol, int trow, int tcol) {

			final Pair<Integer, Integer> tkey = new Pair<Integer, Integer>(Integer.valueOf(trow), Integer.valueOf(tcol));
			if (super.get(tkey) != null) { // target is not empty
				return null;
			}
			
			final Pair<Integer, Integer> skey = new Pair<Integer, Integer>(Integer.valueOf(srow), Integer.valueOf(scol));
			final Block block = super.remove(skey);
			if (block == null) {
				return null;
			}
			
			return add(tkey, block);
		}
		
		public final Block getInRow(int col) {
			for (int i = 1; i < maxRow; ++ i) {//start from 1
				Block block = get(i, col);
				if (block != null) {
					return block;
				}
			}
			return null;
		}
		
		public int checkInRow(int col) {
			for (int i = 1; i < maxRow; ++ i) {//start from 1
				if (get(i, col) != null) {
					return i;
				}
			}
			return -1;
		}
	}
	
	public class Tray {

		public static final int STATUS_EMPTY = 0;
		public static final int STATUS_ATTACHED = 1;
		
		public static final String name = "tray";
		
		public int style = -1;
		public int status = STATUS_EMPTY;
		
		public int posCol = 0;
		
		public Actor actor;
		
		public Tray(int style, int col) {
			this.style = style;
			this.posCol = col;
		}		
	}
	
	public interface OnRenderTweenListener {
		public void onCompleted(boolean isTray, int srow, int scol, int trow, int tcol);
	}	

	private final BoxRenderConfig config;
	
	private BoxRenderer renderer;
	
	private BlockArray blockSource;
	private BlockArray blockTarget;
	private Tray tray;
	
	private OnRenderTweenListener onTweenSuccListener = new OnRenderTweenListener() {

		@Override
		public void onCompleted(boolean isTray, int srow, int scol, int trow, int tcol) {
			if (isTray) {
				onTrayMoveEnd(scol < tcol, true);
			} else {
				onBlockMoveEnd(srow > trow);
			}
		}
		
	};

	private OnRenderTweenListener onTweenFailListener = new OnRenderTweenListener() {

		@Override
		public void onCompleted(boolean isTray, int srow, int scol, int trow, int tcol) {
			if (isTray) {
				onTrayMoveEnd(scol < tcol, false);
			} else {
				onBlockMoveEnd(srow > trow);
			}			
		}
		
	};
	
	private OnBoxEventListener onEventListener;
	
	
	public BoxManager(final BoxRenderConfig config) {
		this.config = config;
	}
	
	public boolean loadScript(final Script script) {
		
		blockSource = new BlockArray(BoxConfig.MAX_ROW, BoxConfig.MAX_COL);
		if (script.getSource() != null) {
			for (final Script.BlockData data : script.getSource()) {
				inflateBlock(blockSource, data);
			}
		}
		
		blockTarget = new BlockArray(BoxConfig.MAX_ROW, BoxConfig.MAX_COL);
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
		block.add(unit.row,  unit.col, new Block(unit.style));
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

	public void moveBlock(int col, Direction direction) {
		//updata block
		if (direction == Direction.DOWN) {
			//check tray
			if (tray.posCol != col || tray.status != Tray.STATUS_EMPTY) {
				//error
			}
			
			int row = blockSource.checkInRow(col);
			if (row != -1) {
				final Block block = blockSource.update(row, col, 0, col);
				tray.status = Tray.STATUS_ATTACHED;
				renderer.moveBlock(block, row, col, 0, col, onTweenSuccListener);
			} else {
				// call event listener - fail
			}			
			
		} else if (direction == Direction.UP){
			//row must be 0
			int row = blockSource.checkInRow(col);
			if (row == -1) {
				row = BoxConfig.MAX_ROW - 1;
			} else if (row == 1){
				// call event listenr - fail
				return;
			}
			
			final Block block = blockSource.update(0, col, row, col);
			tray.status = Tray.STATUS_EMPTY;
			renderer.moveBlock(block, 0, col, row, col, onTweenSuccListener);
			
		} else {
			// impossible.
		}
		
		onBlockMoveStart(direction == Direction.DOWN);
	}
	
	public void moveTray(Direction direction) {
		int col = tray.posCol;
		int tcol = -1;
		if (direction == Direction.LEFT) {
			 tcol = col - 1;
		} else if (direction == Direction.RIGHT) {
			tcol = col + 1;
		} else {
			return;
		}
		
		OnRenderTweenListener callback = null; 
		if (tcol > 0 && tcol < BoxConfig.MAX_COL) {
			callback = onTweenSuccListener;
		} else {
			callback = onTweenFailListener;
		}
		
		tray.posCol = tcol;
		
		if (tray.status == Tray.STATUS_ATTACHED) {
			final Block block = blockSource.update(0, col, 0, tcol);
			renderer.moveTrayWithBlock(tray, block, col, tcol, callback);
		} else {
			renderer.moveTray(tray, col, tcol, callback);
		}
		onTrayMoveStart(direction == Direction.RIGHT);
	}

	public void setOnEventListener(OnBoxEventListener listener) {
		onEventListener = listener;
	}

	private void onBlockMoveStart(boolean down) {
		if (onEventListener != null) {
			onEventListener.onBlockMoveStart(down);
			onEventListener.onTrayStatusChanged(down);
		}
	}
	
	private void onBlockMoveEnd(boolean down) {
		if (onEventListener != null) {
			onEventListener.onBlockMoveEnd(down);
		}		
	}
	
	private void onTrayMoveStart(boolean right) {
		if (onEventListener != null) {
			onEventListener.onTrayMoveStart(right);
		}		
	}
	
	private void onTrayMoveEnd(boolean right, boolean succ) {
		if (onEventListener != null) {
			onEventListener.onTrayMoveEnd(right, succ);
		}		
	}
	
	public void doAction() {
		if (tray.status == Tray.STATUS_EMPTY) {
			this.moveBlock(tray.posCol, Direction.DOWN);
		} else {
			this.moveBlock(tray.posCol, Direction.UP);
		}
	}
	
	public void doMove(boolean right) {
		if (right) {
			this.moveTray(Direction.RIGHT);
		} else {
			this.moveTray(Direction.LEFT);
		}
	}
}
