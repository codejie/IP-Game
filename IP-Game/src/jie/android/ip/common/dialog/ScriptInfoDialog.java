package jie.android.ip.common.dialog;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import jie.android.ip.common.actor.LabelActor;
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
	
	private void initAuthor() {
		final LabelActor title = new LabelActor("AUTHOR:", fontTitle);
		
	}

	private void initComment() {
		// TODO Auto-generated method stub
		
	}

	public void setAuthor(final String author) {
		
	}
	
	public void setComment(final String comment) {
		
	}
}
