package jie.android.ip.executor;

import java.util.ListIterator;
import java.util.Stack;

import jie.android.ip.executor.CommandConsts.CommandType;
import jie.android.ip.executor.CommandConsts.EmptyType;
import jie.android.ip.executor.CommandSet.Command;


public abstract class BaseExecutor {

	public class InnerCommand {
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
	
	public class CommandStack extends Stack<InnerCommand> {

		private static final long serialVersionUID = 1L;

		public void loadCommand(final CommandSet cmdset, int func) {
			CommandSet.CommandQueue cmds = cmdset.get(func);
			if (cmds != null) {
				CommandSet.CommandQueue tmp = CommandSet.makeCommandQueue();
				for (final Command cmd : cmds) {
					if (cmd.getType() == CommandType.EMPTY) {
						int type = cmd.getParamAsInt(0, -1);
						if (type == EmptyType.ACT.getId()) {
							break;
						} else {
							continue;
						}
					}
					tmp.add(cmd);
				}
				
				int idx = tmp.size();
				for (final ListIterator<Command> iterator = tmp.listIterator(tmp.size()); iterator.hasPrevious(); -- idx) {
					super.push(new InnerCommand(iterator.previous(), func, idx));
				}				
			}			
		}
	}
	
	abstract public boolean start(final CommandSet cmdset, OnCommandListener listener);
	abstract public void stop();
	abstract public void setDelay(int delay);
	
}
