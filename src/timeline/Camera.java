package timeline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import logging.Log;
import logging.LogLevel;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private int x = 0;
	private int y = 0;
	
	private float zoom = 1f;
	
	private static final int MIN_X = -10000;
	private static final int MAX_X = 10000;
	private static final int MIN_Y = -20;
	
	private static final float MIN_ZOOM = 0.1f;
	private static final float MAX_ZOOM = 10f;
	
	private ObjectManager manager;
	
	public Camera(ObjectManager manager) {
		this.manager = manager;
	}
	
	public Camera(ObjectManager manager, int x, int y) {
		this.manager = manager;
		setX(x);
		setY(y);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
		if(x > MAX_X / zoom) this.x = (int) (MAX_X / zoom);
		else if(x < MIN_X / zoom) this.x = (int) (MIN_X / zoom);
		System.out.println(this.x);
		System.out.println(zoom);
	}
	
	public void changeX(int change) {
		setX(x + (int) (change * zoom));
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
		if(y < MIN_Y) this.y = MIN_Y;
	}
	
	public void changeY(int change) {
		setY(y + (int) (change * zoom));
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		float oldZoom = this.zoom;
		
		this.zoom = zoom;
		
		if(zoom > MAX_ZOOM) this.zoom = MAX_ZOOM;
		else if(zoom < MIN_ZOOM) this.zoom = MIN_ZOOM;
		
		log.info("Zoom is now " + this.zoom + ".");

		float change = this.zoom / oldZoom;
		
		int midScreenX = x + Timeline.WIDTH / 2;
		int newX = (int) (midScreenX * change - Timeline.WIDTH / 2);
		setX(newX);
		
		manager.updateZoomSpecifications(zoom);
	}
	
	public void changeZoom(float change) {
		setZoom(zoom + change);
	}
	
	private int pressedX, pressedY;

	@Override
	public void mouseDragged(MouseEvent e) {
		int diffX = pressedX - e.getX();
		int diffY = pressedY - e.getY();
		setX(diffX);
		setY(diffY);
	}

	@Override
	public void mouseMoved(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		pressedX = e.getX() + x;
		pressedY = e.getY() + y;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	long timeMillisSinceLastScroll = 0;
	
	final int scrollDelayMillis = 800;
	
	Runnable increaseZoom;
	int amountOfScroll = 0;
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		manager.mouseScrolled((float) e.getPreciseWheelRotation());
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
}
