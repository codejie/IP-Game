package jie.android.ip.screen.play;

import jie.android.ip.screen.play.PlayScreenListener.ManagerEventListener;

public class PlayManager {

	private PlayScreenListener.RendererEventListener rendererListener = new PlayScreenListener.RendererEventListener() {
		
		@Override
		public void onBlockMoveEnd() {
			// TODO Auto-generated method stub
			
		}
	};
	
	private final PlayScreen screen;
	
	private PlayScreenListener.ManagerEventListener managerListener;	
	
	
	public PlayManager(final PlayScreen screen) {
		this.screen = screen;
	}

	public void setEventListener(final ManagerEventListener listener) {
		this.managerListener = listener;
	}

	public final PlayScreenListener.RendererEventListener getRendererEventListener() {
		// TODO Auto-generated method stub
		return rendererListener;
	}

}
