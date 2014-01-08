package jie.android.ip.screen.menu;

import java.util.ArrayList;

public interface MenuScreenListener {
	public interface ManagerEventListener {
		public void onPackLoadCompleted(final ArrayList<Pack> packs);		
	}
	
	public interface RendererEventListener {
		
	}
}
