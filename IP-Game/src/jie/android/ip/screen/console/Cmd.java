package jie.android.ip.screen.console;


import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

public final class Cmd {
	
	public enum Type { 
		NONE, RUN;
		
		public int getId() {
			return this.ordinal();
		}		
	};
	
	public enum State {
		NONE, SELECTED;
		
		public int getId() {
			return this.ordinal();
		}
	}
	public static class Button {
		
		public final Type type;
		public State state = State.NONE;
		public Actor actor;
		
		public Button(final Type type) {
			this.type = type;
		}
	}
	
	public interface OnButtonListener {
		public void onClick(final Button button);
	}	
	
	public static class Panel extends ArrayList<Button> {
		
		private static final long serialVersionUID = 1L;

		public Panel(final OnButtonListener listener) {
			Type type[] = Type.values();
			for (int i = 0; i < type.length; ++ i) {
				Button btn = new Button(type[i]);
				super.add(btn);
			}
		}
		
		public final Button[] getButtons() {
			Button[] ret = new Button[this.size()];
			return this.toArray(ret);
		}
	}
	
}
