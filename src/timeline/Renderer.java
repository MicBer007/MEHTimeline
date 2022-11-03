package timeline;

import java.awt.Color;
import java.awt.Graphics;

import logging.Log;
import logging.LogLevel;
import objects.Dateline;
import objects.Item;
import objects.Period;
import positioning.Hitbox;
import positioning.PeriodPositioning;
import settings.Settings;

public class Renderer {
	
	@SuppressWarnings("unused")
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private int cameraX;
	private int cameraY;
	
	public Renderer() {}
	
	public void prepareForRender(Camera camera) {
		cameraX = (int) (-camera.getXAtCorner());
//		cameraX = Timeline.WIDTH / 2;
//		cameraX = 0;
		cameraY = -camera.getYAtCorner();
	}
	
	public void renderBackground(Graphics g) {
		g.setColor(Settings.BACKGROUND_COLOR);
		g.fillRect(0, 0, Timeline.WIDTH, Timeline.HEIGHT);
	}
	
	public void renderLine(Graphics g, Camera camera, Dateline dateline) {
		g.setColor(Color.BLACK);
		int markerStartY = dateline.getPositioning().getDatelineYPosition() - Settings.SIGNIFICANT_DATE_MARKER_HEIGHT + cameraY;
		g.drawLine(0, dateline.getPositioning().getDatelineYPosition() + cameraY, Timeline.WIDTH, dateline.getPositioning().getDatelineYPosition() + cameraY);
		
		for(Integer x: dateline.getPositioning().getDateXPositionsAfterCameraManipulations()) {
			g.drawLine(x + cameraX, markerStartY, x + cameraX, markerStartY + Settings.SIGNIFICANT_DATE_MARKER_HEIGHT * 2);
		}
		
		g.drawLine(cameraX, 0, cameraX, Timeline.HEIGHT);
		g.setColor(Color.RED);
		g.drawLine(Timeline.WIDTH / 2, 0, Timeline.WIDTH / 2, Timeline.HEIGHT);
	}
	
	public void renderPeriod(Graphics g, Period period, Camera camera) {
		PeriodPositioning positions = period.getPositioning();
		Hitbox h = positions.getHitbox();
		
		g.setColor(period.getDisplayColour());
		g.fillRect(h.getX() + cameraX, h.getY() + cameraY, h.getWidth(), h.getHeight());
		
//		int textY = positions.getNameYPosition() + cameraY;
//		g.setColor(period.getTextColour());
//		for(int i = 0; i < positions.getNameXPositions().size(); i++) {
//			g.drawString(period.getPeriodName(), positions.getNameXPositions().get(i) + cameraX, textY);
//		}
	}
	
	public void renderItem(Graphics g, Item item) {
		
	}

}