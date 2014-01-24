package jie.android.ip.setup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.database.AndroidConnectionAdapter;
import jie.android.ip.database.ConnectionAdapter;
import jie.android.ip.utils.AssetsHelper;

import android.content.Context;
import android.os.Environment;

public class AndroidSetup extends Setup {

	private static final String APP_ROOT = "/jie/ip/";
	private static final String ASSETS_FILE = "assets.zip";
	
	private final Context context;
	private final ConnectionAdapter connectionAdapter;
	
	public AndroidSetup(Context context) {
		this.context = context;
		
		create();
		
		this.connectionAdapter = new AndroidConnectionAdapter(getStorageDirectory() + APP_ROOT + SystemConfig.DATABASE_FILE);
	}

	@Override
	public void create() {
		final String root = getStorageDirectory() + APP_ROOT;
		final File ipPath = new File(root);
		if (!ipPath.exists()) {
			ipPath.mkdirs();
		}
		final File dbFile = new File(root + SystemConfig.DATABASE_FILE);
		if (!dbFile.exists()) {
			unzipAssets(root);
		}
	}	
	
	private void unzipAssets(final String root) {
		try {
			InputStream input = context.getAssets().open(ASSETS_FILE);
			AssetsHelper.UnzipTo(input, root, null);
		} catch (IOException e) {
			e.printStackTrace();
		}		
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
