package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.Color;

import aurelienribon.tweenengine.TweenAccessor;

public class LabelActorAccessor implements TweenAccessor<LabelActor> {
	
	public static final int POSITION_X = 1;
	public static final int SCALE_XY = 4;
	
	@Override
	public int getValues(LabelActor target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_X:
			returnValues[0] = target.getX();
			return 1;		
		case SCALE_XY:
			returnValues[0] = target.getScaleX();
			returnValues[1] = target.getScaleY();
			return 2;
		default:
			return -1;
		}
	}

	@Override
	public void setValues(LabelActor target, int tweenType, float[] newValues) {
        switch (tweenType) {
        case POSITION_X: 
        	target.setX(newValues[0]);
        	break;        
		case SCALE_XY:
			target.setScale(newValues[0], newValues[1]);
			break;
        default:
        	break;
        }
	}

}
