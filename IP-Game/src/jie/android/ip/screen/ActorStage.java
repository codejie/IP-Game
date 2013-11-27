package jie.android.ip.screen;

import jie.android.ip.CommonConsts.ScreenConfig;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class ActorStage extends Stage {

	
	public ActorStage(SpriteBatch spriteBatch) {
		super(ScreenConfig.WIDTH, ScreenConfig.HEIGHT, true, spriteBatch);
		// TODO Auto-generated constructor stub
	}

	public void render(float delta) {
		this.act(delta);
		this.draw();		
	}

	public InputProcessor getInputProcessor() {
		return this;
	}

}
