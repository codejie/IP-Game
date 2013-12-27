package jie.android.ip.screen.box;

public interface OnBoxEventListener {
	public void onTrayStatusChanged(boolean attached);
	public void onBlockMoveStart(boolean down);
	public void onBlockMoveEnd(boolean down, int value, boolean completed);
	public void onTrayMoveStart(boolean right);
	public void onTrayMoveEnd(boolean right, boolean succ, boolean completed);
	public void onNoneBlockMove();
	
	public void onZoomSourceEnd();
//	public void onBoxCompleted();
}
