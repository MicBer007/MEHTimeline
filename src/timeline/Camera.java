package timeline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import logging.Log;
import logging.LogLevel;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.WARN);
	
	private int y = 0; //same as yYear

	private int x = 0;
	private int xYear = 0;
	
	private float zoom = 1f;
	
	private static final int MIN_X = -10000;
	private static final int MAX_X = 10000;
	private static final int MIN_Y = -20 + Timeline.HEIGHT / 2;
	
	private static final float MIN_ZOOM = 0.05f;
	private static final float MAX_ZOOM = 2f;
	
	private ObjectManager manager;
	
	public Camera(ObjectManager manager) {
		this.manager = manager;
	}
	
	public Camera(ObjectManager manager, int x, int y) {
		this.manager = manager;
		setXYear(x);
		setY(y);
	}
	
	public int getXAtCorner() {
		return (int) (x - (Timeline.WIDTH / 2));
	}
	
	public int getXYear() {
		return xYear;
	}
	
	public int getXIncludingZoom() {
		return x;
	}
	
	public void setXYear(int x) {
		this.xYear = x;
		if(x > MAX_X) this.xYear = MAX_X;
		else if(x < MIN_X) this.xYear = MIN_X;
		updateZoomedCoords();
	}
	
	public void changeXYear(int change) {
		setXYear(xYear + change);
	}
	
	public void setXIncludingZoom(int xWithZoom) {
		setXYear((int) (xWithZoom / zoom));
	}
	
	public void changeXIncludingZoom(int change) {
		changeXYear((int) (change / zoom));
	}
	
	public void updateZoomedCoords() {
		x = (int) (xYear * zoom);
	}
	
	public int getYAtCorner() {
		return y - Timeline.HEIGHT / 2;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
		if(y < MIN_Y) this.y = MIN_Y;
	}
	
	public void changeY(int change) {
		setY(y + change);
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		
		float oldZoom = this.zoom;
		
		if(zoom > MAX_ZOOM) this.zoom = MAX_ZOOM;
		else if(zoom < MIN_ZOOM) this.zoom = MIN_ZOOM;
		else this.zoom = zoom;
		
		manager.updateZoomSpecifications(this.zoom, oldZoom);
		
		log.info("Zoom is now " + this.zoom);
	}
	
	public void changeZoom(float change) {
		setZoom(zoom + change);
	}

	private int pressedX, pressedY;

	@Override
	public void mouseDragged(MouseEvent e) {
		int newX = pressedX - (int) ((e.getX() - Timeline.WIDTH / 2));
		int newY = pressedY - (e.getY() - Timeline.HEIGHT / 2);
		
		setXIncludingZoom(newX);
		setY(newY);
	}

	@Override
	public void mouseMoved(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		pressedX = (int) ((e.getX() - Timeline.WIDTH / 2)  + x);
		pressedY = e.getY() - Timeline.HEIGHT / 2 + y;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		manager.mouseScrolled((float) e.getPreciseWheelRotation());
	}
	
	
	
	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
}
