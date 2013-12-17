package jie.android.ip.screen.console;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.BoxScreenEventListener;
import jie.android.ip.screen.console.Code.Button;
import jie.android.ip.utils.Utils;

public class ConsoleManager {

	protected static final String Tag = ConsoleManager.class.getSimpleName();
	
	private BoxRenderConfig config;	
	private ConsoleRenderer renderer;
	
	private final BoxScreenEventListener screenListener;
	
	private Cmd.Panel cmdPanel;
	private Code.Lines codeLines;
	private Code.Panel codePanel;
	
	private Cmd.OnButtonListener cmdListener = new Cmd.OnButtonListener() {

		@Override
		public void onClick(final Cmd.Button button) {
			Utils.log(Tag, "cmdbtn: type = " + button.type.getId() + " state = " + button.state);
			if (screenListener != null) {
				screenListener.onConsoleButtonClick(button.type.getId(), button.state.getId());
			}
		}		
	};
	
	private Code.OnButtonListener codeListener = new Code.OnButtonListener() {

		@Override
		public void onClick(final Code.OnButtonListener.Which which, int index, final Code.Button button) {
			if (which == Code.OnButtonListener.Which.PANEL_GROUP || which == Code.OnButtonListener.Which.CODE_GROUP) {
				Utils.log(Tag, "code group click : index = " + index + " which = " + which.getId());
				onCodeGroupClick(index);
			} else if (which == Code.OnButtonListener.Which.PANEL) {
				Utils.log(Tag, "code panel click : index = " + index + " which = " + which.getId() + "  button type = " + button.type.getId());
				onCodePanelClick(index, button);
			} else if (which == Code.OnButtonListener.Which.CODE) {
				Utils.log(Tag, "code line click : index = " + index + " which = " + which.getId() + "  button type = " + button.type.getId());
				onCodeLineClick(index, button);
			}
		}
	}; 
	
	private ClickListener groupListener = new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (hitGroup(x, y)) {
				Utils.log(Tag, "console group click : x = " + x + " y = " + y);
				onConsoleGroupClick(x, y);
			}
		}
	};
	
	public ConsoleManager(final BoxRenderConfig config) {
		this.config = config;
		this.screenListener = this.config.getScreenListener();
		
		initButtons();
		
		initRenderer();
	}

	private void initButtons() {
		cmdPanel = new Cmd.Panel(cmdListener);
		codeLines = new Code.Lines();
		codePanel = new Code.Panel();
	}
	
	private void initRenderer() {
		renderer = new ConsoleRenderer(config);
		
		renderer.initCmdPanel(cmdPanel, cmdListener);
		renderer.initCodeLines(codeLines, codePanel, codeListener);
//		
		renderer.setGroupClickListener(groupListener);
	}
	
	protected boolean hitGroup(float x, float y) {
		return renderer.hitGroup(x, y);
	}
	
	protected void onCodeLineClick(int index, final Code.Button button) {
		renderer.showCodePanel(index, button);
	}

	protected void onCodePanelClick(int index, final Code.Button button) {
		
	}

	protected void onCodeGroupClick(int index) {
		renderer.updataCodeGroupState(index);
	}
	
	protected void onConsoleGroupClick(float x, float y) {
		renderer.updataCodeGroupState(-1);		
	}	
	
//	
//	protected void onCodeButtonClick(boolean inPanel, Button button) {
//		
//		if (button != cacheCodePanelButton) {
//			resetCacheCodePanelCode();
//	
//			if (inPanel) {
//				changePanelButtonState(button);
//			} else {
//				changeLinesButtonState(button);
//			}
//		} else {
//			resetCacheCodePanelCode();
//		}
//	}
//	
//	protected void changePanelButtonState(final Code.Button button) {
//		if (button.state == Code.State.NONE) {
//			button.state = Code.State.SELECTED;
//			
//			cacheCodePanelButton = button;
//		} else {
//			button.state = Code.State.NONE;
//		}
//		renderer.updateCodePanelButton(button);
//	}
//	
//	protected void changeLinesButtonState(Button button) {
//		if (button.state == Code.State.NONE) {
//			button.state = Code.State.SELECTED;
//		} else {
//			button.state = Code.State.NONE;
//		}
//		renderer.updateCodeLinesButton(button);		
//	}
//
//	protected void onGroupClick(float x, float y) {
//		if (cacheCodePanelButton != null) {
//			createCodeLinesButton(cacheCodePanelButton.type, x, y);
//			resetCacheCodePanelCode();
//		}
//	}
//
//	private void createCodeLinesButton(final Code.Type type, float x, float y) {
//		
//		Holder<Integer> func = new Holder<Integer>(-1);
//		Holder<Integer> pos = new Holder<Integer>(-1);
//		
//		if (renderer.getLinesLocation(type, x, y, func, pos)) {
//			Utils.log(Tag, "code lines : func = " + func.getValue() + " pos = "  + pos.getValue());
//			Code.Button locbtn = null;
//			Code.Button btn = new Code.Button(type, codeListener);
//			if (!type.isJudge()) {
//				 locbtn = codeLines.getButton(func.getValue(), pos.getValue() - 1);
//			}
//			renderer.addCodeLinesButton(func.getValue(), pos.getValue(), btn, locbtn);
//			
//			codeLines.setButton(func.getValue(), pos.getValue(), btn);
//		}
//	}
//	
//	private void resetCacheCodePanelCode() {
//		if (cacheCodePanelButton != null) {
//			final Code.Button btn = cacheCodePanelButton;
//			changePanelButtonState(btn);
//			cacheCodePanelButton = null;
//		}		
//	}
//	
//	
	
}
