package jie.android.ip.screen.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface OnActorInputListener {
	public void onTouchUp(Actor actor, float x, float y, int pointer, int button);
	public void onTouchDown(Actor actor, float x, float y, int pointer,	int button);
	public void onTouchDragged(Actor actor, float x, float y, int pointer);
}
