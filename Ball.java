import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.util.Random;

public class Ball extends Thread {

   private static final int XSIZE = 10;
   private static final int YSIZE = 10;

   private JPanel panel;
   private InfoPanel infoPanel;

   private int x;
   private int y;

   private Random random;

   private int dx;
   private int dy;

   Graphics2D g2;
   private Color backgroundColor;
   private Dimension dimension;
   private Color colour = Color.RED;

   private Bat bat;

   SoundManager soundManager;
			
   public Ball(JPanel p, Bat bat, InfoPanel infoPanel) {
      panel = p;
      this.infoPanel = infoPanel;

      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;
      backgroundColor = panel.getBackground ();

      this.bat = bat;					// set reference to bat

      soundManager = SoundManager.getInstance();	// get Singleton instance

      dimension = panel.getSize();
      random = new Random();

      x = random.nextInt(dimension.width - XSIZE);	// randomly generate x location
      y = 0;						// set y to top of the panel

      setColour();					// set colour of the ball

      dx = 0;
      dy = 5;
  
      infoPanel.displayInfo();				// display information on information panel	
   }
 
   public Rectangle2D.Double getBoundingRectangle() {	// obtain bounding rectangle for ball
      	return new Rectangle2D.Double (x, y, XSIZE, YSIZE);
   }

   public void draw () {				// draw ball on the panel
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;

      g2.setColor (colour);
      g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));

      infoPanel.displayInfo();				// update information displayed in info panel
   }

   public void setColour () {				// set colour of the ball
	int red, green, blue;

	red = random.nextInt(256);
	green = random.nextInt(256);
	blue = random.nextInt(256);

	colour = new Color(red, green, blue);

   }
	
   public void erase () {				// erases the ball from the panel
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;

      g2.setColor (backgroundColor);
      g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
   }

   public void move () {

      if (!panel.isVisible ()) return;

      //x = x + dx;					// no horizonal movement of ball

      y = y + dy;					// change vertical position of ball

      Rectangle2D.Double myRect = getBoundingRectangle();
      Rectangle2D.Double batRect = bat.getBoundingRectangle();
 
      if (myRect.intersects(batRect)) {			// check if bat collides with the ball

	  soundManager.playSound("hit", false);		// if so, request sound manager to play hit clip
	  infoPanel.incrementHits();			// increment hits by 1 in information panel
	  infoPanel.displayInfo();			// display updated information

	  try {						// take a break before continuing
		sleep (1000);
	  }
	  catch (InterruptedException e) {};

	  infoPanel.decrementTime(1000);		// decrease time by 1000 millis in info panel
      }

      if (y > dimension.height)	{			// increment misses by 1 in information panel
	  infoPanel.incrementMisses();
	  infoPanel.displayInfo();			// display updated information
      }
      
      if (myRect.intersects(batRect) || (y > dimension.height)) {
      	  x = random.nextInt(dimension.width - XSIZE);	// randomly generate x location of ball
      	  y = 0;					// set y to top of the panel
	  setColour();					// set colour of ball

	  soundManager.playSound("appear", false);	// request sound manager to play reappear clip
      }
   }

    public void run () {
	try {
		draw ();
		while (true) {				// loop forever
			erase();
			move ();
			draw();
			sleep (50);			// increase value to slow down ball
			infoPanel.decrementTime(50);	// decrease time by 50 millis in info panel
		}
	}
	catch(InterruptedException e) {}
    }

}