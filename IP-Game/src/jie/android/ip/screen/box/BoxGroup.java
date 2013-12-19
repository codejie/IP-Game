package jie.android.ip.screen.box;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.Resources;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.box.BoxConfig.Image;

public class BoxGroup extends BaseGroup {

//	private final Resources resources;
	private final TextureAtlas textureAtlas;
	
	public BoxGroup(final Resources resources) {
		
		this.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		
//		this.resources = resources;
		this.textureAtlas = resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);

		initStage();
	}

	@Override
	protected void initStage() {
		ImageActor top = new ImageActor(textureAtlas.findRegion(Image.Box.FRAME));
		top.setBounds(4, ScreenConfig.HEIGHT - 16, ScreenConfig.WIDTH - 4, 16);
		this.addActor(top);
		
		ImageActor bottom = new ImageActor(textureAtlas.findRegion(Image.Box.FRAME));
		bottom.setBounds(4, 0, ScreenConfig.WIDTH - 4, 16);
		this.addActor(bottom);
	}
	
	
}
