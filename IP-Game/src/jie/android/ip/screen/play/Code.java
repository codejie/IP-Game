package jie.android.ip.screen.play;

import jie.android.ip.executor.CommandConsts;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.executor.CommandConsts.EmptyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Code {
	
	public static final int NUM_CODE_LINES = 4;
	public static final int NUM_CODE_PER_LINE = 8 * 2; 
	
	public enum Type {
		
		NULL, LEFT, RIGHT, ACT, IF_NULL, IF_0, IF_1, IF_2, IF_3, IF_NONE, IF_ANY,
		CALL_0, CALL_1, CALL_2, CALL_3;
		
		public int getId() {
			return ordinal();
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

		public static Type getType(int id) {
			for (final Type type : Type.values()) {
				if (id == type.getId()) {
					return type;
				}
			}
			return null;
		}
	}

	public interface OnButtonListener {
		
//		public enum Which {
//			BASE_GROUP, PANEL_GROUP, PANEL, LINE_GROUP, LINE;
//			
//			public int getId() {
//				return this.ordinal();
//			}
//		}
		//void onClick(final Which which, int index, int pos);
		void onBaseGroupClick();
		void onLineGroupClick(int index);
		void onLineClick(int index, int pos);
		void onPanelClick(final Code.Type type);
	}	

	public static class Lines {
		
		final PlayScreenListener.ManagerInternalEventListener internalListener;
		
		private Type[][] node = new Type[NUM_CODE_LINES][NUM_CODE_PER_LINE];
		
		public Lines(final PlayScreenListener.ManagerInternalEventListener listener) {
			this.internalListener = listener;
			
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
			internalListener.onCodeLineResetCompleted(this);
		}

		public void setNode(int func, int pos, final Code.Type type) {
			node[func][pos] = type;
			internalListener.onCodeLineUpdated(this, func, pos);
		}

		public void loadCmdSet(final CommandSet cmdSet) {
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
			
			internalListener.onCodeLineLoadCompleted(this);
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

		public final CommandSet makeCommandSet() {
			final CommandSet cmdSet = new CommandSet();
			for (int f = 0; f < Code.NUM_CODE_LINES; ++ f) {
				CommandSet.CommandQueue que = CommandSet.makeCommandQueue();
				for (int p = 0; p < Code.NUM_CODE_PER_LINE; ++ p) {					
					if (node[f][p] == Code.Type.IF_NULL) {
						que.add(CommandSet.makeCommand(CommandType.EMPTY, EmptyType.CHECK.getId()));
					} else if (node[f][p] == Code.Type.NULL) {
						que.add(CommandSet.makeCommand(CommandType.EMPTY, EmptyType.ACT.getId()));
					} else if (node[f][p] == Code.Type.RIGHT) {
						que.add(CommandSet.makeCommand(CommandType.ACT, ActType.MOVE_RIGHT.getId(), 1));
					} if (node[f][p] == Code.Type.LEFT) {
						que.add(CommandSet.makeCommand(CommandType.ACT, ActType.MOVE_LEFT.getId(), 1));
					} if (node[f][p] == Code.Type.ACT) {
						que.add(CommandSet.makeCommand(CommandType.ACT, ActType.ACTION.getId(), 0));
					} if (node[f][p] == Code.Type.CALL_0) {
						que.add(CommandSet.makeCommand(CommandType.CALL, 0));
					} if (node[f][p] == Code.Type.CALL_1) {
						que.add(CommandSet.makeCommand(CommandType.CALL, 1));
					} if (node[f][p] == Code.Type.CALL_2) {
						que.add(CommandSet.makeCommand(CommandType.CALL, 2));
					} if (node[f][p] == Code.Type.CALL_3) {
						que.add(CommandSet.makeCommand(CommandType.CALL, 3));
					} if (node[f][p] == Code.Type.IF_0) {
						que.add(CommandSet.makeCommand(CommandType.CHECK, 0, 0));//variant 0 is the value of block
					} if (node[f][p] == Code.Type.IF_1) {
						que.add(CommandSet.makeCommand(CommandType.CHECK, 1, 0));
					} if (node[f][p] == Code.Type.IF_2) {
						que.add(CommandSet.makeCommand(CommandType.CHECK, 2, 0));
					} if (node[f][p] == Code.Type.IF_3) {
						que.add(CommandSet.makeCommand(CommandType.CHECK, 3, 0));
					} if (node[f][p] == Code.Type.IF_ANY) {
						que.add(CommandSet.makeCommand(CommandType.CHECK, 1, 1));//variant 1 is the indication of block
					} if (node[f][p] == Code.Type.IF_NONE) {
						que.add(CommandSet.makeCommand(CommandType.CHECK, 0, 1));
					} 
				}
				
				if (que != null && que.size() > 0) {
					cmdSet.put(f, que);
				}
			}
			
			return cmdSet;
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
