package jie.android.ip.screen;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.StateImageActor;

public class DDTestScreen extends BaseScreen {

	private BaseGroup group;
	
	private StateImageActor actor;
	private boolean isSelected = false;
	private float cx, cy;
	
	public DDTestScreen(IPGame game) {
		super(game);
		
		initGroup();		
	}
	
	private void initGroup() {
		group = new BaseGroup();
		group.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		group.setScale(1.0f);
		
		group.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (group.hit(x, y, true) == group) {
					if (isSelected) {
						actor.setPosition(x - cx, y - cy);
						actor.setState(2);
						isSelected = false;
					}
				}
			}			
		});
		
		actor = new StateImageActor(1, game.getResources().getSkin().getRegion("ic"));
		actor.addState(2, game.getResources().getSkin().getRegion("t"));
		actor.setPosition(0, 0);
		actor.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isSelected = true;
				cx = x;
				cy = y;
			}
		});
		
		group.addActor(actor);
		
		this.addActor(group);
	}
	
	

}
