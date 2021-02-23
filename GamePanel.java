import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

/**
   A component that draws an alien face
*/
public class GamePanel extends JPanel {
   
   int x, y;			// reference position to draw entities in 2D space
   
   Bat bat;
   Ball ball;
   InfoPanel infoPanel;

   SoundManager soundManager;

   public GamePanel (InfoPanel infoPanel) {
	setBackground(Color.CYAN);
	this.infoPanel = infoPanel;
	soundManager = SoundManager.getInstance();	// obtain reference to SoundManager
   }

   public void createGameEntities() {
       bat = new Bat (this, 10, 375);
       //ball = new Ball (this);
   }

   public void startBall() {
	soundManager.playSound("background", true); // request sound manager to play background clip
	ball = new Ball (this, bat, infoPanel);
      	ball.start();
   }

   public void drawGameEntities() {

       if (bat != null)
       	  bat.draw();

   }

  public void updateGameEntities(int direction) {

	if (bat == null)
	  return;

	if (direction == 1) {
	  bat.erase();
       	  bat.moveLeft();
	}
	else
	if (direction == 3) {
	  bat.erase();
       	  bat.moveRight();
	}

 }

   public void setX (int xPos) {
	x = xPos;
   }

   public void setY (int yPos) {
	y = yPos;
   }

   public void paintComponent (Graphics g) {

      super.paintComponent(g);

     //repaint();
   }
}
