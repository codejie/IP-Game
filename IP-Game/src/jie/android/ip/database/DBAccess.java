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

import jie.android.ip.CommonConsts.SystemConfig;

public class DBAccess extends BaseAccess {

	public DBAccess(final Connection connection) {
		super(connection);
	}
	
	public final ResultSet loadScript(int id) {
		final String sql = "SELECT script, status, base_score FROM script WHERE id=" + id;
		return querySQL(sql);
	}

	public void saveSolution(int scriptid, final String cmd) {
		final String s = loadSolution(scriptid);
		if (s == null) {
			final String sql = "INSERT INTO solution (script_id, command, score, utime) VALUES(?, ?, ?, ?)";
			ArrayList<String> val = new ArrayList<String>();
			val.add(String.valueOf(scriptid));
			val.add(cmd);
			val.add("0");
			val.add(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
			execSQL(sql, val);			
		} else if (s != cmd){
			final String sql = "UPDATE solution SET command=?, utime=? WHERE script_id=?";
			ArrayList<String> val = new ArrayList<String>();
			val.add(cmd);
			val.add(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));			
			val.add(String.valueOf(scriptid));
			execSQL(sql, val);
		}
//		final String sql = "REPLACE INTO solution (script_id, command, utime) VALUES(?, ?, ?)";
	}	

	public void clearSolution(int id) {
		final String sql = "UPDATE solution SET command=NULL WHERE script_id=" + id;
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
	
	public boolean scriptExist(int id) {
		final String sql = "select COUNT(*) from script where id=" + id;
		final ResultSet rs = querySQL(sql);
		try {
			try {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return false;		
	}
	
	public void addScript(final ArrayList<String> val) {
		final String sql = "INSERT INTO script (id,pack_id,script,status,base_score,ctime) VALUES (?,?,?,?,?,?)";
		execSQL(sql, val);
	}

	public void updateScript(final ArrayList<String> val) {
		final String sql = "UPDATE script SET id=?, pack_id=?,base_score=? WHERE id=?";
		execSQL(sql, val);		
	}

	public void deleteScript(int id) {
		execSQL("DELETE FROM script WHERE id=" + id);
		execSQL("DELETE FROM solution WHERE script_id=" + id);		
	}

	public void updateSolutionId(int id, int new_id) {
		final String sql = "UPDATE solution SET script_id=" + new_id + " WHERE script_id=" + id;
		execSQL(sql);
	}

	public void updateDBVersion(int ver) {
		final String sql = "UPDATE sys SET int=" + ver + " WHERE attr=" + SystemConfig.SYS_ATTR_VERSION;
		execSQL(sql);
	}	
}
