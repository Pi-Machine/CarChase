/*  
 * Vector class for a mathematical/physics vector
 * direction and value
 * 
 * */

package math;

public class Vector2 {

 public final static Vector2 X = new Vector2(1, 0);
 public final static Vector2 Y = new Vector2(0, 1);
 public final static Vector2 Zero = new Vector2(0, 0);

 public float x;
 public float y;

 public Vector2() {
 }

 public Vector2(float x, float y) {
  this.x = x;
  this.y = y;
 }

 public Vector2(Vector2 v) {
  set(v);
 }

 public Vector2 copy() {
  return new Vector2(this);
 }

 public static float len(float x, float y) {
  return (float) Math.sqrt(x * x + y * y);
 }

 public float len() {
  return (float) Math.sqrt(x * x + y * y);
 }

 public static float len2(float x, float y) {
  return x * x + y * y;
 }

 public float len2() {
  return x * x + y * y;
 }

 public Vector2 set(Vector2 v) {
  x = v.x;
  y = v.y;
  return this;
 }

 public Vector2 set(float x, float y) {
  this.x = x;
  this.y = y;
  return this;
 }

 public Vector2 subtract(Vector2 v) {
  x -= v.x;
  y -= v.y;
  return this;
 }

 public Vector2 subtract(float x, float y) {
  this.x -= x;
  this.y -= y;
  return this;
 }

 public Vector2 add(Vector2 v) {
  x += v.x;
  y += v.y;
  return this;
 }

 public Vector2 add(float x, float y) {
  this.x += x;
  this.y += y;
  return this;
 }

 public Vector2 multiply(float x) {
  this.x *= x;
  this.y *= x;
  return this;
 }

 public static Vector2 multiply(Vector2 v, float x) {
  v.x *= x;
  v.y *= x;
  return v;
 }

 public static float distance(float x1, float y1, float x2, float y2) {
  final float x_d = x2 - x1;
  final float y_d = y2 - y1;
  return (float) Math.sqrt(x_d * x_d + y_d * y_d);
 }

 public float distance(Vector2 v) {
  final float x_d = v.x - x;
  final float y_d = v.y - y;
  return (float) Math.sqrt(x_d * x_d + y_d * y_d);
 }

 public float distance(float x, float y) {
  final float x_d = x - this.x;
  final float y_d = y - this.y;
  return (float) Math.sqrt(x_d * x_d + y_d * y_d);
 }

 public static float distance2(float x1, float y1, float x2, float y2) {
  final float x_d = x2 - x1;
  final float y_d = y2 - y1;
  return x_d * x_d + y_d * y_d;
 }

 public float distance2(Vector2 v) {
  final float x_d = v.x - x;
  final float y_d = v.y - y;
  return x_d * x_d + y_d * y_d;
 }

 public float distance2(float x, float y) {
  final float x_d = x - this.x;
  final float y_d = y - this.y;
  return x_d * x_d + y_d * y_d;
 }
 
 public Vector2 clamp (float min, float max) {
  final float len2 = len2();
  if (len2 == 0f)
   return this;
  float max2 = max * max;
  if (len2 > max2)
   return scl((float)Math.sqrt(max2 / len2));
  float min2 = min * min;
  if (len2 < min2)
   return scl((float)Math.sqrt(min2 / len2));
  return this;
 }
 
 public Vector2 scl (float scalar) {
  x *= scalar;
  y *= scalar;
  return this;
 }
 
 public Vector2 scl (float x, float y) {
  this.x *= x;
  this.y *= y;
  return this;
 }
 
 public Vector2 scl (Vector2 v) {
  this.x *= v.x;
  this.y *= v.y;
  return this;
 }

 public Vector2 normalize() {
  float len = len();
  if (len != 0) {
   x /= len;
   y /= len;
  }
  return this;
 }

 public String toString() {
  return "[" + x + ":" + y + "]";
 }

 public float angle() {
  float angle = (float) (Math.atan2(y, x) * 180f / Math.PI);
  if (angle < 0)
   angle += 360;
  return angle;
 }

 public float angleRad() {
  return (float) Math.atan2(y, x);
 }

 public Vector2 setAngle(float degrees) {
  return setAngleRad((float) (degrees * 180f / Math.PI));
 }

 public Vector2 setAngleRad(float radians) {
  this.set(len(), 0f);
  this.rotateRad(radians);

  return this;
 }

 public Vector2 rotate(float degrees) {
  return rotateRad((float) (degrees * 180f / Math.PI));
 }

 public Vector2 rotateRad(float radians) {
  float cos = (float) Math.cos(radians);
  float sin = (float) Math.sin(radians);

  float newX = this.x * cos - this.y * sin;
  float newY = this.x * sin + this.y * cos;

  this.x = newX;
  this.y = newY;

  return this;
 }

 public boolean isZero() {
  return x == 0 && y == 0;
 }

 public boolean isZero(final float margin) {
  return len2() < margin;
 }

 public Vector2 setZero() {
  this.x = 0;
  this.y = 0;
  return this;
 }
}
