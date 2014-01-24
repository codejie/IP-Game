package jie.android.ip.setup;

import java.sql.Connection;

public abstract class Setup {

	public abstract void create();
	public abstract String getStorageDirectory();
	public abstract Connection getDatabaseConnection();
}
