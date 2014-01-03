package jie.android.ip.screen.play;

import com.badlogic.gdx.utils.Disposable;

import jie.android.ip.database.DBAccess;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Script;
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
				executor.stop();
				onExecuteEnd(true);
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
			} else {
				
			}
			
		}

	};
	
	private final PlayScreenListener.ManagerInternalEventListener internalListener = new PlayScreenListener.ManagerInternalEventListener() {
		
		@Override
		public void onExecuteMove(boolean right) {
			box.tryMoveTray(right);
		}
		
		@Override
		public void onExecuteCompleted(boolean succ) {
			Utils.log(Tag, "executeCompleted : " + succ);
			onExecuteEnd(succ);
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
		public void onBoxMoved(final Box.Tray tray, final Box.Block block, int col, int row, int tcol, int trow) {
			if (managerListener != null) {
				managerListener.onBoxMoved(tray, block, col, row, tcol, trow);
			}
		}

		@Override
		public void onBoxMoveException(int error) {
			Utils.log(Tag, "onBoxMoveException : " + false);			
		}

		@Override
		public void onExecuteBroken() {
			Utils.log(Tag, "onExecuteBroken : " + false);			
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
		script = new Script();
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
			dbAccess.saveSolution(1, cmdset.saveToString());
			executor.execute(cmdset);
		} else {
			box.reload(script);
			executor.reset();
		}
	}

	protected void onExecuteEnd(boolean succ) {
		Utils.log(Tag, "execute end : " + succ);		
	}	
}
