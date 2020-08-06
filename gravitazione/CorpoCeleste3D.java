package gravitazione;

import java.util.*;
import gravitazione.util.*;

public class CorpoCeleste3D {

  //************************************//
  // Massa, Pasozione e Velocità
  //************************************//

  protected double m;
  protected Vector3D x;
  protected Vector3D v;

  public CorpoCeleste3D(){
    m = 0;
    x = new Vector3D();
    v = new Vector3D();
  }

  public CorpoCeleste3D(double mass){
    m = mass;
    x = new Vector3D();
    v = new Vector3D();
  }

  public CorpoCeleste3D(double mass, Vector3D position){
    m = mass;
    x = new Vector3D(position);
    v = new Vector3D();
  }

  public CorpoCeleste3D(double mass, Vector3D position, Vector3D velocity){
    m = mass;
    x = new Vector3D(position);
    v = new Vector3D(velocity);
  }

  public double getMass(){
    return m;
  }

  public Vector3D getPos(){
    return x;
  }

  public Vector3D getVel(){
    return v;
  }

  public void trasla(Vector3D t){
    x = x.sum(t);
  }

  //************************************//
  // Colcolo della risultante delle forze
  // di gravitazione sul corpo celeste
  //************************************//
  public Vector3D getForce(ArrayList<CorpoCeleste3D> solarSystem){
    Vector3D ris = new Vector3D();
    for(CorpoCeleste3D pianeta : solarSystem){
      Vector3D r = new Vector3D(pianeta.getPos().diff(this.getPos()));
      double dist = r.mod();
      Vector3D dir = r.unit();
      if(dist != 0){
        double k = (Universal.G*m*pianeta.getMass())/(dist*dist);
        Vector3D f = new Vector3D(dir.multiply(k));
        ris = ris.sum(f);
      }
    }
    return ris;
  }

  private void evolve(double dt, Vector3D force){
    Vector3D a = new Vector3D(force.divide(m));
    v = v.sum(a.multiply(dt));
    x = x.sum(v.multiply(dt));
  }

  public void evolve(double dt, ArrayList<CorpoCeleste3D> solarSystem){
    evolve(dt,this.getForce(solarSystem));
  }

  public String toString(){
    return "Mass : "+m+"\n"
      + "Position : "+x+"\n"
      + "Velocity : "+v+"\n";
  }

}
