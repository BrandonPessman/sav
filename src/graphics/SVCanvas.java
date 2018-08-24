package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SVCanvas extends JPanel {
	private int WIDTH;
	private int HEIGHT;
	private int numbersToSort[];
	private int iterations;
	private String name;
	
	public SVCanvas(int width, int height, int[] numbersToSort, String name) {
		this.WIDTH = width;
		this.HEIGHT = height;
		this.numbersToSort = numbersToSort;
		this.name = name;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for (int i = 1; i < WIDTH; i++) {
			if (numbersToSort[i] > 0) {
				g.setColor(new Color((int) ((double) numbersToSort[i] / HEIGHT * 50),
						(int) ((double) numbersToSort[i] / WIDTH * 75), (int) ((double) numbersToSort[i] / WIDTH * 255)));
				g.drawLine(i, HEIGHT, i, HEIGHT - numbersToSort[i]);
			}
		}

		g.setColor(new Color(60, 60, 60, 175));
		g.fillRect(5, 5, 200, 40);

		g.setColor(Color.WHITE);
		g.drawString("Iterations: " + iterations, 10, 20);
		g.drawString("Sort Method: " + name, 10, 40);
	}
	
	public void updateCanvas(int[] numbersToSort, int iterations) {
		this.iterations = iterations;
		this.numbersToSort = numbersToSort;
		repaint();
	}
}
