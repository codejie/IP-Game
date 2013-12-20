package jie.android.ip.screen.box.console;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.Resources;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.screen.box.BoxConfig.Const;
import jie.android.ip.screen.box.BoxConfig.Image;

public class ConsoleGroup extends BaseGroup {

	private final Resources resources;
	private final TextureAtlas textureAtlas;
	
	private ClickListener clickListener;
	
	private CodeLineGroup[] groupLines;
	private CodePanelGroup groupPanel;	
	
	public ConsoleGroup(final Resources resources) {
		this.resources = resources;
		this.textureAtlas = this.resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
		
		initCodeGroup();
	}

	private void initCodeGroup() {
//		this.groupLines = new CodeLineGroup[CodeConfig.SIZE_CODE_LINES];		
//		this.groupPanel = new CodePanelGroup(this.resources);		
	}

	@Override
	protected void initStage() {
		
	}

	public void setClickListener(final ClickListener clickListener) {
		if (this.clickListener != null) {
			this.removeListener(this.clickListener);
		}
		this.clickListener = clickListener;
		this.addListener(this.clickListener);		
	}


	public void initCmdPanel(final Cmd.Panel cmdPanel, final Cmd.OnButtonListener cmdListener) {
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			if (btn.type == Cmd.Type.RUN) {
				btn.actor = new ImageActor(textureAtlas.findRegion(Image.Console.Cmd.RUN));
				btn.actor.setBounds(Const.Console.Cmd.X_RUN, Const.Console.Cmd.Y_RUN, btn.actor.getWidth(), btn.actor.getHeight());
			} else if (btn.type == Cmd.Type.STOP) {
				btn.actor = new ImageActor(textureAtlas.findRegion(Image.Console.Cmd.STOP));
				btn.actor.setBounds(Const.Console.Cmd.X_STOP, Const.Console.Cmd.Y_STOP, btn.actor.getWidth(), btn.actor.getHeight());				
			} else {
				continue;
			}
			
			if (btn.actor != null) {
				btn.actor.addListener(new ClickListener() {
					
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (cmdListener != null) {
							cmdListener.onClick(btn);
						}
					}			
				});	
				this.addActor(btn.actor);
			}
		}			
	}	
	
	public void initCodeLineGroup(final CodeLineGroup[] groupLines, final Code.OnButtonListener codeListener) {
		for (int i = 0; i < groupLines.length; ++ i) {
			
			groupLines[i].setBounds(Const.Console.Lines.BASE_X, Const.Console.Lines.BASE_Y + (groupLines.length - i - 1) * (Const.Console.Lines.Small.HEIGHT_BG + Const.Console.Lines.Small.SPACE_Y),
					groupLines[i].getWidth(), groupLines[i].getHeight());
			
			this.addActor(groupLines[i]);
		}
	}

	public void initCodeGroup(final Code.Lines codeLines, final Code.Panel codePanel, final Code.OnButtonListener codeListener) {
		
		//groupLines
		groupLines = new CodeLineGroup[Code.NUM_CODE_LINES];		
		
		for (int i = 0; i < groupLines.length; ++ i) {
			groupLines[i] = new CodeLineGroup(i, codeLines.getFuncButton(i), codeListener, this.resources);			
			final CodeLineGroup group = groupLines[i];
			final int func = i;
			//group.setPosition(CodeConfig.BASE_X_CODE_LINES, CodeConfig.BASE_Y_CODE_LINES + (groupLines.length - func - 1) * (CodeConfig.HEIGHT_SMALL_CODE_LINE + CodeConfig.SPACE_Y_CODE_LINES));
			final Vector2 v = getCodeLinePosition(func);
			group.setBounds(v.x, v.y, Const.Console.Lines.Small.WIDTH_BG, Const.Console.Lines.Small.HEIGHT_BG);
			
			group.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (group.getState() == CodeLineGroup.State.SMALL || group.hit(x, y, true) == group) {
						if (codeListener != null) {
							codeListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, func, -1, null);
						}
					}
				}				
			});
			
			this.addActor(group);
		}

		//groupPanel
		groupPanel = new CodePanelGroup(codePanel, codeListener, this.resources);
		groupPanel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (groupPanel.hit(x, y, true) == groupPanel) {
					if (codeListener != null) {
						codeListener.onClick(Code.OnButtonListener.Which.PANEL_GROUP, -1, -1, null);
					}
				}
			}			
		});
		
		groupPanel.setVisible(false);//.setBounds(0, 0, Const.Console.Panel. groupPanel.getWidth(), groupPanel.getHeight());
		this.addActor(groupPanel);
	}
	
	private final Vector2 getCodeLinePosition(int index) {
		Vector2 vct = new Vector2();
		vct.x =	Const.Console.Lines.BASE_X;
		vct.y = Const.Console.Lines.BASE_Y + (groupLines.length - index - 1) * (Const.Console.Lines.Small.HEIGHT_BG + Const.Console.Lines.SPACE_LILNES);
		return vct;
	}

	public void toggleCodeLineState(int index, final TweenManager tweenManager) {

		for (int i = 0; i < groupLines.length; ++ i) {
			if (groupLines[i].getState() == CodeLineGroup.State.BIG) {
				changeCodeLineState(i, tweenManager);
				if (i == index) {
					return;
				}
				break;
			}
		}
		if (index != -1) {
			changeCodeLineState(index, tweenManager);
		}
	}
	
	private void changeCodeLineState(int index, final TweenManager tweenManager) {
		
		final CodeLineGroup group = groupLines[index];
		
		final TweenCallback callback = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				if (group.getState() == CodeLineGroup.State.SMALL) {
					group.setScale(1.0f);
					group.setState(CodeLineGroup.State.BIG);					
				} else {
					group.setZIndex(0x00);
					group.setScale(1.0f);
					group.setState(CodeLineGroup.State.SMALL);
				}
			}
		};
		
		final Vector2 vct = getCodeLinePosition(index);
		float scale = 0.6f;
		
		if (group.getState() == CodeLineGroup.State.SMALL) {
			if (index == 0 || index == 1) {
				vct.y = vct.y - (Const.Console.Lines.Big.HEIGHT_BG - Const.Console.Lines.Small.HEIGHT_BG);
			} else {
			
			}
			group.setZIndex(0x0f);
			scale = 1.8f;			
		} else {
		}

		Timeline.createParallel()
			.push(Tween.to(group, BaseGroupAccessor.POSITION_XY, 0.2f).target(vct.x, vct.y))
			.push(Tween.to(group, BaseGroupAccessor.SCALE_XY, 0.2f).target(scale, scale))
			.setCallback(callback)
		.start(tweenManager);
	}
	
