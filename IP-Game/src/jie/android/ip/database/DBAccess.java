package jie.android.ip.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jie.android.ip.script.Script;

public class DBAccess {
	
	private final Connection connection;
	
	public DBAccess(final Connection connection) {
		this.connection = connection;
	}
	
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private int execSQL(final String sql) {
		try {
			Statement stat = connection.createStatement();
			return stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	private ResultSet querySQL(final String sql) {
		try {
			Statement stat = connection.createStatement();
			return stat.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean check() {
		createTables();//debug
		return true;
	}
	
	public boolean createTables() {
		String sql = "CREATE TABLE IF NOT EXISTS sys ("
				+ "attr INTEGER,"
				+ "int INTEGER,"
				+ "str TEXT"
				+ ")";
		if (execSQL(sql) == -1) {
			return false;
		}
		
		sql = "CREATE TABLE IF NOT EXISTS script ("
				+ "id INTEGER,"
				+ "status INTEGER,"
				+ "script TEXT,"
				+ "command TEXT,"
				+ "ctime TEXT"
				+ ")";
		if (execSQL(sql) == -1) {
			return false;
		}

		sql = "CREATE TABLE IF NOT EXISTS solution ("
				+ "scriptid INTEGER,"
				+ "command TEXT,"
				+ "utime TEXT,"
				+ "score INTEGER"
				+ ")";
		if (execSQL(sql) == -1) {
			return false;
		}

//		sql = "CREATE TABLE IF NOT EXISTS group ("
//				+ "id INTEGER,"
//				+ "title TEXT,"
//				+ "descript TEXT"
//				+ ")";
//		if (execSQL(sql) == -1) {
//			return false;
//		}
				
		return true;
	}
	
	public final String loadScript(int id) {
		final String sql = "SELECT script FROM script WHERE id=" + id;
		final ResultSet rs = querySQL(sql);
		try {
			try {
				if (rs.next()) {
					return rs.getString(1);
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
