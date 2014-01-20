package jie.android.ip.common.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ButtonActor extends Button {

	public ButtonActor(final Button.ButtonStyle buttonStyle) {
		super(buttonStyle);
	}
	
	public ButtonActor(final Drawable up, final Drawable down, final Drawable checked) {
		this(new Button.ButtonStyle(up, down, checked));
	}
	
	public ButtonActor(final Skin skin, final String up, final String down, final String checked) {
		this(new Button.ButtonStyle(skin.getDrawable(up), skin.getDrawable(down), skin.getDrawable(checked)));
	}
	
	public ButtonActor(final Skin skin, final String up, final String down) {
		this(new Button.ButtonStyle(skin.getDrawable(up), skin.getDrawable(down), null));
	}

}
