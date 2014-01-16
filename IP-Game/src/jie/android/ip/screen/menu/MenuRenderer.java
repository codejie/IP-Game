package jie.android.ip.screen.menu;

public class MenuRenderer {
	
	public interface PackGroupEventListener {
		public void onPackClick(int id);
		public void onPackItemClick(int id);
		public void onBtnBackClicked(int curPack);
	}
	
	private final MenuScreen screen;
	private MenuScreenListener.RendererEventListener rendererListener;
	
	private PackGroup groupPack;
	
	
	
	private final MenuScreenListener.ManagerEventListener managerListener = new MenuScreenListener.ManagerEventListener() {
		
		@Override
		public void onPackLoadCompleted(final Pack[] packs) {
			groupPack.loadPacks(packs);
		}

		@Override
		public void onPackItemLoadCompleted(final Pack pack, int itemStart) {
			groupPack.loadPackItem(pack);
		}
	};
	
	private final PackGroupEventListener packGroupListener = new PackGroupEventListener() {

		@Override
		public void onPackClick(int id) {
			if (rendererListener != null) {
				rendererListener.onPackClicked(id);
			}
		}

		@Override
		public void onPackItemClick(int id) {
			if (rendererListener != null) {
				rendererListener.onPackItemClicked(id);
			}
		}

		@Override
		public void onBtnBackClicked(int curPack) {
			if (rendererListener != null) {
				rendererListener.onLoadPack();
			}
		}
		
		
		
	};
	
	
	public MenuRenderer(final MenuScreen screen) {
		this.screen = screen;
		
		initBackgroup();
		
		initGroups();
	}

	private void initBackgroup() {
		
	}

	private void initGroups() {
		groupPack = new PackGroup(screen, packGroupListener);
	}

	public void setEventListener(final MenuScreenListener.RendererEventListener listener) {
		this.rendererListener = listener;
	}
	
	public final MenuScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}
}
