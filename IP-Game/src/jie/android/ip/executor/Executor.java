package jie.android.ip.executor;

import java.util.HashMap;
import java.util.HashSet;
import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.utils.Utils;

public class Executor extends BaseExecutor {

	private static final String Tag = Executor.class.getSimpleName();
	
	public static void main(String[] args) {
		System.out.println("helloworld");
//		test();
	}
	
	private class BreakData {
		private int func;
		private int index;
		
		public BreakData(int func, int index) {
			this.func = func;
			this.index = index;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			if (o == this) {
				return true;
			}
			if (!(o instanceof BreakData)) {
				return false;
			}
			
			return (this.func == ((BreakData)o).func) && (this.index == ((BreakData)o).index);
		}

		@Override
		public int hashCode() {
			return this.func * 37 + index; 
		}
	}
	
	private class BreakDataSet extends HashSet<BreakData> {
		
		private static final long serialVersionUID = 1L;
		
		private boolean enabled = false;
		private Object lock = new Object();
		
		public boolean enabled() {
			synchronized(this) {
				return enabled;
			}
		}
		public void enabled(boolean val) {
			synchronized(this) {
				enabled = val;
			}
		}
		
		public boolean isBreak(final InnerCommand cmd) {
			synchronized(this) {
				if (!enabled) {
					return false;
				}
				return contains(new BreakData(cmd.func, cmd.index));
			}
		}

		public void setData(int func, int index) {
			synchronized(this) {
				this.add(new BreakData(func, index));
			}
		}

		public void clear() {
			synchronized(this) {
				clear();
			}
		}
		
