package objects;

import java.awt.Color;

import positioning.PeriodPositioning;

public class Period {
	
	private String periodName;
	
	private int startDate, endDate;
	
	private Color displayColour;
	private Color textColour = Color.BLACK;
	
	private PeriodPositioning positioning;
	
	public Period(String periodName, int startDate, int endDate, Color displayColour) {
		this.periodName = periodName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.displayColour = displayColour;
	}
	
	public Period(String periodName, int startDate, int endDate, Color displayColour, Color textColour) {
		this.periodName = periodName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.displayColour = displayColour;
		this.textColour = textColour;
	}
	
	public String getPeriodName() {
		return periodName;
	}
	
	public int getStartDate() {
		return startDate;
	}
	
	public int getEndDate() {
		return endDate;
	}
	
	public Color getDisplayColour() {
		return displayColour;
	}
	
	public Color getTextColour() {
		return textColour;
	}
	
	public PeriodPositioning getPositioning() {
		return positioning;
	}
	
	public void setPositioning(PeriodPositioning positioning) {
		this.positioning = positioning;
	}
	
}
