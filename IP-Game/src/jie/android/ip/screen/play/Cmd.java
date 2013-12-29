package jie.android.ip.screen.play;


import java.util.ArrayList;

import jie.android.ip.common.actor.ButtonActor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public final class Cmd {
	
	public enum Type { 
		NONE, RUN, CLEAR, MENU;
		
		public int getId() {
			return this.ordinal();
		}		
	};
	
	public enum State {
		NONE, SELECTED, DISABLED;
		
		public int getId() {
			return this.ordinal();
		}
	}
	public static class Button {
		
		public final Type type;
		public State state = State.NONE;
		public ButtonActor actor;
		
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
		
		public final Button setState(final Type type, final State state) {
			for (final Button btn : this) {
				if (btn.type == type) {
					btn.state = state;
					if (btn.actor != null) {
						if (btn.state == State.NONE) {
							btn.actor.setChecked(false);
							btn.actor.setDisabled(false);
						} else if (btn.state == State.SELECTED) {
							btn.actor.setChecked(true);
						} else if (btn.state == State.DISABLED) {
							btn.actor.setDisabled(true);
						}
					}
					return btn;
				}
			}
			return null;
		}
	}
	
	
}
