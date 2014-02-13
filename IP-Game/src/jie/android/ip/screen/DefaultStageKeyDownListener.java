package jie.android.ip.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import jie.android.ip.screen.ActorStage.OnKeyDownListener;

public class DefaultStageKeyDownListener implements OnKeyDownListener {

	final BaseScreen screen;
	
	public DefaultStageKeyDownListener(final BaseScreen screen) {
		this.screen = screen;
	}
	
	@Override
	public boolean isHandled(int keyCode) {
		if (keyCode == Keys.BACK) {
			Gdx.app.exit();
			return true;
		}
		return false;
	}

}
