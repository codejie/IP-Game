package jie.android.ip.setup;

import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.database.ConnectionAdapter;
import jie.android.ip.database.DesktopConnectionAdapter;

public class DesktopSetup extends Setup {

//	private final ConnectionAdapter connectionAdapter;
	
	public DesktopSetup() {
		super();
		
//		connectionAdapter = new DesktopConnectionAdapter(getStorageDirectory() + SystemConfig.DATABASE_FILE);
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public final String getStorageDirectory() {
		return "./doc/";
	}
	
	@Override
	public final String getAppDirectory() {
		return "./doc/";
	}

	@Override
	public final String getCacheDirectory() {
		return "./doc/cache/";
	}	

	@Override
	public final Connection getDatabaseConnection() {
		final ConnectionAdapter connectionAdapter = new DesktopConnectionAdapter(getStorageDirectory() + SystemConfig.DATABASE_FILE);
		return connectionAdapter.getConnection();
	}

	@Override
	public boolean shareScreen() {
		return false;
	}

}
