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
    boolean started;
	InfoPanel infoPanel;
	GamePanel gamePanel;
	JPanel buttonPanel;
	JPanel mainPanel;

	private Container c;

	int x, y;

	public GameWindow() {
 
		setTitle ("Football game!");
		setSize (450, 575);

		// create labels

		gameL = new JLabel ("");
        started =  false;
		// create buttons

		createBallB = new JButton ("Start game");
		exitB = new JButton ("Exit");

		// add listener to each button (same as the current object)


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
		buttonPanel.add (createBallB);
		buttonPanel.add (exitB);

		// add sub-panels and GUI objects to mainPanel

		mainPanel = new JPanel();
		mainPanel.add(infoPanel);
		mainPanel.add(gameL);
		mainPanel.add(gamePanel);
		mainPanel.add(buttonPanel);

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



	}

	// implement methods in KeyListener interface

	public void keyPressed(KeyEvent e) {
		if(started){
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
		 gamePanel.drawGameEntities();

		}


		String keyText = e.getKeyText(e.getKeyCode());
		gamePanel.updateGameEntities(direction);
		gamePanel.drawGameEntities();
	}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	// implement methods in MouseListener interface

	public void mouseClicked(MouseEvent e) {
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

		if (command.equals(createBallB.getText())) {
			gamePanel.drawGameEntities();
			gamePanel.startGoal();
			started = true;
		}
		else
		if (command.equals(exitB.getText())) {
			System.exit(0);
		}
		mainPanel.requestFocus();

	}

}
      
