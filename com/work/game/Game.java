
/* 
 * Main Game Logic and Updating
 * extends Thread (runs as a separate thread)
 * implements MouseListener and must use
 * MouseEntered, MouseExited, MouseClicked, MousePressed, MouseReleased methods
 * to get info from mouse
 * 
 * intializes
 * updates the game logic each time
 * draws the game onto the bufferedimage
 * */
package com.work.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import math.Vector2;

import com.work.game.sprite.Cop;
import com.work.game.sprite.Gold;
import com.work.game.sprite.Robber;

public class Game extends Thread implements MouseListener  {

 private boolean atEnd = false;
 private boolean atMenu = true;
 private boolean atInstr = false;
 private JFrame frame;
 private Robber robber = new Robber();
 private List<Cop> cops = new ArrayList<Cop>();
 private Gold gold = new Gold();
 private int score;
 private Graphics2D backgroundGraphics;
 private BufferedImage bufferedImage;
 private FontMetrics metrics;
 private Random rand = new Random();
 private Image policeImage;
 private Image robberImage;
 private Image goldImage;
 private int width;
 private int height;
 private GraphicsConfiguration config = GraphicsEnvironment
   .getLocalGraphicsEnvironment().getDefaultScreenDevice()
   .getDefaultConfiguration();

 public void init(JFrame frame, int width, int height) {
  this.frame = frame;
  this.width = width;
  this.height = height;
  this.frame.addMouseListener(this);
  

  bufferedImage = config.createCompatibleImage(width, height);

  this.backgroundGraphics = (Graphics2D) bufferedImage.getGraphics();
  Font f = new Font("Comic Sans MS", Font.BOLD, 50);
  this.backgroundGraphics.setFont(f);
  metrics = backgroundGraphics.getFontMetrics(f);

  policeImage = loadImage("police.png");
  robberImage = loadImage("robber.png");
  goldImage = loadImage("gold.png");
  
  load();
 }

 public void load() {
  atMenu = true;
  atEnd = false;
  cops.clear();
  score = 0;
  
  initializeRobber();
  initGold();
 }

 private void initializeRobber() {
  robber.setLocation(new Vector2(width / 2, height / 2));
  robber.setSize(new Vector2(34, 16));
  robber.setAcceleration(new Vector2(0.001f, 0f));
  robber.setMaxSpeed(0.3f);
  robber.setImage(robberImage);
 }

 private void initGold() {
  gold.setLocation(new Vector2(robber.getLocation().x - 100, robber
    .getLocation().y - 100));// new Vector2(width /
  // 3, height / 4));
  gold.setSize(new Vector2(32, 32));
  gold.setImage(goldImage);

 }

 private Image loadImage(String name) {
  try {
   return ImageIO.read(new File(name));
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
  return null;
 }

 public void render(Graphics2D g2d) {
  backgroundGraphics = (Graphics2D) bufferedImage.getGraphics();
  if(atEnd){
    backgroundGraphics.drawImage(loadImage("game over.png"), 0, 0, width, height, null);
  }else if(atMenu){
     backgroundGraphics.drawImage(loadImage("Car_chase.jpg"), 0, 0, width, height, null);
     
   }else if(atInstr){
     backgroundGraphics.setColor(Color.BLACK);
     backgroundGraphics.fillRect(0, 0, width, height);
     backgroundGraphics.drawImage(loadImage("begin.png"), 0, 0, width, height, null);
   }else {  
  renderGame(backgroundGraphics); 
   }
  g2d.drawImage(bufferedImage, 3, 15, width, height, null);
 }

 private void renderGame(Graphics2D g) {
  g.setColor(Color.BLACK);
  g.fillRect(0, 0, width, height);

  robber.draw(g);
  gold.draw(g);
  for (int i = 0; i < cops.size(); i++) {
   cops.get(i).draw(g);
  }

  String scoreText = "Score:  " + score;
  g.setColor(Color.WHITE);
  int length = metrics.stringWidth(scoreText);
  g.drawString(scoreText, (width - length) / 2, 100);
 }

 @Override
 public void run() {
   while(true){
   while(atMenu || atEnd || atInstr){
     try{
       Thread.sleep(10);
     }catch(Exception e){
     e.printStackTrace();
     }       
   }
   
  long previousTime = System.currentTimeMillis();
  while (!atMenu && !atEnd && !atInstr) {
   long currentTime = System.currentTimeMillis();
   long timeElapsed = currentTime - previousTime;
   previousTime = currentTime;

   updateGame(timeElapsed);
  }
   }
 }

 // MAIN PROCESSING HERE
 private void updateGame(long timeElapsed) {
  // update game logic here
  if (!atMenu && !atEnd && !atInstr) {
   // INPUT - from Mouse
   Point p = MouseInfo.getPointerInfo().getLocation();
   SwingUtilities.convertPointFromScreen(p, frame);

   p = new Point(p.x - 12, p.y - 45);
   Vector2 v = new Vector2(p.x, p.y);

   robber.update(v.copy(), timeElapsed);

   for (Cop cop : cops) {
    cop.update(robber.getLocation().copy(), timeElapsed);
    if (robber.intersects(cop)) {
     atMenu = false;
     atInstr = false;
     atEnd = true;
    }
   }

   if (robber.intersects(gold)) {
    relocate();
    generateCop();
    score++;
   }
  }
 }

 public void relocate() {
  gold.setLocation(new Vector2(rand.nextInt(width - 120) + 60, rand
    .nextInt(height - 100) + 50));
 }

 public void generateCop() {
  Cop cop = new Cop();
  cop.setLocation(new Vector2(-10, -10));
  cop.setSize(new Vector2(34, 16));
  cop.setAcceleration(new Vector2(
    (float) (rand.nextDouble() * 0.0005f + 0.0002f), 0f));
  cop.setMaxSpeed((float) (rand.nextDouble() * 0.3f + 0.2));
  cop.setImage(policeImage);
  cops.add(cop);
 }
 
 public void restart(){
   load();
 }
 
  // Implemented Mouse Listener Methods (Input from Mouse)
 @Override
 public void mouseClicked(MouseEvent e) {
  if (atEnd && !atMenu) {
    atMenu = true;
    restart();
  }else if (!atEnd && atMenu){
    atMenu = false;
    atInstr = true;
  }else if(atInstr){
    atInstr = false;
  }
 }
 
  @Override
 public void mouseEntered(MouseEvent e) {
  // TODO Auto-generated method stub

 }

 @Override
 public void mouseExited(MouseEvent e) {
  // TODO Auto-generated method stub

 }

 @Override
 public void mousePressed(MouseEvent e) {
  // TODO Auto-generated method stub

 }

 @Override
 public void mouseReleased(MouseEvent e) {
  // TODO Auto-generated method stub

 }

}
