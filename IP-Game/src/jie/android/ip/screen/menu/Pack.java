package jie.android.ip.screen.menu;

import java.sql.SQLException;
import java.util.ArrayList;

import jie.android.ip.executor.Script;

public class Pack {

	public static final int NUM_PACK = 6;
	
	public class Item {

		private final Script script;
		
		private final int status;
		private final int base_score;
		private final int score;
		
		public Item(final Script script, int status,int base_score, int score) {
			this.script = script;		

			this.status = status;
			this.base_score = base_score;
			this.score = score;
		}
		
		public final Script getScript() {
			return script;
		}

		public int getId() {
			return script.getId();
		}
//
		public int getStatus() {
			return status;
		}

		public int getBaseScore() {
			return base_score;
		}
		
		public int getScore() {
			return score;
		}
		
	}
	
	//
	
	private final int id;
	private final String title;
	private final int total_all;
	private final int total_pass;
	
	private final ArrayList<Item> items = new ArrayList<Item>();
	
	public Pack(int id, final String title, int all, int pass )	{
		this.id = id;
		this.title = title;
		this.total_all = all;
		this.total_pass = pass;
	}
	
	public int getId() {
		return id;
	}

	public final String getTitle() {
		return title;
	}

	public int getTotal_all() {
		return total_all;
	}

	public int getTotal_pass() {
		return total_pass;
	}

	public final Item[] getItems() {
		final Item[] ret = new Item[items.size()];		
		return items.toArray(ret);
	}
	
	public void addItem(int id, int status, int base_score, int score, final String script) {
		final Script sc = new Script(id);
		if(!sc.loadString(script)) {
			return;
		}
		
		items.add(new Item(sc, status, base_score, score)); 
	}
	
}
