package jie.android.ip.executor;

import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.utils.Utils;

public class SyncExecutor  extends BaseExecutor {

	private static final String Tag = SyncExecutor.class.getSimpleName();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test();
	}
	
	private int execDelay = 100;
	private CommandStack cmdStack;
	private boolean stopRun = false;
	
	@Override
	public boolean start(final CommandSet cmdset, OnCommandListener listener) {
		
		stopRun = false;
		
		cmdStack = new CommandStack();
		cmdStack.loadCommand(cmdset, 0);
		
		InnerCommand icmd = null;
		CommandSet.Command cmd = null;
		while (!cmdStack.isEmpty()) {
			icmd = cmdStack.pop();
			Utils.log(Tag, icmd.toString());

			cmd = icmd.cmd;
			if (cmd.getType() == CommandType.ACT) {
				if (listener != null) {
					listener.onAct(icmd.func, icmd.index, cmd.getParam(0), cmd.getParam(1));
				}
			} else if (cmd.getType() == CommandType.CHECK) {
				if (!cmd.getParam(0).equals(cmd.getParam(1))) {
					cmdStack.pop();
				}
				if (listener != null) {
					listener.onCheck(icmd.func, icmd.index, cmd.getParam(0), cmd.getParam(1));
				}
			} else if (cmd.getType() == CommandType.CALL) {
				int func = cmd.getParamAsInt(0, -1);
				if (func != -1) {
					cmdStack.loadCommand(cmdset, func);
				}
				if (listener != null) {
					listener.onCall(icmd.func, icmd.index, cmd.getParam(0), (func != -1));
				}
			} else {
				Utils.log(Tag, "Unknown command - " + cmd.getType().getTitle());
				return false;
			}
			
			if (stopRun) {
				return false;
			}
			
			if (execDelay != -1) {
				try {
					Thread.sleep(execDelay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
		return true;
	}
	
	@Override
	public void stop() {
		stopRun = true;
	}

	@Override
	public void setDelay(int delay) {
		execDelay = delay;		
	}
	
	private static void test() {
		CommandSet cmdset = Analyser.makeCommandSet(".\\doc\\test.xml");
		SyncExecutor executor = new SyncExecutor();
		executor.start(cmdset, null);
	}
	
}
