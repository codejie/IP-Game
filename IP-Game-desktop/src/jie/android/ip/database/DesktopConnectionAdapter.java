package jie.android.ip.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DesktopConnectionAdapter extends ConnectionAdapter {
	
	public DesktopConnectionAdapter(final String dbfile) {
		super(dbfile);
	}

	@Override
	public Connection getConnection() {
		final String url = "jdbc:sqlite:" + dbfile;
		
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
