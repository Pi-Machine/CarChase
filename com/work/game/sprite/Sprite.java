/*  
 * Sprite class
 * sprites in general here
 * 
 * */

package com.work.game.sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import math.Vector2;

public abstract class Sprite {
 private Vector2 location;
 private Vector2 size;
 private Color colour;
 private float rotateAngle;
 private Image image;

 public Vector2 getLocation() {
  return location;
 }

 public void setLocation(Vector2 location) {
  this.location = location;
 }

 public Vector2 getSize() {
  return size;
 }

 public void setSize(Vector2 size) {
  this.size = size;
 }

 public void draw(Graphics2D g) {
  g.setColor(getColour());
  Vector2 v = location.copy().subtract(size.copy().multiply(0.5f));

  g.rotate(Math.toRadians(rotateAngle), v.x, v.y);

  g.drawImage(image, (int) v.x, (int) v.y, (int) getSize().x, (int) getSize().y, null);

  g.rotate(Math.toRadians(-1 * rotateAngle), v.x, v.y);
 }

 public Color getColour() {
  return colour;
 }

 public void setColour(Color colour) {
  this.colour = colour;
 }

 public Rectangle getBound() {
  return new Rectangle((int) getLocation().x, (int) getLocation().y,
    (int) getSize().x, (int) getSize().y);
 }

 public float getRotateAngle() {
  return rotateAngle;
 }

 public void setRotateAngle(float rotateAngle) {
  this.rotateAngle = rotateAngle;
 }

 public Image getImage() {
  return image;
 }

 public void setImage(Image image) {
  this.image = image;
 }

}
