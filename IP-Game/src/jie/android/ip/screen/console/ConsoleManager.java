package jie.android.ip.screen.console;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

import jie.android.ip.screen.BoxRenderConfig;
import jie.android.ip.screen.BoxScreenEventListener;
import jie.android.ip.script.Script;
import jie.android.ip.utils.Utils;

public class ConsoleManager {

	protected static final String Tag = ConsoleManager.class.getSimpleName();
	
	public enum ScreenState {		
	};
	
	public enum CmdType { 
		NONE, RUN;
		
		public int getId() {
			return this.ordinal();
		}
		
	};
	
	public class CmdButton {
		
		public final CmdType type;
		public final OnCmdButtonListener listener;
		public Actor actor;
		public int state;
		
		public CmdButton(final CmdType type, final OnCmdButtonListener listener) {
			this.type = type;
			this.listener = listener;
			this.state = 0;
		}
	}

	
	private BoxRenderConfig config;	
	private ConsoleRenderer renderer;
	
	private final BoxScreenEventListener screenListener;
	
	private ArrayList<CmdButton> buttons;
	
	private OnCmdButtonListener listener = new OnCmdButtonListener() {

		@Override
		public void onClick(CmdType type, int state) {
			Utils.log(Tag, "cmdbtn: type = " + type.getId() + " state = " + state);
			if (screenListener != null) {
				screenListener.onConsoleButtonClick(type.getId(), state);
			}
		}
		
	};
	
	public ConsoleManager(final BoxRenderConfig config) {
		this.config = config;
		this.screenListener = this.config.getScreenListener();
		
		initCmdButton();
		
		initRenderer();
	}

	private void initCmdButton() {
		buttons = new ArrayList<CmdButton>();
		
		CmdButton run = new CmdButton(CmdType.RUN, listener);
		
		buttons.add(run);
	}
	
	private void initRenderer() {
		
		renderer = new ConsoleRenderer(config);
		
		for (final CmdButton btn : buttons) {
			renderer.addButton(btn);
		}
	}
	
	
}
