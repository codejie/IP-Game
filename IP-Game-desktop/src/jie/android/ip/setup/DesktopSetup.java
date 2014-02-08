package jie.android.ip.setup;

import java.io.File;
import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.database.ConnectionAdapter;
import jie.android.ip.database.DesktopConnectionAdapter;

public class DesktopSetup extends Setup {

	private final ConnectionAdapter connectionAdapter;
	
	public DesktopSetup() {
		super();
		
		connectionAdapter = new DesktopConnectionAdapter(getStorageDirectory() + SystemConfig.DATABASE_FILE);
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
	public final Connection getDatabaseConnection() {
		return connectionAdapter.getConnection();
	}

	@Override
	public boolean shareScreen() {
		return false;
	}

}
