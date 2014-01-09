package jie.android.ip.screen.menu;

import java.util.ArrayList;

import jie.android.ip.screen.menu.MenuScreenListener.ManagerEventListener;

public class MenuRenderer {
	
	public interface ItemClickListener {
		public void onClick(int id);
	}
	
	private final MenuScreen screen;
	private MenuScreenListener.RendererEventListener rendererListener;
	
	private PackGroup groupPack;
	
	
	
	private final MenuScreenListener.ManagerEventListener managerListener = new MenuScreenListener.ManagerEventListener() {
		
		@Override
		public void onPackLoadCompleted(final Pack[] packs) {
			groupPack.load(packs);
			
		}
	};
	
	private final ItemClickListener packClickListener = new ItemClickListener() {

		@Override
		public void onClick(int id) {
			if (rendererListener != null) {
				rendererListener.onPackClicked(id);
			}
		}
		
	};
	
	private final ItemClickListener itemClickListener = new ItemClickListener() {

		@Override
		public void onClick(int id) {
			// TODO Auto-generated method stub
			
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
		groupPack = new PackGroup(screen, packClickListener, itemClickListener);
	}

	public void setEventListener(final MenuScreenListener.RendererEventListener listener) {
		this.rendererListener = listener;
	}
	
	public final MenuScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}
}
