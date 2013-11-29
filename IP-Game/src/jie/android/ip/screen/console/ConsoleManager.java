package jie.android.ip.screen.console;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.Actor;

import jie.android.ip.script.Script;

public class ConsoleManager {

//	public class Indicator {
//		
//	}
//
//	public enum Code {
//		
//	}
//	
//	public class Program {
//		private static final int MAX_FUNCTION = 4;
//		private static final int MAX_CODE = 8; // 8 * 2
//		
//		private Code code[][];
//	}
	
	public enum ScreenState {
		
	};
	
	public enum CmdType { 
		NONE, RUN 
	};
	
	public interface OnCmdButtonClickListener
	
	public class CmdButton {
		
		public final CmdType type;
		public Actor actor;
		
		public CmdButton(final CmdType type) {
			this.type = type;
		}
	}
	
	private ConsoleRenderConfig config;	
	private ConsoleRenderer renderer;
	
	private ArrayList<CmdButton> buttons;
	
	public ConsoleManager(final ConsoleRenderConfig config) {
		this.config = config;
		
		initCmdButton();
		
		initRenderer();
	}

	private void initCmdButton() {
		buttons = new ArrayList<CmdButton>();
		
		CmdButton run = new CmdButton(CmdType.RUN);
		
		
	}
	
	
	
	
}
