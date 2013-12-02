package jie.android.ip.screen.code;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.group.CodeGroup;
import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.code.CodeManager.CodeButton;
import jie.android.ip.screen.code.CodeManager.CodeType;

public class CodeRenderer {
	
	private final BoxRenderConfig config;
	private final TextureAtlas textureAtlas;

	private final CodeGroup group;
	
	public CodeRenderer(BoxRenderConfig config) {
		this.config = config;
		
		this.textureAtlas = this.config.getResources().getAssetManager().get(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);
		this.group = (CodeGroup) config.getCodeGroup();
	}

	public void putPanelButton(final CodeButton btn) {
		int x = 0, y = 0;
		if (btn.code == CodeType.CALL_0) {
			x = 8;
			y = ScreenConfig.HEIGHT - 32; 
		}
		
		btn.actor = makePanelButton(btn.code, btn.state);
		btn.actor.setPosition(x, y);
		btn.actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (btn.listener != null) {
					btn.listener.onPanelButtonClick(btn.code, btn.state);
				}
			}
			
		});
		
		group.addPanelButton(btn.actor);
	}

	private Actor makePanelButton(CodeType code, int state) {
		ImageActor actor = new ImageActor(textureAtlas.findRegion(ResourceConfig.FRANE_NAME));
		return actor;
	}
	
	

}
