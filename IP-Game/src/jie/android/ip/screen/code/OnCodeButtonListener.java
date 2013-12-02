package jie.android.ip.screen.code;

import jie.android.ip.screen.code.CodeManager.CodeType;

public interface OnCodeButtonListener {

	void onPanelButtonClick(CodeType code, int state);
	void onLineButonClick(int func, int pos, CodeType code, int state);
}
