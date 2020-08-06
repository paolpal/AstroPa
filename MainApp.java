import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.SubScene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;
import javafx.scene.input.ScrollEvent;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.Node;

import controller.*;

public class MainApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private double anchorX, anchorY;
  private double anchorAngleX = 0;
  private double anchorAngleY = 0;
  private final DoubleProperty angleX = new SimpleDoubleProperty(0);
  private final DoubleProperty angleY = new SimpleDoubleProperty(0);

  private void initMouseControl(Group group, SubScene scene, Stage stage) {
    Rotate xRotate;
    Rotate yRotate;
    group.getTransforms().addAll(
        xRotate = new Rotate(0, Rotate.X_AXIS),
        yRotate = new Rotate(0, Rotate.Y_AXIS)
    );
    xRotate.angleProperty().bind(angleX);
    yRotate.angleProperty().bind(angleY);

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

    stage.addEventHandler(ScrollEvent.SCROLL, event -> {
      double delta = event.getDeltaY();
      group.translateZProperty().set(group.getTranslateZ() + delta);
    });
  }

  @Override
  public void start(Stage stage) throws IOException {

    String fxmlDocPath = "interface/astropa.fxml";
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlDocPath));
    Ambient3d ss = new Ambient3d();

    System.out.print("Caricamento interfaccia... ");
    AnchorPane root = (AnchorPane) loader.load();
    System.out.print("completato\n");

    System.out.print("Caricamento controller... ");
    AstropaController controller = loader.getController();
    System.out.print("completato\n");

    Group root3D = new Group();

    SubScene ambient3d = controller.get3DAmbient(root3D);
    root3D.getChildren().add(ss.getBackground());
    root3D.getChildren().add(ss.getGroup());
    ambient3d.setCamera(ss.getCamera());
    ambient3d.setRoot(root3D);

    controller.setSolarSystem(ss.getSolarSystem());

    initMouseControl(ss.getGroup(),ambient3d,stage);

    controller.resetOrder();

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("AstroPa SandBox");
    stage.getIcons().add(new Image(getClass().getClassLoader().getResource("icon/UniSandBox.png").openStream()));
    stage.show();
  }
}
