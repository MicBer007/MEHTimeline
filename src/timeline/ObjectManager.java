package timeline;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import logging.Log;
import logging.LogLevel;
import objects.Dateline;
import objects.Item;
import objects.Period;
import positioning.PeriodPositioning;

public class ObjectManager {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private List<Period> periods = new ArrayList<Period>();
	
	private List<Item> items = new ArrayList<Item>();
	
	private Dateline dateline;
	
	private Camera camera;
	private Renderer renderer;
	
	public ObjectManager() {
		camera = new Camera(this, 0, 0);
		renderer = new Renderer();
		
		definePeriod("Reformation", 1517, 2150, Color.YELLOW, 0);
		definePeriod("Reformation 2", 1517, 1648, Color.YELLOW, 1);
		
		items.add(new Item("Martin Luther", 1486, 1543));
		
		dateline = new Dateline(camera);
	}
	
	public void render(Graphics g) {
		renderer.prepareForRender(camera);
		
		renderer.renderBackground(g);
		
		for(Item item: items) {
			renderer.renderItem(g, item);
		}
		
		for(Period period: periods) {
			renderer.renderPeriod(g, period, camera);
		}
		
		renderer.renderLine(g, camera, dateline);
	}
	
	public void updateZoomSpecifications(float newZoom, float oldZoom) {
		log.info("Updating positions!", 6);
		for(Period period: periods) {
			period.getPositioning().zoomChanged(period, camera);
		}
		dateline.getPositioning().updateDatePositions(camera, oldZoom);
	}
	
	private int numberOfTicksBeforeScrollRealised = 5;
	
	private int lastMouseScrollTick = -numberOfTicksBeforeScrollRealised - 1;
	
	private int totalScrollAmount = 0;
	
	public void mouseScrolled(float scrollAmount) {
		lastMouseScrollTick = ticks;
		totalScrollAmount -= scrollAmount;
	}
	
	private int ticks = 0;
	
	public void tick() {
		ticks++;
		if(totalScrollAmount != 0 && ticks - lastMouseScrollTick > numberOfTicksBeforeScrollRealised) {
			camera.changeZoom(totalScrollAmount * 0.05f);
			totalScrollAmount = 0;
		}
	}
	
	private Period definePeriod(String title, int startDate, int endDate, Color periodColour, int periodLayer) {
		Period period = new Period(title, startDate, endDate, periodColour, Color.BLACK);
		period.setPositioning(new PeriodPositioning(period, periodLayer, camera));
		periods.add(period);
		log.info("New period added.");
		return period;
	}

	public List<Period> getPeriods() {
		return periods;
	}
	
	public void addPeriod(Period period) {
		periods.add(period);
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public Camera getCamera() {
		return camera;
	}
	
}
