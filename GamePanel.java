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
   
   int x;
   int y;			// reference position to draw entities in 2D space
   
   Bat bat;
   Ball ball;
   Goal goal;
   Player player;
   Football football;
   InfoPanel infoPanel;

   SoundManager soundManager;

   public GamePanel (InfoPanel infoPanel) {
	setBackground(Color.CYAN);
	this.infoPanel = infoPanel;
	soundManager = SoundManager.getInstance();	// obtain reference to SoundManager
   }

   public void createGameEntities() {
       player = new Player (this, 10, 375);
       goal = new Goal(this,150,5);
   }

   public void startBall() {
	soundManager.playSound("background", true); // request sound manager to play background clip
	ball = new Ball (this, bat, infoPanel);
      	ball.start();
   }
   public void kickBall(){
      if(football!=null){
       if(football.gety()<0){
         football = new Football(this, bat, infoPanel,player,this.goal);
         football.start();
       }
      }else{
      football = new Football(this, bat, infoPanel,player,this.goal);
      football.start();}
   }
   public void startGoal(){
      goal = new Goal(this,150,5);
      goal.start();
   }


   public void drawGameEntities() {

       if (player != null&&goal!= null){
         player.draw();
         goal.draw();
       }
       	
      

   }

  public void updateGameEntities(int direction) {

	if (player == null)
	  return;

	if (direction == 1) {
	  player.erase();
       	  player.moveLeft();
	}
	else
	if (direction == 3) {
	  player.erase();
       	  player.moveRight();
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
