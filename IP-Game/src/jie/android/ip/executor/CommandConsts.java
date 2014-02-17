package jie.android.ip.executor;

public interface CommandConsts {

	public enum CommandType {
		ACT("act", 2), CHECK("check", 2), /*DEFINE("define"),*/ CALL("call", 6), EMPTY("empty", 0);
		
		private String title;
		private int score;
		
		CommandType(String title, int score) {
			this.title = title;
			this.score = score;
		}
		
		public final String getTitle()
		{
			return title;
		}

		public int getScore() {
			return score;
		}
		
		public int getId() {
			return ordinal();
		}
	}

	public enum ActType {
		MOVE_LEFT, MOVE_RIGHT, ACTION;
		
		public int getId() {
			return ordinal();
		}
	}
	
	public enum EmptyType {
		CHECK, ACT;
		public int getId() {
			return ordinal();
		}
	}
	
}