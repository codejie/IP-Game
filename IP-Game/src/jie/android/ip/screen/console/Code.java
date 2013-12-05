package jie.android.ip.screen.console;


import com.badlogic.gdx.scenes.scene2d.Actor;

public class Code {

	public static final int MAX_FUNC = 4;
	public static final int MAX_CODE = 8;
	
	public enum Type {
		NONE, RIGHT, LEFT, ACT, IF_0, IF_1, IF_2, IF_3, IF_NONE, IF_ANY,
		CALL_0, CALL_1, CALL_2, CALL_3;
		
		public int getId() {
			return this.ordinal();
		}
		
		public boolean isOrder() {
			return (this == RIGHT || this == LEFT || this == ACT);
		}
		
		public boolean isCall() {
			return (this == CALL_0 || this == CALL_1 || this == CALL_2 || this == CALL_3);
		}
		
		public boolean isJudge() {
			return (this == IF_0 || this == IF_1 || this == IF_2 || this == IF_3
					|| this == IF_NONE || this == IF_ANY);
		}
	}
	
	public enum State {
		NONE, SELECTED;
		
		public int getId() {
			return this.ordinal();
		}
	}
	
	public static class Button {
		public final Type type;
		public final OnButtonListener listener;
		public int func = -1;
		public int pos = -1;
		public Actor actor;
		public State state = State.NONE;
		
		public Button(final Type type, final OnButtonListener listener) {
			this(type, listener, -1, -1);
		}
		
		public Button(final Type type, final OnButtonListener listener, int func, int pos) {
			this.type = type;
			this.listener = listener;
			this.func = func;
			this.pos = pos;
		}
	}
	
	public interface OnButtonListener {
		void onClick(boolean inPanel, final Button button);
	}	

	public static class Lines {
		private Button[][] buttons = new Button[MAX_FUNC][MAX_CODE * 2];
		
		public void setCode(int func, int pos, final Button btn) {
			if (btn.type == Type.NONE || btn.type == Type.RIGHT || btn.type == Type.LEFT || btn.type == Type.ACT
					|| btn.type == Type.CALL_0 || btn.type == Type.CALL_1 || btn.type == Type.CALL_2 || btn.type == Type.CALL_3) {
				buttons[func][pos + 1] = btn;
			} else {
				buttons[func][pos] = btn;
			}
		}		
	}
	
	public static class Panel {
		private Button[] buttons = new Button[Type.values().length - 1];
		
		public Panel(OnButtonListener listener) {
			Type[] type = Type.values();
			for (int i = 1; i < type.length; ++ i) {
				buttons[i - 1] = new Button(type[i], listener);
			}
		}
		
		public final Button[] getButtons() {
			return buttons;
		}
	}	
	
}
