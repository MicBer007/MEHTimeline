package timeline;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

public class Window {
	
	public Window(int height, int width, String title, Image icon, Timeline timeline, WindowAdapter onClose) {
		JFrame window = new JFrame(title);
		
		Dimension windowDimension = new Dimension(height, width);
		window.setPreferredSize(windowDimension);
		window.setMinimumSize(windowDimension);
		window.setMaximumSize(windowDimension);
		
		if(icon != null) window.setIconImage(icon);
		
		if(onClose != null) window.addWindowListener(onClose);
		
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.add(timeline);
		window.setVisible(true);
	}

}
