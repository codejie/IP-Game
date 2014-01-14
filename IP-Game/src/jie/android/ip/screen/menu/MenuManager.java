package jie.android.ip.screen.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jie.android.ip.database.DBAccess;
import jie.android.ip.screen.menu.MenuScreenListener.RendererEventListener;

public class MenuManager {

	private final MenuScreen screen;
	private final DBAccess dbAccess;	
	
	private MenuScreenListener.ManagerEventListener managerListener;
	
	private MenuScreenListener.RendererEventListener rendererListener = new MenuScreenListener.RendererEventListener() {
		
		@Override
		public void onPackClicked(int id) {
			loadPackItems(id);
		}

		@Override
		public void onPackItemClicked(int id) {
			loadScriptScreen(id);
		}
	};
	
	private final Pack[] packs = new Pack[Pack.NUM_PACK];
	
	public MenuManager(final MenuScreen screen) {
		this.screen = screen;
		this.dbAccess = this.screen.getGame().getDBAccess();
	}

	public final MenuScreenListener.RendererEventListener getRendererEventListener() {
		return rendererListener;
	}
	
	public void setEventListener(final MenuScreenListener.ManagerEventListener listener) {
		this.managerListener = listener;
	}
	
	public void loadPacks() {
		final ResultSet rs = dbAccess.loadPacks();
		int i = 0;
		if (rs != null) {
			try {
				try {
					while (rs.next()) {
						packs[i ++ ] = new Pack(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
					}
				} finally {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (managerListener != null) {
			managerListener.onPackLoadCompleted(packs);
		}
	}
	
	protected void loadPackItems(int id) {
		final Pack pack = packs[id-1];
		if (pack == null) {
			return;
		}
		
		if (pack.getItems().length == 0) {
		
			final ResultSet rs = dbAccess.loadPackItems(id);
			if (rs != null) {
				try {
					try {
						while (rs.next()) {
							pack.addItem(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
						}
					} finally {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (managerListener != null) {
			managerListener.onPackItemLoadCompleted(pack, 0);
		}		
	}

	protected void loadScriptScreen(int id) {
		this.screen.getGame().setPlayScreen(id);
	}

	
}
