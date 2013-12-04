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
		public final OnButtonListener listener;
		public Actor actor;
		public State state = State.NONE;
		
		public Button(final Type type, final OnButtonListener listener) {
			this.type = type;
			this.listener = listener;
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
				Button btn = new Button(type[i], listener);
				super.add(btn);
			}
		}
		
		public final Button[] getButtons() {
			Button[] ret = new Button[this.size()];
			return this.toArray(ret);
		}
	}
	
}
