package jie.android.ip.screen.data;

import jie.android.ip.Resources;
import jie.android.ip.group.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.script.Script;
import jie.android.ip.script.Script.Unit;

public class BoxManager {
	
	private static final int MAX_ROW = 6;
	private static final int MAX_COL = 8;
	
	private class Block {		
		public int type = -1;
		public int status = -1;
		public ImageActor actor = null;
		
		public Block(int type, int status, ImageActor actor) {
			this.type = type;
			this.status = status;
			this.actor = actor;
		}
	}
	
	private class Tray {
		
		public static final int EMPTY = 0;
		public static final int ATTACHED = 1;
		
		public int type = -1;
		public int status = EMPTY;
		
		public int posCol = 0;
	}
	
	private final Resources resources;
	
	private Block sourceBlock[][];
	private Block targetBlock[][];
	private Tray tray;
	
	public BoxManager(final Resources res) {
		resources = res;
	}
	
	public boolean load(final Script script) {
		
		init();
		
		if (script.getSource() != null) {
			for (final Script.Unit unit : script.getSource()) {
				inflateBlock(sourceBlock, unit);
			}
		}
		
		if (script.getTarget() != null) {
			for (final Script.Unit unit : script.getTarget()) {
				inflateBlock(targetBlock, unit);
			}
		}
		return true;
	}

	private void init() {
		sourceBlock = new Block[MAX_ROW][MAX_COL];
		targetBlock = new Block[MAX_ROW][MAX_COL];
		tray = new Tray();
	}
	
	private void inflateBlock(Block[][] block, Unit unit) {
		ImageActor actor = makeActor(String.format("b.%d.%d", unit.row, unit.col), unit.type);
		actor.setPosition(unit.row * 100 + 100, unit.col * 100 + 100);
		block[unit.row][unit.col] = new Block(unit.type, unit.status, actor);
	}

	private ImageActor makeActor(String name, int type) {
		if (type == 1) {
			return new ImageActor(name, resources.getSkin().getRegion("ic"));
		}
		return null;
	}

	public void putSource(BaseGroup group) {
		for (int row = 0; row < MAX_ROW; ++ row) {
			for (int col = 0; col < MAX_COL; ++ col) {
				if (sourceBlock[row][col] != null) {
					group.addActor(sourceBlock[row][col].actor);
				}
			}
		}
	}
	
}
