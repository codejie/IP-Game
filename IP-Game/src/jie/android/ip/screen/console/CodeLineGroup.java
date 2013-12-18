package jie.android.ip.screen.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ConsoleGroupConfig;
import jie.android.ip.CommonConsts.ResourceConfig;
import jie.android.ip.screen.actor.BaseGroup;
import jie.android.ip.screen.actor.ImageActor;
import jie.android.ip.screen.console.Code.Button;
import jie.android.ip.screen.console.Code.Type;

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
		
		initButtons(true);
	}

	public int getIndex() {
		return index;
	}

	public final State getState() {
		return state;
	}
	
	@Override
	protected void initStage() {
		initStage(true);
	}
	
	private void initStage(boolean small) {
		if (small) {
			smallBg = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_BG_SMALL));
			smallBg.setBounds(0, 0, ConsoleGroupConfig.Lines.Small.WIDTH_BG, ConsoleGroupConfig.Lines.Small.HEIGHT_BG);
			smallBg.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), -1, null);
					}
				}				
			});
			this.addActor(smallBg);
			
			smallTitle = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_TITLE_SMALL));
			smallTitle.setBounds(0, 0, ConsoleGroupConfig.Lines.Small.WIDTH_TITLE, ConsoleGroupConfig.Lines.Small.HEIGHT_TITLE);
			smallTitle.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), -1, null);
					}
				}				
			});
			
			this.addActor(smallTitle);
		} else {
			bigBg = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_BG_BIG));
			bigBg.setBounds(0, 0, ConsoleGroupConfig.Lines.Big.WIDTH_BG, ConsoleGroupConfig.Lines.Big.HEIGHT_BG);
//			bigBg.addListener(new ClickListener() {
//				@Override
//				public void clicked(InputEvent event, float x, float y) {
//					if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
//						onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), null);
//					}
//				}				
//			});
			this.addActor(bigBg);
		
			bigTitle = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_LINE_TITLE_BIG));
			bigTitle.setBounds(0, 0, ConsoleGroupConfig.Lines.Big.WIDTH_TITLE, ConsoleGroupConfig.Lines.Big.HEIGHT_TITLE);
			bigTitle.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), -1, null);
					}
				}				
			});
			
			this.addActor(bigTitle);			
		}
	}
	
	private void initButtons(boolean small) {
		
		if (small) {
			for (int i = 0; i < buttons.length; ++ i) {
				buttons[i].smallActor = makeImageActor(buttons[i], i, true);
				this.addActor(buttons[i].smallActor);
			}
		} else {
			for (int i = 0; i < buttons.length; ++ i) {
				buttons[i].bigActor = makeImageActor(buttons[i], i, false);
				this.addActor(buttons[i].bigActor);
			}
		}
	}
	
	
