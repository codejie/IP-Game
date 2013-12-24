package jie.android.ip.setup;

import java.io.File;
import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.database.AndroidConnectionAdapter;
import jie.android.ip.database.ConnectionAdapter;

import android.content.Context;
import android.os.Environment;

public class AndroidSetup implements Setup {

	private static final String APP_ROOT = "/jie/ip/";
	
	private final Context context;
	private final ConnectionAdapter connectionAdapter;
	
	public AndroidSetup(Context context) {
		this.context = context;
		
		this.connectionAdapter = new AndroidConnectionAdapter(getStorageDirectory() + APP_ROOT + SystemConfig.DATABASE_FILE);
	}
	
	@Override
	public final String getStorageDirectory() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	@Override
	public final Connection getDatabaseConnection() {
		return connectionAdapter.getConnection();
	}

}
