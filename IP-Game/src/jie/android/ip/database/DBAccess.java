package jie.android.ip.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	private final ResultSet querySQL(final String sql, final ArrayList<String> bindValues) {
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
	
	public boolean check() {
		createTables();//debug
		return true;
	}
	
	public boolean createTables() {
		String sql = "CREATE TABLE IF NOT EXISTS sys ("
				+ "attr INTEGER PRIMARY KEY,"
				+ "int INTEGER,"
				+ "str TEXT"
				+ ")";
		int ret = execSQL(sql);
		if (ret == -1) {
			return false;
		}
		
		sql = "CREATE TABLE IF NOT EXISTS script ("
				+ "id INTEGER,"
				+ "pack_id INTEGER,"
				+ "script TEXT,"
				+ "status INTEGER,"
				+ "base_score INTEGER,"
				+ "ctime TEXT"
				+ ")";
		if (execSQL(sql) == -1) {
			return false;
		}

		sql = "CREATE TABLE IF NOT EXISTS solution ("
				+ "scriptid INTEGER PRIMARY KEY,"
				+ "command TEXT,"
				+ "score INTEGER,"
				+ "utime TEXT"				
				+ ")";
		if (execSQL(sql) == -1) {
			return false;
		}
		
		sql = "CREATE TABLE IF NOT EXISTS pack ("
				+ "id INTEGER PRIMARY KEY," 
				+ "title TEXT)";
		
		if (execSQL(sql) == -1) {
			return false;
		}
				
		return true;
	}
	
	public final ResultSet loadScript(int id) {
		final String sql = "SELECT script, status, base_score FROM script WHERE id=" + id;
		return querySQL(sql);
	}

	public void saveSolution(int scriptid, final String cmd) {
		final String sql = "REPLACE INTO solution (script_id, command, score, utime) VALUES(?, ?, ?, ?)";
		ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(scriptid));
		val.add(cmd);
		val.add("-1");
		val.add(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));		
		execSQL(sql, val);
	}
	

	public void clearSolution(int id) {
		final String sql = "DELETE FROM solution WHERE script_id=" + id;
		execSQL(sql);
	}	

	public final String loadSolution(int i) {
		final String sql = "SELECT command FROM solution WHERE script_id=" + i;
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
	
	public final String getPackTitle(int id) {
		final String sql = "select title from pack where id = " + id;
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
		
		return "No Title";
	}
	

	public ResultSet loadPackItems(int id) {
		final String sql = "select script.id, script.status, script.base_score, script.script, solution.score from script left join solution on (script.id=solution.script_id) where script.pack_id=? order by script.id";
		ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(id));
		return querySQL(sql, val);
	}

	public int getNextScriptId(int packId, int scriptId) {
		final String sql = "SELECT id FROM script WHERE pack_id=? and id>? ORDER BY id LIMIT 1";
		final ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(packId));
		val.add(String.valueOf(scriptId));
		final ResultSet rs = querySQL(sql, val);
		try {
			try {
				if (rs.next()) {
					return rs.getInt(1);
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return -1;
	}

	public final String getSysDataAsString(int attr) {
		final String sql = "SELECT str FROM sys WHERE attr=" + attr;
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
	
	public int getSysDataAsInt(int attr) {
		final String sql = "SELECT int FROM sys WHERE attr=" + attr;
		final ResultSet rs = querySQL(sql);
		try {
			try {
				if (rs.next()) {
					return rs.getInt(1);
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}	
	
	public final void setSysData(int attr, int i, final String str) {
		final String sql = "INSERT OR REPLACE INTO sys (attr, int, str) VALUES (?,?,?)";
		final ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(attr));
		val.add(String.valueOf(i));
		val.add(str);
		execSQL(sql, val);
	}

	
	public void updateScriptStatus(int id, int status) {
		final String sql = "UPDATE script SET status=? WHERE id=?";
		final ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(status));
		val.add(String.valueOf(id));
		execSQL(sql, val);
	}

	public void updateSolutionScore(int id, int score) {
		final String sql = "UPDATE solution SET score=? WHERE script_id=?";
		final ArrayList<String> val = new ArrayList<String>();
		val.add(String.valueOf(score));
		val.add(String.valueOf(id));
		execSQL(sql, val);		
	}
	
}
