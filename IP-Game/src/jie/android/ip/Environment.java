package jie.android.ip;

import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import aurelienribon.tweenengine.Tween;

public class Environment {

	public static void init() {
		Tween.registerAccessor(ImageActor.class, new ImageActorAccessor());
		Tween.registerAccessor(BaseGroup.class, new BaseGroupAccessor());		
	}
	
}