		public void pause() {
			synchronized(lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void stepOver() {
			synchronized(lock) {
				lock.notify();
			}
		}		
	}
	
	private class CoreExecutor implements Runnable {

		private boolean isRunning = false;
		
		private CommandSet cmdSet;
		private BreakDataSet breakSet;
		private HashMap<Integer, Integer> variantSet = new HashMap<Integer, Integer>();
		
		private OnCommandListener cmdListener;
		
		private int delay = 100;
		private boolean stopRun = false;
		private boolean isOneStep = false;
		private Object stepLock = new Object();
		
		private CommandStack cmdStack;
		
		public CoreExecutor(final CommandSet cmdSet, OnCommandListener cmdListener) {
			this.cmdSet = cmdSet;
			this.isOneStep = false;
			this.cmdListener = cmdListener;
		}

		public CoreExecutor(final CommandSet cmdSet, boolean isOneStep, OnCommandListener cmdListener) {
			this.cmdSet = cmdSet;
			this.isOneStep = isOneStep;
			this.cmdListener = cmdListener;
		}		

		public boolean isRunning() {
			return isRunning;
		}
		
		public void setDelay(int delay) {
			this.delay = delay;
		}
		
		public void enableOneStep(boolean enabled) {
			isOneStep = enabled;
		}
		
		public void stepOver() {
			setStepOver();
			if (breakData != null && breakData.enabled()) {
				breakData.stepOver();				
			}			
		}
		
		public void stepPause() {
			setStepPause();
		}
		
		private void setStepOver() {
			synchronized(stepLock) {
				if (isOneStep) {
					stepLock.notify();
				}
			}
		}
	
		private void setStepPause() {
			synchronized(stepLock) {
				try {
					stepLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void stop() {
			stopRun = true;
		}
		
		public void setBreakDataSet(BreakDataSet breakSet) {
			this.breakSet = breakSet;
		}
		
		public void setVariant(int variant, int value) {
			variantSet.put(Integer.valueOf(variant), Integer.valueOf(value));
		}
		
		private Integer getVariant(int variant) {
			return variantSet.get(Integer.valueOf(variant));			
		}
		
		public void clearVariantSet() {
			variantSet.clear();
		}
		
		public void clearVariant(int variant) {
			variantSet.remove(Integer.valueOf(variant));
		}
		
		private void preExecute() {
			
			stopRun = false;			
			isRunning = true;
			
			if (cmdListener != null) {
				cmdListener.onStart();
			}			
		}
		
		private void postExecute() {
			isRunning = false;
			if (cmdListener != null) {
				cmdListener.onEnd(!stopRun);
			}
		}
		
		private void execute() {
			cmdStack = new CommandStack();
			cmdStack.loadCommand(cmdSet, 0);
			
			while (!cmdStack.isEmpty() && !stopRun) {
				
				InnerCommand icmd = cmdStack.pop();
				CommandSet.Command cmd = icmd.cmd;
				
				Utils.log(Tag, icmd.toString());
				if (breakSet != null && breakSet.isBreak(icmd)) {
					Utils.log(Tag, "breakpoint : " + icmd.toString());
					if (cmdListener != null) {
						cmdListener.onBreakPoint(icmd.func, icmd.index, icmd.cmd.getType().getTitle(), icmd.cmd.getParam(0), icmd.cmd.getParam(1));
					}

					breakSet.pause();
				}
				
				if (cmd.getType() == CommandType.ACT) {
					if (cmdListener != null) {
						cmdListener.onAct(icmd.func, icmd.index, cmd.getParam(0), cmd.getParam(1));
					}

				} else if (cmd.getType() == CommandType.CHECK) {
					Integer variant = (Integer) cmd.getParam(1);
					if (variant != null) {
						variant = getVariant(variant.intValue());
						if (variant == null || !cmd.getParam(0).equals(variant)) {
							if (!cmdStack.empty()) {
								cmdStack.pop();
							}
						}
					} else {
						if (!cmdStack.empty()) {
							cmdStack.pop();
						}
					}
					
					if (cmdListener != null) {
						cmdListener.onCheck(icmd.func, icmd.index, cmd.getParam(0), variant);
					}					
				} else if (cmd.getType() == CommandType.CALL) {
					int func = cmd.getParamAsInt(0, -1);
					if (func != -1) {
						cmdStack.loadCommand(cmdSet, func);
					}
					if (cmdListener != null) {
						cmdListener.onCall(icmd.func, icmd.index, cmd.getParam(0), (func != -1));
					}
				} else {
					Utils.log(Tag, "Unknown command - " + cmd.getType().getTitle());
					break;
				}

				if (isOneStep) {
					setStepPause();
				}
//				
//				 if (stopRun) {
//					 break;
//				 }
				
//				if (delay != -1) {
//					try {
//						Thread.sleep(delay);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//						break;
//					}
//				}				
			}			
		}
		
		@Override
		public void run() {
			preExecute();
			execute();
			postExecute();			
		}
	}
	
	private CoreExecutor executor;
	private int execDelay = 500;
	private boolean isOneStep = false;
	private BreakDataSet breakData = new BreakDataSet();	
	
	@Override
	public boolean start(final CommandSet cmdset, OnCommandListener listener) {
		
		if (executor != null) {
			if (executor.isRunning()) {
				return false;
			}
		}
		
		executor = new CoreExecutor(cmdset, isOneStep, listener);
		
		executor.setBreakDataSet(breakData);
		executor.setDelay(execDelay);
		executor.setVariant(1, 0);
		Thread t = new Thread(executor);
		t.start();
		
		return true;

	}

	public void enableBreakData(boolean enabled) {
		breakData.enabled(enabled);
	}
	
	public void setBreakData(int func, int index) {
		breakData.setData(func, index);
	}
	
	public void clearBreakData() {
		breakData.clear();
	}
	
	public boolean isBreakData(final InnerCommand cmd) {
		return breakData.contains(new BreakData(cmd.func, cmd.index));
	}
	
	@Override
	public void stop() {
		if (executor != null && executor.isRunning()) {
			executor.stepOver();
			executor.stop();			
		}
	}

	public void stepOver() {
		if (executor != null && executor.isRunning()) {
			executor.stepOver();
		}
	}
	
	public void stepPause() {
		if (executor != null && executor.isRunning()) {
			executor.stepPause();
		}		
	}
	
	@Override
	public void setDelay(int delay) {
		execDelay = delay;
	}

	public void enableOneStep(boolean enabled) {
		isOneStep = enabled;
		if (executor != null && executor.isRunning()) {
			executor.enableOneStep(enabled);
		}
	}
	
	public void setRTVariant(int variant, int value) {
		if (executor != null) {
			executor.setVariant(variant, value);
		}
	}
	
	public void clearRTVariant(int variant) {
		if (executor != null) {
			executor.clearVariant(variant);
		}
	}
	
	public boolean isRunning() {
		return (executor != null && executor.isRunning());
	}
	
	
//
//	private static void test() {
//		CommandSet cmdset = new CommandSet();
//		
//		CommandSet.CommandQueue cmdarray = CommandSet.makeCommandQueue();
//		
//		CommandSet.Command cmd = CommandSet.makeCommand(CommandType.ACT, Integer.valueOf(ActType.MOVE_LEFT.getId()), Integer.valueOf(1));		
//		cmdarray.push(cmd);
//		
//		cmd = CommandSet.makeCommand(CommandType.CHECK, Integer.valueOf(2), Integer.valueOf(1));
//		cmdarray.push(cmd);
//		
//		cmd = CommandSet.makeCommand(CommandType.ACT, Integer.valueOf(ActType.MOVE_RIGHT.getId()), Integer.valueOf(1));		
//		cmdarray.push(cmd);
//		
//		cmd = CommandSet.makeCommand(CommandType.CALL, 1);		
//		cmdarray.push(cmd);
//		
//		cmdset.put(0, cmdarray);
//		
//		cmdarray = CommandSet.makeCommandQueue();
//		cmd = CommandSet.makeCommand(CommandType.ACT, Integer.valueOf(ActType.ACTION.getId()));		
//		cmdarray.push(cmd);
//		
////		cmd = CommandSet.makeCommand(CommandSet.CommandType.CALL, "main");		
////		cmdarray.push(cmd);
//		
//		
//		cmdset.put(1, cmdarray);
//
//		Executor executor = new Executor();
//		//executor.setBreakData(0, 1);
//		//executor.enableBreakData(true);
//		executor.setDelay(100);
//		executor.enableOneStep(true);
//		
//		executor.start(cmdset, null);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		executor.stepOver();
//		executor.stop();
//		
//		cmdset.saveToFile(".\\doc\\test.xml");
//		
//	}

//	private static void test2() {
//		CommandSet cmdset = Analyser.makeCommandSet(".\\doc\\cmd_test.xml");

//		Executer executer = new Executer();
//		executer.loadCommandSet(cmdset);
//		executer.start();		
//	}

}