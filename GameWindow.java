import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for certain AWT classes
import java.awt.event.*;
	
public class GameWindow extends JFrame 
				implements KeyListener,
					   MouseListener,
					   ActionListener
{
	// declare instance variables for user interface objects

	// declare labels 

	private JLabel gameL;
	private JLabel statusBarL;

	// declare buttons

	private JButton moveLeftB;
	private JButton moveRightB;
	private JButton createBallB;
	private JButton exitB;

	InfoPanel infoPanel;
	GamePanel gamePanel;
	JPanel buttonPanel;
	JPanel mainPanel;

	private Container c;

	int x, y;

	public GameWindow() {
 
		setTitle ("Game Window");
		setSize (450, 575);

		// create labels

		gameL = new JLabel ("A Bat and Ball Game");
		statusBarL = new JLabel();

		// create buttons

		moveLeftB = new JButton ("Move Left");
		moveRightB = new JButton ("Move Right");
		createBallB = new JButton ("Create Ball");
		exitB = new JButton ("Exit");

		// add listener to each button (same as the current object)

		moveLeftB.addActionListener(this);
		moveRightB.addActionListener(this);
		createBallB.addActionListener(this);
		exitB.addActionListener(this);

		// create panel to display game information

		infoPanel = new InfoPanel();

		// create panel to draw game entities

		gamePanel = new GamePanel(infoPanel);
		gamePanel.createGameEntities();

        	gamePanel.setPreferredSize(new Dimension(400, 400));

		// create buttonPanel

		GridLayout gridLayout;

		buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 4);
		buttonPanel.setLayout(gridLayout);

		// add buttons to buttonPanel

		buttonPanel.add (moveLeftB);
		buttonPanel.add (moveRightB);
		buttonPanel.add (createBallB);
		buttonPanel.add (exitB);

		// add sub-panels and GUI objects to mainPanel

		mainPanel = new JPanel();
		mainPanel.add(infoPanel);
		mainPanel.add(gameL);
		mainPanel.add(gamePanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(statusBarL);

		// add mainPanel to window surface

		c = getContentPane();
		c.add(mainPanel);

		mainPanel.addMouseListener(this);
		mainPanel.addKeyListener(this);

		//gamePanel.setFocusable(true);
    		//gamePanel.requestFocus(); 

		mainPanel.setFocusable(true);
    		mainPanel.requestFocus();

		// apply operations to window

		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		// set status bar message

		statusBarL.setText("Application started.");


	}

	// implement methods in KeyListener interface

	public void keyPressed(KeyEvent e) {
		int direction = 0;

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			direction = 1;	
		}
		else
		if (keyCode == KeyEvent.VK_RIGHT) {
			direction = 3;	
		}
		if(keyCode==KeyEvent.VK_SPACE){
		 gamePanel.kickBall();

		}


		String keyText = e.getKeyText(e.getKeyCode());
		statusBarL.setText("         " + keyText + " pressed." + "         ");
		gamePanel.updateGameEntities(direction);
		gamePanel.drawGameEntities();
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	// implement methods in MouseListener interface

	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		statusBarL.setText("Mouse click at (" + x +", " + y + ")");
		gamePanel.drawGameEntities();
		mainPanel.requestFocus();
	}


	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
	
	}

	public void mousePressed(MouseEvent e) {
	
	}

	public void mouseReleased(MouseEvent e) {
	
	}

	// implement single method in ActionListener interface

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals(moveLeftB.getText())) {
			gamePanel.updateGameEntities(1);
			gamePanel.drawGameEntities();
		}
		else
		if (command.equals(moveRightB.getText())) {
			gamePanel.updateGameEntities(3);
			gamePanel.drawGameEntities();
		}
		else
		if (command.equals(createBallB.getText())) {
			gamePanel.startGoal();
		}
		else
		if (command.equals(exitB.getText())) {
			System.exit(0);
		}
		statusBarL.setText(command + " button clicked.");
		mainPanel.requestFocus();

	}

}
      
