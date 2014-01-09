package jie.android.ip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jie.android.ip.executor.Script;

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
		return -2;
	}
	
	private final ResultSet querySQL(final String sql) {
		try {
			Statement stat = connection.createStatement();
			return stat.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private int execSQL(final String sql, final ArrayList<String> bindValues) {
		try {
			PreparedStatement  stat = connection.prepareStatement(sql);
			for (int i = 0; i < bindValues.size(); ++ i) {
				stat.setString(i+1, bindValues.get(i));
			}
			
			return stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	private final ResultSet querySQL(final String sql, final ArrayList<String> bindValues) {
		try {
			PreparedStatement  stat = connection.prepareStatement(sql);
			for (int i = 0; i < bindValues.size(); ++ i) {
				stat.setString(i+1, bindValues.get(i));
			}
			
			return stat.executeQuery();
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
		int ret = execSQL(sql);
		if (ret == -1) {
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
				+ "scriptid INTEGER PRIMARY KEY,"
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

	public void saveSolution(int scriptid, final String cmd) {
		final String sql = "REPLACE INTO solution (scriptid, command, utime, score) VALUES(?, ?, ?, ?)";
		ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(scriptid));
		val.add(cmd);
		val.add("1000-10-10");
		val.add("0");
		execSQL(sql, val);
	}

	public final String loadSolution(int i) {
		final String sql = "SELECT command FROM solution WHERE scriptid=" + i;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	public final ResultSet loadPacks() {
		//final String sql = "select pack.id, pack.title, t.c, t.s from pack join (select pack_id, count(*) as c, sum(status) as s from script group by pack_id) as t on pack.id = t.pack_id order by pack.id";
		final String sql = "select pack.id, pack.title, t.c, t.s from pack left join (select pack_id, count(*) as c, sum(status) as s from script group by pack_id) as t on pack.id = t.pack_id order by pack.id";
		final ResultSet rs = querySQL(sql);
		return rs;
	}

	public ResultSet loadPackItems(int id) {
		final String sql = "select script.id, solution.score from script left join solution on (script.id=solution.script_id) where script.pack_id=? order by script.id";
		ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(id));
		return querySQL(sql, val);
	}
	
	
}
