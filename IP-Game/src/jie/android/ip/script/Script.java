package jie.android.ip.script;

import java.util.ArrayList;

import jie.android.ip.script.Script.TrayData;

public class Script {
	public class BlockData {
		public int row;
		public int col;
		public int style;
		
		public BlockData(int row, int col, int style) {
			this.row = row;
			this.col = col;
			this.style = style;
		}
	}
	
	public class TrayData {
		public int style;
		public int col;		
		
		public TrayData(int style, int col) {
			this.style = style;
			this.col = col;
		}
	}
	
	public ArrayList<BlockData> source;
	public ArrayList<BlockData> target;
	
	public boolean load(final String file) {
		source = new ArrayList<BlockData>();
		source.add(new BlockData(1, 1, 1));
		source.add(new BlockData(2, 2, 1));
		source.add(new BlockData(3, 3, 1));
		source.add(new BlockData(4, 3, 1));
		return false;
	}
	
	public final ArrayList<BlockData> getSource() {
		return source;
	}
	
	public final ArrayList<BlockData> getTarget() {
		return target;
	}

	public int getTrayPositon() {
		// TODO Auto-generated method stub
		return 0;
	}

	public final TrayData getTray() {
		return new TrayData(0, 2);
	}

}
