package jie.android.ip.screen;

import jie.android.ip.IPGame;
import jie.android.ip.group.BaseGroup;

public class BoxScreen extends BaseScreen {

	private BaseGroup groupSource, groupTarget, groupConsole;
	
	public BoxScreen(IPGame game) {
		super(game);
		
		initBackgroup();
		initGroups();
		
		//initScript();
		//initExecutor();
	}

	private void initBackgroup() {

	}

	private void initGroups() {
		initBoxGroup(groupSource);
		initBoxGroup(groupTarget);
		initConsoleGroup(groupConsole);
	}

	private void initBoxGroup(BaseGroup group) {
		// TODO Auto-generated method stub
		
	}

	private void initConsoleGroup(BaseGroup group) {
		// TODO Auto-generated method stub
		
	}
	
	

}
