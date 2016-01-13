/*  
 * Moveable class (extends Sprite)
 * methods for moveable sprites
 * 
 * */

package com.work.game.sprite;

import math.Vector2;

public abstract class Moveable extends Sprite {
 private Vector2 velocity = Vector2.Zero.copy();
 private Vector2 acceleration;
 private float maxSpeed;

 public Vector2 getVelocity() {
  return velocity;
 }

 public void setVelocity(Vector2 velocity) {
  this.velocity = velocity.copy();
 }

 public float getMaxSpeed() {
  return maxSpeed;
 }

 public void setMaxSpeed(float maxSpeed) {
  this.maxSpeed = maxSpeed;
 }

 public Vector2 getAcceleration() {
  return acceleration;
 }

 public void setAcceleration(Vector2 acceleration) {
  this.acceleration = acceleration;
 }

 public boolean intersects(Sprite other) {
  Vector2 loc1 = this.getLocation().copy();
  Vector2 loc2 = other.getLocation().copy();
  float distance = loc1.distance(loc2);
  float r1 = this.getSize().y;
  float r2 = other.getSize().y;
  if(distance < (r1 + r2)){
   return true;
  }
  return false;
 }

}
