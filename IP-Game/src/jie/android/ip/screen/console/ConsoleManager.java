package jie.android.ip.screen.console;

import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.BoxScreenEventListener;
import jie.android.ip.screen.console.Code.Type;
import jie.android.ip.utils.Utils;

public class ConsoleManager {

	protected static final String Tag = ConsoleManager.class.getSimpleName();
	
	private BoxRenderConfig config;	
	private ConsoleRenderer renderer;
	
	private final BoxScreenEventListener screenListener;
	
//	private ArrayList<Cmd.Button> cmdButtons;
	private Cmd.Panel cmdPanel;
	private Code.Lines codeLines;
	private Code.Panel codePanel;
//	private Code.
	
	private Cmd.OnButtonListener cmdListener = new Cmd.OnButtonListener() {

		@Override
		public void onClick(Cmd.Type type, int state) {
			Utils.log(Tag, "cmdbtn: type = " + type.getId() + " state = " + state);
			if (screenListener != null) {
				screenListener.onConsoleButtonClick(type.getId(), state);
			}
		}		
	};
	
	private Code.OnButtonListener codeListener = new Code.OnButtonListener() {
		
		@Override
		public void onPanelButtonClick(Code.Type type, int state) {
			Utils.log(Tag, "code panel btn: type = " + type.getId() + " state = " + state);
		}
		
		@Override
		public void onLinesButonClick(int func, int pos, Code.Type type, int state) {
			Utils.log(Tag, "code lines btn: type = " + type.getId() + " state = " + state);
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
		
//		
//		cmdButtons = new ArrayList<Cmd.Button>();
//		
//		Cmd.Button run = new Cmd.Button(Cmd.Type.RUN, listener);
//		
//		cmdButtons.add(run);
	}
	
	private void initRenderer() {
		
		renderer = new ConsoleRenderer(config);
		
		for (final Cmd.Button btn : cmdPanel.getButtons()) {
			renderer.addCmdButton(btn);
		}
		
		for (final Code.Button btn : codePanel.getButtons()) {
			renderer.addCodePanelButton(btn);
		}		
	}
	
	
}
