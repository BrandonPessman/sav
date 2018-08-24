package driver;

import javax.swing.SwingUtilities;

import graphics.SVWindow;

public class SVDriver {
	private static final int WIDTH = 860;
	private static final int HEIGHT = 490;
	private static final int CANVAS_WIDTH = 820;
	private static final int CANVAS_HEIGHT = 400;
	private static final String TITLE = "Sorting Visualization Tool";
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SVWindow window = new SVWindow(WIDTH, HEIGHT, TITLE, CANVAS_WIDTH, CANVAS_HEIGHT);
				window.setVisible(true);
			}
		});
	}
}
