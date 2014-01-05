package jie.android.ip.screen.play;

import java.util.HashMap;

import jie.android.ip.executor.Script;
import jie.android.ip.executor.Script.BlockData;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.utils.Extended.Pair;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box {

	public enum Direction {
		DOWN, UP, LEFT, RIGHT;
	}
	
	public class Block {
		
		public static final int VALUE_0 = 0;
		
		public int value = VALUE_0;
		public int style = 0;		
		public Actor actor;
		
		public Block(int value, int style) {
			this.value = value;
			this.style = style;			
		}
	}
	
	public class BlockArray extends HashMap<Pair<Integer, Integer>, Block> {
		
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
	
	//
	
	private final PlayScreenListener.ManagerInternalEventListener internalListener;
	
	private BlockArray source;
	private BlockArray target;
	private Tray tray;	

	public Box(final PlayScreenListener.ManagerInternalEventListener listener) {
		this.internalListener = listener;
	}
	
	public void loadScript(final Script script) {
		source = new BlockArray(Const.Box.MAX_ROW, Const.Box.MAX_COL);
		if (script.getSource() != null) {
			for (final Script.BlockData data : script.getSource()) {
				inflateBlock(source, data);
			}
		}
		
		target = new BlockArray(Const.Box.MAX_ROW, Const.Box.MAX_COL);
		if (script.getTarget() != null) {
			for (final Script.BlockData data : script.getTarget()) {
				inflateBlock(target, data);
			}
		}
		
		final Script.TrayData td = script.getTray();
		tray = new Tray(td.style, td.col);
		
		internalListener.onBoxLoadCompleted(tray, source, target);
	}

	private void inflateBlock(BlockArray block, BlockData unit) {
		block.add(unit.row,  unit.col, new Block(unit.value, unit.style));
	}	
	
	public void reload(final Script script) {
		internalListener.onBoxPreReload(tray, source, target);			
		
		loadScript(script); 
	}
	
	private boolean moveBlock(int col, final Direction direction) throws BoxException {
		Block block = null;
		int row = -1, trow = -1;
		//updata block
		if (direction == Direction.DOWN) {
			//check tray
			if (tray.posCol != col || tray.status != Tray.STATUS_EMPTY) {
				throw new BoxException(BoxException.E_TRAY_MISSING);
			}
			
			row = source.checkInRow(col);
			trow = 0;
			if (row != -1) {
				block = source.update(row, col, trow, col);
				if (block == null) {
					throw new BoxException(BoxException.E_BLOCK_NOTFOUND);
				}				
				tray.status = Tray.STATUS_ATTACHED;
			} else {
				return false;
			}
		} else if (direction == Direction.UP){
			if (tray.status != Tray.STATUS_ATTACHED) { // do nothing
				return false;
			}
			row = 0;
			trow = source.checkInRow(col);
			if (trow == -1) {
				trow = Const.Box.MAX_ROW;
			} else if (trow == 1) {
				throw new BoxException(BoxException.E_BLOCK_NOTROOM);
				//error
			} else {
				trow = trow - 1;
			}
			
			block = source.update(row, col, trow, col);
			if (block == null) {
				throw new BoxException(BoxException.E_BLOCK_NOTFOUND);
			}
			tray.status = Tray.STATUS_EMPTY;
		} else {
			throw new BoxException(BoxException.E_DIRECTION_UNSUPPORT);
		}
		
		internalListener.onBoxMoved(null, block, col, row, col, trow);
		
		return true;
	}
	
	private boolean moveTray(Direction direction) throws BoxException {
		
		Block block = null;
		int col = tray.posCol;
		int tcol = -1;
		if (direction == Direction.LEFT) {
			 tcol = col - 1;
		} else if (direction == Direction.RIGHT) {
			tcol = col + 1;
		} else {
			throw new BoxException(BoxException.E_DIRECTION_UNSUPPORT);
		}

		//if (tcol == 0 || tcol == Const.Box.MAX_COL) {
		if (tcol < 0 || tcol > Const.Box.MAX_COL) {
			throw new BoxException(BoxException.E_TRAY_OUT);
		}
		
		tray.posCol = tcol;
		
		if (tray.status == Tray.STATUS_ATTACHED) {
			block = source.update(0, col, 0, tcol);
			if (block == null) {
				throw new BoxException(BoxException.E_BLOCK_NOTFOUND);
			}			
		}
		
		internalListener.onBoxMoved(tray, block, col, 0, tcol, 0);
		
		return true;
	}

	public void tryMoveBlock() {
		try {	
			if (tray.status == Tray.STATUS_EMPTY) {
				if (!moveBlock(tray.posCol, Direction.DOWN)) {
					internalListener.onBoxMoveEmpty();
				}
			} else {
				if (!moveBlock(tray.posCol, Direction.UP)) {
					internalListener.onBoxMoveEmpty();
				}
			}
		} catch (BoxException e) {
			e.printStackTrace();
			internalListener.onBoxMoveException(e.error());
		}		
	}

	public void tryMoveTray(boolean right) {
		try {
			if (right) {
				moveTray(Direction.RIGHT);
			} else {
				moveTray(Direction.LEFT);
			}
		} catch (BoxException e) {
//			e.printStackTrace();
			internalListener.onBoxMoveException(e.error());			
		}
	}

	public boolean checkResult() {
		return target.isMatch(source);
	}
}
