package jie.android.ip.screen.play;

import java.util.LinkedList;

import com.badlogic.gdx.utils.Disposable;

import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Executor;
import jie.android.ip.executor.OnCommandListener;
import jie.android.ip.executor.CommandConsts.ActType;

public class PlayExecutor implements Disposable {

	private static final String Tag = PlayExecutor.class.getSimpleName();
	
	public enum StopReason {
		NONE, SUCC, RESET, FINISHED, BROKEN, EXCEPTION, OVERFLOW;
	}
	
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
		private long delay = 100;
		private Object callLock = new Object();
		private LinkedList<Data> dataQue = new LinkedList<Data>();
		
		private final PlayExecutor executor;
		
		public CallbackQueue(final PlayExecutor executor) {
			this.executor = executor;
		}

		public void putData(final EventType type, int func, int index, final Object param0, final Object param1) {
			dataQue.add(new Data(type, func, index, param0, param1));
			synchronized(callLock) {
				callLock.notify();
			}
		}
		
		private void processData(final Data data) {
//			//delay
//			try {
//				Thread.sleep(delay);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
			if (!executor.isRunning()) {
				return;
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
		
		public void setDelay(long delay) {
			this.delay = delay;
		}
		
		@Override
		public void run() {
			while (!stop) {
				if (dataQue.size() == 0 ) {
					synchronized(callLock) {
						try {
							callLock.wait();
							//delay
							Thread.sleep(delay);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				processData(dataQue.pop());
			}			
		}		
	}

	
	//
	
	private final Executor executor;
	private final CallbackQueue callbackQueue;
	private final PlayScreenListener.ManagerInternalEventListener internalListener;
	
	private StopReason stopReason = StopReason.NONE;
	
	private final OnCommandListener cmdListener = new OnCommandListener() {

		@Override
		public void onStart() {
		}

		@Override
		public void onEnd(boolean scriptFinished) {
			if (scriptFinished) {
				stopReason = StopReason.FINISHED;
			}
			onExecuteCompleted(stopReason);
		}

		@Override
		public void onStackOverflow() {
			stopReason = StopReason.OVERFLOW;
			onExecuteCompleted(stopReason);
		}	
		
		@Override
		public void onCall(int func, int index, Object funcIndex, boolean found) {
			onExecuteCall(func, index, funcIndex, new Boolean(found));
		}

		@Override
		public void onAct(int func, int index, Object actType, Object step) {
			onExecuteAct(func, index, actType, step);			
		}

		@Override
		public void onCheck(int func, int index, Object left, Object right) {
			onExecuteCheck(func, index, left, right);			
		}

		@Override
		public void onBreakPoint(int func, int index, String cmd, Object param1, Object param2) {
		}
	
	};
	
	
	public PlayExecutor(final PlayScreenListener.ManagerInternalEventListener listener) {
		
		this.internalListener = listener;
		
		executor = new Executor();		
		callbackQueue = new CallbackQueue(this);		
		
		executor.enableOneStep(true);
		
		new Thread(callbackQueue).start();
	}

	@Override
	public void dispose() {
		executor.stop();
		callbackQueue.stop();
	}
	
	public void execute(final CommandSet cmdset) {
		stopReason = StopReason.NONE;
		executor.start(cmdset, cmdListener);
	}
	
	public void setDelay(long delay) {
		callbackQueue.setDelay(delay);
	}

	public void next() {
		executor.stepOver();		
	}	

	public void reset() {
		stopReason = StopReason.RESET;
		executor.stop();		
	}
	
	public void stop(final StopReason reason) {
		stopReason = reason;
		executor.stop();		
	}
	
	public boolean isRunning() {
		return executor.isRunning();
	}
	
	public void setRTVariant(int variant, int value) {
		if (executor != null) {
			executor.setRTVariant(variant, value);
		}
	}
	
	public void clearRTVariant(int variant) {
		if (executor != null) {
			executor.clearRTVariant(variant);
		}
	}
	
	protected void onExecuteCompleted(final StopReason reason) {
		if (internalListener != null) {
			internalListener.onExecuteCompleted(reason);
		}
	}

//	protected void onExecuteBroken(boolean scriptSucc) {
//		Utils.log(Tag, "execute broken.");
//		if (internalListener != null) {
//			internalListener.onExecuteBroken(scriptSucc);
//		}		
//	}
	
	public void onEventCall(int func, int index, int funcIndex, boolean found) {
		next();
	}

	public void onEventCheck(int func, int index, int left, int right) {
		next();		
	}	
	
	public void onEventAct(int func, int index, int actType, int step) {
		if (internalListener != null) {
			if (actType == ActType.ACTION.getId()) {
				internalListener.onExecuteAction();
			} else if (actType == ActType.MOVE_LEFT.getId()){
				internalListener.onExecuteMove(false);
			} else if (actType == ActType.MOVE_RIGHT.getId()){
				internalListener.onExecuteMove(true);
			}
		} else { 
			executor.stepOver();
		}
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
