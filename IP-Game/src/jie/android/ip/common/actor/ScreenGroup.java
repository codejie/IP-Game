package jie.android.ip.common.actor;

import jie.android.ip.Resources;
import jie.android.ip.screen.BaseScreen;

public class ScreenGroup extends BaseGroup {

	protected final BaseScreen screen;
	protected final Resources resources;
	
	public ScreenGroup(final BaseScreen screen) {
		this.screen = screen;
		this.resources = screen.getGame().getResources();
	}
	
	@Override
	protected void initStage() {
	}

}
