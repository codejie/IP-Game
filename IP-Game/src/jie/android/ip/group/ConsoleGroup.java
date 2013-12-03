package jie.android.ip.group;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.actor.ImageActor;

public class ConsoleGroup extends BaseGroup {

	private final Resources resources;
	private final TextureAtlas atlas;
	
	public ConsoleGroup(final Resources resources) {
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
		makeStage();
	}

	private void makeStage() {
	}

	public void addButton(Actor actor) {
		this.addActor(actor);
	}
	
}
