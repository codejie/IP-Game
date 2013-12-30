package jie.android.ip.screen.play;

import jie.android.ip.screen.play.PlayScreenListener.RendererEventListener;

public class PlayRenderer {

	private final PlayScreen screen;
	private PlayScreenListener.RendererEventListener rendererListener;

	private BoxGroup groupBox;
	private CodeLineGroup groupCodeLine;
	
	private PlayScreenListener.ManagerEventListener managerListener = new PlayScreenListener.ManagerEventListener() {

		@Override
		public void onBoxInitCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			groupBox.load(tray, source, target);
		}
		@Override
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onCodeLineInitCompleted(final Code.Lines lines) {
			groupCodeLine.load(lines);
		}

		@Override
		public void onCodeLineRefresh() {
			// TODO Auto-generated method stub
			
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
		public void onBoxUpdated() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
			// TODO Auto-generated method stub
			
		}
	};	
	
	public PlayRenderer(final PlayScreen screen) {
		this.screen = screen;
		
		initGroups();
	}

	public final PlayScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}

	public void setEventListener(RendererEventListener listener) {
		this.rendererListener = listener;
	}

	public void initGroups() {
		groupBox = new BoxGroup(screen);
		groupCodeLine = new CodeLineGroup(screen);
	}
}
