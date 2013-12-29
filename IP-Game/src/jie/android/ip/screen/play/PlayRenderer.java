package jie.android.ip.screen.play;

import jie.android.ip.screen.play.PlayScreenListener.RendererEventListener;

public class PlayRenderer {

	
	private PlayScreenListener.ManagerEventListener managerListener = new PlayScreenListener.ManagerEventListener() {
		
		@Override
		public void onScriptCompleted(boolean succ) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onBlockMoved(boolean isTray, int col, int row, int tcol, int trow) {
			// TODO Auto-generated method stub
			
		}
	};

	private final PlayScreen screen;
	private PlayScreenListener.RendererEventListener rendererListener;
	
	public PlayRenderer(final PlayScreen screen) {
		this.screen = screen;
	}

	public final PlayScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}

	public void setEventListener(RendererEventListener listener) {
		this.rendererListener = listener;
	}

}
