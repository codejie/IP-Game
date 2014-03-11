package jie.android.ip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public abstract class BaseAccess {
	
	protected final Connection connection;
	
	public BaseAccess(final Connection connection) {
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
	
	protected int execSQL(final String sql) {
		try {
			Statement stat = connection.createStatement();
			return stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -2;
	}
	
	protected final ResultSet querySQL(final String sql) {
		try {
			Statement stat = connection.createStatement();
			return stat.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	protected int execSQL(final String sql, final ArrayList<String> bindValues) {
		try {
			PreparedStatement  stat = connection.prepareStatement(sql);
			for (int i = 0; i < bindValues.size(); ++ i) {
				final String val = bindValues.get(i);
				if (val != null) {
					stat.setString(i + 1, bindValues.get(i));
				} else {
					stat.setNull(i + 1, Types.NULL);
				}
			}
			
			return stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	protected final ResultSet querySQL(final String sql, final ArrayList<String> bindValues) {
		try {
			PreparedStatement  stat = connection.prepareStatement(sql);
			for (int i = 0; i < bindValues.size(); ++ i) {
				final String val = bindValues.get(i);
				if (val != null) {
					stat.setString(i + 1, bindValues.get(i));
				} else {
					stat.setNull(i + 1, Types.NULL);
				}
			}
			
			return stat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
