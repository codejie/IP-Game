package jie.android.ip.screen.box;

import jie.android.ip.executor.Analyser;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Executor;
import jie.android.ip.executor.OnCommandListener;
import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.script.Script;
import jie.android.ip.utils.Utils;

public class BoxExecutor {

	private final BoxRenderAdapter adapter;
	
	private BoxManager manager;
	private Executor executor;
	
	private OnCommandListener cmdListener = new OnCommandListener() {

		@Override
		public void onStart() {
		}

		@Override
		public void onEnd(boolean broken) {
			if (!broken) {
				onBoxCompleted(false);
			}
		}

		@Override
		public void onCall(int func, int index, Object funcIndex, boolean found) {
		}

		@Override
		public void onAct(int func, int index, Object actType, Object step) {
			int action = ((Integer)actType); 
			if (action == ActType.ACTION.getId()) {
				manager.doAction();
			} else if (action == ActType.MOVE_LEFT.getId()){
				manager.doMove(false);
			} else if (action == ActType.MOVE_RIGHT.getId()){
				manager.doMove(true);
			}			
		}

		@Override
		public void onCheck(int func, int index, Object left, Object right) {
		}

		@Override
		public void onBreakPoint(int func, int index, String cmd, Object param1, Object param2) {
		}		
	};
	
	private OnBoxEventListener boxListener = new OnBoxEventListener() {

		@Override
		public void onTrayStatusChanged(boolean attached) {
		}

		@Override
		public void onBlockMoveStart(boolean down) {
		}

		@Override
		public void onBlockMoveEnd(boolean down, boolean completed) {
			if (!completed) {
				executor.stepOver();
			} else {
				executor.stop();
				onBoxCompleted(true);
			}
		}

		@Override
		public void onTrayMoveStart(boolean right) {
		}

		@Override
		public void onTrayMoveEnd(boolean right, boolean succ, boolean completed) {			
			if (!completed && succ) {
				executor.stepOver();
			} else {
				executor.stop();
				onBoxCompleted(succ);				
			}
		}
	};
	
	private final BoxScreenEventListener screenEventListener;
	
	public BoxExecutor(final BoxRenderAdapter adapter, final BoxScreenEventListener listener) {
		this.adapter = adapter;
		this.screenEventListener = listener;
		
		init();
		
	}

	private void init() {
		manager = new BoxManager(adapter, boxListener);
		
		executor = new Executor();
		executor.setDelay((int)adapter.getExecuteDelay() * 1000);
		executor.enableOneStep(true);
	}

	public boolean loadScript(final String script) {
		Script s = new Script();
		if (!s.load(script)) {
			return false;
		}
		return manager.loadScript(s);
	}
	
	public boolean execute(final String cmdfile) {
		final CommandSet cmdset = Analyser.makeCommandSet(".\\doc\\test.xml");
		executor.start(cmdset, cmdListener);
		
		return true;
	}

	public void reset() {
		executor.stop();
		manager.resetSource();
	}

	protected void onBoxCompleted(boolean succ) {
		if (screenEventListener != null) {
			screenEventListener.onScriptCompleted(succ);
		}
	}
	
}
