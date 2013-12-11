package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.CodeConfig;
import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.Code.OnButtonListener;

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

		groupJudge.setBounds(0, 0, CodeConfig.WIDTH_CODE_BIG * Code.Panel.SIZE_JUDGE_BUTTON, bg.getHeight());
		groupJudge.setVisible(false);
		groupOrder.setBounds(0, 0, CodeConfig.WIDTH_CODE_BIG * Code.Panel.SIZE_ORDER_BUTTON, bg.getHeight());
		groupOrder.setVisible(false);
		
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
		//if (type == Code.Type.NONE) {
			ret = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_NONE_BIG));
			float x = CodeConfig.SPACE_X_CODE / 2 + (CodeConfig.WIDTH_CODE_BIG + CodeConfig.SPACE_X_CODE / 2) * pos;
			float y = CodeConfig.SPACE_Y_CODE /2;
			ret.setBounds(x, y, ret.getWidth(), ret.getHeight());
		//}
		return ret;
	}
	
}
