package jie.android.ip.screen.code;

import com.badlogic.gdx.scenes.scene2d.Actor;

import jie.android.ip.screen.BoxRenderConfig;

public class CodeManager {
	
	public static final int MAX_FUNC = 4;
	public static final int MAX_CODE = 8;
	
	public enum CodeType {
		NONE, RIGHT, LEFT, ACT, IF_0, IF_1, IF_2, IF_3, IF_NONE, IF_ANY,
		CALL_0, CALL_1, CALL_2, CALL_3;
	}

	public class CodeButton {
		public final CodeType code;
		public final OnCodeButtonListener listener;
		public Actor actor;
		public int state;
		
		public CodeButton(final CodeType code, final OnCodeButtonListener listener) {
			this.code = code;
			this.listener = listener;
		}
	}
	
	public class CodeLines {
		private CodeButton[][] codeButton = new CodeButton[MAX_FUNC][MAX_CODE * 2];
		
		public void setCode(int func, int pos, final CodeButton btn) {
			if (btn.code == CodeType.NONE || btn.code == CodeType.RIGHT || btn.code == CodeType.LEFT || btn.code == CodeType.ACT
					|| btn.code == CodeType.CALL_0 || btn.code == CodeType.CALL_1 || btn.code == CodeType.CALL_2 || btn.code == CodeType.CALL_3) {
				codeButton[func][pos + 1] = btn;
			} else {
				codeButton[func][pos] = btn;
			}
		}		
	}
	
	public class CodePanel {
		private CodeButton[] codeButton = new CodeButton[CodeType.values().length];
		
		public CodePanel() {
			CodeType[] type = CodeType.values();
			for (int i = 1; i < type.length; ++ i) {
				codeButton[i] = new CodeButton(type[i], onPanelButtonListener);
			}
		}
		
		public final CodeButton[] getButtons() {
			return codeButton;
		}
		
	}
	
	private OnCodeButtonListener onPanelButtonListener = new OnCodeButtonListener() {
		
	};
	
	private OnCodeButtonListener onLinesButtonListener = new OnCodeButtonListener() {
		
	};
	
	
	private final BoxRenderConfig config;
	private CodeRenderer renderer;
	
	private CodePanel codePanel = new CodePanel();
	
	public CodeManager(final BoxRenderConfig config) {
		this.config = config;
	
		initRenderer();
	}

	private void initRenderer() {
		renderer = new CodeRenderer(config);
		
		for (final CodeButton btn : codePanel.getButtons()) {
			renderer.putPanelButton(btn);
		}
	}
	
	
	
}
