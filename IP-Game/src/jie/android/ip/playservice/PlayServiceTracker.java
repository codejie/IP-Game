package jie.android.ip.playservice;

import java.util.HashMap;

public class PlayServiceTracker {
	
	public enum ID {
		MOVE_LEFT(1, 5), MOVE_RIGHT(2, 5), ACTION_EMPTY(3, 5),
		CALL_MAX(4, 10), CHECK_MAX(5, 8), STEP_MAX(6, 250),
		CHECK_MAX_0(7, 4), CHECK_MAX_1(8, 4), CHECK_MAX_2(9, 4), CHECK_MAX_3(10, 4),
		STEP_MAX_EXCEPTION(11, 10), STEP_MAX_OVERFLOW(12, 10), STEP_MAX_FINISHED(13, 10),
		STEP_MIN_SUCC(14, 1);
		
		private final int id;
		private final int target;
		
		private ID(int id, int target) {
			this.id = id;
			this.target = target;
		}
		
		public int getId() {
			return id;
		}
		public int getTarget() {
			return target;
		}
	}
	
	public class Data {
		public int value;
		public int target;
		
		public boolean check() {
			return value >= target;
		}
	}
	
	private HashMap<Integer, Data> mapData = new HashMap<Integer, Data>();
	
	public PlayServiceTracker() {
		init();
	}
	
	private void init() {
		
	}
	
	public boolean incData(int id, int step) {
		final Data data = mapData.get(id);
		if (data == null) {
			data = new Data();
		}
	}
	
}
