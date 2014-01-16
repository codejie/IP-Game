package jie.android.ip.screen.menu;

public interface MenuScreenListener {
	public interface ManagerEventListener {
		public void onPackLoadCompleted(final Pack[] packs);
		void onPackItemLoadCompleted(final Pack pack, int itemStart);
	}
	
	public interface RendererEventListener {
		void onPackClicked(int id);
		void onPackItemClicked(int id);
		void onLoadPack();		
	}
}
