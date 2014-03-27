package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ButtonActor;
import jie.android.ip.common.actor.ButtonActorAccessor;
import jie.android.ip.common.actor.ScreenGroup;
import jie.android.ip.screen.BaseScreen;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

public class CmdPanelGroup extends ScreenGroup {

	private final TextureAtlas textureAtlas;
	private final Skin skin;
	private final TweenManager tweenManager;
	
	final PlayScreenListener.RendererInternalEventListener internalListener;
	
	private final Cmd.Panel cmdPanel;
	
	private boolean debugEnabled = false;
	private boolean debugOver = false;
	
//	private ImageActor backGround;
	
	private Cmd.OnButtonListener cmdListener = new Cmd.OnButtonListener() {
		
		@Override
		public void onClick(final Cmd.Button button) {
			internalListener.onCmdButtonClicked(button.type, button.state);
		}
	};
	
	public CmdPanelGroup(final BaseScreen screen, final PlayScreenListener.RendererInternalEventListener internalListener) {
		super(screen);
		this.textureAtlas = super.resources.getTextureAtlas(PackConfig.SCREEN_PLAY);
		this.skin = new Skin(this.textureAtlas);
		this.tweenManager = this.screen.getTweenManager();
		this.internalListener = internalListener;
		
		cmdPanel = new Cmd.Panel(cmdListener);
		initStage();
		
		initButtons();
		
		setBounds();
	}
	
//	public void setRendererEventListener(final PlayScreenListener.RendererEventListener listener) {
//		this.rendererListener = listener;
//	}
//	
	@Override
	protected void initStage() {
//		secondGroup = new Group();
//		backGround = new ImageActor(textureAtlas.findRegion(Image.Cmd.BG));
//		backGround.setBounds(0, 0, Const.Cmd.WIDTH, Const.Cmd.HEIGHT);
//		this.addActor(backGround);
		this.setBounds(Const.Cmd.BASE_X, Const.Cmd.BASE_Y, Const.Cmd.WIDTH, Const.Cmd.HEIGHT);		
	}

