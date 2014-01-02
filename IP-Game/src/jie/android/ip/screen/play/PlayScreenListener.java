package jie.android.ip.screen.play;

public interface PlayScreenListener {

	public interface ManagerEventListener {
		public void onBoxInitCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onCodeLineInitCompleted(final Code.Lines lines);
		public void onCodeLineRefresh();
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos);

		public void onScriptReload();
		public void onScriptStart();		
		public void onScriptCompleted(boolean succ);
		
		public void onBoxUpdated();		
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow);
	}
	
	public interface RendererEventListener {
		public void onCmdButtonClicked(final Cmd.Type type);
		public void onPanelButtonClicked(int index, int pos, final Code.Type type);
		
		public void onBoxMoveStart();
		public void onBoxkMoveEnd();
	}

	public interface RendererInternalEventListener {
		public void onLineGroupChangeBegin(boolean fromSmall);
	}
}

