package jie.android.ip.screen.box.console;


import com.badlogic.gdx.scenes.scene2d.Actor;

public class Code {
	
	public static final int NUM_CODE_LINES = 4;
	public static final int NUM_CODE_PER_LINE = 8 * 2; 
	
	public enum Type {
		
		NULL, RIGHT, LEFT, ACT, IF_NULL, IF_0, IF_1, IF_2, IF_3, IF_NONE, IF_ANY,
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
			return (this == IF_NULL || this == IF_0 || this == IF_1 || this == IF_2 || this == IF_3
					|| this == IF_NONE || this == IF_ANY);
		}
		
		public boolean isNull() {
			return this == NULL;
		}
	}

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
						buttons[f][p] = new Button(Type.IF_NULL);						
					} else {
						buttons[f][p] = new Button(Type.NULL);
					}
				}
			}
		}
		
		private void init() {
			
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
			judgeButton[4] = new Button(Type.IF_ANY);
			judgeButton[5] = new Button(Type.IF_NONE);
			judgeButton[6] = new Button(Type.IF_NULL);
			
			orderButton[0] = new Button(Type.RIGHT);
			orderButton[1] = new Button(Type.LEFT);
			orderButton[2] = new Button(Type.ACT);
			orderButton[3] = new Button(Type.CALL_0);
			orderButton[4] = new Button(Type.CALL_1);
			orderButton[5] = new Button(Type.CALL_2);
			orderButton[6] = new Button(Type.CALL_3);
			orderButton[7] = new Button(Type.NULL);
		}
		
		public final Button[] getJudgeButton() {
			return judgeButton;
		}
		
		public final Button[] getOrderButton() {
			return orderButton;
		}
	}

}
