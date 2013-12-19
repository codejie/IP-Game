package jie.android.ip.screen.box.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.Resources;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.box.BoxConfig.Const;
import jie.android.ip.screen.box.BoxConfig.Image;

public class CodePanelGroup extends BaseGroup {

	public enum State {
		HIDE, JUDGE, ORDER;
	}
	
	private final TextureAtlas textureAtlas;
	
	private final Code.Panel codePanel;
	
	private Group groupJudge;
	private Group groupOrder;
	
	private final Code.OnButtonListener onClickListener;
	
	private State state = State.HIDE;
	
	public CodePanelGroup(final Code.Panel codePanel, final Code.OnButtonListener codeListener, final Resources resources) {
		this.textureAtlas = resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
		
		this.onClickListener = codeListener;
		
		this.codePanel = codePanel;//new Code.Panel();
		
		initStage();
		
		initButtons();		
	}

	@Override
	protected void initStage() {		
		groupJudge = new Group();
		ImageActor bg = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.JUDGE_BG));
		bg.setBounds(0, 0, Const.Console.Panel.Judge.WIDTH_BG, Const.Console.Panel.Judge.HEIGHT_BG);
		groupJudge.addActor(bg);
		
		groupOrder = new Group();
		bg = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.ORDER_BG));
		bg.setBounds(0, 0, Const.Console.Panel.Order.WIDTH_BG, Const.Console.Panel.Order.HEIGHT_BG);
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

		if (type == Code.Type.NONE) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_NONE));
		} else if (type == Code.Type.RIGHT) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_RIGHT));
		} else if (type == Code.Type.LEFT) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_LEFT));
		} else if (type == Code.Type.ACT) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_ACT));
		} else if (type == Code.Type.IF_0) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_0));
		} else if (type == Code.Type.IF_1) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_1));
		} else if (type == Code.Type.IF_2) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_2));
		} else if (type == Code.Type.IF_3) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_3));
		} else if (type == Code.Type.IF_ANY) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_ANY));
		} else if (type == Code.Type.IF_NONE) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_NONE));
		} else if (type == Code.Type.CALL_0) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_0));
		} else if (type == Code.Type.CALL_1) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_1));
		} else if (type == Code.Type.CALL_2) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_2));
		} else if (type == Code.Type.CALL_3) {
			ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_3));
		} else {
			return null;
		}		
			
		float x = Const.Console.Panel.SPACE_X + (Const.Console.Panel.WIDTH_BUTTON + Const.Console.Panel.SPACE_X) * pos;
		float y = Const.Console.Panel.SPACE_Y;
		ret.setBounds(x, y, Const.Console.Panel.WIDTH_BUTTON, Const.Console.Panel.HEIGHT_BUTTON);
		
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
			
			this.setBounds(0, 0, Const.Console.Panel.Judge.WIDTH_BG, Const.Console.Panel.Judge.HEIGHT_BG);
		} else if (this.state == State.ORDER) {
			groupJudge.setVisible(false);
			groupJudge.setZIndex(0x00);
			groupOrder.setVisible(true);
			groupOrder.setZIndex(0x0F);
			this.setBounds(0, 0, Const.Console.Panel.Order.WIDTH_BG, Const.Console.Panel.Order.HEIGHT_BG);
		}
		this.setVisible(this.state != State.HIDE);
		
		return true;
	}	
}
