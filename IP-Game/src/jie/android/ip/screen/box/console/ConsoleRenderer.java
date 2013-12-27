package jie.android.ip.screen.box.console;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.screen.box.BoxRenderAdapter;
import jie.android.ip.screen.box.console.Cmd.State;
import jie.android.ip.screen.box.console.Cmd.Type;
import jie.android.ip.screen.box.console.Code.Lines;

public class ConsoleRenderer implements Disposable {

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

	@Override
	public void dispose() {
		tweenManager.killAll();
	}	
	
	public void setGroupClickListener(ClickListener groupListener) {
		group.setClickListener(groupListener);
	}

	public void initCmdPanel(final Cmd.Panel cmdPanel, final Cmd.OnButtonListener cmdListener) {
		group.initCmdPanel(cmdPanel, cmdListener);
	}
	
	public void initCodeLines(final Code.Lines codeLines, final Code.Panel codePanel, final Code.OnButtonListener codeListener) {
//		group.initCodeGroup(codeLines, codePanel, codeListener);
		group.initCodeLines(codeLines, codeListener);
		group.initCodePanel(codePanel, codeListener);
	}
	
	public void resetCodeLines(final Code.Lines codeLines, final Code.OnButtonListener codeListener) {
		group.resetCodeLines(codeLines, codeListener);
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

	public void removeCodeLines(final Code.Lines codeLines) {
		group.removeCodeLines();
	}

	public boolean isCodeLinesShown() {
		return group.isCodeLinesShow();
	}

//	public void setCmdButtonState(final Cmd.Type type, final Cmd.State state) {
//		group.setCmdPanelButtonState(type, state);
//	}
	
}
