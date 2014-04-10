package jie.android.ip.playservice;

import java.util.HashMap;

import jie.android.ip.PlayEventListener;
import jie.android.ip.utils.Utils;

public class PlayServiceTracker {
	
	private final String Tag = PlayServiceTracker.class.getSimpleName();
	
	public enum Type {
		MOVE_LEFT(1, 50, true, false), MOVE_RIGHT(2, 50, true, false), ACTION_EMPTY(3, 30, true, false), ACTION_MAX(17, 150, true, false),
		CALL_MAX(4, 30, true, false), CHECK_MAX(5, 50, true, false), STEP_MAX(6, 250, true, false),
//		CHECK_MAX_0(7, 4, true, false), CHECK_MAX_1(8, 4, true, false), CHECK_MAX_2(9, 4, true, false), CHECK_MAX_3(10, 4, true, false),
//		CHECK_MAX_ALL(11, 4, true, false), CHECK_MAX_NONE(12, 4, true, false),
		EXECUTE_MAX_EXCEPTION(13, 10, true, true), EXECUTE_MAX_OVERFLOW(14, 10, true, true), EXECUTE_MAX_FINISHED(15, 10, true, true), EXECUTE_MAX_RESET(18, 10, true, true),
		EXECUTE_MIN_SUCC(16, 1, false, true);
		
		private final int id;
		private final int target;
		private final boolean inc;
		private final boolean global;
		
		private Type(int id, int target, boolean inc, boolean global) {
			this.id = id;
			this.target = target;
			this.inc = inc;
			this.global = global;
		}
		
		public int getId() {
			return id;
		}
		public int getTarget() {
			return target;
		}
		
		public boolean isInc() {
			return inc;
		}
		
		public boolean isGlobal() {
			return global;
		}
	}
	
	public class Data {
		public int value;
		public int target;
		
		public boolean check() {
			return value >= target;
		}
	}
	
	private HashMap<Integer, Integer> mapData = new HashMap<Integer, Integer>();
	
	public PlayServiceTracker() {
		init();
	}
	
	private void init() {
		for (final Type type : Type.values()) {
			mapData.put(type.getId(), new Integer(0));
		}
	}
	
	public void update(final Type type) {
		mapData.put(type.getId(), mapData.get(type.getId()) + 1);
	}
	
	public void refresh(boolean all) {
		for (final Type type : Type.values()) {
			if (all || !type.isGlobal()) {
				mapData.put(type.getId(), new Integer(0));
			}
		}
	}
	
	public boolean check(final Type type) {
		final Integer val = mapData.get(type.getId());
		if (type.isInc()) {
			return (val >= type.getTarget());
		} else {
			return (val <= type.getTarget());
		}
	}

	public void check(final PlayEventListener playEventListener) {
		for (final Type type : Type.values()) {
			if (check(type)) {
				Utils.log(Tag, "PlayEvent is triggered - id = " + type.id);
				playEventListener.unlockTrackerAchievement(type.getId());
			}
		}
	}
	
}
