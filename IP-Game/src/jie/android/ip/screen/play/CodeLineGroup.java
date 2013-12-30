package jie.android.ip.screen.play;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import jie.android.ip.Resources;
import jie.android.ip.CommonConsts.ScreenConfig;
import jie.android.ip.CommonConsts.ScreenPackConfig;
import jie.android.ip.common.actor.BaseGroup;
import jie.android.ip.common.actor.BaseGroupAccessor;
import jie.android.ip.common.actor.ImageActor;
import jie.android.ip.screen.play.PlayConfig.Const;
import jie.android.ip.screen.play.PlayConfig.Image;

public class CodeLineGroup {

	protected static final String Tag = CodeLineGroup.class.getSimpleName();
	
	public enum LineState {
		SMALL, BIG;
	}
	
	private class LineGroup extends BaseGroup {
		
		private final int index;
		private final TextureAtlas textureAtlas;		
		private final Code.OnButtonListener onClickListener;
		
		private LineState state = LineState.SMALL;		
		
		private ImageActor smallBg, bigBg;
		private ImageActor smallTitle, bigTitle;		
		
		public LineGroup(int index, final Resources resources, final Code.OnButtonListener listener) {
			this.index = index;
			this.textureAtlas = resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
			this.onClickListener = listener;
			
			init();
			
			initStage();
		}
		
		private void init() {
			final Vector2 v = getCodeLinePosition(index);
			this.setBounds(v.x, v.y, Const.Console.Lines.Small.WIDTH_BG, Const.Console.Lines.Small.HEIGHT_BG);
			
			this.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (LineGroup.this.getState() == CodeLineGroup.LineState.SMALL || LineGroup.this.hit(x, y, true) == LineGroup.this) {
						if (onClickListener != null) {
							onClickListener.onClick(Code.OnButtonListener.Which.LINE_GROUP, LineGroup.this.getIndex(), -1);
						}
					}
				}				
			});
		}

		private final Vector2 getCodeLinePosition(int index) {
			Vector2 vct = new Vector2();
			vct.x =	Const.Console.Lines.BASE_X;
			vct.y = Const.Console.Lines.BASE_Y + (Code.NUM_CODE_LINES - index - 1) * (Const.Console.Lines.Small.HEIGHT_BG + Const.Console.Lines.SPACE_LILNES);
			return vct;
		}

		@Override
		protected void initStage() {
			//Small
			smallBg = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Small.BG));
			smallBg.setBounds(0, 0, Const.Console.Lines.Small.WIDTH_BG, Const.Console.Lines.Small.HEIGHT_BG);
			smallBg.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (LineGroup.this.getState() == CodeLineGroup.LineState.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.LINE_GROUP, LineGroup.this.getIndex(), -1);
					}
				}				
			});
			
			smallBg.setVisible(state == LineState.SMALL);
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
					if (LineGroup.this.getState() == LineState.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.LINE_GROUP, LineGroup.this.getIndex(), -1);
					}
				}				
			});
			
			smallTitle.setVisible(state == LineState.SMALL);
			this.addActor(smallTitle);
			
			//Big
			bigBg = new ImageActor(textureAtlas.findRegion(Image.Console.Lines.Big.BG));
			bigBg.setBounds(0, 0, Const.Console.Lines.Big.WIDTH_BG, Const.Console.Lines.Big.HEIGHT_BG);
