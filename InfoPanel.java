import java.awt.Color;
import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for certain AWT classes

public class InfoPanel extends JPanel {

	private int hits;
	private int misses;
	private int points;
	private int time;

	private JLabel hitsL;
	private JLabel missesL;
	private JLabel pointsL;
	private JLabel timeL;

	private JTextField hitsTF;
	private JTextField missesTF;
	private JTextField pointsTF;
	private JTextField timeTF;


   	public InfoPanel () {

		setBackground(Color.PINK);

		// create labels
	
		hitsL = new JLabel ("# Hits");
		missesL = new JLabel ("# Misses");
		pointsL = new JLabel ("Points");
		timeL = new JLabel ("Time Remaining");

		// create text fields
	
		hitsTF = new JTextField ();
		missesTF = new JTextField ();
		pointsTF = new JTextField ();
		timeTF = new JTextField ();

		hitsTF.setEditable(false);
		missesTF.setEditable(false);
		pointsTF.setEditable(false);
		timeTF.setEditable(false);

		// create layout manager

		GridLayout gridLayout;

		gridLayout = new GridLayout(2, 4);
		setLayout(gridLayout);

		// add labels and text fields to panel

		add (hitsL);
		add (missesL);
		add (pointsL);
		add (timeL);

		add (hitsTF);
		add (missesTF);
		add (pointsTF);
		add (timeTF);

		resetInfo();
  	}

	public void resetInfo () {
		hits = misses = points = 0;
		time = 300000;			// maximum time to play game in milliseconds
	}

	public void incrementHits () {
		hits++;
	}

	public void incrementMisses () {
		misses++;
	}

	public void incrementPoints (int numPoints) {
		points = points + numPoints;
	}

	public void decrementTime (int millis) {
		time = time - millis;
	}

	public void displayInfo () {
	
		int timeSecs = time / 1000;			// convert to seconds

		hitsTF.setText (hits+"");
		missesTF.setText (misses+"");
		pointsTF.setText (points+"");
		timeTF.setText (timeSecs+"");
	}

}
