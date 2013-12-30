package jie.android.ip.screen.play;

import jie.android.ip.database.DBAccess;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Script;
import jie.android.ip.screen.play.PlayScreenListener.ManagerEventListener;

public class PlayManager {
	
	private final PlayScreen screen;
	private final DBAccess dbAccess;
	
	private Script script;
	private CommandSet cmdSet;
	
	private PlayScreenListener.ManagerEventListener managerListener;
	
	private Box box = new Box();
	private Code.Lines codeLines = new Code.Lines();

	private PlayScreenListener.RendererEventListener rendererListener = new PlayScreenListener.RendererEventListener() {

		@Override
		public void onCmdButtonClicked() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBoxMoveStart() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBoxkMoveEnd() {
			// TODO Auto-generated method stub
			
		}

	};

	//
	
	public PlayManager(final PlayScreen screen) {
		this.screen = screen;
		this.dbAccess = this.screen.getGame().getDBAccess();
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

	public void setEventListener(final ManagerEventListener listener) {
		this.managerListener = listener;
	}

	public final PlayScreenListener.RendererEventListener getRendererEventListener() {
		// TODO Auto-generated method stub
		return rendererListener;
	}
	
	private void init() {
		box.loadScript(script, managerListener);
		codeLines.loadCmdSet(cmdSet, managerListener);
	}

}
