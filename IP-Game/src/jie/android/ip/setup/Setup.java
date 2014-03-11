package jie.android.ip.setup;

import java.io.File;
import java.sql.Connection;

import jie.android.ip.CommonConsts.SystemConfig;

public abstract class Setup {

	public abstract void create();
	public abstract String getStorageDirectory();
	public abstract String getAppDirectory();
	public abstract String getCacheDirectory();
	public abstract Connection getDatabaseConnection();
	public abstract Connection getPatchConnection();
	
	public boolean hasPatch() {
		final File patch = new File(getStorageDirectory() + SystemConfig.PATCH_FILE);
		return patch.exists();
	}

	public void removePatch() {
		final File patch = new File(getStorageDirectory() + SystemConfig.PATCH_FILE);
		patch.delete();
	}
	
	public abstract boolean shareScreen();
	
}
