package jie.android.ip.screen.play;

import jie.android.ip.executor.CommandConsts;
import jie.android.ip.executor.CommandSet;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Code {
	
	public static final int NUM_CODE_LINES = 4;
	public static final int NUM_CODE_PER_LINE = 8 * 2; 
	
	public enum Type {
		
		NULL, LEFT, RIGHT, ACT, IF_NULL, IF_0, IF_1, IF_2, IF_3, IF_NONE, IF_ANY,
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

	public interface OnButtonListener {
		
		public enum Which {
			BASE_GROUP, PANEL_GROUP, PANEL, LINE_GROUP, LINE;
			
			public int getId() {
				return this.ordinal();
			}
		}
		
		void onClick(final Which which, int index, int pos);
	}	

	public static class Lines {
		
		private Type[][] node = new Type[NUM_CODE_LINES][NUM_CODE_PER_LINE];
		
		public Lines() {
			init();
		}
		
		private void init() {
			for (int f = 0; f < NUM_CODE_LINES; ++ f) {
				for (int p = 0; p < NUM_CODE_PER_LINE; ++ p) {
					if (p % 2 == 0) {						
						node[f][p] = Type.IF_NULL;						
					} else {
						node[f][p] = Type.NULL;
					}
				}
			}			
		}
		
		public final Type getNode(int func, int pos) {
			return node[func][pos];
		}
		
		public final Type[] getFuncNode(int func) {
			return node[func];
		}

		public void reset() {
			init();
		}

//		public void setButton(int func, int pos, final Code.Type type) {
//			node[func][pos] = type;
//		}

		public void loadCmdSet(final CommandSet cmdSet, final PlayScreenListener.ManagerEventListener listener) {
			if (cmdSet != null) {
				for (int i = 0; i < Code.NUM_CODE_LINES; ++ i) {
					final CommandSet.CommandQueue que = cmdSet.get(i);
					if (que != null) {
						int pos = 0;
						for (final CommandSet.Command cmd : que) {
							final Code.Type type = getCodeTypeByCommand(cmd);
							if (type != null) {
								node[i][pos ++] = type;
							}
						}
					}
				}
			}
			
			if (listener != null) {
				listener.onCodeLineInitCompleted(this);
			}
		}
		
		private final Code.Type getCodeTypeByCommand(final CommandSet.Command cmd) {
			final CommandConsts.CommandType cmdType = cmd.getType();
			if (cmdType == CommandConsts.CommandType.ACT) {
				int action = cmd.getParamAsInt(0, -1);
				if (action == CommandConsts.ActType.MOVE_RIGHT.getId()) {
					return Code.Type.RIGHT;
				} else if (action == CommandConsts.ActType.MOVE_LEFT.getId()) {
					return Code.Type.LEFT;
				} else if (action == CommandConsts.ActType.ACTION.getId()) {
					return Code.Type.ACT;
				}
			} else if (cmdType == CommandConsts.CommandType.CHECK) {
				int val = cmd.getParamAsInt(0, -1);
				int var = cmd.getParamAsInt(1, -1);
				if (var == 0) {
					if (val == 0) {
						return Code.Type.IF_0;
					} else if (val == 1) {
						return Code.Type.IF_1;
					} else if (val == 2) {
						return Code.Type.IF_2;
					} else if (val == 3) {
						return Code.Type.IF_3;
					}
				} else if (var == 1) {
					if (val == 0) {
						return Code.Type.IF_NONE;
					} else if (val == 1) {
						return Code.Type.IF_ANY;
					}				
				}
			} else if (cmdType == CommandConsts.CommandType.CALL) {
				int func = cmd.getParamAsInt(0, -1);
				if (func == 0) {
					return Code.Type.CALL_0;
				} else if (func == 1) {
					return Code.Type.CALL_1;
				} else if (func == 2) {
					return Code.Type.CALL_2;
				} else if (func == 3) {
					return Code.Type.CALL_3;
				}
			} else if (cmdType == CommandConsts.CommandType.EMPTY) {
				int type = cmd.getParamAsInt(0, -1);
				if (type == CommandConsts.EmptyType.CHECK.getId()) {
					return Code.Type.IF_NULL;
				} else if (type == CommandConsts.EmptyType.ACT.getId()) {
					return Code.Type.NULL;
				}
			}

			return null;
		}		
		
	}
	
	public static class Button {
		public final Type type;
//		public Actor smallActor;
		public Actor bigActor;
		
		public Button(final Type type) {
			this.type = type;
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
