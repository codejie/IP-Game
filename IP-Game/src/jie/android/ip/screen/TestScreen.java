package jie.android.ip.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import jie.android.ip.IPGame;
import jie.android.ip.group.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.OnActorInputListener;
import jie.android.ip.utils.Utils;

public class TestScreen extends BaseScreen {

	private Image texture;
	
	private OnActorInputListener listener;
	
	public TestScreen(IPGame game) {
		super(game);
		
		listener = new OnActorInputListener() {

			@Override
			public void onTouchUp(Actor actor, float x, float y, int pointer, int button) {
				Utils.log("test", "touchUp - x = " + x + " y = " + y);
			}

			@Override
			public void onTouchDown(Actor actor, float x, float y, int pointer, int button) {
				Utils.log("test", "touchDown - x = " + x + " y = " + y);				
			}

			@Override
			public void onTouchDragged(Actor actor, float x, float y, int pointer) {
				Utils.log("test", "touchDragged - x = " + x + " y = " + y);			
			}
			
		};
		
		initActors();
		
	}

	private void initActors() {
		
		BaseGroup group = new BaseGroup();
		
//		SpriteActor sa = new SpriteActor(game.getResources().getSkin().getRegion("ic"));
//
//		group.addActor(sa);
		
//		texture = new Image(game.getResources().getSkin().getRegion("ic"));// new Texture(Gdx.files.internal("data/ic.png")));
//		texture.setPosition(0, 0);	
//		group.addActor(texture);
		
		
//		Image i = new Image(game.getResources().getSkin().getRegion("ic"));
//		i.setPosition( 100, 100);
//		group.addActor(i);

		ImageActor ia = new ImageActor("ic", game.getResources().getSkin().getRegion("ic"));
		ia.setPosition(0, 0);
		group.addActor(ia);
		ia.tweenToX(tweenManager, 100, 1.0f);
		ia.setInputListener(listener);
		
		group.setBounds(0, 0, 200, 200);
		group.setScale(8.0f);
		
		
		
		
//		texture.setBounds(32, 32, 200, 100);
		//stage.addActor(texture);
		this.addActor(group);
		
	}
	
	

}
