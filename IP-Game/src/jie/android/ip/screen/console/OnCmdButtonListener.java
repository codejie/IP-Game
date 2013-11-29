package jie.android.ip.screen.console;

import jie.android.ip.screen.console.ConsoleManager.CmdType;

public interface OnCmdButtonListener {
	public void onClick(final CmdType type, int state);
}
