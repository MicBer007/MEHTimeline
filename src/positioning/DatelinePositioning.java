package positioning;

import java.util.ArrayList;
import java.util.List;

import timeline.Camera;
import utilities.Maths;

public class DatelinePositioning {
	
	private List<Integer> dateXPositions;
	
	private List<Integer> dateXPositionsAfterCameraManipulations = new ArrayList<Integer>();
	
	private int datelineYPosition;
	
	public DatelinePositioning(Camera camera, List<Integer> dates) {
		dateXPositions = dates;
		datelineYPosition = 10;
		updateDatePositions(camera, camera.getZoom());
	}
	
	public void updateDatePositions(Camera camera, float oldZoom) {
		dateXPositionsAfterCameraManipulations.clear();
		for(Integer i: dateXPositions) {
			dateXPositionsAfterCameraManipulations.add(Maths.getXPositionAfterCameraZoomChange(camera, i, oldZoom));
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
