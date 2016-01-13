/* MAIN WINDOW
 * MainWindow Java Class
 *  
 * **************EXECUTE THIS FILE**********************
 * 
 * contains a game object (operating as a separate thread) for logic
 * contains a gameWindow to draw graphics
 * 
 * 
 * */

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.work.game.Game;
import com.work.game.GameWindow;

public class MainWindow {

 public static void main(String[] args) {
  int width = 1020;
  int height = 620; 

  //game object
  Game game = new Game();

  //a GameWindow object, and passes game object
  GameWindow gw = new GameWindow(game);
  //initializing and starting game, a separate thread
  game.init(gw, width, height);
  game.start();

  //JFrame intialization
  gw.setSize(width, height);
  gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  gw.setResizable(false);
  gw.setVisible(true);

  //looping for graphics
  while (true) {
   gw.validate();
   gw.repaint();
   
   try {
    Thread.sleep(10);
   } catch (InterruptedException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }

 }

}
