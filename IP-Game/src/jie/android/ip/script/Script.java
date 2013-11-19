package jie.android.ip.script;

import java.util.ArrayList;

public class Script {
	public static class Unit {
		public int row;
		public int col;
		public int type;
		public int status;
		
		public Unit(int row, int col, int type, int status) {
			this.row = row;
			this.col = col;
			this.type = type;
			this.status = status;
		}
	}
	
	public ArrayList<Unit> source;
	public ArrayList<Unit> target;
	
	public boolean load(final String file) {
		source = new ArrayList<Unit>();
		source.add(new Unit(1, 1, 1, 1));
		return false;
	}
	
	public final ArrayList<Unit> getSource() {
		return source;
	}
	
	public final ArrayList<Unit> getTarget() {
		return target;
	}

}
