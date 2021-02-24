import javax.swing.*;			
import java.awt.*;			
import java.awt.event.*;
	
public class GameWindow extends JFrame 
				implements KeyListener,
					   ActionListener
{


	private JLabel gameL;


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



		gameL = new JLabel ("");
        started =  false;


		createBallB = new JButton ("Start game");
		exitB = new JButton ("Exit");




		createBallB.addActionListener(this);
		exitB.addActionListener(this);



		infoPanel = new InfoPanel();


		gamePanel = new GamePanel(infoPanel);
		gamePanel.createGameEntities();

        	gamePanel.setPreferredSize(new Dimension(400, 400));



		GridLayout gridLayout;

		buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 4);
		buttonPanel.setLayout(gridLayout);


		buttonPanel.add (createBallB);
		buttonPanel.add (exitB);


		mainPanel = new JPanel();
		mainPanel.add(infoPanel);
		mainPanel.add(gameL);
		mainPanel.add(gamePanel);
		mainPanel.add(buttonPanel);


		c = getContentPane();
		c.add(mainPanel);

		mainPanel.addKeyListener(this);



		mainPanel.setFocusable(true);
    		mainPanel.requestFocus();

	

		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);
	}

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
		gamePanel.updateGameEntities(direction);
		gamePanel.drawGameEntities();
	}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
	

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
      
