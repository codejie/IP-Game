package jie.android.ip.screen.console;


import com.badlogic.gdx.scenes.scene2d.Actor;

public class Code {
	
	public static final int NUM_CODE_LINES = 4;
	public static final int NUM_CODE_PER_LINE = 8 * 2; 
	
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
		
		public boolean isNone() {
			return this == NONE;
		}
	}
//	
//	public enum State {
//		NONE, SELECTED;
//		
//		public int getId() {
//			return this.ordinal();
//		}
//	}
//	
	public static class Button {
		public final Type type;
		public Actor smallActor;
		public Actor bigActor;
		
		public Button(final Type type) {
			this.type = type;
		}
	}
//	
	public interface OnButtonListener {
		
		public enum Which {
			PANEL_GROUP, PANEL, CODE_GROUP, CODE;// code is code line
			
			public int getId() {
				return this.ordinal();
			}
		}
		
		void onClick(final Which which, int index, int pos, final Button button);
	}	

	public static class Lines {
		
		private Button[][] buttons = new Button[NUM_CODE_LINES][NUM_CODE_PER_LINE];
		
		public Lines() {
			for (int f = 0; f < NUM_CODE_LINES; ++ f) {
				for (int p = 0; p < NUM_CODE_PER_LINE; ++ p) {
					if (p % 2 == 0) {
						buttons[f][p] = new Button(Type.NONE);
					} else {
						buttons[f][p] = new Button(Type.IF_NONE);
					}
				}
			}
		}
		
		public void setButton(int func, int pos, final Button btn) {
			if (!btn.type.isJudge()) {
				buttons[func][pos + 1] = btn;
			} else {
				buttons[func][pos] = btn;
			}
		}
		
		public final Button getButton(int func, int pos) {
			return buttons[func][pos];
		}
		
		public final Button[] getFuncButton(int func) {
			return buttons[func];
		}		
	}
	
	public static class Panel {
		public static final int NUM_JUDGE_BUTTON = 7;
		public static final int NUM_ORDER_BUTTON = 8;
		private Button[] judgeButton = new Button[NUM_JUDGE_BUTTON];
		private Button[] orderButton = new Button[NUM_ORDER_BUTTON];
		
		public Panel() {
			judgeButton[0] = new Button(Type.IF_0);
			judgeButton[1] = new Button(Type.IF_1);
			judgeButton[2] = new Button(Type.IF_2);
			judgeButton[3] = new Button(Type.IF_3);
			judgeButton[4] = new Button(Type.IF_NONE);
			judgeButton[5] = new Button(Type.IF_ANY);
			judgeButton[6] = new Button(Type.NONE);
			
			orderButton[0] = new Button(Type.RIGHT);
			orderButton[1] = new Button(Type.LEFT);
			orderButton[2] = new Button(Type.ACT);
			orderButton[3] = new Button(Type.CALL_0);
			orderButton[4] = new Button(Type.CALL_1);
			orderButton[5] = new Button(Type.CALL_2);
			orderButton[6] = new Button(Type.CALL_3);
			orderButton[7] = new Button(Type.NONE);
		}
		
		public final Button[] getJudgeButton() {
			return judgeButton;
		}
		
		public final Button[] getOrderButton() {
			return orderButton;
		}
	}
//	
//	public static class Panel {
//		private Button[] buttons = new Button[Type.values().length - 1];
//		
//		public Panel(OnButtonListener listener) {
//			Type[] type = Type.values();
//			for (int i = 1; i < type.length; ++ i) {
//				buttons[i - 1] = new Button(type[i], listener);
//			}
//		}
//		
//		public final Button[] getButtons() {
//			return buttons;
//		}
//	}	
//	
}
