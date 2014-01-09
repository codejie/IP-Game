package jie.android.ip.screen.menu;

import java.util.ArrayList;

public interface MenuScreenListener {
	public interface ManagerEventListener {
		public void onPackLoadCompleted(final Pack[] packs);

		public void onPackItemLoadCompleted(final Pack pack);
	}
	
	public interface RendererEventListener {
		void onPackClicked(int id);		
	}
}
