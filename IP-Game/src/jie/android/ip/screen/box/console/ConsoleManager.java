package jie.android.ip.screen.box.console;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import jie.android.ip.executor.CommandConsts;
import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.executor.CommandConsts.EmptyType;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.CommandSet.Command;
import jie.android.ip.screen.box.BoxRenderAdapter;
import jie.android.ip.screen.box.BoxScreenEventListener;
import jie.android.ip.screen.box.console.Cmd.Button;
import jie.android.ip.screen.box.console.Code.Type;
import jie.android.ip.utils.Utils;

public class ConsoleManager implements Disposable {

	protected static final String Tag = ConsoleManager.class.getSimpleName();
	
	private BoxRenderAdapter adapter;	
	private ConsoleRenderer renderer;
	
	private final BoxScreenEventListener screenListener;
	
	private Cmd.Panel cmdPanel;
	private Code.Lines codeLines;
	private Code.Panel codePanel;
	
	private int cacheIndex = -1;
	private int cachePos = -1;
	
	private boolean isRunning = false;
	
	private Cmd.OnButtonListener cmdListener = new Cmd.OnButtonListener() {

		@Override
		public void onClick(final Cmd.Button button) {
			Utils.log(Tag, "cmdbtn: type = " + button.type.getId() + " state = " + button.state);
			onCmdButtonClick(button);
		}		
	};
	
	private Code.OnButtonListener codeListener = new Code.OnButtonListener() {

		@Override
		public void onClick(final Code.OnButtonListener.Which which, int index, int pos, final Code.Button button) {
			if (which == Code.OnButtonListener.Which.PANEL_GROUP || which == Code.OnButtonListener.Which.CODE_GROUP) {
				Utils.log(Tag, "code group click : index = " + index + " which = " + which.getId());
				onCodeGroupClick(index);
			} else if (which == Code.OnButtonListener.Which.PANEL) {
				Utils.log(Tag, "code panel click : index = " + index + " which = " + which.getId() + "  button type = " + button.type.getId());
				onCodePanelClick(pos, button);
			} else if (which == Code.OnButtonListener.Which.CODE) {
				Utils.log(Tag, "code line click : index = " + index + " which = " + which.getId() + "  button type = " + button.type.getId());
				onCodeLineClick(index, pos, button);
			}
		}
	}; 
	
