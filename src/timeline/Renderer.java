package timeline;

import java.awt.Color;
import java.awt.Graphics;

import logging.Log;
import logging.LogLevel;
import objects.Item;
import objects.Period;
import positioning.Hitbox;
import positioning.PeriodPositioning;
import settings.Settings;

public class Renderer {
	
	@SuppressWarnings("unused")
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	public Renderer() {}
	
	public void renderBackground(Graphics g) {
		g.setColor(Settings.BACKGROUND_COLOR);
		g.fillRect(0, 0, Timeline.WIDTH, Timeline.HEIGHT);
	}
	
	public void renderLine(Graphics g, Camera camera) {
		g.setColor(Color.BLACK);
		int lineHeight = Settings.DATELINE_HEIGHT - camera.getY();
		g.drawLine(0, lineHeight, Timeline.WIDTH, lineHeight);
	}
	
	public void renderPeriod(Graphics g, Period period, Camera camera) {
		PeriodPositioning positions = period.getPositioning();
		Hitbox h = positions.getHitbox();
		
		g.setColor(period.getDisplayColour());
		g.fillRect(h.getX() - camera.getX(), h.getY() - camera.getY(), h.getWidth(), h.getHeight());
		
		int textY = positions.getNameYPosition() - camera.getY();
		g.setColor(period.getTextColour());
		for(int i = 0; i < positions.getNameXPositions().size(); i++) {
			g.drawString(period.getPeriodName(), positions.getNameXPositions().get(i) - camera.getX(), textY);
		}
	}
	
	public void renderItem(Graphics g, Item item, Camera camera) {
		
	}

}