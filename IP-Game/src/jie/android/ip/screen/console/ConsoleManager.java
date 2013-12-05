package jie.android.ip.screen.console;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.BoxScreenEventListener;
import jie.android.ip.screen.console.Code.Button;
import jie.android.ip.screen.console.Code.Type;
import jie.android.ip.utils.Holder;
import jie.android.ip.utils.Utils;

public class ConsoleManager {

	protected static final String Tag = ConsoleManager.class.getSimpleName();
	
	private BoxRenderConfig config;	
	private ConsoleRenderer renderer;
	
	private final BoxScreenEventListener screenListener;
	
	private Cmd.Panel cmdPanel;
	private Code.Lines codeLines;
	private Code.Panel codePanel;
	
	private Code.Button  cacheCodePanelButton = null;
	
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
		public void onClick(boolean inPanel, final Code.Button button) {
			Utils.log(Tag, "code panel btn: type = " + button.type.getId() + " state = " + button.state.getId());
			onCodeButtonClick(inPanel, button);
		}
	}; 
	
	private ClickListener groupListener = new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (hitGroup(x, y)) {
				Utils.log(Tag, "group click : x = " + x + " y = " + y);
				onGroupClick(x, y);
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
		codePanel = new Code.Panel(codeListener);
		codeLines = new Code.Lines();
	}
	
	private void initRenderer() {
		
		renderer = new ConsoleRenderer(config);
		
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			renderer.addCmdButton(btn);
		}
		
		for (final Code.Button btn : codePanel.getButtons()) {			
			renderer.addCodePanelButton(btn);
		}
		
		renderer.setGroupClickListener(groupListener);
	}
	
	protected boolean hitGroup(float x, float y) {
		return renderer.hitGroup(x, y);
	}
	
	protected void onCodeButtonClick(boolean inPanel, Button button) {
		
		resetCacheCodePanelCode();
		
		if (inPanel) {
			changePanelButtonState(button);
		} else {
			changeLinesButtonState(button);
		}		
	}
	
	protected void changePanelButtonState(final Code.Button button) {
		if (button.state == Code.State.NONE) {
			button.state = Code.State.SELECTED;
			
			cacheCodePanelButton = button;
		} else {
			button.state = Code.State.NONE;
		}
		renderer.updateCodePanelButton(button);
	}
	
	protected void changeLinesButtonState(Button button) {
		if (button.state == Code.State.NONE) {
			button.state = Code.State.SELECTED;
		} else {
			button.state = Code.State.NONE;
		}
		renderer.updateCodeLinesButton(button);		
	}

	protected void onGroupClick(float x, float y) {
		if (cacheCodePanelButton != null) {
			createCodeLinesButton(cacheCodePanelButton.type, x, y);
			resetCacheCodePanelCode();
		}
	}

	private void createCodeLinesButton(final Code.Type type, float x, float y) {
		
		Holder<Integer> func = null;
		Holder<Integer> pos = null;
		
		if (renderer.getLinesLocation(type, x, y, func, pos)) {
			Utils.log(Tag, "code lines : func = " + func + " pos = "  + pos);
			Code.Button btn = new Code.Button(type, codeListener, func.getValue(), pos.getValue());
			//codeLines.
			renderer.addCodeLinesButton(btn);			
		}
		
	}
	
	private void resetCacheCodePanelCode() {
		if (cacheCodePanelButton != null) {
			final Code.Button btn = cacheCodePanelButton;
			changePanelButtonState(btn);
			cacheCodePanelButton = null;
		}		
	}
	
	
	
}
