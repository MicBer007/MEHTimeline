package settings;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class Settings {
	
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	
	public static final Color DATELINE_COLOUR = Color.BLACK;
	public static final int PERIOD_LAYERS_BEFORE_DATELINE = 4;
	
	public static final int Y_PADDING = 7;
	
	public static final float PERIOD_LAYER_SIZE = 26;
	public static final int PERIOD_HEIGHT = (int) (PERIOD_LAYER_SIZE / 10 * 7);
	
	public static final int DATELINE_HEIGHT = (int) (PERIOD_LAYERS_BEFORE_DATELINE * PERIOD_LAYER_SIZE + Y_PADDING);
	
	public static final int PERIOD_TEXT_RECURRENCE_INTERVAL = 500;
	
	public static final int PERIOD_TEXT_X_PADDING = 5;
	
	public static final Font PERIOD_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	
	public static final int ITEM_LAYER_SIZE = 42;
	
	
	public static final int TIMELINE_START = 0;
	public static final int TIMELINE_END = 200;
	
	public static final int SIGNIFICANT_DATE_INTERVAL = 50;
	public static final int SIGNIFICANT_DATE_MARKER_HEIGHT = 4;//in both directions
	
	public static final List<Integer> DATELINE_DATES = generateDatelineDates();
	
	private static List<Integer> generateDatelineDates(){
		List<Integer> dates = new ArrayList<Integer>();;
		for(int i = TIMELINE_START; i < TIMELINE_END; i += SIGNIFICANT_DATE_INTERVAL) {
			dates.add(i);
		}
		return dates;
	}
	
}
