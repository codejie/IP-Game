package jie.android.ip.screen.play;


public class PlayRenderer {

	public interface RendererInternalEventListener {
		public void onLineGroupChangeBegin(boolean fromSmall);
	}	
	
	private final PlayScreen screen;
	private PlayScreenListener.RendererEventListener rendererListener;

	private BoxGroup groupBox;
	private CodeLineGroup groupCodeLine;
	private CmdPanelGroup groupCmdPanel;
	
	private final PlayScreenListener.ManagerEventListener managerListener = new PlayScreenListener.ManagerEventListener() {

		@Override
		public void onBoxLoadCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			groupBox.load(tray, source, target);
		}
		@Override
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			groupCmdPanel.setChecked(Cmd.Type.RUN, false);
			groupBox.clearActors(tray, source, target);			
		}

		@Override
		public void onCodeLineLoadCompleted(final Code.Lines lines) {
			groupCodeLine.load(lines);
		}

		@Override
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
			groupBox.move(tray, block, col, row, tcol, trow);
		}
		
		@Override
		public void onBoxMoveEmpty() {
			internalListener.onBoxMoveEnd();
		}
		
		@Override
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos) {
			groupCodeLine.update(lines, index, pos);
		}
		
		@Override
		public void onCodeLineResetCompleted(final Code.Lines lines) {
			groupCodeLine.reset(lines);
		}
		
		@Override
		public void onExecuteSucc() {
			groupCmdPanel.showSuccStage();
		}
		
		@Override
		public void onExecuteFail() {
			groupCmdPanel.showFailStage();
		}
		
		@Override
		public void onExecuteFinished() {
			groupCmdPanel.showFinishedStage();
		}
	};	
	
	private final PlayScreenListener.RendererInternalEventListener internalListener = new PlayScreenListener.RendererInternalEventListener() {
		
		@Override
		public void onLineGroupChangeBegin(boolean fromSmall) {
			groupCmdPanel.showPanel(!fromSmall);
		}

		@Override
		public void onPanelButtonClicked(int index, int pos, final Code.Type type) {
			if (rendererListener != null) {
				rendererListener.onPanelButtonClicked(index, pos, type);
			}
		}

		@Override
		public void onCmdButtonClicked(final Cmd.Type type, final Cmd.State state) {

			if (type == Cmd.Type.RUN) {
				if (!onCmdRun(state)) {
					return;
				}
			} else if (type == Cmd.Type.MENU) {
				if (!onCmdMenu(state)) {
					return;
				}
			} else if (type == Cmd.Type.BACK) {
				if (!onCmdBack(state)) {
					return;
				}
			}
			
			if (rendererListener != null) {
				rendererListener.onCmdButtonClicked(type, state);
			}
		}

		@Override
		public void onBoxMoveEnd() {
			if (rendererListener != null) {
				rendererListener.onBoxkMoveEnd();
			}
		}

		@Override
		public void onSourceFocused() {
			if (rendererListener != null) {
				rendererListener.onCmdButtonClicked(Cmd.Type.RUN, Cmd.State.NONE);
			}
		}
	};
	
	public PlayRenderer(final PlayScreen screen) {
		this.screen = screen;
		
		initGroups();
	}

	public final PlayScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}

	public void setEventListener(final PlayScreenListener.RendererEventListener listener) {
		this.rendererListener = listener;
	}

	public void initGroups() {
		groupBox = new BoxGroup(screen, internalListener);
		groupCodeLine = new CodeLineGroup(screen, internalListener);
		groupCmdPanel = new CmdPanelGroup(screen, internalListener);
	}

	protected void changeRunStage(boolean show) {
		groupCmdPanel.focusRun(show);
		
		groupBox.focusSource(show);
		groupCodeLine.minimizeLines(show);
		groupCmdPanel.setChecked(Cmd.Type.RUN, show);
	}	
	
	protected boolean onCmdRun(final Cmd.State state) {
		changeRunStage(state == Cmd.State.NONE);
		if (state == Cmd.State.NONE) {					
			return false;
		}

		return true;
	}

	protected boolean onCmdMenu(final Cmd.State state) {
		groupCmdPanel.showSecondMenu(true);		
		return true;
	}	

	protected boolean onCmdBack(final Cmd.State state) {
		groupCmdPanel.showSecondMenu(false);
		return true;
	}
}