	private ClickListener groupListener = new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (hitGroup(x, y)) {
				Utils.log(Tag, "console group click : x = " + x + " y = " + y);
				onConsoleGroupClick(x, y);
			}
		}
	};
	
	public ConsoleManager(final BoxRenderAdapter adapter, final BoxScreenEventListener listener) {
		this.adapter = adapter;
		this.screenListener = listener;
		
		initButtons();
		
		initRenderer();
	}

	@Override
	public void dispose() {
		if (renderer != null) {
			renderer.dispose();
		}
	}
	
	
	private void initButtons() {
		cmdPanel = new Cmd.Panel(cmdListener);
		codeLines = new Code.Lines();
		codePanel = new Code.Panel();
	}
	
	private void initRenderer() {
		renderer = new ConsoleRenderer(adapter);
		
		renderer.initCmdPanel(cmdPanel, cmdListener);
		renderer.initCodeLines(codeLines, codePanel, codeListener);
//		
		renderer.setGroupClickListener(groupListener);
	}
	
	protected boolean hitGroup(float x, float y) {
		return renderer.hitGroup(x, y);
	}
	
	protected void onCmdButtonClick(Button button) {
		if (renderer.isCodeLinesShown()) {
			return;
		}
		
		if (screenListener != null) {
			screenListener.onConsoleButtonClick(button.type.getId(), button.state.getId());
		}
	}
	
	protected void onCodeLineClick(int index, int pos, final Code.Button button) {
		setCacheCodeButton(index, pos, button);
		renderer.showCodePanel(index, pos, button);
	}

	protected void onCodePanelClick(int index, final Code.Button button) {
		updateCachedCodeButtonType(button.type);
		renderer.hideCodePanel(index);
	}

	protected void onCodeGroupClick(int index) {
		renderer.updataCodeGroupState(index);
	}
	
	protected void onConsoleGroupClick(float x, float y) {
		setCacheCodeButton(-1, -1, null);
		renderer.updataCodeGroupState(-1);		
	}	

	private void setCacheCodeButton(int index, int pos, final Code.Button button) {
		cacheIndex = index;
		cachePos = pos;
	}

	private void updateCachedCodeButtonType(final Code.Type type) {
		renderer.updateCodeLinesButton(cacheIndex, cachePos, type);		
		setCacheCodeButton(-1, -1, null);
	}

	public final CommandSet makeCommandSet() {
		final CommandSet cmdSet = new CommandSet();
		for (int i = 0; i < Code.NUM_CODE_LINES; ++ i) {
			CommandSet.CommandQueue que = CommandSet.makeCommandQueue();
			for (final Code.Button btn : codeLines.getFuncButton(i)) {
				if (btn.type == Code.Type.IF_NULL) {
					que.add(CommandSet.makeCommand(CommandType.EMPTY, EmptyType.CHECK.getId()));
				} else if (btn.type == Code.Type.NULL) {
					que.add(CommandSet.makeCommand(CommandType.EMPTY, EmptyType.ACT.getId()));
				} else if (btn.type == Code.Type.RIGHT) {
					que.add(CommandSet.makeCommand(CommandType.ACT, ActType.MOVE_RIGHT.getId(), 1));
				} if (btn.type == Code.Type.LEFT) {
					que.add(CommandSet.makeCommand(CommandType.ACT, ActType.MOVE_LEFT.getId(), 1));
				} if (btn.type == Code.Type.ACT) {
					que.add(CommandSet.makeCommand(CommandType.ACT, ActType.ACTION.getId(), 0));
				} if (btn.type == Code.Type.CALL_0) {
					que.add(CommandSet.makeCommand(CommandType.CALL, 0));
				} if (btn.type == Code.Type.CALL_1) {
					que.add(CommandSet.makeCommand(CommandType.CALL, 1));
				} if (btn.type == Code.Type.CALL_2) {
					que.add(CommandSet.makeCommand(CommandType.CALL, 2));
				} if (btn.type == Code.Type.CALL_3) {
					que.add(CommandSet.makeCommand(CommandType.CALL, 3));
				} if (btn.type == Code.Type.IF_0) {
					que.add(CommandSet.makeCommand(CommandType.CHECK, 0, 0));//variant 0 is the value of block
				} if (btn.type == Code.Type.IF_1) {
					que.add(CommandSet.makeCommand(CommandType.CHECK, 1, 0));
				} if (btn.type == Code.Type.IF_2) {
					que.add(CommandSet.makeCommand(CommandType.CHECK, 2, 0));
				} if (btn.type == Code.Type.IF_3) {
					que.add(CommandSet.makeCommand(CommandType.CHECK, 3, 0));
				} if (btn.type == Code.Type.IF_ANY) {
					que.add(CommandSet.makeCommand(CommandType.CHECK, 1, 1));//variant 1 is the indication of block
				} if (btn.type == Code.Type.IF_NONE) {
					que.add(CommandSet.makeCommand(CommandType.CHECK, 0, 1));
				} 
			}
			
			if (que != null && que.size() > 0) {
				cmdSet.put(i, que);
			}
		}
		
		return cmdSet;
	}

	public void loadSolution(final String cmdString) {
		if (cmdString == null) {
			return;
		}

		final CommandSet cmdSet = CommandSet.loadFromString(cmdString);
		if (cmdSet != null) {
			resetCodeLines(cmdSet);
		}
	}
	
	public void reset() {
		resetCommandButtons();
		renderer.removeCodeLines(codeLines);
		codeLines.reset();
		renderer.resetCodeLines(codeLines, codeListener);
	}
	
	private void resetCodeLines(final CommandSet cmdSet) {
		renderer.removeCodeLines(codeLines);
		codeLines.reset();
		
		for (int i = 0; i < Code.NUM_CODE_LINES; ++ i) {
			final CommandSet.CommandQueue que = cmdSet.get(i);
			if (que != null) {
				int pos = 0;
				for (final CommandSet.Command cmd : que) {
					final Code.Type type = getCodeTypeByCommand(cmd);
					if (type != null) {
						codeLines.setButton(i, pos ++ , type);
					}
				}
			}
		}
		
		renderer.resetCodeLines(codeLines, codeListener);
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

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunState(boolean running) {		
		isRunning = running;
		if (isRunning) {
			setCmdButtonChecked(Cmd.Type.RUN, true);
		}
		setCmdButtonEnabled(Cmd.Type.CLEAR, !isRunning);
		setCmdButtonEnabled(Cmd.Type.MENU, !isRunning);
	}
	
	public void resetCommandButtons() {
		setCmdButtonChecked(Cmd.Type.RUN, false);
	}
	
	public void setCmdButtonChecked(final Cmd.Type type, boolean checked) {
		cmdPanel.setState(type, checked ? Cmd.State.SELECTED : Cmd.State.NONE);
	}
	
	public void setCmdButtonEnabled(final Cmd.Type type, boolean enabled) {
		cmdPanel.setState(type, enabled ? Cmd.State.NONE : Cmd.State.DISABLED);
	}
	
}