//				bigBg.addListener(new ClickListener() {
//					@Override
//					public void clicked(InputEvent event, float x, float y) {
//						if (CodeLineGroup.this.getState() == CodeLineGroup.State.BIG && onClickListener != null) {
//							onClickListener.onClick(Code.OnButtonListener.Which.CODE_GROUP, CodeLineGroup.this.getIndex(), null);
//						}
//					}				
//				});
			
			bigBg.setVisible(state == LineState.BIG);
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
					if (LineGroup.this.getState() == LineState.BIG && onClickListener != null) {
						onClickListener.onClick(Code.OnButtonListener.Which.LINE_GROUP, LineGroup.this.getIndex(), -1);
					}
				}				
			});
			
			bigTitle.setVisible(state == LineState.BIG);
			this.addActor(bigTitle);
		}
		
		public int getIndex() {
			return index;
		}

		public final LineState getState() {
			return state;
		}
		
		private void setState(final LineState state) {
			if (this.state == state) {
				return;
			}
			
			if (state == LineState.SMALL) {
				
			}
			
			
		}
	
		public final void loadButtons(final Code.Button[] buttons) {
			for (int i = 0; i < buttons.length; ++ i) {
				//small
				buttons[i].smallActor = makeImageActor(buttons[i], i, true);
				this.addActor(buttons[i].smallActor);
				if (i % 2 != 0) {
					if (buttons[i - 1].smallActor != null) {
						buttons[i - 1].smallActor.setZIndex(buttons[i].smallActor.getZIndex() + 1);
					}
				}				
				//big
				buttons[i].bigActor = makeImageActor(buttons[i], i, false);
				this.addActor(buttons[i].bigActor);				
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
							if (LineGroup.this.getState() == CodeLineGroup.LineState.BIG && onClickListener != null) {
								onClickListener.onClick(Code.OnButtonListener.Which.LINE, LineGroup.this.getIndex(), pos);
							}
						}				
					});			
				}
				
				setButtonBounds(ret, pos, type.isJudge(), small);
				ret.setVisible(small ? state == LineState.SMALL : state == LineState.BIG);
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

		public void setSmallState(TweenManager tweenManager) {
			// TODO Auto-generated method stub
			
		}

		public void setBigState(TweenManager tweenManager) {
		}
		
		public void toggleState(final TweenManager tweenManager) {
			
			final TweenCallback callback = new TweenCallback() {
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					if (state == LineState.SMALL) {
						setScale(1.0f);
						setState(LineState.BIG);					
					} else {
						setZIndex(0x00);
						setScale(1.0f);
						setState(LineState.SMALL);
					}
				}
			};
			
			final Vector2 vct = getCodeLinePosition(index);
			float scale = 0.6f;
			
			if (state == LineState.SMALL) {
				if (index == 0 || index == 1) {
					vct.y = vct.y - (Const.Console.Lines.Big.HEIGHT_BG - Const.Console.Lines.Small.HEIGHT_BG);
				} else {
				
				}
				setZIndex(0x0f);
				scale = 1.8f;			
			} else {
			}

			Timeline.createParallel()
				.push(Tween.to(this, BaseGroupAccessor.POSITION_XY, 0.1f).target(vct.x, vct.y))
				.push(Tween.to(this, BaseGroupAccessor.SCALE_XY, 0.1f).target(scale, scale))
				.setCallback(callback)
			.start(tweenManager);			
		}
	}
	//
	
	public enum PanelState {
		HIDE, JUDGE, ORDER;
		
		public int getId() {
			return this.ordinal();
		}
	}
	
	private class PanelGroup extends BaseGroup {

		private final TextureAtlas textureAtlas;		
		private final Code.OnButtonListener onClickListener;
		
		private final Code.Panel codePanel = new Code.Panel();
		
		private Group groupJudge;
		private Group groupOrder;
		
		private PanelState state = PanelState.HIDE;
		
		public PanelGroup(final Resources resources, final Code.OnButtonListener listener) {
			this.textureAtlas = resources.getTextureAtlas(ScreenPackConfig.SCREEN_BOX);
			this.onClickListener = listener;
			
			initStage();
			
			initPanel();
		}

		@Override
		protected void initStage() {
			groupJudge = new Group();
			ImageActor bg = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.JUDGE_BG));
			bg.setBounds(0, 0, Const.Console.Panel.Judge.WIDTH_BG, Const.Console.Panel.Judge.HEIGHT_BG);
			groupJudge.addActor(bg);
			
			groupOrder = new Group();
			bg = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.ORDER_BG));
			bg.setBounds(0, 0, Const.Console.Panel.Order.WIDTH_BG, Const.Console.Panel.Order.HEIGHT_BG);
			groupOrder.addActor(bg);
			
			groupJudge.setVisible(false);
			groupOrder.setVisible(false);
			
			this.addActor(groupJudge);
			this.addActor(groupOrder);			
		}
		
		private void initPanel() {
			Code.Button buttons[] = codePanel.getJudgeButton();
			for (int i = 0; i < buttons.length; ++ i) {
				final Code.Button btn = buttons[i];
				final int pos = i;
				btn.bigActor = makeImageActor(i, btn.type);
				btn.bigActor.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (onClickListener != null) {
							onClickListener.onClick(Code.OnButtonListener.Which.PANEL, PanelState.JUDGE.getId(), pos);
						}
					}
				});
				groupJudge.addActor(btn.bigActor);
			}
			
			buttons = codePanel.getOrderButton();
			for (int i = 0; i < buttons.length; ++ i) {
				final Code.Button btn = buttons[i];
				final int pos = i;
				btn.bigActor = makeImageActor(i, btn.type);
				btn.bigActor.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						if (onClickListener != null) {
							onClickListener.onClick(Code.OnButtonListener.Which.PANEL, PanelState.ORDER.getId(), pos);
						}
					}
				});
				groupOrder.addActor(btn.bigActor);
			}
		}		
		
		private final Actor makeImageActor(int pos, Code.Type type) {
			ImageActor ret = null;

			if (type == Code.Type.NULL) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_NULL));
			} else if (type == Code.Type.RIGHT) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_RIGHT));
			} else if (type == Code.Type.LEFT) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_LEFT));
			} else if (type == Code.Type.ACT) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_ACT));
			} else if (type == Code.Type.IF_0) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_0));
			} else if (type == Code.Type.IF_1) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_1));
			} else if (type == Code.Type.IF_2) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_2));
			} else if (type == Code.Type.IF_3) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_3));
			} else if (type == Code.Type.IF_ANY) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_ANY));
			} else if (type == Code.Type.IF_NULL) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_NULL));			
			} else if (type == Code.Type.IF_NONE) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_IF_NONE));
			} else if (type == Code.Type.CALL_0) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_0));
			} else if (type == Code.Type.CALL_1) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_1));
			} else if (type == Code.Type.CALL_2) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_2));
			} else if (type == Code.Type.CALL_3) {
				ret = new ImageActor(this.textureAtlas.findRegion(Image.Console.Panel.CODE_CALL_3));
			} else {
				return null;
			}		
				
			float x = Const.Console.Panel.SPACE_X + (Const.Console.Panel.WIDTH_BUTTON + Const.Console.Panel.SPACE_X) * pos;
			float y = Const.Console.Panel.SPACE_Y;
			ret.setBounds(x, y, Const.Console.Panel.WIDTH_BUTTON, Const.Console.Panel.HEIGHT_BUTTON);
			
			return ret;
		}
		
		public final PanelState getState() {
			return state;
		}
		
		public boolean setState(final PanelState state) {
			if (this.state == state) {
				return false;
			}

			this.state = state;
			
			if (this.state == PanelState.JUDGE) {
				groupJudge.setVisible(true);
				groupJudge.setZIndex(0x0F);
				groupOrder.setVisible(false);
				groupOrder.setZIndex(0x00);
				
				this.setBounds(0, 0, Const.Console.Panel.Judge.WIDTH_BG, Const.Console.Panel.Judge.HEIGHT_BG);
			} else if (this.state == PanelState.ORDER) {
				groupJudge.setVisible(false);
				groupJudge.setZIndex(0x00);
				groupOrder.setVisible(true);
				groupOrder.setZIndex(0x0F);
				this.setBounds(0, 0, Const.Console.Panel.Order.WIDTH_BG, Const.Console.Panel.Order.HEIGHT_BG);
			}
			this.setVisible(this.state != PanelState.HIDE);
			
			return true;
		}	
		
		public final Code.Type getButtonType(int state, int pos) {
			
			if (state == PanelState.JUDGE.getId()) {
				final Code.Button[] buttons = codePanel.getJudgeButton();
				return buttons[pos].type;
			} else if (state == PanelState.ORDER.getId()) {
				final Code.Button[] buttons = codePanel.getOrderButton();
				return buttons[pos].type;
			}
			
			return null;
		}

		public void hide(int pos, final TweenManager tweenManager) {
			if (state != PanelState.HIDE) {
				
				if (pos != -1) {
					float x = groupPanel.getX() + (Const.Console.Panel.SPACE_X + Const.Console.Panel.WIDTH_BUTTON) * (pos + 0.5f);
					float y = groupPanel.getY() + Const.Console.Panel.SPACE_Y + Const.Console.Panel.HEIGHT_BUTTON * 0.5f;
					
					TweenCallback completeCallback = new TweenCallback() {
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							setState(PanelState.HIDE);					
						}				
					};
					
					Timeline.createParallel()
						.push(Tween.to(groupPanel, BaseGroupAccessor.POSITION_XY, 0.2f).target(x, y))
						.push(Tween.to(groupPanel, BaseGroupAccessor.SCALE_XY, 0.2f).target(0.0f, 0.0f))
						.setCallback(completeCallback )
						.start(tweenManager);
				} else {
					setState(PanelState.HIDE);	
				}			
			}
			
		}
	}
	
	//
	private final PlayScreen screen;
	private final TweenManager tweenManager;
	
	private final LineGroup[] groupLine = new LineGroup[Code.NUM_CODE_LINES];
	private PanelGroup groupPanel;
	
	private int cacheLineIndex = -1;
	
	private final Code.OnButtonListener listener = new Code.OnButtonListener() {

		@Override
		public void onClick(Code.OnButtonListener.Which which, int index, int pos) {
			if (which == Code.OnButtonListener.Which.LINE_GROUP) {
				onLineGroupClick(index);
			} else if (which == Code.OnButtonListener.Which.LINE) {
				onLineClick(index, pos);
			} else if (which == Code.OnButtonListener.Which.PANEL) {
				onPanelClick(index, pos);
			} else if (which == Code.OnButtonListener.Which.PANEL_GROUP || which == Code.OnButtonListener.Which.BASE_GROUP) {
				onBaseGroupClick();
			}
		}
	};
	
	public CodeLineGroup(final PlayScreen screen) {
		this.screen = screen;
		this.tweenManager = this.screen.getTweenManager();

		initGroups();
	}

	private void initGroups() {
		final Group base = new Group();
		base.setBounds(0, 0, ScreenConfig.WIDTH, ScreenConfig.HEIGHT);
		base.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (base.hit(x, y, true) == base) {
					listener.onClick(Code.OnButtonListener.Which.BASE_GROUP, -1, -1);
				}
			}
			
		});
		screen.addActor(base);
		
		for (int i = 0; i < groupLine.length; ++ i) {
			groupLine[i] = new LineGroup(i, screen.getGame().getResources(), listener);
			
			base.addActor(groupLine[i]);
		}
		
		groupPanel = new PanelGroup(screen.getGame().getResources(), listener);
		base.addActor(groupPanel);
	}
	
	public void load(final Code.Lines lines) {
		for (int i = 0; i < groupLine.length; ++ i) {
			groupLine[i].loadButtons(lines.getFuncButton(i));
		}
	}
	
	protected void onLineGroupClick(int index) {
		groupPanel.hide(-1, tweenManager);
		
		if (index == cacheLineIndex) {
			groupLine[cacheLineIndex].toggleState(tweenManager);
			cacheLineIndex = -1;
		}
		
		groupLine[index].toggleState(tweenManager);
		cacheLineIndex = index;
	}	

	protected void onLineClick(int index, int pos) {
		// TODO Auto-generated method stub
		
	}

	protected void onPanelClick(int index, int pos) {
		// TODO Auto-generated method stub
		
	}

	protected void onBaseGroupClick() {
		// TODO Auto-generated method stub
		
	}

}
