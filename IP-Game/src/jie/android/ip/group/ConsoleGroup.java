package jie.android.ip.group;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
		final ImageActor run = new ImageActor(atlas.findRegion(ResourceConfig.RUN_BUTTON_NAME));
		run.setPosition(0, 0);
		run.addListener(new ClickListener() {
			
		});
		this.addActor(run);
	}
	
}
