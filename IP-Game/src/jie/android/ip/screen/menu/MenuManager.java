package jie.android.ip.screen.menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jie.android.ip.database.DBAccess;

public class MenuManager {

	private final MenuScreen screen;
	private final DBAccess dbAccess;	
	
	private MenuScreenListener.ManagerEventListener managerListener;
	
	private final ArrayList<Pack> packs = new ArrayList<Pack>();
	
	
	
	public MenuManager(final MenuScreen screen) {
		this.screen = screen;
		this.dbAccess = this.screen.getGame().getDBAccess();
	}

	public void setEventListener(final MenuScreenListener.ManagerEventListener listener) {
		this.managerListener = listener;
	}
	
	public void loadPacks() {
		final ResultSet rs = dbAccess.loadPacks();
		if (rs != null) {
			try {
				try {
					while (rs.next()) {
						packs.add(new Pack(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
					}
				} finally {
					rs.close();
				}
			} catch (SQLException e) {
				//
			}
		}
		
		if (managerListener != null) {
			managerListener.onPackLoadCompleted(packs);
		}
	}
	
}
