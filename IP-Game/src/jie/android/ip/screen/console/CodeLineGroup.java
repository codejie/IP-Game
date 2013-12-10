package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import jie.android.ip.utils.Utils;

public class CodeLineGroup extends BaseGroup {

	protected static final String Tag = CodeLineGroup.class.getSimpleName();	
	
	public enum State {
		SMALL, BIG;
	}
	
	private final int index;
	private final Resources resources;
	private final TextureAtlas atlas;
	
	private final Code.Lines codeLines;
	
	private State state = State.SMALL;
	
	private ImageActor smallBg, bigBg;
	
	
	public CodeLineGroup(int index, final Code.Lines codeLines, final Resources resources) {
		this.index = index;
		this.resources = resources;
		this.atlas = this.resources.getAssetManager().get(ResourceConfig.CONSOLE_PACK_NAME, TextureAtlas.class);
		this.codeLines = codeLines;
	
		initStage();
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
		initSmallGroup();
	}

	private void initSmallGroup() {
		
		ImageActor bg = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_BG_SMALL));
		bg.setBounds(0, 0, bg.getWidth(), bg.getHeight());
		this.addActor(bg);
		
		ImageActor title = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_TITLE_SMALL));
		this.setBounds(0, 0, title.getWidth(), title.getHeight());
		this.addActor(title);
		
		for (int i = 0; i < CodeConfig.SIZE_CODE_PER_LINE; ++ i) {
			ImageActor def = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_DEFAULT_SMALL));
			float x = title.getWidth() + (def.getWidth() + CodeConfig.SPACE_X_CODE) * i;
			float y = CodeConfig.SPACE_X_CODE / 2;			
			def.setBounds(x, y, def.getWidth(), def.getHeight());
			this.addActor(def);
		}
		
		this.setWidth(bg.getWidth());
		this.setHeight(bg.getHeight());
		
		//click listener
		
		this.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Utils.log(Tag, "code line group : index = " + getIndex() + " x = " + x + " y = " + y);
			}			
		});
//		
//		this.addActor(groupSmall);		
	}
	
	private void update() {
		
	}
	
}
