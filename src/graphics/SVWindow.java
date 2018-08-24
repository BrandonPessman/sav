package graphics;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import sorts.BubbleSort;

public class SVWindow extends JFrame {
	private final int WIDTH;
	private final int HEIGHT;
	private final String TITLE;
	private final int CANVAS_WIDTH;
	private final int CANVAS_HEIGHT;
	private int iterations;
	
	JPanel navigationPanel;
	JButton shuffleButton;
	JButton randomizeButton;
	JButton setDelayButton;
	JTextField delayTextField;
	JButton setMaxIterationsButton;
	JTextField maxIterationsTextField;
	
	Timer timer;
	private int delay;
	private int[] numbersToSort;
	
	public SVWindow(int WIDTH, int HEIGHT, String TITLE, int CANVAS_WIDTH, int CANVAS_HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.TITLE = TITLE;
		this.CANVAS_WIDTH = CANVAS_WIDTH;
		this.CANVAS_HEIGHT = CANVAS_HEIGHT;
		
		randomizeArray();
		windowSetup();
		render();
	}
	
	public void windowSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(TITLE);
	}
	
	public void render() {
		BubbleSort bubbleSort = new BubbleSort();
		SVCanvas canvas = new SVCanvas(CANVAS_WIDTH,CANVAS_HEIGHT, numbersToSort, bubbleSort.toString());
		
		JPanel mainPanel = new JPanel();
		this.getContentPane().add(mainPanel);
		
		/* ---------- Timer Action / Sorting Iteration ---------- */
		
		ActionListener timerAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!bubbleSort.checkIfDone()) {
					numbersToSort = bubbleSort.sort(numbersToSort);
					iterations++;
					canvas.updateCanvas(numbersToSort, iterations);
				} else {
					timer.stop();
				}
			}
			
		};
		
		
		/* ---------- Navigation Panel ---------- */
		
		navigationPanel = new JPanel();
		mainPanel.add(navigationPanel);
		
		shuffleButton = new JButton("Sort");
		shuffleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer = new Timer(delay, timerAction);
		        timer.setInitialDelay(0);
		        timer.start();
			}
			
		});
		navigationPanel.add(shuffleButton);
		
		randomizeButton = new JButton("Randomize");
		randomizeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				timer.stop();
				iterations = 0;
				randomizeArray();
				canvas.updateCanvas(numbersToSort, iterations);
			}
			
		});
		navigationPanel.add(randomizeButton);
		
		navigationPanel.add(new JSeparator(SwingConstants.VERTICAL));
		navigationPanel.add(new JSeparator(SwingConstants.VERTICAL));
		
		setDelayButton = new JButton("Set Delay");
		setDelayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				delay = Integer.parseInt(delayTextField.getText());
			}
			
		});
		navigationPanel.add(setDelayButton);
		
		JLabel delayText = new JLabel("Delay (ms):");
		navigationPanel.add(delayText);
		
		delayTextField = new JTextField();
		delayTextField.setPreferredSize(new Dimension(60, 20));
		navigationPanel.add(delayTextField);
		
		setMaxIterationsButton = new JButton("Set Max Iterations");
		navigationPanel.add(setMaxIterationsButton);
		
		JLabel maxIterationsText = new JLabel("Max Iterations:");
		navigationPanel.add(maxIterationsText);
		
		maxIterationsTextField = new JTextField();
		maxIterationsTextField.setPreferredSize(new Dimension(60, 20));
		navigationPanel.add(maxIterationsTextField);
		
		/* ---------- Canvas Panel ---------- */
		
		JPanel canvasPanel = canvas;
		canvasPanel.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		mainPanel.add(canvasPanel);
	}
	
	public void randomizeArray() {
		numbersToSort = new int[CANVAS_WIDTH];
		for (int i = 0; i < CANVAS_WIDTH; i++) {
			numbersToSort[i] = (int) (Math.random()*CANVAS_HEIGHT);
		}
	}
}
