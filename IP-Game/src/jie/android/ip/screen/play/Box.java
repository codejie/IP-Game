package jie.android.ip.screen.play;

import java.util.HashMap;

import jie.android.ip.utils.Extended.Pair;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Box {

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
	
}
