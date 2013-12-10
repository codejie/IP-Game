package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.CodeConfig;
import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.Code.OnButtonListener;

public class ConsoleGroup extends BaseGroup {

	
	private final Resources resources;
	private final TextureAtlas atlas;
	
	private ClickListener clickListener;
	
	public ConsoleGroup(final Resources resources) {
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		
	}

	@Override
	protected void initStage() {
		// TODO Auto-generated method stub
		
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
			
			groupLines[i].setBounds(CodeConfig.BASE_X_CODE_LINES, CodeConfig.BASE_Y_CODE_LINES + (groupLines.length - i - 1) * (CodeConfig.HEIGHT_SMALL_CODE_LINE + CodeConfig.SPACE_Y_CODE_LINES),
					groupLines[i].getWidth(), groupLines[i].getHeight());
			
			this.addActor(groupLines[i]);
		}
	}

	public void initCodePanelGroup(final CodePanelGroup groupPanel, final OnButtonListener codeListener) {
		groupPanel.setBounds(0, 0, groupPanel.getWidth(), groupPanel.getHeight());
		this.addActor(groupPanel);
	}

	
}
