package controller;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import fx.util.*;
import fx.camera.*;
import gravitazione.util.*;
import gravitazione.*;


public class AstropaController {

  private AnimationTimer timer;
  private boolean timerStatus;
  private long lastNanoTime;
  private SistemaSolare3D solarSystem;
  private double speed;

  @FXML
  private Text astro_pa;

  @FXML
  private Pane controll_pane;

  @FXML
  private ImageView play_image;

  @FXML
  private ImageView pause_image;

  @FXML
  private ImageView fast_image;

  @FXML
  private ImageView slow_image;

  @FXML
  private SubScene scene;

  @FXML
  private Button play_btn;

  @FXML
  private Button pause_btn;

  @FXML
  private Button fast_btn;

  @FXML
  private Button slow_btn;

  @FXML
  void pause(ActionEvent event) {
    play_btn.setDisable(false);
    slow_btn.setDisable(false);
    fast_btn.setDisable(false);
    pause_btn.setDisable(true);
    timer.stop();
  }

  @FXML
  void play(ActionEvent event) {
    play_btn.setDisable(true);
    pause_btn.setDisable(false);
    slow_btn.setDisable(false);
    fast_btn.setDisable(false);
    speed = 1;
    lastNanoTime = System.nanoTime();
    timer.start();
  }

  @FXML
  void playx05(ActionEvent event) {
    slow_btn.setDisable(true);
    play_btn.setDisable(false);
    pause_btn.setDisable(false);
    fast_btn.setDisable(false);
    speed = 0.5;
    lastNanoTime = System.nanoTime();
    timer.start();
  }

  @FXML
  void playx2(ActionEvent event) {
    slow_btn.setDisable(false);
    play_btn.setDisable(false);
    pause_btn.setDisable(false);
    fast_btn.setDisable(true);
    speed = 2;
    lastNanoTime = System.nanoTime();
    timer.start();
  }

  @FXML
  void initialize() throws IOException {
    URL play_url = getClass().getClassLoader().getResource("resources/icons/play.png");
    URL pause_url = getClass().getClassLoader().getResource("resources/icons/pause.png");
    URL fast_url = getClass().getClassLoader().getResource("resources/icons/fast.png");
    URL slow_url = getClass().getClassLoader().getResource("resources/icons/slow.png");
    Image play = new Image(play_url.openStream());
    Image pause = new Image(pause_url.openStream());
    Image slow = new Image(slow_url.openStream());
    Image fast = new Image(fast_url.openStream());
    play_image.setImage(play);
    pause_image.setImage(pause);
    fast_image.setImage(fast);
    slow_image.setImage(slow);
    speed = 1;

    setTimerFunction();
  }

  private void setTimerFunction() {
    timer = new AnimationTimer() {
      public void handle(long currentNanoTime) {
        double dt = (currentNanoTime - lastNanoTime)/5e2;
        lastNanoTime = currentNanoTime;
        solarSystem.evolve(dt*speed);
        for(CorpoCeleste3D pianeta: solarSystem.getSystem()){
          if(pianeta instanceof Model){
            Model tmp = (Model)pianeta;
            tmp.render();
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
    AnchorPane parent = (AnchorPane) scene.getParent();
    scene = new SubScene(root,0,0,true,SceneAntialiasing.BALANCED);
    scene.heightProperty().bind(parent.heightProperty());
    scene.widthProperty().bind(parent.widthProperty());
    scene.setManaged(false);
    parent.getChildren().add(scene);
    return scene;
  }

  public void resetOrder(){
    astro_pa.toFront();
    controll_pane.toFront();
  }

}
