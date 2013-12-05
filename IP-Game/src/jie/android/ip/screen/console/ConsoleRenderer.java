package jie.android.ip.screen.console;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.group.ConsoleGroup;
import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;
import jie.android.ip.screen.console.Code.Button;
import jie.android.ip.utils.Holder;

public class ConsoleRenderer {

	private static final int BASE_X_CODE_PANEL = ScreenConfig.WIDTH * 2 / 3 + 16;
	private static final int BASE_Y_CODE_PANEL = 16;
	private static final int SPACE_CODE_PANEL = 8;
	private static final int SIZE_CODE_PANEL = 80;
	
	private static final int BASE_X_CODE_LINES = 48;
	private static final int BASE_Y_CODE_LINES = 16;
	private static final int SPACE_CODE_LINES = 8;
	private static final int SIZE_CODE_LINES = 76;
	
	private final BoxRenderConfig config;		
	private final ConsoleGroup group;
	private final TextureAtlas atlas;
	
	private final TweenManager tweenManager;
	
	public ConsoleRenderer(BoxRenderConfig config) {
		this.config = config;
		this.group = (ConsoleGroup) this.config.getConsoleGroup();
		
		this.atlas = this.config.getResources().getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
		this.tweenManager = this.config.getTweenManager();
	}

	public void setGroupClickListener(ClickListener groupListener) {
		group.setClickListener(groupListener);
	}
	
	public void addCmdButton(final Cmd.Button button) {
		button.actor = new ImageActor(atlas.findRegion(ResourceConfig.RUN_BUTTON_NAME));
		button.actor.setPosition(0, 0);
		button.actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (button.listener != null) {
					button.listener.onClick(button);
				}
			}			
		});
		group.addButton(button.actor);
	}

	public void addCodePanelButton(final Code.Button button) {
		button.actor = new ImageActor(atlas.findRegion("72"));
		setCodePanelButtonPosition(button);
		button.actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (button.listener != null) {
					button.listener.onClick(true, button);
				}
			}			
		});
		
		group.addButton(button.actor);		
	}

	private void setCodePanelButtonPosition(final Code.Button button) {
		int x = 0, y = 0;
		if (button.type == Code.Type.RIGHT) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 3 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.LEFT) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 1 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 3 + SPACE_CODE_PANEL;
		} if (button.type == Code.Type.ACT) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 3 + SPACE_CODE_PANEL;			
		} else if (button.type == Code.Type.IF_0) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.IF_1) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 1 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.IF_2) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.IF_3) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 3 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;		
		} else if (button.type == Code.Type.IF_ANY) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 1 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.IF_NONE) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 1 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 1 + SPACE_CODE_PANEL;			
		} else if (button.type == Code.Type.CALL_0) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.CALL_1) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 1 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.CALL_2) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 2 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
		} else if (button.type == Code.Type.CALL_3) {
			x = BASE_X_CODE_PANEL + SIZE_CODE_PANEL * 3 + SPACE_CODE_PANEL;
			y = BASE_Y_CODE_PANEL + SIZE_CODE_PANEL * 0 + SPACE_CODE_PANEL;
		}
		
		button.actor.setPosition(x, y);
	}

	public boolean hitGroup(float x, float y) {
		return (group.hit(x, y, true) == group);
	}

	public void updateCodePanelButton(final Code.Button button) {
		if (button.state == Code.State.SELECTED) {
			Tween.to(button.actor, ImageActorAccessor.SCALE_XY, 0.1f).target(1.2f, 1.2f).start(tweenManager);
		} else {
			Tween.to(button.actor, ImageActorAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f).start(tweenManager);
		}
	}
	
	public void updateCodeLinesButton(final Code.Button button) {
		if (button.state == Code.State.SELECTED) {
			Tween.to(button.actor, ImageActorAccessor.SCALE_XY, 0.1f).target(1.2f, 1.2f).start(tweenManager);
		} else {
			Tween.to(button.actor, ImageActorAccessor.SCALE_XY, 0.1f).target(1.0f, 1.0f).start(tweenManager);
		}
	}

	public void addCodeLinesButton(final Code.Button button) {
		button.actor = new ImageActor(atlas.findRegion("72"));
		setCodeLinesButtonPosition(button);
		button.actor.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (button.listener != null) {
					button.listener.onClick(false, button);
				}
			}			
		});
		
		group.addButton(button.actor);		
	}

	private void setCodeLinesButtonPosition(Button button) {
		
	}

	public boolean getLinesLocation(Code.Type type, float x, float y, Holder<Integer> func, Holder<Integer> pos) {
		
		for (int i = 0; i < Code.MAX_CODE; ++ i) {
			if (x > BASE_X_CODE_LINES + SIZE_CODE_LINES * (i) + SPACE_CODE_LINES
					&& x < BASE_X_CODE_LINES + SIZE_CODE_LINES * (i + 1) + SPACE_CODE_LINES) {
				if (type.isJudge()) {
					pos = new Holder<Integer>(i * 2);	
				} else {
					pos = new Holder<Integer>(i * 2 + 1);
				}
				break;
			}				
		}
		for (int i = 0; i < Code.MAX_FUNC; ++ i) {
			if (y > BASE_Y_CODE_LINES + SIZE_CODE_LINES * (i) + SPACE_CODE_LINES
					&& y < BASE_Y_CODE_LINES + SIZE_CODE_LINES * (i + 1) + SPACE_CODE_LINES) {
				func = new Holder<Integer>(i);
				break;
			}
		}
		
		return (func != null && pos != null);
	}
	
}