	private void initButtons() {
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			if (btn.type == Cmd.Type.RUN) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.RUN_UP), skin.getDrawable(Image.Cmd.RUN_DOWN), skin.getDrawable(Image.Cmd.RUN_CHECKED)));//down, checked) new ImageActor(textureAtlas.findRegion(Image.Cmd.RUN));
				btn.actor.setBounds(Const.Cmd.X_RUN, Const.Cmd.Y_RUN, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.DEBUG) {
				final Button.ButtonStyle buttonStyle = new Button.ButtonStyle(skin.getDrawable(Image.Cmd.DEBUG_UP), skin.getDrawable(Image.Cmd.DEBUG_DOWN),null);
				buttonStyle.disabled = skin.getDrawable(Image.Cmd.DEBUG_DISABLED);
				btn.actor = new ButtonActor(buttonStyle);//new Button.ButtonStyle(skin.getDrawable(Image.Cmd.DEBUG_UP), skin.getDrawable(Image.Cmd.DEBUG_DOWN), skin.getDrawable(Image.Cmd.DEBUG_CHECKED)));//down, checked) new ImageActor(textureAtlas.findRegion(Image.Cmd.DEBUG));
				btn.actor.setBounds(Const.Cmd.X_DEBUG, Const.Cmd.Y_DEBUG, btn.actor.getWidth(), btn.actor.getHeight());
				btn.actor.setVisible(false);
			} else if (btn.type == Cmd.Type.DEBUG_OVER) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.DEBUG_OVER_UP), skin.getDrawable(Image.Cmd.DEBUG_OVER_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_DEBUG_OVER, Const.Cmd.Y_DEBUG_OVER, btn.actor.getWidth(), btn.actor.getHeight());
				btn.actor.setVisible(false);				
			} else if (btn.type == Cmd.Type.CLEAR) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.CLEAR_UP), skin.getDrawable(Image.Cmd.CLEAR_DOWN), null));// new ImageActor(textureAtlas.findRegion(Image.Cmd.CLEAR));
				btn.actor.setBounds(Const.Cmd.X_CLEAR, Const.Cmd.Y_CLEAR, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.MENU) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.MENU_UP), skin.getDrawable(Image.Cmd.MENU_DOWN), null));// new ImageActor(textureAtlas.findRegion(Image.Cmd.SETTING));
				btn.actor.setBounds(Const.Cmd.X_MENU, Const.Cmd.Y_MENU, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.BACK) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.BACK_UP), skin.getDrawable(Image.Cmd.BACK_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_BACK, Const.Cmd.Y_BACK, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.INFO) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.INFO_UP), skin.getDrawable(Image.Cmd.INFO_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_INFO, Const.Cmd.Y_INFO, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.SETTING) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.SETTING_UP), skin.getDrawable(Image.Cmd.SETTING_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_SETTING, Const.Cmd.Y_SETTING, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.CLOSE) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.CLOSE_UP), skin.getDrawable(Image.Cmd.CLOSE_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_CLOSE, Const.Cmd.Y_CLOSE, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.ENABLE_DEBUG) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.ENABLE_DEBUG_UP), skin.getDrawable(Image.Cmd.ENABLE_DEBUG_DOWN), skin.getDrawable(Image.Cmd.ENABLE_DEBUG_CHECKED)));
				btn.actor.setBounds(Const.Cmd.X_ENABLE_DEBUG, Const.Cmd.Y_ENABLE_DEBUG, btn.actor.getWidth(), btn.actor.getHeight());				
			} else if (btn.type == Cmd.Type.SHARE) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.SHARE_UP), skin.getDrawable(Image.Cmd.SHARE_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_SHARE, Const.Cmd.Y_SHARE, btn.actor.getWidth(), btn.actor.getHeight());								
			} else if (btn.type == Cmd.Type.NEXT) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.NEXT_UP), skin.getDrawable(Image.Cmd.NEXT_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_NEXT, Const.Cmd.Y_NEXT, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.BACK2) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.BACK2_UP), skin.getDrawable(Image.Cmd.BACK2_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_BACK2, Const.Cmd.Y_BACK2, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.CLOSE2) {
				btn.actor = new ButtonActor(new Button.ButtonStyle(skin.getDrawable(Image.Cmd.CLOSE2_UP), skin.getDrawable(Image.Cmd.CLOSE2_DOWN), null));
				btn.actor.setBounds(Const.Cmd.X_CLOSE2, Const.Cmd.Y_CLOSE2, btn.actor.getWidth(), btn.actor.getHeight());
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

				if (btn.layer != Cmd.Layer.FIRST) {
					btn.actor.setVisible(false);
				}
				
				this.addActor(btn.actor);
			}			
		}	
	}
	
	private void setBounds() {
		this.setBounds(Const.Cmd.BASE_X, Const.Cmd.BASE_Y, Const.Cmd.WIDTH, Const.Cmd.HEIGHT);
		
		screen.addActor(this);
	}

	private void showButtons(final Cmd.Layer layer) {
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			if (btn.actor != null) {
				if (layer == Cmd.Layer.FIRST) {
					if (btn.type != Cmd.Type.DEBUG && btn.type != Cmd.Type.DEBUG_OVER) {
						btn.actor.setVisible(btn.layer == layer);
					} else {
	//					btn.actor.setVisible(btn.layer == layer && debugEnabled);
					}
				} else {
					btn.actor.setVisible(btn.layer == layer);
				}
			}
		}
	}
	
	public void showPanel(boolean show) {
		if (show) {
			Tween.to(this, BaseGroupAccessor.POSITION_X, 0.1f).target(Const.Cmd.BASE_X).start(tweenManager);
		} else {
			Tween.to(this, BaseGroupAccessor.POSITION_X, 0.1f).target(ScreenConfig.WIDTH).start(tweenManager);
		}
	}

	public void setChecked(final Cmd.Type type, boolean checked) {
		cmdPanel.setState(type, checked ? Cmd.State.SELECTED : Cmd.State.NONE);
	}
	
	public boolean isChecked(final Cmd.Type type) {
		return cmdPanel.isChecked(type);
	}
	
	public void setDisabled(final Cmd.Type type, boolean disabled) {
		cmdPanel.setState(type, disabled ? Cmd.State.DISABLED : Cmd.State.NONE);
	}	
	
	public void focusRun(boolean show) {
		final Cmd.Button btnClear = cmdPanel.getButton(Cmd.Type.CLEAR);
		final Cmd.Button btnMenu = cmdPanel.getButton(Cmd.Type.MENU);

		if (show) {			
			Tween.to(btnClear.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.WIDTH_BUTTON).start(tweenManager);
			Tween.to(btnMenu.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.WIDTH_BUTTON).start(tweenManager);
		} else {
			Tween.to(btnClear.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.BASE_BUTTON_X).start(tweenManager);
			Tween.to(btnMenu.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.BASE_BUTTON_X).start(tweenManager);
		}
		
		if (debugEnabled) {
			final Cmd.Button btnDebug = cmdPanel.getButton(Cmd.Type.DEBUG);
			if(!btnDebug.actor.isVisible()) {
				btnDebug.actor.setVisible(true);
			}
			final Cmd.Button btnDebugOver = cmdPanel.getButton(Cmd.Type.DEBUG_OVER);
			if (!btnDebugOver.actor.isVisible()) {
				btnDebugOver.actor.setVisible(true);
			}
			
			if (show) {
				Tween.to(btnDebug.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.BASE_BUTTON_X).start(tweenManager);
				Tween.to(btnDebugOver.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.BASE_BUTTON_X).start(tweenManager);
			} else {
				Tween.to(btnDebug.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.WIDTH_BUTTON).start(tweenManager);
				Tween.to(btnDebugOver.actor, ButtonActorAccessor.POSITION_X, 0.1f).target(Const.Cmd.WIDTH_BUTTON).start(tweenManager);
			}
		}
		
		debugOver = false;
	}
	
	public void showMenu(final Cmd.Layer layer) {
		Tween.to(this, BaseGroupAccessor.POSITION_X, 0.1f).target(ScreenConfig.WIDTH)
		.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				showButtons(layer);
				Tween.to(CmdPanelGroup.this, BaseGroupAccessor.POSITION_X, 0.1f).target(Const.Cmd.BASE_X).start(tweenManager);
			}					
		})
		.start(tweenManager);		
	}

	public void enabledDebug(boolean enabled) {
		debugEnabled = enabled;
		final Cmd.Button btnDebug = cmdPanel.getButton(Cmd.Type.DEBUG);
		btnDebug.actor.setVisible(false);
		final Cmd.Button btnDebugOver = cmdPanel.getButton(Cmd.Type.DEBUG_OVER);
		btnDebugOver.actor.setVisible(false);
		
		this.setChecked(Cmd.Type.ENABLE_DEBUG, debugEnabled);
		
		debugOver = false;
	}

	public boolean isDebugOver() {
		return debugOver;
	}

	public void setDebugOver(boolean enabled) {
		debugOver = enabled;
	}

}
