package jie.android.ip.screen.play;


import java.util.ArrayList;

import jie.android.ip.common.actor.ButtonActor;

public final class Cmd {
	
	public enum Type { 
		NONE, 
		RUN, CLEAR, MENU, DEBUG,
		BACK, INFO, SETTING, CLOSE, ENABLE_DEBUG,
		SHARE, NEXT, BACK2, CLOSE2;
		
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
	
	public enum Layer {
		FIRST, SECOND, THIRD;
	}
	
//	public enum LayerState {
//		FIRST, SECOND;
//	}
	
	public static class Button {
		
		public final Type type;
		public final Layer layer;
		public State state = State.NONE;
		public ButtonActor actor;
		
		public Button(final Type type) {
			this(type, Layer.FIRST);
		}
		
		public Button(final Type type, final Layer layer) {
			this.type = type;
			this.layer = layer;
		}
	}
	
	public interface OnButtonListener {
		public void onClick(final Button button);
	}	
	
	public static class Panel extends ArrayList<Button> {
		
		private static final long serialVersionUID = 1L;

		public Panel(final OnButtonListener listener) {
			for (final Type type : Type.values()) {				
				if (type == Type.RUN || type == Type.CLEAR || type ==Type.MENU || type == Type.DEBUG) {
					super.add(new Button(type, Layer.FIRST));
				} else if (type == Type.BACK || type == Type.INFO || type == Type.SETTING|| type == Type.CLOSE || type == Type.ENABLE_DEBUG){
					super.add(new Button(type, Layer.SECOND));
				} else {
					super.add(new Button(type, Layer.THIRD));
				}
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
		
		public final Button getButton(final Type type) {
			for (final Button btn : this) {
				if (btn.type == type) {
					return btn;
				}
			}
			return null;
		}
	}
	
	
}
