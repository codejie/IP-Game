package jie.android.ip.screen;

import aurelienribon.tweenengine.TweenManager;
import jie.android.ip.Resources;
import jie.android.ip.screen.actor.BaseGroup;
import jie.android.ip.screen.console.ConsoleGroup;

public class BoxRenderConfig {

	private Resources resources;
	
	private BaseGroup sourceGroup;
	private BaseGroup targetGroup;
	private BaseGroup consoleGroup;
	
	private TweenManager tweenManager;
	
	private float executeDelay = 0.1f;
	private float renderDelay = 0.1f;
	
	private BoxScreenEventListener screenListener; 

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

	public BoxScreenEventListener getScreenListener() {
		return screenListener;
	}

	public void setScreenListener(BoxScreenEventListener screenListener) {
		this.screenListener = screenListener;
	}

}
