package jie.android.ip.common.actor;

import aurelienribon.tweenengine.TweenAccessor;

public class ButtonActorAccessor implements TweenAccessor<ButtonActor> {

	public static final int POSITION_X = 0;
	
	@Override
	public int getValues(ButtonActor target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case POSITION_X:
			returnValues[0] = target.getX();
			return 1;
		default:
			return -1;
		}
	}

	@Override
	public void setValues(ButtonActor target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case POSITION_X:
			target.setX(newValues[0]);
			break;
		default:
			break;
		}
	}

}
