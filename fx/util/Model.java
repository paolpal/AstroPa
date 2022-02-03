package fx.util;

import java.net.URL;
import java.io.IOException;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import gravitazione.util.*;
import gravitazione.*;

public class Model extends CorpoCeleste3D {

  //***********************//
  // Raggio in Raggi Terrestri
  // La terra rimane con 20 px
  // gli altri pianeti vengono
  // modificati in proporzione
  //***********************//
  private Sphere globo;
  private double radius;
  private double rotationRate;
  private final static double rTerra = 10;
  private final static double rotTerra = 1;

  public Model(){
    super();
    radius = 1;
    globo = new Sphere(rTerra);
    rotationRate = 1;
    globo.setRotationAxis(Rotate.Y_AXIS);
  }

  public Model(double mass){
    super(mass);
    radius = 1;
    globo = new Sphere(rTerra);
    rotationRate = 1;
    globo.setRotationAxis(Rotate.Y_AXIS);
  }

  public Model(double mass, Vector3D _position){
    super(mass,_position);
    radius = 1;
    globo = new Sphere(rTerra);
    rotationRate = 1;
    globo.setRotationAxis(Rotate.Y_AXIS);
  }

  public Model(double mass, Vector3D _position, Vector3D velocity){
    super(mass,_position,velocity);
    radius = 1;
    globo = new Sphere(rTerra);
    rotationRate = 1;
    globo.setRotationAxis(Rotate.Y_AXIS);
  }

  public Model(double mass, Vector3D _position, Vector3D velocity, double rad){
    super(mass,_position,velocity);
    radius = rad;
    globo = new Sphere(rescaleRad(radius)*rTerra);
    rotationRate = 1;
    globo.setRotationAxis(Rotate.Y_AXIS);
  }

  public void setImage(Image i){
    PhongMaterial material = new PhongMaterial();
    material.setDiffuseMap(i);
    globo.setMaterial(material);
  }

  public void setImage(String filename) throws IOException{
    URL image_url = getClass().getClassLoader().getResource(filename);
    Image i = new Image(image_url.openStream());
    setImage(i);
  }

  public Sphere getSphere(){
    return globo;
  }

  public void setRotationRate(double rate){
    rotationRate = rate;
  }

  public void setAxialTilt(double deg){
    Rotate ry = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
    ry.setAngle(deg);
    globo.getTransforms().add(ry);
  }

  private double abs(double n){
    return Math.abs(n);
  }

  private double log(double n){
    return Math.log(n);
  }

  private double log(double n, double b){
    return Math.log(n)/Math.log(b);
  }

  private double rescaleDist(double n){
    double sign = n/abs(n);
    return sign * log(1+abs(n),1.01);
  }

  private double rescaleRad(double n){
    return log(n+1,5);
  }

  public void render(){
    /*globo.setTranslateX(getPos().getX()/(1e9));
    globo.setTranslateY(getPos().getY()/(1e9));
    globo.setTranslateZ(getPos().getZ()/(1e9));*/
    globo.setTranslateX(rescaleDist(getPos().getX()/Universal.UA));
    globo.setTranslateY(getPos().getY());
    globo.setTranslateZ(rescaleDist(getPos().getZ()/Universal.UA));
    globo.rotateProperty().set(globo.getRotate() + rotationRate*rotTerra);
  }

  public String toString(){
    return "Mass : "+getMass()+"\n"
      + "Position : "+getPos()+"\n"
      + "Velocity : "+getVel();
  }

}
