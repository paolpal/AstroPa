/**
 * Sample Skeleton for 'uniSandBox.fxml' Controller Class
 */

package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.SubScene;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.Group;


import fx.util.*;
import fx.camera.*;
import gravitazione.util.*;
import gravitazione.*;

public class UniSandControll {

  private AnimationTimer timer;
  private boolean timerStatus;
  private long lastNanoTime;
  private SistemaSolare3D solarSystem;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private ListView<?> planetList;

  @FXML
  private ToggleButton TimerStatus;

  @FXML
  private SubScene scene3d;

  @FXML
  void initialize() {
    setTimerFunction();
    timerStatus = false;
    //System.out.println(scene3d.getAntiAliasing());
    //SceneAntialiasing a = scene3d.getAntiAliasing();
    //a = SceneAntialiasing.BALANCED;
  }

  @FXML
  void click(ActionEvent event) {
    if(timerStatus == false) {
      lastNanoTime = System.nanoTime();
      timer.start();
      timerStatus = true;
    }
    else {
      timer.stop();
      timerStatus = false;
    }
  }

  private void setTimerFunction() {
    timer = new AnimationTimer() {
      public void handle(long currentNanoTime) {
        double dt = (currentNanoTime - lastNanoTime)/5e2;
        lastNanoTime = currentNanoTime;
        solarSystem.evolve(dt);
        for(CorpoCeleste3D pianeta: solarSystem.getSystem()){
          if(pianeta instanceof Model){
            Model tmp = (Model)pianeta;
            tmp.render();
            //tmp.getSphere().setTranslateX(tmp.getSphere().getTranslateX()/100000);
            //tmp.getSphere().setTranslateY(tmp.getSphere().getTranslateY()/100000);
            //tmp.getSphere().setTranslateZ(tmp.getSphere().getTranslateZ()/100000);
            //System.out.println("("+tmp.getSphere().getTranslateX()+" "+tmp.getSphere().getTranslateY()+" "+tmp.getSphere().getTranslateZ()+")");
            //System.out.println("("+tmp.getSphere().getScaleX()+" "+tmp.getSphere().getScaleY()+" "+tmp.getSphere().getScaleZ()+")\n");
            //System.out.println("("+tmp.getSphere().getLayoutX()+" "+tmp.getSphere().getLayoutY()+" "+"tmp.getSphere().getLayoutZ()"+")\n");
          }
        }
      }
    };
  }

  public void setSolarSystem(SistemaSolare3D solarSystem) {
    this.solarSystem = solarSystem;
  }

  public SubScene get3DAmbient(Group root) {
    //*********
    // SubScene Resizable
    //*********
    AnchorPane parent = (AnchorPane)scene3d.getParent();
    scene3d = new SubScene(root,0,0,true,SceneAntialiasing.BALANCED);
    scene3d.heightProperty().bind(parent.heightProperty());
    scene3d.widthProperty().bind(parent.widthProperty());
    scene3d.setManaged(false);
    parent.getChildren().add(scene3d);
    return scene3d;
  }
}
