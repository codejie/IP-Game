package jie.android.ip.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AndroidConnectionAdapter extends ConnectionAdapter {

	public AndroidConnectionAdapter(final String dbfile) {
		super(dbfile);
	}
	
	@Override
	public Connection getConnection() {
		final String url = "jdbc:sqldroid:" + dbfile;

		try {
			Class.forName("org.sqldroid.SQLDroidDriver").newInstance();
			return DriverManager.getConnection(url);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
