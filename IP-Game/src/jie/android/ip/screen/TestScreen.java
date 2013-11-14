package jie.android.ip.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import jie.android.ip.IPGame;
import jie.android.ip.screen.actor.SpriteActor;

public class TestScreen extends BaseScreen {

	private Image texture;
	
	public TestScreen(IPGame game) {
		super(game);
		
		initActors();
	}

	private void initActors() {
		
		BaseGroup group = new BaseGroup();
		
		SpriteActor sa = new SpriteActor(game.getResources().getSkin().getRegion("ic"));
		sa.setPosition(x, y)
		
		texture = new Image(game.getResources().getSkin().getRegion("ic"));// new Texture(Gdx.files.internal("data/ic.png")));
		texture.setPosition(0, 0);	
		group.addActor(texture);
		
		
		Image i = new Image(game.getResources().getSkin().getRegion("ic"));
		i.setPosition( 100, 100);
		group.addActor(i);
		
		group.setBounds(0, 0, 200, 200);
		group.setScale(1.5f);
		
		
		
		
//		texture.setBounds(32, 32, 200, 100);
		//stage.addActor(texture);
		this.addActor(group);
		
	}
	
	

}
