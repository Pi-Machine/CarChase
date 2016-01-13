/*  
 * Robber class (extends Moveable)
 * is a Sprite
 * for Robber car, the player's car * 
 * */

package com.work.game.sprite;

import math.Vector2;

public class Robber extends Moveable {

 public void update(Vector2 pointer, long timeElapsed) {
  float a = this.getLocation().copy().subtract(pointer).normalize()
    .angleRad();
  this.setRotateAngle((float) Math.toDegrees(a));

  if (this.getLocation().distance(pointer) < 5f) {
   this.setVelocity(Vector2.Zero);
  } else {
   this.getVelocity()
     .add(getAcceleration().copy().multiply(timeElapsed))
     .clamp(0, 0.3f);
   this.getLocation().subtract(
     this.getVelocity().copy().rotateRad(a).multiply(timeElapsed));
  }

  
 }
}
