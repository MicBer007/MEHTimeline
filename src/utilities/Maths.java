package utilities;

import timeline.Camera;
import timeline.Timeline;

public class Maths {
	
	public static int getXPositionAfterCameraZoomChange(Camera camera, int xPosition, float oldZoom) {
		int positionOfRenderedPoint = (int) (xPosition * camera.getZoom()) + -camera.getXAtCorner();
		int middle = Timeline.WIDTH / 2;
		int delta = middle - positionOfRenderedPoint;
		float differenceInZoom = camera.getZoom() / oldZoom;
		int offset = (int) (delta * (1 - differenceInZoom));
		return (int) ((xPosition) * camera.getZoom() + offset);
		
		
		
		
//		return (int) ((xPosition + Timeline.WIDTH / 2) * camera.getZoom());
//		return (int) ((xPosition - camera.getX()) * camera.getZoom() + camera.getX() + Timeline.WIDTH / 2);
	}

}
