package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ConsoleGroupConfig;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.actor.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.CodePanelGroup.State;

public class CodePanelGroup extends BaseGroup {

	public enum State {
		HIDE, JUDGE, ORDER;
	}
	
	private final Resources resources;
	private final TextureAtlas atlas;
	
	private final Code.Panel codePanel;
	
	private Group groupJudge;
	private Group groupOrder;
	
	private final Code.OnButtonListener onClickListener;
	
	private State state = State.HIDE;
	
	public CodePanelGroup(final Code.Panel codePanel, final Code.OnButtonListener codeListener, final Resources resources) {
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
		this.onClickListener = codeListener;
		
		this.codePanel = codePanel;//new Code.Panel();
		
		initStage();
		
		initButtons();		
	}

	@Override
	protected void initStage() {		
		groupJudge = new Group();
		ImageActor bg = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_PANEL_JUDGE_BG));
		bg.setBounds(0, 0, ConsoleGroupConfig.Panel.Judge.WIDTH_BG, ConsoleGroupConfig.Panel.Judge.HEIGHT_BG);
		groupJudge.addActor(bg);
		
		groupOrder = new Group();
		bg = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_PANEL_ORDER_BG));
		bg.setBounds(0, 0, ConsoleGroupConfig.Panel.Order.WIDTH_BG, ConsoleGroupConfig.Panel.Order.HEIGHT_BG);
		groupOrder.addActor(bg);
		
		groupJudge.setVisible(false);
		groupOrder.setVisible(false);
		
		this.addActor(groupJudge);
		this.addActor(groupOrder);	
	}

	private void initButtons() {
		Code.Button buttons[] = codePanel.getJudgeButton();
		for (int i = 0; i < buttons.length; ++ i) {
			final Code.Button btn = buttons[i];
			final int pos = i;
			btn.bigActor = makeImageActor(i, btn.type);
			btn.bigActor.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.PANEL, -1, pos, btn);
					}
				}
			});
			groupJudge.addActor(btn.bigActor);
		}
		
		buttons = codePanel.getOrderButton();
		for (int i = 0; i < buttons.length; ++ i) {
			final Code.Button btn = buttons[i];
			final int pos = i;
			btn.bigActor = makeImageActor(i, btn.type);
			btn.bigActor.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.PANEL, -1, pos, btn);
					}
				}
			});
			groupOrder.addActor(btn.bigActor);
		}
	}

	private Actor makeImageActor(int pos, Code.Type type) {
		ImageActor ret = null;

//		if (type == Code.Type.NONE) {
//			ret = new ImageActor(atlas.findRegion(small ? ResourceConfig.CONSOLE_CODE_NONE_SMALL : ResourceConfig.CONSOLE_CODE_NONE_BIG));
//		} else if (type == Code.Type.RIGHT) {
//		} else if (type == Code.Type.LEFT) {
//		} else if (type == Code.Type.ACT) {
//		} else if (type == Code.Type.IF_0) {
//		} else if (type == Code.Type.IF_1) {
//		} else if (type == Code.Type.IF_2) {
//		} else if (type == Code.Type.IF_3) {
//		} else if (type == Code.Type.IF_ANY) {
//		} else if (type == Code.Type.IF_NONE) {
//			ret = new ImageActor(atlas.findRegion(small ? ResourceConfig.CONSOLE_CODE_IF_NONE_SMALL : ResourceConfig.CONSOLE_CODE_IF_NONE_BIG));
//		} else if (type == Code.Type.CALL_0) {
//		} else if (type == Code.Type.CALL_1) {
//		} else if (type == Code.Type.CALL_2) {
//		} else if (type == Code.Type.CALL_3) {
//		} else {
//			return null;
//		}		
		
//		if (type == Code.Type.NONE) {
			ret = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_NONE_BIG));			
//		} else if (type == Code.Type.RIGHT){
			
//		}
			
		float x = ConsoleGroupConfig.Panel.SPACE_X + (ConsoleGroupConfig.Panel.WIDTH_BUTTON + ConsoleGroupConfig.Panel.SPACE_X) * pos;
		float y = ConsoleGroupConfig.Panel.SPACE_Y;
		ret.setBounds(x, y, ConsoleGroupConfig.Panel.WIDTH_BUTTON, ConsoleGroupConfig.Panel.HEIGHT_BUTTON);
		
		return ret;
	}

	public final State getState() {
		return state;
	}
	
	public boolean setState(final CodePanelGroup.State state) {
		if (this.state == state) {
			return false;
		}

		this.state = state;
		
		if (this.state == State.JUDGE) {
			groupJudge.setVisible(true);
			groupJudge.setZIndex(0x0F);
			groupOrder.setVisible(false);
			groupOrder.setZIndex(0x00);
			
			this.setBounds(0, 0, ConsoleGroupConfig.Panel.Judge.WIDTH_BG, ConsoleGroupConfig.Panel.Judge.HEIGHT_BG);
		} else if (this.state == State.ORDER) {
			groupJudge.setVisible(false);
			groupJudge.setZIndex(0x00);
			groupOrder.setVisible(true);
			groupOrder.setZIndex(0x0F);
			this.setBounds(0, 0, ConsoleGroupConfig.Panel.Order.WIDTH_BG, ConsoleGroupConfig.Panel.Order.HEIGHT_BG);
		}
		this.setVisible(this.state != State.HIDE);
		
		return true;
	}	
}
