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
      g2.setColor(Color.gray);
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

   public void move () {
    
    if (!panel.isVisible ()) return;

    dimension = panel.getSize();

    if (((x + dx + XSIZE) < dimension.width)&&mvright==false){
           x+= dx;
    }
    if((x + dx + XSIZE) == dimension.width){
       mvright = true;
    }   
    if (((x - dx) > 0)&&mvright==true){
      x = x - dx;      
    }
    if((x-dx)==0){
   mvright=false;
    }

 }

  public void run () {
  try {
      draw ();
      while (true) {			
          erase();
          move ();
          draw();
          sleep (50);			
      }
  }
  catch(InterruptedException e) {
      System.out.println(e +" bro idk nah");

  }
  }

}