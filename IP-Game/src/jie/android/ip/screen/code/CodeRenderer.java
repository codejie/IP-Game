package jie.android.ip.screen.code;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.code.CodeManager.CodeButton;
import jie.android.ip.screen.code.CodeManager.CodeType;

public class CodeRenderer {
	
	private final BoxRenderConfig config;
	private final TextureAtlas textureAtlas;

	public CodeRenderer(BoxRenderConfig config) {
		this.config = config;
		
		this.textureAtlas = config.getResources().getAssetManager().get(ResourceConfig.BOX_PACK_NAME, TextureAtlas.class);
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
					btn.listener.onClick(btn.code, btn.state);
				}
			}
			
		});
		
		config.getCodeGroup().addPanelButton(btn.actor);
	}
	
	

}
