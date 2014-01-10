package jie.android.ip.screen.menu;

import java.util.ArrayList;

public class Pack {

	public static final int NUM_PACK = 6;
	
	public class Item {

		private final int id;
		private final int status;
		private final int score;
		private final String script;
		
		public Item(int id, int status, int score, final String script) {
			this.id = id;
			this.status = status;
			this.score = score;
			this.script = script;
		}

		public int getId() {
			return id;
		}

		public int getStatus() {
			return status;
		}

		public int getScore() {
			return score;
		}

		public String getScript() {
			return script;
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
	
	public void addItem(int id, int status, int score, final String script) {
		items.add(new Item(id, status, score, script));
	}
	
}
