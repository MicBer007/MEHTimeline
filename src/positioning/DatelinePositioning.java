package positioning;

import java.util.ArrayList;
import java.util.List;

import settings.Settings;
import timeline.Camera;
import utilities.Maths;

public class DatelinePositioning {
	
	private List<Integer> dateXPositions;
	
	private List<Integer> dateXPositionsAfterCameraManipulations = new ArrayList<Integer>();
	
	private int datelineYPosition;
	
	public DatelinePositioning(Camera camera, List<Integer> dates) {
		dateXPositions = dates;
		datelineYPosition = Settings.DATELINE_HEIGHT;
		updateDatePositions(camera);
	}
	
	public void updateDatePositions(Camera camera) {
		dateXPositionsAfterCameraManipulations.clear();
		for(Integer xPosition: dateXPositions) {
			dateXPositionsAfterCameraManipulations.add(Maths.getXPositionAfterCameraZoomChange(camera, xPosition));
		}
	}

	public List<Integer> getDateXPositions() {
		return dateXPositions;
	}

	public List<Integer> getDateXPositionsAfterCameraManipulations() {
		return dateXPositionsAfterCameraManipulations;
	}

	public int getDatelineYPosition() {
		return datelineYPosition;
	}
	
}
