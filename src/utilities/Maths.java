package utilities;

import timeline.Camera;
import timeline.Timeline;

public class Maths {
	
	public static int getXPositionAfterCameraZoomChange(Camera camera, int xPosition, float oldZoom) {
		return (int) ((xPosition) * camera.getZoom() + Timeline.WIDTH / 2 * camera.getZoom());
		
		
		
		
//		return (int) ((xPosition - Timeline.WIDTH / 2) * camera.getZoom());
//		return (int) ((xPosition - camera.getX()) * camera.getZoom() + camera.getX() + Timeline.WIDTH / 2);
	}

}
