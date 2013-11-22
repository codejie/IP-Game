package jie.android.ip.executor;

import java.util.HashSet;
import java.util.Stack;

import jie.android.ip.executor.CommandConsts.ActType;
import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.utils.Utils;

public class Executor {

	public static void main(String[] args) {
		System.out.println("helloworld");
		test();
	}
	
	private class InnerCommand {
		public CommandSet.Command cmd;
		public int func;
		public int index;
		
		public InnerCommand(CommandSet.Command cmd, int func, int index) {
			this.cmd = cmd;
			this.func = func;
			this.index = index;
		}

		@Override
		public String toString() {
			return String.format("[%d:%d] cmd = %s (%s,%s)", func, index, cmd.getType().getTitle(), (cmd.getParam(0) != null ? cmd.getParamAsString(0) : "null"), (cmd.getParam(1) != null ? cmd.getParamAsString(1) : "null")); 
		}		
	}
	
	private class CommandStack extends Stack<InnerCommand> {

		private static final long serialVersionUID = 1L;

		public void loadCommand(final CommandSet cmdset, int func) {
			CommandSet.CommandQueue cmds = cmdset.get(func);
			if (cmds != null) {
				int idx = cmds.size();
				for (final CommandSet.Command cmd : cmds) {				
					push(new InnerCommand(cmd, func, -- idx));
				}
			}			
		}
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
		
		public void setStepOver() {
			synchronized(stepLock) {
				if (isOneStep) {
					stepLock.notify();
				}
			}
		}
		
		private void stepPause() {
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
//			synchronized(this) {
				this.stopRun = true;
//				if (breakSet != null && breakSet.enabled()) {
//					breakSet.stepOver();
//				}
//				if (isOneStep) {
//					stepLock.notify();
//				}
//			}
		}
		
		public void setBreakDataSet(BreakDataSet breakSet) {
			this.breakSet = breakSet;
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
				cmdListener.onEnd();
			}
		}
		
		private synchronized void execute() {
			cmdStack = new CommandStack();
			cmdStack.loadCommand(cmdSet, 0);
			
			while (!cmdStack.isEmpty()) {
				
				InnerCommand icmd = cmdStack.pop();
				CommandSet.Command cmd = icmd.cmd;
				
				Utils.logDebug(icmd.toString());
				if (breakSet != null && breakSet.isBreak(icmd)) {
					Utils.logDebug("breakpoint : " + icmd.toString());
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
					if (!cmd.getParam(0).equals(cmd.getParam(1))) {
						cmdStack.pop();
						if (cmdListener != null) {
							cmdListener.onCheck(icmd.func, icmd.index, cmd.getParam(0), cmd.getParam(1));
						}					
					}
				} else if (cmd.getType() == CommandType.CALL) {
					int func = cmd.getParamAsInt(0, -1);
					if (func != -1) {
						cmdStack.loadCommand(cmdSet, func);
						if (cmdListener != null) {
							cmdListener.onCall(icmd.func, icmd.index);
						}
					}
				} else {
					Utils.logDebug("Unknown command - " + cmd.getType().getTitle());
					break;
				}

				if (isOneStep) {
					stepPause();
				}
				
				if (stopRun) {
					break;
				}
				
				if (delay != -1) {
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}				
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
	
	public boolean start(final CommandSet cmdset, OnCommandListener listener) {
		
		if (executor != null) {
			if (executor.isRunning()) {
				return false;
			}
		}
		
		executor = new CoreExecutor(cmdset, isOneStep, listener);
		
		executor.setBreakDataSet(breakData);
		executor.setDelay(execDelay);
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
	
	public void stop() {
		if (executor != null && executor.isRunning()) {
			executor.stop();
			executor.stepOver();
		}
	}
	
	
	public void stepOver() {
		if (executor != null && executor.isRunning()) {
			executor.stepOver();
//			executor.setStepOver();
//			if (breakData != null && breakData.enabled()) {
//				breakData.stepOver();				
//			}
		}
	}
	
	public void setDelay(int delay) {
		execDelay = delay;
	}

	public void enableOneStep(boolean enabled) {
		isOneStep = enabled;
		if (executor != null && executor.isRunning()) {
			executor.enableOneStep(enabled);
		}
	}
//
	private static void test() {
		CommandSet cmdset = new CommandSet();
		
		CommandSet.CommandQueue cmdarray = CommandSet.makeCommandQueue();
		
		CommandSet.Command cmd = CommandSet.makeCommand(CommandType.ACT, Integer.valueOf(ActType.MOVE_LEFT.getId()), Integer.valueOf(1));		
		cmdarray.push(cmd);
		
		cmd = CommandSet.makeCommand(CommandType.CHECK, Integer.valueOf(2), Integer.valueOf(1));
		cmdarray.push(cmd);
		
		cmd = CommandSet.makeCommand(CommandType.ACT, Integer.valueOf(ActType.MOVE_RIGHT.getId()), Integer.valueOf(1));		
		cmdarray.push(cmd);
		
		cmd = CommandSet.makeCommand(CommandType.CALL, 1);		
		cmdarray.push(cmd);
		
		cmdset.put(0, cmdarray);
		
		cmdarray = CommandSet.makeCommandQueue();
		cmd = CommandSet.makeCommand(CommandType.ACT, Integer.valueOf(ActType.ACTION.getId()));		
		cmdarray.push(cmd);
		
//		cmd = CommandSet.makeCommand(CommandSet.CommandType.CALL, "main");		
//		cmdarray.push(cmd);
		
		
		cmdset.put(1, cmdarray);

		Executor executor = new Executor();
		//executor.setBreakData(0, 1);
		//executor.enableBreakData(true);
		executor.setDelay(100);
		executor.enableOneStep(true);
		
		executor.start(cmdset, null);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.stepOver();
		executor.stop();
		
		cmdset.save(".\\doc\\test.xml");
		
	}

	private static void test2() {
		CommandSet cmdset = Analyser.makeCommandSet(".\\doc\\cmd_test.xml");

//		Executer executer = new Executer();
//		executer.loadCommandSet(cmdset);
//		executer.start();		
	}

}