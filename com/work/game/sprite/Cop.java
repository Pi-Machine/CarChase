/*  
 * Cop class (extends Moveable)
 * a Sprite
 * for Cop car
 * */

package com.work.game.sprite;

import java.awt.Graphics2D;
import java.awt.Image;

import math.Vector2;

public class Cop extends Moveable {
  private long totalTime;
  int id =0;
  
 public void update(Vector2 robber, long timeElapsed) {
   totalTime += timeElapsed;
   id = (int)((totalTime % 900) / 300);
   
   
  float angle = robber.copy().subtract(this.getLocation()).angleRad();
  float angle2 = getVelocity().copy().angleRad();
  //System.out.println("a1=" + Math.toDegrees(angle));
  //System.out.println("a2=" + Math.toDegrees(angle2));

  this.setRotateAngle((float) Math.toDegrees(angle));

  Vector2 counter = getAcceleration().copy();
  counter.rotateRad((float) (angle2 + Math.PI));
  float turn = angle - angle2;
  float perc = (float) ((Math.PI - Math.abs(turn % Math.PI)) / Math.PI);
  counter.multiply(1 - perc);
  getVelocity().add(counter.multiply(timeElapsed)).clamp(0, getMaxSpeed());

  Vector2 accelerate = getAcceleration().copy().rotateRad(angle);
  getVelocity().add(accelerate.multiply(timeElapsed)).clamp(0, getMaxSpeed());

  getLocation().add(getVelocity().copy().multiply(timeElapsed));
 }

  public void draw(Graphics2D g) {
  g.setColor(getColour());
  Vector2 v = getLocation().copy().subtract(getSize().copy().multiply(0.5f));

  g.rotate(Math.toRadians(getRotateAngle()), v.x, v.y);

  g.drawImage(getImage(), (int) v.x, (int) v.y, (int) (getSize().x + v.x), (int) (getSize().y + v.y), id*95, 0, 95 * (id + 1), 45, null);

  g.rotate(Math.toRadians(-1 * getRotateAngle()), v.x, v.y);
 }

}
