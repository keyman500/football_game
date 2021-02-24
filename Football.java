import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
public class Football extends Thread{

    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
 
    private JPanel panel;
    private InfoPanel infoPanel;
 
    private int x;
    private int y;
 
 
    private int dx;
    private int dy;
 
    Graphics2D g2;
    private Color backgroundColor;
    private Dimension dimension;
    private Color colour = Color.RED;
 
    private Bat bat;
    private Player player;
    private Goal goal;
    private boolean hit;
    private boolean kicked;
 
    SoundManager soundManager;
             
    public Football(JPanel p, Bat bat, InfoPanel infoPanel,Player player,Goal goal) {
       this.goal = goal;
       panel = p;
       this.infoPanel = infoPanel;
       this.player = player;
       this.hit = false;
       this.kicked = false;

 
       Graphics g = panel.getGraphics ();
       g2 = (Graphics2D) g;
       backgroundColor = panel.getBackground ();
 
       this.bat = bat;					// set reference to bat
 
       soundManager = SoundManager.getInstance();	// get Singleton instance
 
       dimension = panel.getSize(); 
       x = player.getx();
       y = player.gety() - 10;						// set y to top of the panel
 
       setColour();					// set colour of the ball
 
       dx = 0;
       dy = -10;
   
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
 
     //  infoPanel.displayInfo();				// update information displayed in info panel
    }
 
    public void setColour () {		
     colour = new Color(255, 255, 255);
 
    }
     
    public void erase () {				// erases the ball from the panel
       Graphics g = panel.getGraphics ();
       g2 = (Graphics2D) g;
 
       g2.setColor (backgroundColor);
       g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
    }
 
    public void move () {
 
       if (!panel.isVisible ()) {return;}

       y = y + dy;

       Rectangle2D.Double goalRect = goal.getBoundingRectangle();
       Rectangle2D.Double myRect = this.getBoundingRectangle();
       if(myRect.intersects(goalRect)){
          soundManager.playSound("goal", false);
         this.hit = true;
          System.out.println("goal!");
          y = -5;
          infoPanel.incrementPoints(10);
          infoPanel.incrementHits();
          infoPanel.displayInfo();	
      
       }
       //missed the ball
       if ((y <0)&&hit==false&&kicked==false)	{		
         soundManager.playSound("missed", false);	
         infoPanel.incrementMisses();
         infoPanel.decreasePoints(10);
         infoPanel.displayInfo();	
         kicked = true;
   
      }
         

 
    /*   
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

     public int gety(){
        return this.y;
     }
     public void sety(int y){
        this.y = y;
     }
 
 
}
