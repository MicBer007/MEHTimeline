package utilities;

import timeline.Camera;
import timeline.Timeline;

public class Maths {
	
	public static int getXPositionAfterCameraZoomChange(Camera camera, int xPosition) {
		return (int) ((xPosition - Timeline.WIDTH / 2) * camera.getZoom() + Timeline.WIDTH / 2);
	}

}
