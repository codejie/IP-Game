package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.CodeConfig;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.Code.Button;
import jie.android.ip.screen.console.Code.Lines;
import jie.android.ip.screen.console.Code.OnButtonListener;
import jie.android.ip.screen.console.Code.Type;
import jie.android.ip.utils.Utils;

public class CodeLineGroup extends BaseGroup {

	protected static final String Tag = CodeLineGroup.class.getSimpleName();	
	
	public enum State {
		SMALL, BIG;
	}
	
	private final int index;
	private final Resources resources;
	private final TextureAtlas atlas;
	
	private State state = State.SMALL;

	private final Code.Button buttons[];
	
	private ImageActor smallBg, bigBg;
	private ImageActor smallTitle, bigTitle;
	
	private final Code.OnButtonListener onClickListener;
	
	public CodeLineGroup(int index, final Button[] buttons, final Code.OnButtonListener codeListener, final Resources resources) {
		this.index = index;
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		this.buttons = buttons;
		
		this.onClickListener = codeListener;
	
		initStage();
		
		initButtons();
	}

	public int getIndex() {
		return index;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}	
	
	@Override
	protected void initStage() {
		smallBg = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_BG_SMALL));
		smallBg.setBounds(0, 0, smallBg.getWidth(), smallBg.getHeight());
		smallBg.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
					onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), null);
				}
			}				
		});
		this.addActor(smallBg);
		
		smallTitle = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_TITLE_SMALL));
		smallTitle.setBounds(0, 0, smallTitle.getWidth(), smallTitle.getHeight());
		smallTitle.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
					onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), null);
				}
			}				
		});
		
		this.addActor(smallTitle);
	}
	
	private void initButtons() {
		for (int i = 0; i < CodeConfig.SIZE_CODE_PER_LINE; ++ i) {
			//small
			final Code.Button btn = buttons[i];
			btn.smallActor = makeImageActor(i, btn.type, true);
			if (btn.smallActor != null) {
				btn.smallActor.addListener(new ClickListener() {

					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
							onClickListener.onClick(Code.OnButtonListener.Which.CODE, CodeLineGroup.this.getIndex(), btn);
						}
					}				
				});
			}
			
			this.addActor(btn.smallActor);
			
			//big
//			buttons[i].bigActor = makeImageActor(i, buttons[i].type, false);			
//			buttons[i].bigActor.setVisible(false);		
//			this.addActor(buttons[i].bigActor);
		}
	}

	
	private Actor makeImageActor(int pos, Code.Type type, boolean small) {
		ImageActor ret = null;
		if (small) {
			if (type == Code.Type.NONE) {
				ret = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_DEFAULT_SMALL));
				float x = CodeConfig.WIDTH_CODE_TITLE_SMALL + (CodeConfig.WIDTH_SMALL_CODE_BUTTON + CodeConfig.SPACE_X_CODE) * pos / 2;
				float y = CodeConfig.SPACE_Y_CODE / 2;			
				ret.setBounds(x, y, ret.getWidth(), ret.getHeight());
			} else if (type == Code.Type.IF_0) {
				ret = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_IF_0_SMALL));
				float x = CodeConfig.WIDTH_CODE_TITLE_SMALL + (CodeConfig.WIDTH_SMALL_CODE_BUTTON + CodeConfig.SPACE_X_CODE) * (pos - 1) / 2;
				float y = CodeConfig.HEIGHT_SMALL_CODE_LINE - CodeConfig.SPACE_Y_CODE / 2 - ret.getHeight();
				ret.setBounds(x, y, ret.getWidth(), ret.getHeight());
			}
		} else {
			
		}
		
		return ret;
	}
	
}
