package jie.android.ip.setup;

import java.sql.Connection;

public interface Setup {
	public String getStorageDirectory();
	public Connection getDatabaseConnection();
}
