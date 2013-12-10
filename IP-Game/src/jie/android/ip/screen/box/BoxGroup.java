package jie.android.ip.screen.box;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.Resources;
import jie.android.ip.screen.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;

public class BoxGroup extends BaseGroup {

	private final Resources resources;
	private final TextureAtlas atlas;
	
	public BoxGroup(final Resources resources) {
		
		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);

		initStage();
	}

	@Override
	protected void initStage() {
		ImageActor top = new ImageActor(atlas.findRegion(ResourceConfig.FRANE_NAME));
		top.setBounds(4, ScreenConfig.HEIGHT - 16, ScreenConfig.WIDTH - 4, 16);
		this.addActor(top);
		
		ImageActor bottom = new ImageActor(atlas.findRegion(ResourceConfig.FRANE_NAME));
		bottom.setBounds(4, 0, ScreenConfig.WIDTH - 4, 16);
		this.addActor(bottom);
	}
	
	
}
