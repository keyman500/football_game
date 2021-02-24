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
		missesL = new JLabel ("# Misses   ");
		pointsL = new JLabel ("Points");


		// create text fields
	
		hitsTF = new JTextField ();
		missesTF = new JTextField ();
		pointsTF = new JTextField ();


		hitsTF.setEditable(false);
		missesTF.setEditable(false);
		pointsTF.setEditable(false);


		// create layout manager

		GridLayout gridLayout;

		gridLayout = new GridLayout(2, 4);
		setLayout(gridLayout);

		// add labels and text fields to panel

		add (hitsL);
		add (missesL);
		add (pointsL);

		add (hitsTF);
		add (missesTF);
		add (pointsTF);


		resetInfo();
  	}

	public void resetInfo () {
		hits = misses = points = 0;
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

	public void decreasePoints(int points){
		if(this.points>-1){
		this.points-= points;}
	}


	public void displayInfo () {
		hitsTF.setText (hits+"");
		missesTF.setText (misses+"");
		pointsTF.setText (points+"");
	}

}
