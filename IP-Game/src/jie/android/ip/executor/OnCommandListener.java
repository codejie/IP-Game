package jie.android.ip.executor;

public interface OnCommandListener {
	public void onStart();
	public void onEnd(boolean broken);
	public void onStackOverflow();
	
	public void onCall(int func, int index, Object funcIndex, boolean found);	
	public void onAct(int func, int index, Object actType, Object step);
	public void onCheck(int func, int index, Object left, Object right);
	
	public void onBreakPoint(int func, int index, final String cmd, Object param1, Object param2);
}
