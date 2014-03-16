package jie.android.ip.screen.play;

public interface PlayScreenListener {

	public interface ManagerEventListener {
		public void onScriptLoaded(int packId, int scriptId, final String packTitle, int scriptSelfId, final String scriptTitle, final String author, final String comment);
		
		public void onBoxLoadCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target);
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow);
		public void onBoxMoveEmpty();
		
		public void onCodeLineLoadCompleted(final Code.Lines lines);
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos);
		public void onCodeLineResetCompleted(final Code.Lines lines);
		public void onExecuteSucc(int base_score, int score, int execStep);
		public void onExecuteFail();
		public void onExecuteFinished();
		public void onExecuteOverflow();

		public void onCodeCalled(int type, int func, int index);
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
		public void onBoxMoveEmpty();

		public void onCodeLineLoadCompleted(final Code.Lines lines);
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos);
		public void onCodeLineResetCompleted(final Code.Lines lines);
		
		public void onExecuteAction();
		public void onExecuteMove(boolean right);
		public void onExecuteCompleted(final PlayExecutor.StopReason reason);
		public void onBoxMoveException(int error);
		
		public void onCodeCalled(int type, int func, int index);
	}
	
	public interface RendererInternalEventListener {
		public void onPanelButtonClicked(int index, int pos, final Code.Type type);
		public void onCmdButtonClicked(final Cmd.Type type, final Cmd.State state);
		public void onLineGroupChangeBegin(boolean fromSmall);
		public void onBoxMoveEnd();
		public void onSourceFocused();
		
		public void onLessonGroupAdded();
		public void onLessonGroupRemoved();
	}
}

