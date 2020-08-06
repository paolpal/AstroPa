package fx.camera;

import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.ScrollEvent;

import fx.util.*;
import gravitazione.util.*;

public class MyCamera {

  private Camera camera;
  private Model planet;

  private Translate pivot;

  private Rotate yRotate;
  private Rotate xRotate;

  private DoubleProperty posX;
  private DoubleProperty posY;

  private DoubleProperty angleX;
  private DoubleProperty angleY;

  private double anchorX, anchorY;
  private double anchorAngleX = 0;
  private double anchorAngleY = 0;


  private void createRotations(){
    xRotate = new Rotate(0, Rotate.X_AXIS);
    yRotate = new Rotate(0, Rotate.Y_AXIS);

    xRotate.angleProperty().bind(angleX);
    yRotate.angleProperty().bind(angleY);
  }

  private void createPivot(){
    pivot = new Translate();
    pivot.xProperty().bind(posX);
    pivot.zProperty().bind(posY);
  }

  private void setTransformations(){
    camera.getTransforms().addAll(
      pivot,
      yRotate,
      xRotate,
      new Translate(0, 0, -1000)
    );
  }

  public MyCamera(){
    this.camera = new PerspectiveCamera(true);;
    this.camera.setNearClip(1);
    this.camera.setFarClip(10000);
    planet = null;
    posX = new SimpleDoubleProperty(0);
    posY = new SimpleDoubleProperty(0);
    angleX = new SimpleDoubleProperty(0);
    angleY = new SimpleDoubleProperty(0);

    createPivot();
    createRotations();
    setTransformations();
  }

  public Camera getCamera(){
    return camera;
  }

  public void setPlanet(Model planet){
    this.planet = planet;
  }

  public void addScrollListner(Stage stage){
    stage.addEventHandler(ScrollEvent.SCROLL, event -> {
      double delta = event.getDeltaY();
      camera.translateZProperty().set(camera.getTranslateZ() + delta);
    });
  };

  public void addMouseListner(Scene scene){
    scene.setOnMousePressed(event -> {
      anchorX = event.getSceneX();
      anchorY = event.getSceneY();
      anchorAngleX = angleX.get();
      anchorAngleY = angleY.get();
    });

    scene.setOnMouseDragged(event -> {
      angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
      angleY.set(anchorAngleY + anchorX - event.getSceneX());
    });
  };

  public void render(){
    posX.set(planet.getPos().getX()/(1e9));
    posY.set(-planet.getPos().getY()/(1e9));
    //angleY.set(180+planet.getAngleY().get());
  }

}
