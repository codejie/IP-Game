package jie.android.ip.screen.box;

import aurelienribon.tweenengine.TweenManager;
import jie.android.ip.Resources;
import jie.android.ip.common.actor.BaseGroup;

public class BoxRenderAdapter {

	private Resources resources;
	
	private BaseGroup sourceGroup;
	private BaseGroup targetGroup;
	private BaseGroup consoleGroup;
	
	private TweenManager tweenManager;
	
	private float executeDelay = 0.1f;
	private float renderDelay = 0.05f;
	
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

	public BaseGroup getConsoleGroup() {
		return this.consoleGroup;
	}
	
	public void setConsoleGroup(BaseGroup consoleGroup) {
		this.consoleGroup = consoleGroup;
	}
	
	public TweenManager getTweenManager() {
		return tweenManager;
	}

	public void setTweenManager(TweenManager tweenManager) {
		this.tweenManager = tweenManager;
	}

	public float getExecuteDelay() {
		return executeDelay;
	}

	public void setExecuteDelay(float executeDelay) {
		this.executeDelay = executeDelay;
	}

	public float getRenderDelay() {
		return renderDelay;
	}

	public void setRenderDelay(float renderDelay) {
		this.renderDelay = renderDelay;
	}

}
