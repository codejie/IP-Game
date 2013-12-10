package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;

import jie.android.ip.CommonConsts.CodeConfig;
import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;

public class CodePanelGroup extends BaseGroup {

	public enum State {
		HIDE, JUDGE, ORDER;
	}
	
	private final Resources resources;
	private final TextureAtlas atlas;
	
	private Group groupJudge;
	private Group groupOrder;
	
	private State state = State.HIDE;
	
	public CodePanelGroup(final Resources resources) {
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
	
		initStage();		
	}

	@Override
	protected void initStage() {		
		groupJudge = new Group();
		groupOrder = new Group();

		ImageActor bg = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_PANEL_BG));
		bg.setPosition(0, 0);
		groupJudge.addActor(bg);
		groupOrder.addActor(bg);
		
		ImageActor none = new ImageActor(this.atlas.findRegion(ResourceConfig.CONSOLE_CODE_NONE_BIG));
		none.setData(Code.Type.NONE);
		
		none.setBounds(0, 0, CodeConfig.WIDTH_CODE_BIG, CodeConfig.HEIGHT_CODE_BIG);		
		groupJudge.addActor(none);
		none.setBounds(0, 0, CodeConfig.WIDTH_CODE_BIG, CodeConfig.HEIGHT_CODE_BIG);
		groupOrder.addActor(none);

		groupJudge.setBounds(0, 0, CodeConfig.WIDTH_CODE_BIG * 7, bg.getHeight());
		groupJudge.setVisible(false);
		groupOrder.setBounds(0, 0, CodeConfig.WIDTH_CODE_BIG * 8, bg.getHeight());
		groupOrder.setVisible(false);
		
		this.addActor(groupJudge);
		this.addActor(groupOrder);	
	}
	
}
