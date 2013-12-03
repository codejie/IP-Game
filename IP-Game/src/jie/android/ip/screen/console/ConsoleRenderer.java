package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.group.ConsoleGroup;
import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.Code.Button;

public class ConsoleRenderer {

	private final BoxRenderConfig config;		
	private final ConsoleGroup group;
	private final TextureAtlas atlas;	
	
	public ConsoleRenderer(BoxRenderConfig config) {
		this.config = config;
		this.group = (ConsoleGroup) this.config.getConsoleGroup();
		
		this.atlas = this.config.getResources().getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
	}

	public void addCmdButton(final Cmd.Button btn) {
		btn.actor = new ImageActor(atlas.findRegion(ResourceConfig.RUN_BUTTON_NAME));
		btn.actor.setPosition(0, 0);
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

	public void addCodePanelButton(final Code.Button btn) {
		
		int x = 110, y = 0;
		if (btn.type == Code.Type.CALL_0) {
			x = 188;
			y = ScreenConfig.HEIGHT - 32; 
		}
		
		btn.actor = new ImageActor(atlas.findRegion(ResourceConfig.RUN_BUTTON_NAME));
		btn.actor.setPosition(x, y);
		btn.actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (btn.listener != null) {
					btn.listener.onPanelButtonClick(btn.type, btn.state);
				}
			}			
		});
		
		group.addButton(btn.actor);
		
	}

}
