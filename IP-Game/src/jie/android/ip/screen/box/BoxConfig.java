package jie.android.ip.screen.box;

import aurelienribon.tweenengine.TweenManager;
import jie.android.ip.Resources;
import jie.android.ip.group.BaseGroup;

public class BoxConfig {

	private int maxRow = 7;
	private int maxCol = 6;
	
//	private float scale = 1.0f;
	
	private int colSpace = 20;
	private int rowSpace = 4;
	private int colBase = 20;
	private int rowBase = 20;
	
	private int blockWidth = 64;
	private int blockHeight = 64;
	private int trayWidth = 64;
	private int trayHeight = 64;	

	private Resources resources;
	
	private BaseGroup sourceGroup;
	private BaseGroup targetGroup;
	
	private TweenManager tweenManager;

	public int getColBase() {
		return colBase;
	}

	public void setColBase(int colBase) {
		this.colBase = colBase;
	}

	public int getRowBase() {
		return rowBase;
	}

	public void setRowBase(int rowBase) {
		this.rowBase = rowBase;
	}

	public int getBlockWidth() {
		return blockWidth;
	}

	public void setBlockWidth(int blockWidth) {
		this.blockWidth = blockWidth;
	}

	public int getBlockHeight() {
		return blockHeight;
	}

	public void setBlockHeight(int blockHeight) {
		this.blockHeight = blockHeight;
	}

	public int getTrayWidth() {
		return trayWidth;
	}

	public void setTrayWidth(int trayWidth) {
		this.trayWidth = trayWidth;
	}

	public int getTrayHeight() {
		return trayHeight;
	}

	public void setTrayHeight(int trayHeight) {
		this.trayHeight = trayHeight;
	}	
	
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
	
	public int getRowSpace() {
		return rowSpace;
	}

	public void setRowSpace(int rowSpace) {
		this.rowSpace = rowSpace;
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
