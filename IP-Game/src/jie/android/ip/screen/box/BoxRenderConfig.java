package jie.android.ip.screen.box;

import aurelienribon.tweenengine.TweenManager;
import jie.android.ip.Resources;
import jie.android.ip.group.BaseGroup;

public class BoxRenderConfig {

	private Resources resources;
	
	private BaseGroup sourceGroup;
	private BaseGroup targetGroup;
	
	private TweenManager tweenManager;

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	public BaseGroup getSourceGroup() {
		return sourceGroup;
	}

	public void setSourceGroup(BaseGroup sourceGroup) {
		this.sourceGroup = sourceGroup;
	}

	public BaseGroup getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(BaseGroup targetGroup) {
		this.targetGroup = targetGroup;
	}

	public TweenManager getTweenManager() {
		return tweenManager;
	}

	public void setTweenManager(TweenManager tweenManager) {
		this.tweenManager = tweenManager;
	}
	
}
