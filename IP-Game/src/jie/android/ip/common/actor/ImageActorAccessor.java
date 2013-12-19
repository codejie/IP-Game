package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.Color;

import aurelienribon.tweenengine.TweenAccessor;

public class ImageActorAccessor implements TweenAccessor<ImageActor> {

	//public static final int POSITION = 0;
	public static final int POSITION_X = 1;
	public static final int POSITION_Y = 2;
	public static final int POSITION_XY= 3;
	public static final int SCALE_XY = 4;
	public static final int ROTATION = 5;
	public static final int OPACITY = 6;
	public static final int TINT = 7;
	public static final int SCALE_TO_XY = 8;
	
	@Override
	public int getValues(ImageActor target, int tweenType, float[] returnValues) {
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
		case ROTATION:
			returnValues[0] = target.getRotation();
			return 1;
		case OPACITY:
			returnValues[0] = target.getColor().a;
			return 1;
		case TINT:
			returnValues[0] = target.getColor().r;
			returnValues[1] = target.getColor().g;
			returnValues[2] = target.getColor().b;
			return 3;
		case SCALE_TO_XY:
			returnValues[0] = target.getX();
			returnValues[1] = target.getY();
			return 2;			
		default:
			return -1;
		}
	}

	@Override
	public void setValues(ImageActor target, int tweenType, float[] newValues) {
        switch (tweenType) {
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
		case ROTATION:
			target.setRotation(newValues[0]);
			break;
		case OPACITY: {
				Color c = target.getColor();
				c.set(c.r, c.g, c.b, newValues[0]);
				target.setColor(c);
			}
			break;
		case TINT: {
				Color c = target.getColor();
				c.set(newValues[0], newValues[1], newValues[2], c.a);
				target.setColor(c);
			}
			break;
		case SCALE_TO_XY:
			target.setWidth(newValues[0]);
			target.setHeight(newValues[1]);
			break;
        default:
        	break;
        }
    }

}
