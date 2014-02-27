package jie.android.ip.common.dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;

import jie.android.ip.common.actor.LabelActor;
import jie.android.ip.common.dialog.DialogConfig.Const;
import jie.android.ip.screen.BaseScreen;

public class ScriptInfoDialog extends BaseDialog {

	private final BitmapFont fontTitle;
	private final BitmapFont fontText;
	
	public ScriptInfoDialog(final BaseScreen screen, final String author, final String comment) {
		super(screen);
		fontTitle = super.resources.getBitmapTrueFont(55);
		fontText = super.resources.getBitmapTrueFont(40);
	
		setOKButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				ScriptInfoDialog.this.dismiss();
			}
		});
		
		initAuthor();
		initComment();
		
		setAuthor(author);
		setComment(comment);
	}
	
	private void initAuthor() {
		final LabelActor title = new LabelActor("AUTHOR:", fontTitle);
		title.setColor(new Color(0.56f, 0.99f, 0.43f, 1.f));
		title.setPosition(Const.Info.X_AUTHOR_TITLE, Const.Info.Y_AUTHOR_TITLE);
		this.addActor(title);
		
	}

	private void initComment() {
		final LabelActor title = new LabelActor("COMMENT:", fontTitle);
		title.setColor(new Color(0.56f, 0.99f, 0.43f, 1.f));
		title.setPosition(Const.Info.X_COMMENT_TITLE, Const.Info.Y_COMMENT_TITLE);
		this.addActor(title);		
	}

	public void setAuthor(final String author) {
		final LabelActor info = new LabelActor(author, fontText);
		info.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		info.setPosition(Const.Info.X_AUTHOR_INFO, Const.Info.Y_AUTHOR_INFO);
		this.addActor(info);		
	}
	
	public void setComment(final String comment) {
		final LabelActor info = new LabelActor(comment, fontText);
		info.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f));
		info.setPosition(Const.Info.X_COMMENT_INFO, Const.Info.Y_COMMENT_INFO);
		info.setWrap(true);
		info.setWrapWidth(Const.Info.WIDTH_COMMENT_INFO);
		info.setTextAlign(HAlignment.LEFT);
		this.addActor(info);
	}
}
