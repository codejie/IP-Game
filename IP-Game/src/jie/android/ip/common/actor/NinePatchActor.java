package jie.android.ip.common.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NinePatchActor extends Actor {
	
	private final NinePatch ninePatch;
	
	public NinePatchActor(final TextureRegion region, int frameSize, int x, int y, int width, int height) {
		ninePatch = new NinePatch(region, frameSize, frameSize, frameSize, frameSize);
		
		super.setBounds(x, y, width, height); 
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		ninePatch.draw(batch, super.getX(), super.getY(), super.getWidth(), super.getHeight());
	}
}
