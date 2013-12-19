package jie.android.ip.screen.box.console;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.box.BoxConfig.Const;
import jie.android.ip.screen.box.BoxConfig.Image;
import jie.android.ip.screen.box.BoxRenderAdapter;

public class ConsoleRenderer {

	private final BoxRenderAdapter renderAdapter;		
	private final ConsoleGroup group;
	private final TextureAtlas textureAtlas;
	private final TweenManager tweenManager;
	
	public ConsoleRenderer(final BoxRenderAdapter adapter) {
		this.renderAdapter = adapter;
		this.group = (ConsoleGroup) this.renderAdapter.getConsoleGroup();		
		this.textureAtlas = this.renderAdapter.getResources().getTextureAtlas(ScreenPackConfig.SCREEN_BOX);		
		this.tweenManager = this.renderAdapter.getTweenManager();	
	}
	
	public void setGroupClickListener(ClickListener groupListener) {
		group.setClickListener(groupListener);
	}
	
	
	private void addCmdButton(final Cmd.Button button, final Cmd.OnButtonListener listener) {
		
		button.actor = new ImageActor(textureAtlas.findRegion(Image.Console.Cmd.RUN));
		button.actor.setPosition(Const.Console.Cmd.BASE_X, Const.Console.Cmd.BASE_Y);
		button.actor.addListener(new ClickListener() {
	
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (listener != null) {
					listener.onClick(button);
				}
			}			
		});
		group.addButton(button.actor);
}

	public void initCmdPanel(final Cmd.Panel cmdPanel, final Cmd.OnButtonListener cmdListener) {
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			addCmdButton(btn, cmdListener);
		}		
	}
	
	public void initCodeLines(final Code.Lines codeLines, final Code.Panel codePanel, final Code.OnButtonListener codeListener) {		
		group.initCodeGroup(codeLines, codePanel, codeListener);
	}
	
	public boolean hitGroup(float x, float y) {
		return (group.hit(x, y, true) == group);
	}

	public void updataCodeGroupState(int index) {
		group.hideCodePanel(-1, tweenManager);
		group.toggleCodeLineState(index, tweenManager);
	}

	public void showCodePanel(int index, int pos, final Code.Button button) {
		group.showCodePanel(index, pos, button, tweenManager);
	}

	public void hideCodePanel(int pos) {
		group.hideCodePanel(pos, tweenManager);
	}

	public void updateCodeLinesButton(int index, int pos, final Code.Type type) {
		group.updateCodeLineButton(index, pos, type);
	}	
	
}
