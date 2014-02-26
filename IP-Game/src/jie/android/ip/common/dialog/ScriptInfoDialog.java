package jie.android.ip.common.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import jie.android.ip.screen.BaseScreen;

public class ScriptInfoDialog extends BaseDialog {

	private final BitmapFont fontTitle;
	private final BitmapFont fontText;
	
	public ScriptInfoDialog(final BaseScreen screen) {
		super(screen);
		fontTitle = super.resources.getBitmapTrueFont(55);
		fontText = super.resources.getBitmapTrueFont(35);
	
		setOKButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				ScriptInfoDialog.this.dismiss();
			}
		});
		
		initAuthor();
		initComment();
	}
}
