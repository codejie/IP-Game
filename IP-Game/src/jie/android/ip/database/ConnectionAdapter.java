package jie.android.ip.database;

import java.sql.Connection;

public abstract class ConnectionAdapter {
	
	protected final String dbfile;
	
	public ConnectionAdapter(final String dbfile) {
		this.dbfile = dbfile;
	}
	
	public abstract Connection getConnection();
}
