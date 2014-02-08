package jie.android.ip.common.dialog;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import jie.android.ip.screen.BaseScreen;

public class AlertDialog extends BaseDialog {
	
	public interface ButtonClickListener {
		public void onClick();
	}
	
	public AlertDialog(final BaseScreen screen) {
		super(screen);
	}

	public AlertDialog(final BaseScreen screen, final String textRes, final ButtonClickListener positiveListener, final ButtonClickListener negativeListener) {
		super(screen);
		
		setPositiveButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				if (positiveListener != null) {
					positiveListener.onClick();
				}
				AlertDialog.this.dismiss();
			}
		});
		setNegativeButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				if (negativeListener != null) {
					negativeListener.onClick();
				}
				AlertDialog.this.dismiss();
			}
		});
		
		setTextImage(textRes);
	}

	public AlertDialog(final BaseScreen screen, final String textRes, final ButtonClickListener positiveListener) {
		super(screen);
		
		setPositiveButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				if (positiveListener != null) {
					positiveListener.onClick();
				}
				AlertDialog.this.dismiss();
			}
		});
		
		setTextImage(textRes);
	}

	public AlertDialog(final BaseScreen screen, final String text, final BitmapFont font, final Color color, final ButtonClickListener positiveListener, final ButtonClickListener negativeListener) {
		super(screen);

		setPositiveButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				if (positiveListener != null) {
					positiveListener.onClick();
				}
				AlertDialog.this.dismiss();
			}
		});
		setNegativeButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				if (negativeListener != null) {
					negativeListener.onClick();
				}
				AlertDialog.this.dismiss();
			}
		});
		setTextString(text, font, color);
	}
	
	public AlertDialog(final BaseScreen screen, final String text, final BitmapFont font, final Color color, final ButtonClickListener positiveListener) {
		super(screen);

		setPositiveButton(new BaseDialog.ButtonClickListener() {
			
			@Override
			public void onClick(int id) {
				if (positiveListener != null) {
					positiveListener.onClick();
				}
				AlertDialog.this.dismiss();
			}
		});

		setTextString(text, font, color);
	}

}
