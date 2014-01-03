package jie.android.ip.screen.play;

public interface PlayScreenListener {

	public interface ManagerEventListener {
		public void onBoxLoadCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow);
		
		public void onCodeLineLoadCompleted(final Code.Lines lines);
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos);

		public void onScriptReload();
		public void onScriptStart();		
		public void onScriptCompleted(boolean succ);		
	}
	
	public interface RendererEventListener {
		public void onCmdButtonClicked(final Cmd.Type type, final Cmd.State state);
		public void onPanelButtonClicked(int index, int pos, final Code.Type type);
		
		public void onBoxMoveStart();
		public void onBoxkMoveEnd();
	}

	public interface ManagerInternalEventListener {
		public void onBoxLoadCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow);

		public void onCodeLineLoadCompleted(final Code.Lines lines);
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos);
		
		public void onExecuteAction();
		public void onExecuteMove(boolean right);
		public void onExecuteCompleted(boolean succ);
		public void onBoxMoveException(int error);
		public void onExecuteBroken();
	}
	
	public interface RendererInternalEventListener {
		public void onPanelButtonClicked(int index, int pos, final Code.Type type);
		public void onCmdButtonClicked(final Cmd.Type type, final Cmd.State state);
		public void onLineGroupChangeBegin(boolean fromSmall);
		public void onBoxMoveEnd();
	}
}

