package jie.android.ip.screen.box;

import aurelienribon.tweenengine.TweenManager;
import jie.android.ip.Resources;
import jie.android.ip.group.BaseGroup;

public class BoxConfig {

	private int maxRow = 7;
	private int maxCol = 6;
	
	private int colSpace = 20;

	private Resources resources;
	
	private BaseGroup sourceGroup;
	private BaseGroup targetGroup;
	
	private TweenManager tweenManager;

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	public int getColSpace() {
		return colSpace;
	}

	public void setColSpace(int colSpace) {
		this.colSpace = colSpace;
	}

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
