package jie.android.ip.executor;

public interface CommandConsts {

	public enum CommandType {
		ACT("act"), CHECK("check"), /*DEFINE("define"),*/ CALL("call");
		
		private String title;
		
		CommandType(String title) {
			this.title = title;
		}
		
		public final String getTitle()
		{
			return title;
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
	
}