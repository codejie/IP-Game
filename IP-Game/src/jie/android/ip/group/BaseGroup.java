package jie.android.ip.group;

import java.util.HashMap;

import jie.android.ip.screen.actor.ImageActor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class BaseGroup extends Group {

	private HashMap<String, Actor> mapActor = new HashMap<String, Actor>();
	
	public void addActor(final String name, Actor actor) {
		mapActor.put(name, actor);
		super.addActor(actor);
	}
	
	public void removeActor(final String name) {
		Actor actor = mapActor.get(name);
		if (actor != null) {
			super.removeActor(actor);
		}
	}
	
	public void addActor(ImageActor actor) {
		addActor(actor.getName(), actor);
	}
	
	public void removeActor(ImageActor actor) {
		removeActor(actor.getName());
	}
	
}
