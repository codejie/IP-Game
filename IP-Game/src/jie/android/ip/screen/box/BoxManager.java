package jie.android.ip.screen.box;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

import jie.android.ip.executor.Script;
import jie.android.ip.executor.Script.BlockData;
import jie.android.ip.screen.box.BoxConfig.Const;
import jie.android.ip.utils.Extended.Pair;

public class BoxManager implements Disposable {
	
	public enum Direction {
		DOWN, UP, LEFT, RIGHT;
	}
	
	public class Block {
		
		public static final int VALUE_0 = 0;
		
		public int value = VALUE_0;
		public int style = -1;		
		public Actor actor;
		
		public Block(int value, int style) {
			this.value = value;
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
		
		public synchronized final Block update(int srow, int scol, int trow, int tcol) {

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
		
//		public final Block getInRow(int col) {
//			for (int i = 1; i <= maxRow; ++ i) {//start from 1
//				Block block = get(i, col);
//				if (block != null) {
//					return block;
//				}
//			}
//			return null;
//		}
		
		public int checkInRow(int col) {
			for (int i = 1; i <= maxRow; ++ i) {//start from 1
				if (get(i, col) != null) {
					return i;
				}
			}
			return -1;
		}
		
		public boolean isMatch(final BlockArray right) {
			for (int col = 1; col < maxCol; ++ col) {
				for (int row = 1; row <= maxRow; ++ row) {
					final Block l = this.get(row, col);
					final Block r = right.get(row, col);
					if ((l == null && r != null) || (l != null && r == null)) {
						return false;
					} else if (l != null && r != null) {
						if (l.value != r.value) {
							return false;
						}
					}					
				}
			}
			return true;
		}
		
		public synchronized void reset() {
			super.clear();
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
		
		public void reset(int col) {
			this.posCol = col;
			status = STATUS_EMPTY;			
		}
	}
	
	public interface OnRenderTweenListener {
		public void onCompleted(boolean isTray, final Block block, int srow, int scol, int trow, int tcol);
	}

	private final BoxRenderAdapter adapter;
	
	private BoxRenderer renderer;
	
	private Script script;
	
	private BlockArray blockSource;
	private BlockArray blockTarget;
	private Tray tray;
	
	private OnRenderTweenListener onTweenSuccListener = new OnRenderTweenListener() {

		@Override
		public void onCompleted(boolean isTray, final Block block, int srow, int scol, int trow, int tcol) {
			if (isTray) {
				onTrayMoveEnd(scol < tcol, true);
			} else {
				onBlockMoveEnd(srow > trow, block);
			}
		}
		
	};

	private OnRenderTweenListener onTweenFailListener = new OnRenderTweenListener() {

		@Override
		public void onCompleted(boolean isTray, final Block block, int srow, int scol, int trow, int tcol) {
			if (isTray) {
				onTrayMoveEnd(scol < tcol, false);
			} else {
				onBlockMoveEnd(srow > trow, block);
			}			
		}		
	};
	
	private final OnBoxEventListener onEventListener;	
	
	public BoxManager(final BoxRenderAdapter adapter, final OnBoxEventListener boxListener) {
		this.adapter = adapter;
		this.onEventListener = boxListener;
	}
	
	@Override
	public void dispose() {
		if (renderer != null) {
			renderer.dispose();
		}
	}
	
	public boolean loadScript(final Script script) {
		
		blockSource = new BlockArray(Const.Box.MAX_ROW, Const.Box.MAX_COL);
		if (script.getSource() != null) {
			for (final Script.BlockData data : script.getSource()) {
				inflateBlock(blockSource, data);
			}
		}
		
		blockTarget = new BlockArray(Const.Box.MAX_ROW, Const.Box.MAX_COL);
		if (script.getTarget() != null) {
			for (final Script.BlockData data : script.getTarget()) {
				inflateBlock(blockTarget, data);
			}
		}
		
		final Script.TrayData td = script.getTray();
		tray = new Tray(td.style, td.col);
		
		initRenderer();
		
		this.script = script;
		
		return true;
	}
	
	public boolean resetSource() {
		
		for (final Entry<Pair<Integer, Integer>, Block> entry : blockSource.entrySet()) {
			renderer.removeSourceBlock(entry.getValue());
		}
		
		renderer.removeTray(tray);
		
		blockSource.reset();

		if (script.getSource() != null) {
			for (final Script.BlockData data : script.getSource()) {
				inflateBlock(blockSource, data);
			}
		}

		tray.reset(script.getTray().col);

		for (final Entry<Pair<Integer, Integer>, Block> entry : blockSource.entrySet()) {
			renderer.putSourceBlock(entry.getKey().first(), entry.getKey().second(), entry.getValue());
		}		
		
		renderer.putTray(tray);
		
		return true;
	}

	private boolean checkBlock() {
		return blockTarget.isMatch(blockSource);
	}
	
	private void inflateBlock(BlockArray block, BlockData unit) {
		block.add(unit.row,  unit.col, new Block(unit.value, unit.style));
	}

	private void initRenderer() {
		renderer = new BoxRenderer(adapter);
		
		for (final Entry<Pair<Integer, Integer>, Block> entry : blockSource.entrySet()) {
			renderer.putSourceBlock(entry.getKey().first(), entry.getKey().second(), entry.getValue());
		}		

		for (final Entry<Pair<Integer, Integer>, Block> entry : blockTarget.entrySet()) {
			renderer.putTargetBlock(entry.getKey().first(), entry.getKey().second(), entry.getValue());
		}
		
		renderer.putTray(tray);
	}
	
	private void moveBlock(int col, Direction direction) throws BoxException {
		//updata block
		if (direction == Direction.DOWN) {
			//check tray
			if (tray.posCol != col || tray.status != Tray.STATUS_EMPTY) {
				throw new BoxException(BoxException.E_TRAY_MISSING);
			}
			
			int row = blockSource.checkInRow(col);
			if (row != -1) {
				final Block block = blockSource.update(row, col, 0, col);
				if (block == null) {
					return;
				}				
				tray.status = Tray.STATUS_ATTACHED;
				renderer.moveBlock(block, row, col, 0, col, onTweenSuccListener);
			} else {
				onNoneBlockMove();
				//Nothing
				//throw new BoxException(BoxException.E_BLOCK_NOTFOUND);
				return;
			}
		} else if (direction == Direction.UP){
			if (tray.status != Tray.STATUS_ATTACHED) { // do nothing
				onNoneBlockMove();
				return;
			}
			int row = blockSource.checkInRow(col);
			if (row == -1) {
				row = Const.Box.MAX_ROW;
			} else if (row == 1) {
				throw new BoxException(BoxException.E_BLOCK_NOTROOM);
				//error
			} else {
				row = row - 1;
			}
			
			final Block block = blockSource.update(0, col, row, col);
			if (block == null) {
				return;
			}
			tray.status = Tray.STATUS_EMPTY;
			renderer.moveBlock(block, 0, col, row, col, onTweenSuccListener);			
		} else {
			throw new BoxException(BoxException.E_DIRECTION_UNSUPPORT);
		}
		
		onBlockMoveStart(direction == Direction.DOWN);
	}

	private void moveTray(Direction direction) throws BoxException {
		int col = tray.posCol;
		int tcol = -1;
		if (direction == Direction.LEFT) {
			 tcol = col - 1;
		} else if (direction == Direction.RIGHT) {
			tcol = col + 1;
		} else {
			throw new BoxException(BoxException.E_DIRECTION_UNSUPPORT);
		}
		
		OnRenderTweenListener callback = null; 
		if (tcol > 0 && tcol < Const.Box.MAX_COL) {
			callback = onTweenSuccListener;
		} else if (tcol == 0 || tcol == Const.Box.MAX_COL){
			callback = onTweenFailListener;
		} else {
			onTrayMoveEnd(direction == Direction.RIGHT, false);
			return;
		}
		
		tray.posCol = tcol;
		
		if (tray.status == Tray.STATUS_ATTACHED) {
			final Block block = blockSource.update(0, col, 0, tcol);
			if (block == null) {
				return;
			}			
			renderer.moveTrayWithBlock(tray, block, col, tcol, callback);
		} else {
			renderer.moveTray(tray, col, tcol, callback);
		}
		onTrayMoveStart(direction == Direction.RIGHT);
	}

//	public void setOnEventListener(OnBoxEventListener listener) {
//		onEventListener = listener;
//	}

	private void onBlockMoveStart(boolean down) {
		if (onEventListener != null) {
			onEventListener.onBlockMoveStart(down);
			onEventListener.onTrayStatusChanged(down);
		}
	}
	
	private void onBlockMoveEnd(boolean down, final Block block) {
		if (onEventListener != null) {
			onEventListener.onBlockMoveEnd(down, block.value,  checkBlock());
		}		
	}
	
	private void onTrayMoveStart(boolean right) {
		if (onEventListener != null) {
			onEventListener.onTrayMoveStart(right);
		}		
	}
	
	private void onTrayMoveEnd(boolean right, boolean succ) {
		if (onEventListener != null) {
			onEventListener.onTrayMoveEnd(right, succ, checkBlock());
		}		
	}
	
	private void onNoneBlockMove() {
		if (onEventListener != null) {
			onEventListener.onNoneBlockMove();
		}
	}
	
	
	public void doAction() {
		try {	
			if (tray.status == Tray.STATUS_EMPTY) {
				this.moveBlock(tray.posCol, Direction.DOWN);
			} else {
				this.moveBlock(tray.posCol, Direction.UP);
			}
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void doMove(boolean right) {
		try {
			if (right) {
				this.moveTray(Direction.RIGHT);
			} else {
				this.moveTray(Direction.LEFT);
			}
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
