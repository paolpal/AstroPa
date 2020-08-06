package gravitazione.util;

import java.util.*;

public class Vector2D {

  protected double x;
  protected double y;

  public Vector2D(){
    x = 0;
    y = 0;
  }

  public Vector2D(double _x, double _y){
    x = _x;
    y = _y;
  }

  public Vector2D(Vector2D vector){
    x = vector.x;
    y = vector.y;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public double mod(){
    return Math.sqrt(x*x+y*y);
  }

  public Vector2D multiply(double k){
    return new Vector2D(x*k,y*k);
  }

  public Vector2D divide(double d){
    return this.multiply(1/d);
  }

  public void sign(){
    x = -x;
    y = -y;
  }

  public Vector2D unit(){
    return this.divide(this.mod());
  }

  public Vector2D sum(Vector2D vector){
    return new Vector2D(this.x+vector.x,this.y+vector.y);
  }

  public Vector2D diff(Vector2D vector){
    return new Vector2D(this.x-vector.x,this.y-vector.y);

  }

  public String toString(){
    return "("+x+","+y+")";
  }

}
