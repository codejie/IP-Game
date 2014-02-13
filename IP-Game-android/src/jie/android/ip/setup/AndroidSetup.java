package jie.android.ip.setup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.database.AndroidConnectionAdapter;
import jie.android.ip.database.ConnectionAdapter;
import jie.android.ip.utils.AssetsHelper;
import jie.android.ip.utils.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class AndroidSetup extends Setup {

	private static final String APP_ROOT = "/jie/ip/";
	private static final String APP_CACHE = "/jie/cache/";
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

	@Override
	public boolean shareScreen() {
		final String root = getStorageDirectory() + APP_CACHE;
		final File ipPath = new File(root);
		if (!ipPath.exists()) {
			ipPath.mkdirs();
		}
		final String file = root + "ip_shot.png";
		Utils.saveScreenToFile(file);
		
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(file)));
        context.startActivity(intent);
		
		return true;
	}

}
