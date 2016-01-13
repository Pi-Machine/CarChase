/* 
 * Graphics and drawing
 * GameWindow class (extends JFrame) has the properties of JFrame
 * calls render from Game class to draw the image onto the screen
 * */

package com.work.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
 
 private static final long serialVersionUID = 3027445574553335368L;
 private Game game;
 
 public GameWindow(Game game){
  this.game = game;
 }
 
 
 //drawing the images for every update
 @Override
 public void paint(Graphics g) { 
//  super.paint(g);
   
  Graphics2D g2d = (Graphics2D) g;
  
  game.render(g2d);
 }
 
}