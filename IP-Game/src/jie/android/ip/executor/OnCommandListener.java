package jie.android.ip.executor;

public interface OnCommandListener {
	public void onStart();
	public void onEnd();
	
	public void onCall(int func, int index);	
	public void onAct(int func, int index, Object param1, Object param2);
	public void onCheck(int func, int index, Object param1, Object param2);
	
	public void onBreakPoint(int func, int index, final String cmd, Object param1, Object param2);
}
