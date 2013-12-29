package jie.android.ip.screen.play;

public interface PlayScreenListener {

	public interface ManagerEventListener {
		public void onBoxInitCompleted();
		public void onCodeLineInitCompleted();

		public void onScriptReload();
		public void onScriptStart();		
		public void onScriptCompleted(boolean succ);
		
		public void onBoxUpdated();		
		public void onBoxMoved(boolean isTray, int col, int row, int tcol, int trow);
	}
	
	public interface RendererEventListener {
		public void onCmdButtonClicked();
		
		public void onBoxkMoveEnd();
	}
}

