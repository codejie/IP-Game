package jie.android.ip.setup;

import java.io.File;
import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.database.ConnectionAdapter;
import jie.android.ip.database.DesktopConnectionAdapter;

public class DesktopSetup implements Setup {

	private final ConnectionAdapter connectionAdapter;
	
	public DesktopSetup() {
		connectionAdapter = new DesktopConnectionAdapter(getStorageDirectory() + SystemConfig.DATABASE_FILE);
	}
	
	@Override
	public final String getStorageDirectory() {
		return "./doc/";
	}

	@Override
	public final Connection getDatabaseConnection() {
		return connectionAdapter.getConnection();
	}

}
