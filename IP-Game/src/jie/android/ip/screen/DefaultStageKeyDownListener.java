package jie.android.ip.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

import jie.android.ip.common.dialog.AlertDialog;
import jie.android.ip.screen.ActorStage.OnKeyDownListener;

public class DefaultStageKeyDownListener implements OnKeyDownListener {

	final BaseScreen screen;
	
	public DefaultStageKeyDownListener(final BaseScreen screen) {
		this.screen = screen;
	}
	
	@Override
	public boolean isHandled(int keyCode) {
		if (keyCode == Keys.BACK) {
			
			final AlertDialog.ButtonClickListener pListener = new AlertDialog.ButtonClickListener() {
				
				@Override
				public void onClick() {
					Gdx.app.exit();
				}
			};
			
			final AlertDialog dlg = new AlertDialog(screen, "Are you sure to exit I;P ?", screen.getGame().getResources().getBitmapTrueFont(65), Color.CYAN, pListener, null);
			dlg.show();
			return true;
		}
		return false;
	}

}
