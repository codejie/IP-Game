package jie.android.ip.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import jie.android.ip.CommonConsts.PackConfig;
import jie.android.ip.IPGame;
import jie.android.ip.screen.BaseScreen;

public class TestScreen extends BaseScreen {

	private final TextureAtlas atlas;
	private final Skin skin;
	
	public TestScreen(IPGame game) {
		super(game);
	
		atlas = game.getResources().getTextureAtlas(PackConfig.SCREEN_PLAY);
		skin = new Skin();
		skin.addRegions(atlas);
		
		initButtons();
	}

	private void initButtons() {
		Button.ButtonStyle style = new Button.ButtonStyle(skin.getDrawable("cmd_run"), skin.getDrawable("lines_small_title"), skin.getDrawable("cmd_run"));
		Button btn = new Button(style);
		btn.setBounds(10, 10, 128, 128);
		btn.setChecked(true);
		this.addActor(btn);
	}

	

}
