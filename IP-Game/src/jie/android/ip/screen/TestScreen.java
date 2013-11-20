package jie.android.ip.screen;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.IPGame;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.executor.Analyser;
import jie.android.ip.executor.CommandSet;
import jie.android.ip.executor.Executor;
import jie.android.ip.executor.OnCommandListener;
import jie.android.ip.group.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.actor.ImageActorAccessor;
import jie.android.ip.screen.actor.OnActorInputListener;
import jie.android.ip.screen.box.BoxConfig;
import jie.android.ip.screen.box.BoxManager;
import jie.android.ip.script.Script;
import jie.android.ip.utils.Utils;

public class TestScreen extends BaseScreen {

	private ImageActor block;
	private Button btn;
	
	private BoxManager bmanager;
	
	private OnCommandListener cmdListener = new OnCommandListener() {

		@Override
		public void onStart() {
		}

		@Override
		public void onEnd() {
		}

		@Override
		public void onCall(int func, int index) {
		}

		@Override
		public void onAct(int func, int index, Object param1, Object param2) {
			
			//Tween.to(block, ImageActorAccessor.POSITION_X, 0.1f).target(block.getX() + 100).start(tweenManager);
			
//			block.setPosition(block.getX() + 100, block.getY());
		}

		@Override
		public void onCheck(int func, int index, Object param1, Object param2) {
		}

		@Override
		public void onBreakPoint(int func, int index, String cmd, Object param1, Object param2) {			
		}
		
	};
	
//	private OnActorInputListener listener;
	
	public TestScreen(IPGame game) {
		super(game);
		
//		listener = new OnActorInputListener() {
//
//			@Override
//			public void onTouchUp(Actor actor, float x, float y, int pointer, int button) {
//				Utils.log("test", "touchUp - x = " + x + " y = " + y);
//			}
//
//			@Override
//			public void onTouchDown(Actor actor, float x, float y, int pointer, int button) {
//				Utils.log("test", "touchDown - x = " + x + " y = " + y);				
//			}
//
//			@Override
//			public void onTouchDragged(Actor actor, float x, float y, int pointer) {
//				Utils.log("test", "touchDragged - x = " + x + " y = " + y);			
//			}
//			
//		};
		
		initActors();
		
		//initCmds();
		
		
		BaseGroup group = new BaseGroup();
		group.setBounds(100, 100, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
//		group.setBounds(200, 100, 100, 100);
//		group.setScale(0.5f);
		
		Script script = new Script();
		script.load("");
		
		BoxConfig config = new BoxConfig();
		config.setSourceGroup(group);
		config.setResources(game.getResources());
		config.setTweenManager(this.tweenManager);
		
		bmanager = new BoxManager(config);
		bmanager.loadScript(script);
//		bmanager.putSource(group);
		
		this.addActor(group);
	
//		Gdx.input.setInputProcessor(this);
	}

	private void initActors() {
		
//		block = new ImageActor("ic", game.getResources().getSkin().getRegion("ic"));
//		block.setPosition(0, 100);
//		this.addActor(block);
		
		btn = new Button(game.getResources().getSkin().getDrawable("ic"));
		btn.setBounds(0, 0, 64, 64);
		btn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Utils.log("testscreen", "clicked");
				bmanager.moveBlock(2, BoxManager.Direction.DOWN);
				//run();
			}			
		});
		this.addActor(btn);
	}
	
	private void initCmds() {
		
	}
	
	private void run() {
		CommandSet cmdset = Analyser.makeCommandSet(".\\doc\\test.xml");
		
		Executor exe = new Executor();
		exe.setDelay(100);
		exe.start(cmdset, cmdListener);
		
	}
	

}
