package jie.android.ip.group;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.Resources;

public class CodeGroup extends BaseGroup {

	private final Resources resources;
	
	private BaseGroup groupLines;
	private BaseGroup groupPanel;
	
	public CodeGroup(final Resources resources) {
		this.resources = resources;
		
		initGroups();
	}

	private void initGroups() {
		groupLines = new BaseGroup();
		groupLines.setBounds(0, 0, ScreenConfig.WIDTH * 0.7f, ScreenConfig.HEIGHT);		
		
		groupPanel = new BaseGroup();
		groupPanel.setBounds(ScreenConfig.WIDTH * 0.7f, 0, ScreenConfig.WIDTH * 0.3f, ScreenConfig.HEIGHT);
		
		this.addActor(groupLines);
		this.addActor(groupPanel);
	}
	
}
