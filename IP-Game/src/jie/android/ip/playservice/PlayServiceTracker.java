package jie.android.ip.playservice;

import java.util.HashMap;

import jie.android.ip.PlayEventListener;
import jie.android.ip.utils.Utils;

public class PlayServiceTracker {
	
	private static final String Tag = PlayServiceTracker.class.getSimpleName();
	
	private static final int ID_MOVE_LEFT = 10;
	private static final int ID_MOVE_RIGHT = 11;
	private static final int ID_ACTION_EMPTY = 12;
	private static final int ID_ACTION_MAX = 13;
	private static final int ID_CALL_MAX = 14;
	private static final int ID_CHECK_MAX = 15;
	private static final int ID_STEP_MAX = 20;
	private static final int ID_EXECUTE_MAX_EXCEPTIN = 30;
	private static final int ID_EXECUTE_MAX_OVERFLOW = 31;
	private static final int ID_EXECUTE_MAX_FINISHED = 32;
	private static final int ID_EXECUTE_MAX_RESET = 33;
	private static final int ID_EXECUTE_MIN_SUCC = 34;
	private static final int ID_EVENT_NO_UNSOLVED = 40;
	
	public enum Type {
		MOVE_LEFT(ID_MOVE_LEFT, 50, true, false), MOVE_RIGHT(ID_MOVE_RIGHT, 50, true, false), ACTION_EMPTY(ID_ACTION_EMPTY, 30, true, false), 
		ACTION_MAX(ID_ACTION_MAX, 180, true, false), CALL_MAX(ID_CALL_MAX, 30, true, false), CHECK_MAX(ID_CHECK_MAX, 50, true, false),
		STEP_MAX(ID_STEP_MAX, 200, true, false),
//		CHECK_MAX_0(7, 4, true, false), CHECK_MAX_1(8, 4, true, false), CHECK_MAX_2(9, 4, true, false), CHECK_MAX_3(10, 4, true, false),
//		CHECK_MAX_ALL(11, 4, true, false), CHECK_MAX_NONE(12, 4, true, false),
		EXECUTE_MAX_EXCEPTION(ID_EXECUTE_MAX_EXCEPTIN, 5, true, true), EXECUTE_MAX_OVERFLOW(ID_EXECUTE_MAX_OVERFLOW, 5, true, true), EXECUTE_MAX_FINISHED(ID_EXECUTE_MAX_FINISHED, 5, true, true),
		EXECUTE_MAX_RESET(ID_EXECUTE_MAX_RESET, 10, true, true), EXECUTE_MIN_SUCC(ID_EXECUTE_MIN_SUCC, 1, false, true),
		EVENT_NO_UNSOLVED(ID_EVENT_NO_UNSOLVED, 1, true, true);
		
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
