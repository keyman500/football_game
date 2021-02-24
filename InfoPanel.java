import java.awt.Color;
import javax.swing.*;			
import java.awt.*;			

public class InfoPanel extends JPanel {

	private int hits;
	private int misses;
	private int points;


	private JLabel hitsL;
	private JLabel missesL;
	private JLabel pointsL;


	private JTextField hitsTF;
	private JTextField missesTF;
	private JTextField pointsTF;



   	public InfoPanel () {

		setBackground(Color.PINK);

	
		hitsL = new JLabel ("# Goals");
		missesL = new JLabel ("# Misses   ");
		pointsL = new JLabel ("Points");



	
		hitsTF = new JTextField ();
		missesTF = new JTextField ();
		pointsTF = new JTextField ();


		hitsTF.setEditable(false);
		missesTF.setEditable(false);
		pointsTF.setEditable(false);



		GridLayout gridLayout;

		gridLayout = new GridLayout(2, 4);
		setLayout(gridLayout);



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
		points+= numPoints;
	}

	public void decreasePoints(int points){
		if((this.points - points)>-1){
		this.points-= points;}
	}


	public void displayInfo () {
		hitsTF.setText (hits+"");
		missesTF.setText (misses+"");
		pointsTF.setText (points+"");
	}

}
