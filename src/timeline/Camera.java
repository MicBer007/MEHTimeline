package timeline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import logging.Log;
import logging.LogLevel;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.INFO);
	
	private int x, y;
	
	private float zoom = 1.0f;
	
	private ObjectManager manager;
	
	public Camera(ObjectManager manager) {
		this.manager = manager;
	}
	
	public Camera(ObjectManager manager, int x, int y) {
		this.manager = manager;
		this.x = x;
		this.y = y;
	}
	
	public Camera(ObjectManager manager, float zoom) {
		this.manager = manager;
		this.zoom = zoom;
	}
	
	public Camera(ObjectManager manager, int x, int y, float zoom) {
		this.manager = manager;
		this.x = x;
		this.y = y;
		this.zoom = zoom;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void changeX(int change) {
		setX(x + change);
	}
	
	public int getY() {
		return y;
	}
	
	private final int MIN_Y = 0;
	
	public void setY(int y) {
		this.y = y;
		
		if(this.y < MIN_Y) this.y = MIN_Y;
	}
	
	public void changeY(int change) {
		setY(y + change);
	}
	
	public float getZoom() {
		return zoom;
	}
	
	private final float MIN_ZOOM = 0.5f;
	private final float MAX_ZOOM = 10f;
	
	public void setZoom(float zoom) {
		this.zoom = zoom;
		
		if(this.zoom < MIN_ZOOM) {
			this.zoom = MIN_ZOOM;
		} else if(this.zoom > MAX_ZOOM) {
			this.zoom = MAX_ZOOM;
		}
	}
	
	public void changeZoom(float change) {
		setZoom(zoom + change);
	}
	
	private int pressedX, pressedY;
	
	@Override
	public void mousePressed(MouseEvent e) {
		log.info("Mouse Pressed");
		pressedX = e.getX();
		pressedY = e.getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		int deltaX = pressedX - e.getX();
		int deltaY = pressedY - e.getY();
		
		changeX((int) (deltaX / zoom));
		changeY(deltaY);
		
		pressedX = e.getX();
		pressedY = e.getY();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		log.info("Mouse Wheel Moved");
		manager.mouseScrolled((float) e.getPreciseWheelRotation());
	}
	
	
	
	
	@Override
	public void mouseMoved(MouseEvent e) { }
	
	@Override
	public void mouseReleased(MouseEvent e) { }
	
	@Override
	public void mouseClicked(MouseEvent e) { }
	
	@Override
	public void mouseEntered(MouseEvent e) { }
	
	@Override
	public void mouseExited(MouseEvent e) { }
	
}
