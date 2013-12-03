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
	}
	
	public interface OnButtonListener {
		void onPanelButtonClick(Type type, int state);
		void onLinesButonClick(int func, int pos, Type type, int state);
	}	

	public static class Button {
		public final Type type;
		public final OnButtonListener listener;
		public int func = -1;
		public int pos = -1;
		public Actor actor;
		public int state;
		
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
		private Button[] buttons = new Button[Type.values().length];
		
		public Panel(OnButtonListener listener) {
			Type[] type = Type.values();
			for (int i = 0; i < type.length; ++ i) {
				buttons[i] = new Button(type[i], listener);
			}
		}
		
		public final Button[] getButtons() {
			return buttons;
		}
	}	
	
}
