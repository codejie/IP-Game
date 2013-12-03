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

	public interface OnButtonListener {
		public void onClick(final Type type, int state);
	}	
	
	public static class Button {
		
		public final Type type;
		public final OnButtonListener listener;
		public Actor actor;
		public int state;
		
		public Button(final Type type, final OnButtonListener listener) {
			this.type = type;
			this.listener = listener;
			this.state = 0;
		}
	}	
	
	public static class Panel extends ArrayList<Button> {
		
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
