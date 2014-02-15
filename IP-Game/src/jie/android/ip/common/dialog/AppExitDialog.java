package jie.android.ip.common.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import jie.android.ip.screen.BaseScreen;

public class AppExitDialog extends AlertDialog {
	
	private static AppExitDialog instance;
	
	public static final AppExitDialog getInstance(final BaseScreen screen) {
		if (instance == null || instance.getScreen() != screen) {
			instance = new AppExitDialog(screen);
		}
		
		return instance;
	}
	
	private static final AlertDialog.ButtonClickListener pListener = new AlertDialog.ButtonClickListener() {		
		@Override
		public void onClick() {
			Gdx.app.exit();
		}
	};
	
	public AppExitDialog(final BaseScreen screen) {
		super(screen, "Are you sure to exit I;P ?", screen.getGame().getResources().getBitmapTrueFont(65), Color.CYAN, pListener, null);		
	}
	
	public final BaseScreen getScreen() {
		return screen;
	}
}
