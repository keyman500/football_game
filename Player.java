import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Player {

   private static final int XSIZE = 35;
   private static final int YSIZE = 10;

   private JPanel panel;
   private int x;
   private int y;
   private int dx;
   private int dy;

   Graphics2D g2;
   private Color backgroundColor;
   private Dimension dimension;

   public Player (JPanel p, int xPos, int yPos) {
      panel = p;
      backgroundColor = panel.getBackground ();
      x = xPos;
      y = yPos;
      dx = 5;
      dy = 0;
   }

   public void draw () {
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;

      Ellipse2D bat =new Ellipse2D.Double (x, y, XSIZE, YSIZE);
      g2.setColor(Color.RED);
      g2.fill(bat);
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, XSIZE, YSIZE);
   }

   public void erase () {
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;

      g2.setColor (backgroundColor);
      g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
   }

   public void moveLeft () {
      Dimension dimension;

      if (!panel.isVisible ()) return;

      dimension = panel.getSize();

      if (x - dx > 0)
	  x = x - dx;      


   }

   public void moveRight () {
      Dimension dimension;

      if (!panel.isVisible ()) return;

      dimension = panel.getSize();

      if (x + dx + XSIZE < dimension.width)
	  x = x + dx;      


   }

   public int getx(){
       return this.x;
   }

   public int gety(){
    return this.y;
}


}