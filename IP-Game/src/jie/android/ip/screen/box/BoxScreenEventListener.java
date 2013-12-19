package jie.android.ip.screen.box;

public interface BoxScreenEventListener {
	public void onConsoleButtonClick(int type, int state);
	
	public void onBoxMoveCompleted(boolean succ);
}
