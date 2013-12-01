package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.group.ConsoleGroup;
import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.ConsoleManager.CmdButton;

public class ConsoleRenderer {

	private final BoxRenderConfig config;		
	private final ConsoleGroup group;
	private final TextureAtlas atlas;	
	
	public ConsoleRenderer(BoxRenderConfig config) {
		this.config = config;
		this.group = (ConsoleGroup) this.config.getConsoleGroup();
		
		this.atlas = this.config.getResources().getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
	}

	public void addButton(final CmdButton btn) {
		btn.actor = new ImageActor(atlas.findRegion(ResourceConfig.RUN_BUTTON_NAME));
		btn.actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (btn.listener != null) {
					btn.listener.onClick(btn.type, btn.state);
				}
			}
			
		});
		group.addButton(btn.actor);
	}

}