//	private void setBigState(final CodeLineGroup group, float x, float y, float scale, final TweenManager tweenManager) {
//		
//		final TweenCallback callback = new TweenCallback() {
//
//			@Override
//			public void onEvent(int type, BaseTween<?> source) {
//				setBigState(group);
//			}
//		};
//		
//		group.setZIndex(0x0f);
//		
//		Timeline.createParallel()
////			.setUserData(group)
//			.push(Tween.to(group, BaseGroupAccessor.POSITION_XY, 0.2f).target(x, y))
//			.push(Tween.to(group, BaseGroupAccessor.SCALE_XY, 0.2f).target(scale, scale))
//			.setCallback(callback)
//			.start(tweenManager);
//	}
//
//	protected void setBigState(final CodeLineGroup group) {
//		group.setState(CodeLineGroup.State.BIG);
//		group.setScale(1.0f);
//	}

	public void showCodePanel(int index, int pos, final Code.Button button, final TweenManager tweenManager) {

		float x = 0, y = 0;
		
		if (pos %2 == 0) {
			x = Const.Console.Lines.Big.WIDTH_TITLE + (Const.Console.Lines.Big.WIDTH_BUTTON_ORDER + Const.Console.Lines.Big.SPACE_X) * (pos / 2) / 1.5f + Const.Console.Lines.Big.SPACE_X;
			if (x + Const.Console.Panel.Order.WIDTH_BG > ScreenConfig.WIDTH) {
				x = ScreenConfig.WIDTH - Const.Console.Panel.Order.WIDTH_BG;
			}			
			
		} else {
			x = Const.Console.Lines.Big.WIDTH_TITLE + (Const.Console.Lines.Big.WIDTH_BUTTON_JUDGE + Const.Console.Lines.Big.SPACE_X) * ((pos - 1) / 2) / 1.5f + Const.Console.Lines.Big.SPACE_X;
			if (x + Const.Console.Panel.Judge.WIDTH_BG > ScreenConfig.WIDTH) {
				x = ScreenConfig.WIDTH - Const.Console.Panel.Judge.WIDTH_BG;
			}			
		}
		
		final Vector2 vct = getCodeLinePosition(index);
		
		if (index == 0 || index == 1) {
			if (pos % 2 == 0) {
				y = vct.y - (Const.Console.Lines.Big.HEIGHT_BG - Const.Console.Lines.Small.HEIGHT_BG) - Const.Console.Panel.Order.HEIGHT_BG;
			} else {
				y = vct.y + Const.Console.Lines.Small.HEIGHT_BG;
			}
		} else if (index == 2) {
			if (pos % 2 == 0) {
				y = vct.y - Const.Console.Panel.Order.HEIGHT_BG;
			} else {
				y = vct.y + Const.Console.Lines.Big.HEIGHT_BG;
			}
		} else {
			y = vct.y + Const.Console.Lines.Big.HEIGHT_BG;
		}
		
		float sx = x + (Const.Console.Panel.SPACE_X + Const.Console.Panel.WIDTH_BUTTON) * (3 + 0.5f);
		float sy = y + Const.Console.Panel.SPACE_Y + Const.Console.Panel.HEIGHT_BUTTON * 0.5f;
		
		boolean changed = groupPanel.setState(button.type.isJudge() ? CodePanelGroup.State.JUDGE : CodePanelGroup.State.ORDER);
		
		if (changed) {			
			Timeline.createParallel()
				.push(Tween.set(groupPanel, BaseGroupAccessor.POSITION_XY).target(sx, sy))
				.push(Tween.set(groupPanel, BaseGroupAccessor.SCALE_XY).target(0.0f, 0.0f))
				.push(Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.2f).target(x, y))
				.push(Tween.to(groupPanel, BaseGroupAccessor.SCALE_XY, 0.2f).target(1.0f, 1.0f))
				.start(tweenManager);
		} else {
			Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.1f).target(x, y).start(tweenManager);
		}
	}

	public void hideCodePanel(int pos, TweenManager tweenManager) {
		
		if (groupPanel.getState() != CodePanelGroup.State.HIDE) {
			if (pos != -1) {
				float x = groupPanel.getX() + (Const.Console.Panel.SPACE_X + Const.Console.Panel.WIDTH_BUTTON) * (pos + 0.5f);
				float y = groupPanel.getY() + Const.Console.Panel.SPACE_Y + Const.Console.Panel.HEIGHT_BUTTON * 0.5f;
				
				TweenCallback completeCallback = new TweenCallback() {
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						groupPanel.setState(CodePanelGroup.State.HIDE);					
					}				
				};
				
				Timeline.createParallel()
					.push(Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.2f).target(x, y))
					.push(Tween.to(groupPanel, BaseGroupAccessor.SCALE_XY, 0.2f).target(0.0f, 0.0f))
					.setCallback(completeCallback )
					.start(tweenManager);
			} else {
				groupPanel.setState(CodePanelGroup.State.HIDE);	
			}			
		}
	}

	public void updateCodeLineButton(int index, int pos, final Code.Type type) {
		groupLines[index].updateCodeButton(pos, type);
	}

	
}