//	private Actor makeImageActor(final Code.Button btn, final int pos,  boolean small) {
//		final Code.Type type = btn.type;
//		ImageActor ret = null;
//		if (small) {
//			if (type == Code.Type.NONE) {
//				ret = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_NONE_SMALL));
//				setButtonBounds(ret, pos, false, true);
//				
//			} else if (type == Code.Type.IF_NONE) {
//				ret = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_IF_NONE_SMALL));
//				setButtonBounds(ret, pos, true, true);
//			}
//						
//		} else {
//			if (type == Code.Type.NONE) {
//				ret = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_NONE_BIG));
//				setButtonBounds(ret, pos, false, false);
//			} else if (type == Code.Type.IF_NONE) {
//				ret = new ImageActor(atlas.findRegion(ResourceConfig.CONSOLE_CODE_IF_NONE_BIG));				
//				setButtonBounds(ret, pos, true, false);
//			}
//			
//			if (ret != null) {
//				ret.addListener(new ClickListener() {
//					
//					@Override
//					public void clicked(InputEvent event, float x, float y) {
//						if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
//							onClickListener.onClick(Code.OnButtonListener.Which.CODE, CodeLineGroup.this.getIndex(), pos, btn);
//						}
//					}				
//				});			
//			}			
//		}		
//		
//		return ret;
//	}
	
	private Actor makeImageActor(final Code.Button btn, final int pos,  boolean small) {
		final Code.Type type = btn.type;
		ImageActor ret = null;
		
		if (type == Code.Type.NONE) {
			ret = new ImageActor(atlas.findRegion(small ? ResourceConfig.CONSOLE_CODE_NONE_SMALL : ResourceConfig.CONSOLE_CODE_NONE_BIG));
		} else if (type == Code.Type.RIGHT) {
		} else if (type == Code.Type.LEFT) {
		} else if (type == Code.Type.ACT) {
		} else if (type == Code.Type.IF_0) {
		} else if (type == Code.Type.IF_1) {
		} else if (type == Code.Type.IF_2) {
		} else if (type == Code.Type.IF_3) {
		} else if (type == Code.Type.IF_ANY) {
		} else if (type == Code.Type.IF_NONE) {
			ret = new ImageActor(atlas.findRegion(small ? ResourceConfig.CONSOLE_CODE_IF_NONE_SMALL : ResourceConfig.CONSOLE_CODE_IF_NONE_BIG));
		} else if (type == Code.Type.CALL_0) {
		} else if (type == Code.Type.CALL_1) {
		} else if (type == Code.Type.CALL_2) {
		} else if (type == Code.Type.CALL_3) {
		} else {
			return null;
		}

		if (ret != null) {
			if (!small) {
				ret.addListener(new ClickListener() {
					
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
							onClickListener.onClick(Code.OnButtonListener.Which.CODE, CodeLineGroup.this.getIndex(), pos, btn);
						}
					}				
				});			
			}
			
			setButtonBounds(ret, pos, type.isJudge(), small);
		}
		
		return ret;	
	}	
	
	private void setButtonBounds(final ImageActor actor, int pos, boolean judge, boolean small) {
		float x = 0, y = 0;
		if (small) {
			if (pos % 2 == 0) {
				//order
				x = ConsoleGroupConfig.Lines.Small.WIDTH_TITLE + (ConsoleGroupConfig.Lines.Small.WIDTH_BUTTON_ORDER + ConsoleGroupConfig.Lines.Small.SPACE_X) * (pos / 2) + ConsoleGroupConfig.Lines.Small.SPACE_X;
				y = ConsoleGroupConfig.Lines.Small.SPACE_Y;
			} else {
				x = ConsoleGroupConfig.Lines.Small.WIDTH_TITLE + (ConsoleGroupConfig.Lines.Small.WIDTH_BUTTON_ORDER + ConsoleGroupConfig.Lines.Small.SPACE_X) * ((pos - 1) / 2) + ConsoleGroupConfig.Lines.Small.SPACE_X;
				y = ConsoleGroupConfig.Lines.Small.SPACE_Y + ConsoleGroupConfig.Lines.Small.HEIGHT_BUTTON_ORDER - ConsoleGroupConfig.Lines.Small.HEIGHT_BUTTON_JUDGE; 
			}
		} else {
			if (pos %2 == 0) {
				x = ConsoleGroupConfig.Lines.Big.WIDTH_TITLE + (ConsoleGroupConfig.Lines.Big.WIDTH_BUTTON_CODE + ConsoleGroupConfig.Lines.Big.SPACE_X) * (pos / 2) + ConsoleGroupConfig.Lines.Big.SPACE_X;
				y = ConsoleGroupConfig.Lines.Big.SPACE_Y;				
			} else {
				x = ConsoleGroupConfig.Lines.Big.WIDTH_TITLE + (ConsoleGroupConfig.Lines.Big.WIDTH_BUTTON_CODE + ConsoleGroupConfig.Lines.Big.SPACE_X) * ((pos - 1) / 2) + ConsoleGroupConfig.Lines.Big.SPACE_X;
				y = ConsoleGroupConfig.Lines.Big.SPACE_Y + ConsoleGroupConfig.Lines.Big.HEIGHT_BUTTON_CODE;// - ConsoleGroupConfig.Lines.Big.HEIGHT_BUTTON_JUDGE; 
			}
		}
		
		actor.setBounds(x, y, actor.getWidth(), actor.getHeight());
	}
	
	public void setState(final State state) {
		if (this.state == state) {
			return;
		}

		if (state == State.SMALL) {
			setSmallState();
		} else {
			setBigState();
		}
	}
	
	private void setSmallState() {
		//hide big
		if (bigBg != null) {
			bigBg.setVisible(false);
		}
		
		if (bigTitle != null) {
			bigTitle.setVisible(false);
		}
		
		for (int i = 0; i < buttons.length; ++ i) {
			final Actor btn = buttons[i].bigActor;
			if (btn != null) {
				btn.setVisible(false);
			}
		}
		
		// show small
		if (smallBg == null || smallTitle == null) {
			initStage(true);
		} else {
			smallBg.setVisible(true);
			smallTitle.setVisible(true);
		}
		
		if (buttons[0].smallActor != null) {
			for (int i = 0; i < buttons.length; ++ i) {
				final Actor btn = buttons[i].smallActor;
				if (btn != null) {
					btn.setVisible(true);
				}				
			}			
		} else {
			initButtons(true);
		}
		
		state = State.SMALL;		
	}
	
	private void setBigState() {
		// hide small actors			
		if (smallBg != null) {
			smallBg.setVisible(false);
		}
		
		if (smallTitle != null) {
			smallTitle.setVisible(false);
		}
		
		for (int i = 0; i < buttons.length; ++ i) {
			final Actor btn = buttons[i].smallActor;
			if (btn != null) {
				btn.setVisible(false);
			}
		}
		
		// show big actors
		if (bigBg == null || bigTitle == null) {
			initStage(false);
		} else {
			bigBg.setVisible(true);
			bigTitle.setVisible(true);
		}		
		
		if (buttons[0].bigActor != null) {
			for (int i = 0; i < buttons.length; ++ i) {
				final Actor btn = buttons[i].bigActor;
				if (btn != null) {
					btn.setVisible(true);
				}				
			}			
		} else {
			initButtons(false);
		}
		
		state = State.BIG;
	}

	public void updateCodeButton(int pos, final Code.Type type) {
		Code.Button btn = buttons[pos];
		if (btn.smallActor != null) {
			this.removeActor(btn.smallActor);
		}
		if (btn.bigActor != null) {
			this.removeActor(btn.bigActor);
		}
		
		btn = new Code.Button(type);
		btn.smallActor = makeImageActor(btn, pos, true);		
		btn.bigActor = makeImageActor(btn, pos, false);
		
		btn.smallActor.setVisible(state == State.SMALL);
		btn.bigActor.setVisible(state == State.BIG);
		
		this.addActor(btn.smallActor);
		this.addActor(btn.bigActor);
		
		if (!type.isJudge()) {
			buttons[pos + 1].smallActor.setZIndex(btn.smallActor.getZIndex() + 1);
		}
		
		buttons[pos] = btn;		
	}
	
}
