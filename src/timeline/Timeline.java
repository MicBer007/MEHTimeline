package timeline;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import logging.Log;
import logging.LogLevel;

public class Timeline extends Canvas implements Runnable {
	
	private Log log = new Log(this.getClass().getSimpleName(), LogLevel.ERROR);
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private Thread thread;
	private boolean running = false;
	
	private ObjectManager manager;
	
	public static void main(String[] args) {
		new Timeline();
	}
	
	public Timeline() {
		try {
			init();
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		start();
	}
	
	public void init() {
		log.info("Initializing...", 10);
		new Window(WIDTH, HEIGHT, "game", null, this, new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
                running = false;
            }
		});
		manager = new ObjectManager();
		addMouseMotionListener(manager.getCamera());
		addMouseWheelListener(manager.getCamera());
		addMouseListener(manager.getCamera());
	}

	public synchronized void start() {
		log.info("Starting...", 10);
		thread = new Thread();
		thread.start();
		running = true;
		run();
	}
	
	public synchronized void stop() {
		log.info("Stopping...", 10);
		try {
			thread.join();
			running = false;
			System.exit(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int FPS = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			FPS++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				log.info("FPS: " + FPS, 10);
				log.info("Camera X: " + manager.getCamera().getX(), 10);
				log.info("Camera Y: " + manager.getCamera().getY(), 10);
				log.info("Camera Zoom: " + manager.getCamera().getZoom(), 10);
				FPS = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		manager.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		manager.render(g);
		
		g.dispose();
		bs.show();
	}
	
}