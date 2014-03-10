package jie.android.ip;

import jie.android.ip.CommonConsts.SystemConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ButtonActorAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.common.actor.ImageActorAccessor;
import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.actor.LabelActorAccessor;
import jie.android.ip.database.DBAccess;
import aurelienribon.tweenengine.Tween;

public class Environment {

	private static String version;
	
	public static void init() {
		Tween.registerAccessor(ImageActor.class, new ImageActorAccessor());
		Tween.registerAccessor(BaseGroup.class, new BaseGroupAccessor());
		Tween.registerAccessor(ButtonActor.class, new ButtonActorAccessor());
		Tween.registerAccessor(LabelActor.class, new LabelActorAccessor());
	}
	
	public static void loadAppData(final DBAccess dbAccess) {
		version = dbAccess.getSysDataAsString(SystemConfig.SYS_ATTR_VERSION);
	}
	
	public static final String getVersion() {
		return version;
	}
	
	public static final boolean hasPackPatch() {
		return false;
	}
}
