package jie.android.ip.screen.play;

import com.badlogic.gdx.utils.Disposable;

import jie.android.ip.database.DBAccess;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Script;
import jie.android.ip.screen.play.Cmd.State;
import jie.android.ip.screen.play.Code.Lines;
import jie.android.ip.utils.Utils;

public class PlayManager implements Disposable {
	
	protected static final String Tag = PlayManager.class.getSimpleName();
	
	private final PlayScreen screen;
	private final DBAccess dbAccess;
	
	private Script script;
	private CommandSet cmdSet;
	
	private PlayScreenListener.ManagerEventListener managerListener;
	
	private final Box box;;
	private final Code.Lines codeLines;
	private final PlayExecutor executor;

	private final PlayScreenListener.RendererEventListener rendererListener = new PlayScreenListener.RendererEventListener() {

		@Override
		public void onBoxMoveStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBoxkMoveEnd() {
			
			if (!box.checkResult()) {
				executor.next();
			} else {
				executor.stop(PlayExecutor.StopReason.SUCC);
//				onExecuteEnd(true);
			}
		}

		@Override
		public void onPanelButtonClicked(int index, int pos, final Code.Type type) {
			codeLines.setNode(index, pos, type);
		}

		@Override
		public void onCmdButtonClicked(final Cmd.Type type, final Cmd.State state) {
			if (type == Cmd.Type.RUN) {
				onCmdRun(state);
			} else if (type == Cmd.Type.CLEAR) {
				onCmdClear(state);
			} else if (type == Cmd.Type.NEXT) {
				onCmdNext(state);
			} else if (type == Cmd.Type.CLOSE || type == Cmd.Type.CLOSE2) {
				onCmdClose(state);
			} else if (type == Cmd.Type.SETTING) {
				
			} else if (type == Cmd.Type.INFO) {
				
			}			
		}

	};
	
	private final PlayScreenListener.ManagerInternalEventListener internalListener = new PlayScreenListener.ManagerInternalEventListener() {
		
		@Override
		public void onExecuteMove(boolean right) {
			box.tryMoveTray(right);
		}
		
		@Override
		public void onExecuteCompleted(final PlayExecutor.StopReason reason) {
			Utils.log(Tag, "executeCompleted : " + reason);
			
			if (reason == PlayExecutor.StopReason.SUCC) {
				onExecuteSucc();
			} else if (reason == PlayExecutor.StopReason.RESET) {
				onExecuteReset();
			} else if (reason == PlayExecutor.StopReason.FINISHED) {
				onExecuteFinished();
			} else if (reason == PlayExecutor.StopReason.EXCEPTION) {
				onExecuteException();
			} else {
				Utils.log(Tag, "Unsupport execute stop reason - " + reason);
			}
		}
		
		@Override
		public void onExecuteAction() {
			box.tryMoveBlock();
		}

		@Override
		public void onBoxLoadCompleted(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			if (managerListener != null) {
				managerListener.onBoxLoadCompleted(tray, source, target);
			}
		}

		@Override
		public void onBoxPreReload(final Box.Tray tray, final Box.BlockArray source, final Box.BlockArray target) {
			if (managerListener != null) {
				managerListener.onBoxPreReload(tray, source, target);
			}
		}

		@Override
		public void onCodeLineLoadCompleted(final Code.Lines lines) {
			if (managerListener != null) {
				managerListener.onCodeLineLoadCompleted(lines);
			}			
		}

		@Override
		public void onCodeLineUpdated(final Code.Lines lines, int index, int pos) {
			if (managerListener != null) {
				managerListener.onCodeLineUpdated(lines, index, pos);
			}			
		}

		@Override
		public void onCodeLineResetCompleted(Lines lines) {
			if (managerListener != null) {
				managerListener.onCodeLineResetCompleted(lines);
			}			
		}		

		@Override
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
			
			if (tray == null && block != null) { // block moved
				if (row - trow > 0) { // down
					executor.setRTVariant(0, block.value);
					executor.setRTVariant(1, 1);
				} else {
					executor.clearRTVariant(0);				
					executor.setRTVariant(1, 0);					
				}
			}
			
			if (managerListener != null) {
				managerListener.onBoxMoved(tray, block, col, row, tcol, trow);
			}
		}

		@Override
		public void onBoxMoveEmpty() {
			if (managerListener != null) {
				managerListener.onBoxMoveEmpty();
			}
		}
		
		@Override
		public void onBoxMoveException(int error) {
			Utils.log(Tag, "onBoxMoveException : " + error);
			executor.stop(PlayExecutor.StopReason.EXCEPTION);
//			onExecuteEnd(false);
		}

	};

	//
	
	public PlayManager(final PlayScreen screen) {
		this.screen = screen;
		this.dbAccess = this.screen.getGame().getDBAccess();
		
		this.box = new Box(internalListener);
		this.codeLines = new Code.Lines(internalListener);
		this.executor = new PlayExecutor(internalListener);		
	}

	@Override
	public void dispose() {
		executor.dispose();
	}	
	
	public boolean loadScript(final int scriptId) {
		String str = dbAccess.loadScript(scriptId);
		if (str == null) {
			return false;
		}
		script = new Script(scriptId);
		if (!script.loadString(str)) {
			return false;
		}
		
		str = dbAccess.loadSolution(scriptId);
		if (str != null) {
			cmdSet = CommandSet.loadFromString(str);
		}
		
		init();
		
		return true;
	}

	public void setEventListener(final PlayScreenListener.ManagerEventListener listener) {
		this.managerListener = listener;
	}

	public final PlayScreenListener.RendererEventListener getRendererEventListener() {
		// TODO Auto-generated method stub
		return rendererListener;
	}
	
	private void init() {
		box.loadScript(script);
		codeLines.loadCmdSet(cmdSet);
	}

	protected void onCmdRun(final Cmd.State state) {
		if (state == Cmd.State.NONE) {
			final CommandSet cmdset = codeLines.makeCommandSet();
			dbAccess.saveSolution(script.getId(), cmdset.saveToString());
			executor.execute(cmdset);
		} else {
			box.reload(script);
			executor.reset();
		}
	}

	protected void onCmdClear(final Cmd.State state) {
		box.reload(script);
		executor.reset();
		
		dbAccess.clearSolution(script.getId());
		codeLines.reset();
	}	

	protected void onCmdNext(final Cmd.State state) {
		screen.setNextScreen();
	}
	
	protected void onCmdClose(State state) {
		screen.returnMenuScreen();
	}
	
	protected void onExecuteSucc() {
		// TODO Auto-generated method stub
		if (managerListener != null) {
			managerListener.onExecuteSucc();
		}
	}

	protected void onExecuteReset() {
		// TODO Auto-generated method stub
		
	}

	protected void onExecuteFinished() {
		if (managerListener != null) {
			managerListener.onExecuteFinished();
		}
	}

	protected void onExecuteException() {
		if (managerListener != null) {
			managerListener.onExecuteFail();
		}
		
	}
	
}
