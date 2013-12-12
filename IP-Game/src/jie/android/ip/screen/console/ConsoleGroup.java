package jie.android.ip.screen.console;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.CodeConfig;
import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.actor.BaseGroup;
import jie.android.ip.screen.actor.BaseGroupAccessor;
import jie.android.ip.screen.console.Code.OnButtonListener;

public class ConsoleGroup extends BaseGroup {

	
	private final Resources resources;
	private final TextureAtlas atlas;
	
	private ClickListener clickListener;
	
	private CodeLineGroup[] groupLines;
	private CodePanelGroup groupPanel;	
	
	public ConsoleGroup(final Resources resources) {
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
		initCodeGroup();
	}

	private void initCodeGroup() {
//		this.groupLines = new CodeLineGroup[CodeConfig.SIZE_CODE_LINES];		
//		this.groupPanel = new CodePanelGroup(this.resources);		
	}

	@Override
	protected void initStage() {
		
	}

	public void addButton(Actor actor) {
		this.addActor(actor);
	}

	public void setClickListener(final ClickListener clickListener) {
		if (this.clickListener != null) {
			this.removeListener(this.clickListener);
		}
		this.clickListener = clickListener;
		this.addListener(this.clickListener);		
	}

	public void initCodeLineGroup(final CodeLineGroup[] groupLines, final OnButtonListener codeListener) {
		for (int i = 0; i < groupLines.length; ++ i) {
			
			groupLines[i].setBounds(CodeConfig.BASE_X_CODE_LINES, CodeConfig.BASE_Y_CODE_LINES + (groupLines.length - i - 1) * (CodeConfig.HEIGHT_CODE_LINE_SMALL + CodeConfig.SPACE_Y_CODE_LINES),
					groupLines[i].getWidth(), groupLines[i].getHeight());
			
			this.addActor(groupLines[i]);
		}
	}

	public void initCodePanelGroup(final CodePanelGroup groupPanel, final OnButtonListener codeListener) {
		groupPanel.setBounds(0, 0, groupPanel.getWidth(), groupPanel.getHeight());
		this.addActor(groupPanel);
	}

	public void initCodeGroup(final Code.Lines codeLines, final Code.Panel codePanel, final Code.OnButtonListener codeListener) {
		
		groupLines = new CodeLineGroup[CodeConfig.SIZE_CODE_LINES];		
		
		for (int i = 0; i < CodeConfig.SIZE_CODE_LINES; ++ i) {
			groupLines[i] = new CodeLineGroup(i, codeLines.getFuncButton(i), codeListener, this.resources);			
			final CodeLineGroup group = groupLines[i];
			final int func = i;
			//group.setPosition(CodeConfig.BASE_X_CODE_LINES, CodeConfig.BASE_Y_CODE_LINES + (groupLines.length - func - 1) * (CodeConfig.HEIGHT_SMALL_CODE_LINE + CodeConfig.SPACE_Y_CODE_LINES));
			
			group.setBounds(CodeConfig.BASE_X_CODE_LINES, CodeConfig.BASE_Y_CODE_LINES + (groupLines.length - func - 1) * (CodeConfig.HEIGHT_CODE_LINE_SMALL + CodeConfig.SPACE_Y_CODE_LINES),
					group.getWidth(), group.getHeight());
			
			group.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (group.getState() == CodeLineGroup.State.SMALL || group.hit(x, y, true) == group) {
						if (codeListener != null) {
							codeListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, func, null);
						}
					}
				}				
			});
			
			this.addActor(group);
		}

		groupPanel = new CodePanelGroup(codePanel, codeListener, this.resources);
		groupPanel.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (groupPanel.hit(x, y, true) == groupPanel) {
					if (codeListener != null) {
						codeListener.onClick(Code.OnButtonListener.Which.PANEL_GROUP, -1, null);
					}
				}
			}			
		});
		
		groupPanel.setBounds(0, 0, groupPanel.getWidth(), groupPanel.getHeight());
		this.addActor(groupPanel);
	}

	public void toggleCodeLineState(int index, final TweenManager tweenManager) {
		final CodeLineGroup group = groupLines[index];
		float x, y, scale;
		if (group.getState() == CodeLineGroup.State.SMALL) {
			switch (index) {
			case 0:
				x = 100.0f;
				y = 450.0f;
				scale = 1.8f;
				break;
			default:
				return;
			}
			setBigState(group, x, y, scale, tweenManager);
		} else {
			
		}
	}
	
	private void setBigState(final CodeLineGroup group, float x, float y, float scale, final TweenManager tweenManager) {
		
		final TweenCallback callback = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				setBigState(group);
			}
		};
		
		Timeline.createParallel()
//			.setUserData(group)
			.push(Tween.to(group, BaseGroupAccessor.POSITION_XY, 2.2f).target(x, y))
			.push(Tween.to(group, BaseGroupAccessor.SCALE_XY, 2.2f).target(scale, scale))
			.setCallback(callback)
			.start(tweenManager);
	}

	protected void setBigState(final CodeLineGroup group) {
		group.setState(CodeLineGroup.State.BIG);
		group.setScale(1.0f);
	}

	
}
