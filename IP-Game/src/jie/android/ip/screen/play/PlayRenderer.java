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
			groupBox.clearActors(tray, source, target);			
		}

		@Override
		public void onCodeLineLoadCompleted(final Code.Lines lines) {
			groupCodeLine.load(lines);
		}

		@Override
		public void onScriptReload() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onScriptStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onScriptCompleted(boolean succ) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
			groupBox.move(tray, block, col, row, tcol, trow);
		}
		@Override
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos) {
			groupCodeLine.update(lines, index, pos);
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
				groupCmdPanel.setChecked(type, state == Cmd.State.NONE);
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
}
