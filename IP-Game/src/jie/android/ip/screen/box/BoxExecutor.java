package jie.android.ip.screen.box;

import java.util.LinkedList;
import java.util.Queue;

import jie.android.ip.executor.Analyser;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Executor;
import jie.android.ip.executor.OnCommandListener;
import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.script.Script;
import jie.android.ip.utils.Utils;

public class BoxExecutor {

	private static class CallbackQueue implements Runnable {

		private enum EventType {
			ACT, CHECK, CALL, BREAK;
		}
		
		private class Data {
			public EventType type;
			public int func;
			public int index;
			public Object param0;
			public Object param1;
			
			public Data(EventType type, int func, int index, final Object param0, final Object param1) {
				this.type = type;
				this.func = func;
				this.index = index;
				this.param0 = param0;
				this.param1 = param1;
			}
		}
		
		private boolean stop = false;
		private Object callLock = new Object();
		private LinkedList<Data> dataQue = new LinkedList<Data>();
		
		private BoxExecutor executor;
		
		public CallbackQueue(final BoxExecutor executor) {
			this.executor = executor;
		}

		public void putData(final EventType type, int func, int index, final Object param0, final Object param1) {
			dataQue.add(new Data(type, func, index, param0, param1));
			synchronized(callLock) {
				callLock.notify();
			}
		}
		
		private void processData(final Data data) {
			//delay
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			if (data.type == EventType.ACT) {				
				executor.onEventAct(data.func, data.index, ((Integer)data.param0).intValue(), ((Integer)data.param1).intValue());
			} else if (data.type == EventType.CHECK) {
				executor.onEventCheck(data.func, data.index, ((Integer)data.param0).intValue(), (data.param1 != null ? ((Integer)data.param1).intValue() : -1));
			} else if (data.type == EventType.CALL) {
				executor.onEventCall(data.func, data.index, ((Integer)data.param0).intValue(), ((Boolean)data.param1).booleanValue());				
			} else if (data.type == EventType.BREAK) {
				
			} else {
				
			}
		}
		
		public void stop() {
			stop = true;
			callLock.notify();			
		}
		
		@Override
		public void run() {
			while (!stop) {
				if (dataQue.size() == 0 ) {
					synchronized(callLock) {
						try {
							callLock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				processData(dataQue.pop());				
			}			
		}
		
	}
	
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
			onExecuteCall(func, index, funcIndex, new Boolean(found));
//			executor.stepOver();
		}

		@Override
		public void onAct(int func, int index, Object actType, Object step) {
			onExecuteAct(func, index, actType, step);
			
//			int action = ((Integer)actType); 
//			if (action == ActType.ACTION.getId()) {
//				manager.doAction();
//			} else if (action == ActType.MOVE_LEFT.getId()){
//				manager.doMove(false);
//			} else if (action == ActType.MOVE_RIGHT.getId()){
//				manager.doMove(true);
//			}			
		}

		@Override
		public void onCheck(int func, int index, Object left, Object right) {
			onExecuteCheck(func, index, left, right);
			
//			executor.stepOver();
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
		public void onBlockMoveEnd(boolean down, int value, boolean completed) {
			if (down) {
				executor.setRTVariant(0, value);
				executor.setRTVariant(1, 1);
			} else {
				executor.clearRTVariant(0);				
				executor.setRTVariant(1, 0);
			}
//			if (completed) {
//				executor.stop();
//				onBoxCompleted(true);				
//			}
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
//			if (completed || !succ) {
//				executor.stop();
//				onBoxCompleted(succ);				
//			}
			if (!completed && succ) {
				executor.stepOver();
			} else {
				executor.stop();
				onBoxCompleted(succ);				
			}
		}

		@Override
		public void onNoneBlockMove() {
			executor.stepOver();
		}
	};
	
	private CallbackQueue callbackQueue;
	
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
		
		callbackQueue  = new CallbackQueue(this);
		new Thread(callbackQueue).start();
	}

	public boolean loadScript(final String script) {
		Script s = new Script();
		if (!s.loadFile(script)) {
			return false;
		}
		return manager.loadScript(s);
	}
	
	public boolean execute(final String cmdfile) {
		final CommandSet cmdset = Analyser.makeCommandSet(".\\doc\\test.xml");
		executor.start(cmdset, cmdListener);
		
		return true;
	}

	public boolean execute(final CommandSet cmdSet) {
		executor.start(cmdSet, cmdListener);
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

	public void onEventCall(int func, int index, int funcIndex, boolean found) {
		executor.stepOver();
	}

	public void onEventCheck(int func, int index, int left, int right) {
		executor.stepOver();		
	}

	public void onEventAct(int func, int index, int actType, int step) {
		if (actType == ActType.ACTION.getId()) {
			manager.doAction();
		} else if (actType == ActType.MOVE_LEFT.getId()){
			manager.doMove(false);
		} else if (actType == ActType.MOVE_RIGHT.getId()){
			manager.doMove(true);
		}
		
		//executor.stepOver();
	}

	protected void onExecuteCheck(int func, int index, Object left, Object right) {
		callbackQueue.putData(CallbackQueue.EventType.CHECK, func, index, left, right);
	}

	protected void onExecuteAct(int func, int index, Object actType, Object step) {
		callbackQueue.putData(CallbackQueue.EventType.ACT, func, index, actType, step);
	}

	protected void onExecuteCall(int func, int index, Object funcIndex, Boolean found) {
		callbackQueue.putData(CallbackQueue.EventType.CALL, func, index, funcIndex, found);
	}	
}
