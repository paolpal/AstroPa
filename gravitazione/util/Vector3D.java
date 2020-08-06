package gravitazione.util;

import java.util.*;

public class Vector3D {

  protected double x;
  protected double y;
  protected double z;

  public Vector3D(){
    x = 0;
    y = 0;
    z = 0;
  }

  public Vector3D(double _x, double _y, double _z){
    x = _x;
    y = _y;
    z = _z;
  }

  public Vector3D(Vector3D vector){
    x = vector.x;
    y = vector.y;
    z = vector.z;
  }

  public double getX(){
    return x;
  }

  public double getY(){
    return y;
  }

  public double getZ(){
    return z;
  }

  public double mod(){
    return Math.sqrt(x*x+y*y+z*z);
  }

  public Vector3D multiply(double k){
    return new Vector3D(x*k,y*k,z*k);
  }

  public Vector3D divide(double d){
    return this.multiply(1/d);
  }

  public void sign(){
    x = -x;
    y = -y;
    z = -z;
  }

  public Vector3D unit(){
    return this.divide(this.mod());
  }

  public Vector3D sum(Vector3D vector){
    return new Vector3D(this.x+vector.x,this.y+vector.y,this.z+vector.z);
  }

  public Vector3D diff(Vector3D vector){
    return new Vector3D(this.x-vector.x,this.y-vector.y,this.z-vector.z);

  }

  public String toString(){
    return "("+x+","+y+","+z+")";
  }

}
