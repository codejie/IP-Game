package jie.android.ip.screen;

public interface BoxScreenEventListener {
	public void onConsoleButtonClick(int type, int state);
	
	public void onBoxMoveCompleted(boolean succ);
}
