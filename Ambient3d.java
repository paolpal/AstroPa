import java.net.URL;
import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Translate;

import fx.util.*;
import fx.camera.*;
import gravitazione.util.*;
import gravitazione.*;

public class Ambient3d {

  private Group solar;
  private Vector3D zero;
  private SistemaSolare3D solarSystem;
  private ImageView universo;
  private Camera camera;

  private void createInternalSystem() throws IOException {
    Model sole = new Model(1.989e30,zero,zero,3);
    sole.setImage("img/3d_model/sole_3d.jpg");
    solar.getChildren().add(sole.getSphere());
    solarSystem.addCorpo(sole);

    Model marte = new Model(6.39e23, new Vector3D(227.9e9,0,0), new Vector3D(0,0,-227.9e9*Math.sin(24.077/227.9e6)), 0.53);
    marte.setImage("img/3d_model/marte_3d.jpg");
    solar.getChildren().add(marte.getSphere());
    solarSystem.addCorpo(marte);

    Model mercurio = new Model(3.285e23,new Vector3D(57.91e9,0,0), new Vector3D(0,0,-57.91e9*Math.sin(47.36/57.91e6)), 0.38);
    mercurio.setImage("img/3d_model/mercurio_3d.jpg");
    solar.getChildren().add(mercurio.getSphere());
    solarSystem.addCorpo(mercurio);

    Model terra = new Model(5.97e24,new Vector3D(149.6e9,0,0),new Vector3D(0,0,-149e9*Math.sin(29.783/149e6)));
    terra.setImage("img/3d_model/terra_3d.jpg");
    terra.setAxialTilt(90);
    solar.getChildren().add(terra.getSphere());
    solarSystem.addCorpo(terra);

    Model venere = new Model(4.867e24,new Vector3D(108.2e9,0,0),new Vector3D(0,0,-108.2e9*Math.sin(35.02/108.2e6)),0.94);
    venere.setImage("img/3d_model/venere_3d.jpg");
    solar.getChildren().add(venere.getSphere());
    solarSystem.addCorpo(venere);
  }

  private void createBackground() throws IOException {
    URL uni_url = getClass().getClassLoader().getResource("img/3d_model/space.jpg");
    Image uni = new Image(uni_url.openStream());
    universo = new ImageView(uni);
    universo.getTransforms().add(new Translate(-uni.getWidth()/2,-uni.getHeight()/2,1800));
  }

  private void createCamera() {
    camera = new PerspectiveCamera(true);
    camera.setNearClip(1);
    camera.setFarClip(10000);
    camera.translateZProperty().set(-1000);
  }

  public Ambient3d() throws IOException {
    solar = new Group();
    zero = new Vector3D();
    solarSystem = new SistemaSolare3D();
    createInternalSystem();
    createBackground();
    createCamera();
  }

  public Camera getCamera() {
    return camera;
  }

  public ImageView getBackground() {
    return universo;
  }

  public Group getGroup() {
    return solar;
  }

  public SistemaSolare3D getSolarSystem() {
    return solarSystem;
  }
}
