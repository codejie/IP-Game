package jie.android.ip.screen.play;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;
import jie.android.ip.screen.play.PlayScreenListener.RendererInternalEventListener;

public class CmdPanelGroup extends BaseGroup {

	private final PlayScreen screen;
	private final TextureAtlas textureAtlas;
	private final Skin skin;
	
	private PlayScreenListener.RendererEventListener rendererListener;
	private final PlayScreen
	
	private final Cmd.Panel cmdPanel;
	
	private ImageActor backGround;
	
	private Cmd.OnButtonListener cmdListener = new Cmd.OnButtonListener() {
		
		@Override
		public void onClick(final Cmd.Button button) {
			if (rendererListener != null) {
				rendererListener.onCmdButtonClicked(button.type);
			}
		}
	};
	
	public CmdPanelGroup(final PlayScreen screen, RendererInternalEventListener rendererInternalListener) {
		this.screen = screen;
		this.textureAtlas = screen.getGame().getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
		this.skin = new Skin(this.textureAtlas);
		
		cmdPanel = new Cmd.Panel(cmdListener);
		
		initStage();
		
		initButtons();
		
		setBounds();
	}
	
	public void setRendererEventListener(final PlayScreenListener.RendererEventListener listener) {
		this.rendererListener = listener;
	}
	
	@Override
	protected void initStage() {
//		secondGroup = new Group();
		//backGround = 
	}

	private void initButtons() {
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			if (btn.type == Cmd.Type.RUN) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Console.Cmd.RUN_UP), skin.getDrawable(Image.Console.Cmd.RUN_DOWN), skin.getDrawable(Image.Console.Cmd.RUN_CHECKED)));//down, checked) new ImageActor(textureAtlas.findRegion(Image.Console.Cmd.RUN));
				btn.actor.setBounds(Const.Console.Cmd.X_RUN, Const.Console.Cmd.Y_RUN, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.CLEAR) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Console.Cmd.CLEAR_UP), skin.getDrawable(Image.Console.Cmd.CLEAR_DOWN), null));// new ImageActor(textureAtlas.findRegion(Image.Console.Cmd.CLEAR));
				btn.actor.setBounds(Const.Console.Cmd.X_CLEAR, Const.Console.Cmd.Y_CLEAR, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.MENU) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Console.Cmd.MENU_UP), skin.getDrawable(Image.Console.Cmd.MENU_DOWN), null));// new ImageActor(textureAtlas.findRegion(Image.Console.Cmd.SETTING));
				btn.actor.setBounds(Const.Console.Cmd.X_SETTING, Const.Console.Cmd.Y_SETTING, btn.actor.getWidth(), btn.actor.getHeight());				
			} else {
				continue;
			}
			
			if (btn.actor != null) {
				btn.actor.addListener(new ClickListener() {
					
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (btn.state != Cmd.State.DISABLED) {
							if (cmdListener != null) {
								cmdListener.onClick(btn);
							}
						}
					}			
				});
				
				this.addActor(btn.actor);
			}
			
		}	
	}
	
	public void setBounds() {
		this.setBounds(Const.Console.Cmd.BASE_X, Const.Console.Cmd.BASE_Y, Const.Console.Cmd.WIDTH, Const.Console.Cmd.HEIGHT);
		
		screen.addActor(this);
	}
	
}
