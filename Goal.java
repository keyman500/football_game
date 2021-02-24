import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Goal extends Thread{

   private static final int XSIZE = 100;
   private static final int YSIZE = 15;

   private JPanel panel;
   private int x;
   private int y;
   private int dx;
   private int dy;
   boolean mvright;

   Graphics2D g2;
   private Color backgroundColor;
   private Dimension dimension;

   public Goal (JPanel p, int xPos, int yPos) {
      panel = p;
      backgroundColor = panel.getBackground ();
      x = xPos;
      y = yPos;
      dx = 5;
      dy = 0;
      mvright = false;
   }

   public void draw () {
    Graphics g = panel.getGraphics ();
    g2 = (Graphics2D) g;
      Rectangle goal = new Rectangle(x, y, XSIZE, YSIZE);
      g2.setColor(Color.RED);
      g2.fill(goal);
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, XSIZE, YSIZE);
   }

   public void erase () {
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;
      g2.setColor (backgroundColor);
      g2.fill (new Rectangle(x, y, XSIZE, YSIZE));
   }

   public void moveLeft () {
      Dimension dimension;

      if (!panel.isVisible ()) return;

      erase();

      dimension = panel.getSize();

      if (x - dx > 0)
	  x = x - dx;      

	  // check if x is outside the left side of the panel
	  // NB: dimension.width is the width of the panel, and
	  //     dimension.height is the height of the panel
   }

   public void moveRight () {
      Dimension dimension;

      if (!panel.isVisible ()) return;

      erase();

      dimension = panel.getSize();

      if (x + dx + XSIZE < dimension.width)
	  x = x + dx;      

	  // check if x is outside the right side of the panel
	  // NB: dimension.width is the width of the panel, and
	  //     dimension.height is the height of the panel
   }

   public void move () {
    
    if (!panel.isVisible ()) return;

    dimension = panel.getSize();

    if (((x + dx + XSIZE) < dimension.width)&&mvright==false){
           x+= dx;
    }
    if((x + dx + XSIZE) == dimension.width){
       mvright =false;
    }   
        

    if ((x + dx + XSIZE) == dimension.width){
        moveLeft();
    }

 /*   Rectangle2D.Double myRect = getBoundingRectangle();
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

   // request sound manager to play reappear clip
    }*/
    //soundManager.playSound("appear", false);	
 }




  public void run () {
  try {
      draw ();
      while (true) {				// loop forever
          erase();
          move ();
          draw();
          sleep (50);			// increase value to slow down ball
      }
  }
  catch(InterruptedException e) {
      System.out.println(e +" bro idk nah");

  }
  }

}