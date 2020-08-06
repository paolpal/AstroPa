package fx.util;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import gravitazione.util.*;
import gravitazione.*;

public class Sprite extends CorpoCeleste {
  
  //***********************//
  // Raggio in Raggi Terrestri
  // La terra rimane con 20 px
  // gli altri pianeti vengono
  // modificati in proporzione
  //***********************//
  private ImageView image;
  private double radius;
  private final static double rTerra = 10;

  public Sprite(){
    super();
    image = new ImageView();
    image.setPreserveRatio(true);
    radius = 1;
  }

  public Sprite(double mass){
    super(mass);
    image = new ImageView();
    image.setPreserveRatio(true);
    radius = 1;
  }

  public Sprite(double mass, Vector2D _position){
    super(mass,_position);
    image = new ImageView();
    image.setPreserveRatio(true);
    radius = 1;
  }

  public Sprite(double mass, Vector2D _position, Vector2D velocity){
    super(mass,_position,velocity);
    image = new ImageView();
    image.setPreserveRatio(true);
    radius = 1;
  }

  public Sprite(double mass, Vector2D _position, Vector2D velocity, double rad){
    super(mass,_position,velocity);
    image = new ImageView();
    image.setPreserveRatio(true);
    radius = rad;
  }

  public void setImage(Image i){
    image.setImage(i);
    image.setFitWidth(radius*rTerra*2);
    image.setFitHeight(radius*rTerra*2);
  }

  public void setImage(String filename){
    Image i = new Image(filename);
    setImage(i);
  }

  public ImageView getView(){
    return image;
  }

  //******************//
  // I valori 990 e 540
  // sono valori da scegliere in maniera tale da avere
  // un render migliore
  // il programma va utilizzato centrando il sistema nell'origine
  // il programma da solo sposta l'origine nel centro della finestra
  //******************//

  public void _render(){
    image.setX(getPos().getX()/(1e9)-(image.getFitHeight()/2)+990);
    image.setY(getPos().getY()/(1e9)-(image.getFitWidth()/2)+540);
  }

  public void render(){
    if(getPos().mod()<(227.9e9*1.7)){
      image.setX(getPos().getX()/(1e9)-(image.getFitHeight()/2)+990);
      image.setY(getPos().getY()/(1e9)-(image.getFitWidth()/2)+540);
    }
    else{
      image.setX(getPos().getX()/(2.5e9)-(image.getFitHeight()/2)+990);
      image.setY(getPos().getY()/(2.5e9)-(image.getFitWidth()/2)+540);
    }
  }

  public String toString(){
    return "Mass : "+getMass()+"\n"
      + "Position : "+getPos()+"\n"
      + "Velocity : "+getVel();
  }

}
