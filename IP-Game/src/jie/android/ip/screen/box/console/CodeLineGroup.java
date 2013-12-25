package jie.android.ip.screen.box.console;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.Resources;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.box.BoxConfig.Const;
import jie.android.ip.screen.box.BoxConfig.Image;
import jie.android.ip.screen.box.console.Code.Button;

public class CodeLineGroup extends BaseGroup {

	protected static final String Tag = CodeLineGroup.class.getSimpleName();	
	
	public enum State {
		SMALL, BIG;
	}
	
	private final int index;
	private final TextureAtlas textureAtlas;
	
	private State state = State.SMALL;

	private final Code.Button buttons[];
	
	private ImageActor smallBg, bigBg;
	private ImageActor smallTitle, bigTitle;
	
	private final Code.OnButtonListener onClickListener;
	
	public CodeLineGroup(int index, final Button[] buttons, final Code.OnButtonListener codeListener, final Resources resources) {
		this.index = index;
		this.textureAtlas = resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
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
			smallBg = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Small.BG));
			smallBg.setBounds(0, 0, Const.Console.Lines.Small.WIDTH_BG, Const.Console.Lines.Small.HEIGHT_BG);
			smallBg.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), -1, null);
					}
				}				
			});
			this.addActor(smallBg);
			
			if (index == 0) {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Small.TITLE_0));
			} else if (index == 1) {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Small.TITLE_1));
			} else if (index == 2) {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Small.TITLE_2));
			} else {
				smallTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Small.TITLE_3));
			}

			smallTitle.setBounds(0, 0, Const.Console.Lines.Small.WIDTH_TITLE, Const.Console.Lines.Small.HEIGHT_TITLE);
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
			bigBg = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Big.BG));
			bigBg.setBounds(0, 0, Const.Console.Lines.Big.WIDTH_BG, Const.Console.Lines.Big.HEIGHT_BG);
//			bigBg.addListener(new ClickListener() {
//				@Override
//				public void clicked(InputEvent event, float x, float y) {
//					if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
//						onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), null);
//					}
//				}				
//			});
			this.addActor(bigBg);
		
			if (index == 0) {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Big.TITLE_0));
			} else if (index == 1) {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Big.TITLE_1));
			} else if (index == 2) {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Big.TITLE_2));
			} else {
				bigTitle = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Big.TITLE_3));
			}
			bigTitle.setBounds(0, 0, Const.Console.Lines.Big.WIDTH_TITLE, Const.Console.Lines.Big.HEIGHT_TITLE);
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
	
	private Actor makeImageActor(final Code.Button btn, final int pos,  boolean small) {
		final Code.Type type = btn.type;
		ImageActor ret = null;
		
		if (type == Code.Type.NULL) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_NULL : Image.Console.Lines.Big.CODE_NULL));
		} else if (type == Code.Type.IF_NULL) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_NULL : Image.Console.Lines.Big.CODE_IF_NULL));			
		} else if (type == Code.Type.RIGHT) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_RIGHT : Image.Console.Lines.Big.CODE_RIGHT));
		} else if (type == Code.Type.LEFT) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_LEFT : Image.Console.Lines.Big.CODE_LEFT));
		} else if (type == Code.Type.ACT) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_ACT : Image.Console.Lines.Big.CODE_ACT));
		} else if (type == Code.Type.IF_0) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_0 : Image.Console.Lines.Big.CODE_IF_0));
		} else if (type == Code.Type.IF_1) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_1 : Image.Console.Lines.Big.CODE_IF_1));
		} else if (type == Code.Type.IF_2) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_2 : Image.Console.Lines.Big.CODE_IF_2));
		} else if (type == Code.Type.IF_3) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_3 : Image.Console.Lines.Big.CODE_IF_3));
		} else if (type == Code.Type.IF_ANY) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_ANY : Image.Console.Lines.Big.CODE_IF_ANY));
		} else if (type == Code.Type.IF_NONE) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_IF_NONE : Image.Console.Lines.Big.CODE_IF_NONE));
		} else if (type == Code.Type.CALL_0) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_CALL_0 : Image.Console.Lines.Big.CODE_CALL_0));
		} else if (type == Code.Type.CALL_1) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_CALL_1 : Image.Console.Lines.Big.CODE_CALL_1));
		} else if (type == Code.Type.CALL_2) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_CALL_2 : Image.Console.Lines.Big.CODE_CALL_2));
		} else if (type == Code.Type.CALL_3) {
			ret = new ImageActor(textureAtlas.findRegion(small ? Image.Console.Lines.Small.CODE_CALL_3 : Image.Console.Lines.Big.CODE_CALL_3));
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
		float w = 0, h = 0;
		if (small) {
			if (pos % 2 == 0) {				
				x = Const.Console.Lines.Small.WIDTH_TITLE + (Const.Console.Lines.Small.WIDTH_BUTTON_ORDER + Const.Console.Lines.Small.SPACE_X) * (pos / 2) + Const.Console.Lines.Small.SPACE_X;
				y = Const.Console.Lines.Small.SPACE_Y + Const.Console.Lines.Small.HEIGHT_BUTTON_ORDER - Const.Console.Lines.Small.HEIGHT_BUTTON_JUDGE;
				w = Const.Console.Lines.Small.WIDTH_BUTTON_JUDGE;
				h = Const.Console.Lines.Small.HEIGHT_BUTTON_JUDGE;				
			} else {
				//order
				x = Const.Console.Lines.Small.WIDTH_TITLE + (Const.Console.Lines.Small.WIDTH_BUTTON_ORDER + Const.Console.Lines.Small.SPACE_X) * ((pos - 1) / 2) + Const.Console.Lines.Small.SPACE_X;
				y = Const.Console.Lines.Small.SPACE_Y;
				w = Const.Console.Lines.Small.WIDTH_BUTTON_ORDER;
				h = Const.Console.Lines.Small.HEIGHT_BUTTON_ORDER;
			}
		} else {
			if (pos % 2 == 0) {				
				x = Const.Console.Lines.Big.WIDTH_TITLE + (Const.Console.Lines.Big.WIDTH_BUTTON_JUDGE + Const.Console.Lines.Big.SPACE_X) * (pos / 2) + Const.Console.Lines.Big.SPACE_X;
				y = Const.Console.Lines.Big.SPACE_Y + Const.Console.Lines.Big.HEIGHT_BUTTON_ORDER;// - Const.Console.Lines.Big.HEIGHT_BUTTON_JUDGE;
				w = Const.Console.Lines.Big.WIDTH_BUTTON_JUDGE;
				h = Const.Console.Lines.Big.HEIGHT_BUTTON_JUDGE;					
			} else {
				x = Const.Console.Lines.Big.WIDTH_TITLE + (Const.Console.Lines.Big.WIDTH_BUTTON_ORDER + Const.Console.Lines.Big.SPACE_X) * ((pos - 1) / 2) + Const.Console.Lines.Big.SPACE_X;
				y = Const.Console.Lines.Big.SPACE_Y;
				w = Const.Console.Lines.Big.WIDTH_BUTTON_ORDER;
				h = Const.Console.Lines.Big.HEIGHT_BUTTON_ORDER;				
			}
		}
		
		actor.setBounds(x, y, w, h);
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
			buttons[pos - 1].smallActor.setZIndex(btn.smallActor.getZIndex() + 1);
		}
		
		buttons[pos] = btn;		
	}
	
	public void removeCodeButton(int pos) {
		
	}
}
