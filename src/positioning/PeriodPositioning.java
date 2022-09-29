package positioning;

import java.util.ArrayList;
import java.util.List;

import objects.Period;
import settings.Settings;
import utilities.TextUtils;

public class PeriodPositioning {
	
	private int periodLayer;
	
	private Hitbox hitbox;
	
	private List<Integer> nameXPositions = new ArrayList<Integer>();
	
	private int nameYPosition;
	
	public PeriodPositioning(Period period, int periodLayer, float zoom) {
		this.periodLayer = periodLayer;
		
		definePositioning(period, zoom);
	}
	
	public PeriodPositioning(Period period, float zoom) {
		this.periodLayer = 0;
		
		definePositioning(period, zoom);
	}
	
	
	private void definePositioning(Period period, float zoom) {
		int x1 = (int) (period.getStartDate() * zoom);
		int x2 = (int) (period.getEndDate() * zoom);
		int y1 = (int) (Settings.Y_PADDING + periodLayer * Settings.PERIOD_LAYER_SIZE);
		int width = x2 - x1;
		
		this.hitbox = new Hitbox(x1, y1, width, Settings.PERIOD_HEIGHT);

		nameYPosition = hitbox.getY2() - 4;
		
		definePeriodNameXPositions(period);
	}
	
	public void refreshYPositioning() {
		int y1 = (int) (Settings.Y_PADDING + periodLayer * Settings.PERIOD_LAYER_SIZE);
		nameYPosition = y1 + Settings.PERIOD_HEIGHT - 4;
		hitbox = new Hitbox(hitbox.getPreciseX(), y1, hitbox.getWidth(), Settings.PERIOD_HEIGHT);
	}
	
	public void zoomChanged(Period period, float zoom) {
		int x1 = (int) (period.getStartDate() * zoom);
		int x2 = (int) (period.getEndDate() * zoom);
		int width = x2 - x1;
		hitbox = new Hitbox(x1, hitbox.getPreciseY(), width, hitbox.getHeight());
		
		definePeriodNameXPositions(period);
	}
	
	public void definePeriodNameXPositions(Period period) {
		int periodNameLength = TextUtils.dummyFontMetrics.getFontMetrics(Settings.PERIOD_TEXT_FONT).stringWidth(period.getPeriodName());
		
		float numberOfPeriodNamesToIterate = (float) (hitbox.getWidth() - periodNameLength - (Settings.PERIOD_TEXT_X_PADDING * 2)) / (float) Settings.PERIOD_TEXT_RECURRENCE_INTERVAL;
		
		nameXPositions.clear();
		for(int i = 0; i < numberOfPeriodNamesToIterate; i++) {
			nameXPositions.add(hitbox.getX() + Settings.PERIOD_TEXT_X_PADDING + i * Settings.PERIOD_TEXT_RECURRENCE_INTERVAL);
		}
		
		if(numberOfPeriodNamesToIterate - Math.floor(numberOfPeriodNamesToIterate) > 0.8f && numberOfPeriodNamesToIterate > 0) {
			nameXPositions.add(hitbox.getX2() - periodNameLength - Settings.PERIOD_TEXT_X_PADDING);
		}
	}

	public int getPeriodLayer() {
		return periodLayer;
	}

	public void setPeriodLayer(int periodLayer) {
		this.periodLayer = periodLayer;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public List<Integer> getNameXPositions() {
		return nameXPositions;
	}

	public int getNameYPosition() {
		return nameYPosition;
	}
	
}