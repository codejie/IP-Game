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
		ImageActor bg = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_PANEL_BG));
		bg.setPosition(0, 0);
		groupJudge.addActor(bg);
		
		groupOrder = new Group();
		bg = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_PANEL_BG));
		bg.setPosition(0, 0);
		groupOrder.addActor(bg);

		groupJudge.setBounds(0, 0, ConsoleGroupConfig.Panel.Judge.WIDTH_BG, ConsoleGroupConfig.Panel.Judge.HEIGHT_BG);
		groupJudge.setVisible(false);
		//groupJudge.setScale(0.0f);
		groupOrder.setBounds(0, 0, ConsoleGroupConfig.Panel.Order.WIDTH_BG, ConsoleGroupConfig.Panel.Order.HEIGHT_BG);
		groupOrder.setVisible(false);
		//groupOrder.setScale(0.0f);
		
		this.addActor(groupJudge);
		this.addActor(groupOrder);	
	}

	private void initButtons() {
		Code.Button buttons[] = codePanel.getJudgeButton();
		for (int i = 0; i < buttons.length; ++ i) {
			final Code.Button btn = buttons[i];
			btn.bigActor = makeImageActor(i, btn.type);
			btn.bigActor.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.PANEL, -1, btn);
					}
				}
			});
			groupJudge.addActor(btn.bigActor);
		}
		
		buttons = codePanel.getOrderButton();
		for (int i = 0; i < buttons.length; ++ i) {
			final Code.Button btn = buttons[i];
			btn.bigActor = makeImageActor(i, btn.type);
			btn.bigActor.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.PANEL, -1, btn);
					}
				}
			});
			groupOrder.addActor(btn.bigActor);
		}
	}

	private Actor makeImageActor(int pos, Code.Type type) {
		ImageActor ret = null;
//		if (type == Code.Type.NONE) {
			ret = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_NONE_BIG));			
//		} else if (type == Code.Type.RIGHT){
			
//		}
			
		float x = ConsoleGroupConfig.Panel.SPACE_X + (ConsoleGroupConfig.Panel.WIDTH_BUTTON + ConsoleGroupConfig.Panel.SPACE_X) * pos;
		float y = ConsoleGroupConfig.Panel.SPACE_Y;
		ret.setBounds(x, y, ConsoleGroupConfig.Panel.WIDTH_BUTTON, ConsoleGroupConfig.Panel.HEIGHT_BUTTON);
		
		return ret;
	}

	public boolean setState(final CodePanelGroup.State state) {
		if (this.state == state) {
			return false;
		}

		this.state = state;
		
		groupJudge.setVisible(this.state == State.JUDGE);
		groupOrder.setVisible(this.state == State.ORDER);
		
		return true;
	}	
}
