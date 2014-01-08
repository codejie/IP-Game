package jie.android.ip.screen.menu;

import java.util.ArrayList;

import jie.android.ip.screen.menu.MenuScreenListener.ManagerEventListener;

public class MenuRenderer {
	
	public interface ItemClickListener {
		public void onClick(int id);
	}
	
	private final MenuScreen screen;
	
	private PackGroup groupPack;
	
	private final MenuScreenListener.ManagerEventListener managerListener = new MenuScreenListener.ManagerEventListener() {
		
		@Override
		public void onPackLoadCompleted(final ArrayList<Pack> packs) {
			groupPack.load(packs);
			
		}
	};
	
	private final ItemClickListener packClickListener = new ItemClickListener() {

		@Override
		public void onClick(int id) {
			// TODO Auto-generated method stub
			
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
		groupPack = new PackGroup(screen, packClickListener);
	}

	public final MenuScreenListener.ManagerEventListener getManagerEventListener() {
		return managerListener;
	}
}
