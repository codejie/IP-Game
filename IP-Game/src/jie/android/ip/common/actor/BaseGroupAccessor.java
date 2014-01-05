package jie.android.ip.common.actor;

import aurelienribon.tweenengine.TweenAccessor;

public class BaseGroupAccessor implements TweenAccessor<BaseGroup> {
	public static final int POSITION_X = 0;
	public static final int POSITION_Y = 1;
	public static final int POSITION_XY= 3; 
	public static final int SCALE_XY = 4;
	
	@Override
	public int getValues(BaseGroup target, int tweenType, float[] returnValues) {
		switch(tweenType) {
		case POSITION_X:
			returnValues[0] = target.getX();
			return 1;
		case POSITION_Y:
			returnValues[0] = target.getY();
			return 1;			
		case POSITION_XY:
			returnValues[0] = target.getX();
			returnValues[1] = target.getY();
			return 2;		
		case SCALE_XY:
			returnValues[0] = target.getScaleX();
			returnValues[1] = target.getScaleY();
			return 2;
		default:
			return -1;
		}
	}

	@Override
	public void setValues(BaseGroup target, int tweenType, float[] newValues) {
		switch(tweenType) {
		case POSITION_X:
			target.setX(newValues[0]);
			break;
		case POSITION_Y:
			target.setY(newValues[0]);
			break;			
        case POSITION_XY:
            target.setX(newValues[0]);
            target.setY(newValues[1]);
            break;		
		case SCALE_XY:
			target.setScale(newValues[0], newValues[1]);
			break;
		default:
			break;
		}

	}

}
