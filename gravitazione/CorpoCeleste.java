package gravitazione;

import java.util.*;
import gravitazione.util.*;

public class CorpoCeleste {

  //************************************//
  // Massa, Pasozione e Velocità
  //************************************//

  protected double m;
  protected Vector2D x;
  protected Vector2D v;

  public CorpoCeleste(){
    m = 0;
    x = new Vector2D(0,0);
    v = new Vector2D(0,0);
  }

  public CorpoCeleste(double mass){
    m = mass;
    x = new Vector2D(0,0);
    v = new Vector2D(0,0);
  }

  public CorpoCeleste(double mass, Vector2D position){
    m = mass;
    x = new Vector2D(position);
    v = new Vector2D(0,0);
  }

  public CorpoCeleste(double mass, Vector2D position, Vector2D velocity){
    m = mass;
    x = new Vector2D(position);
    v = new Vector2D(velocity);
  }

  public double getMass(){
    return m;
  }

  public Vector2D getPos(){
    return x;
  }

  public Vector2D getVel(){
    return v;
  }

  public void trasla(Vector2D t){
    x = x.sum(t);
  }

  //************************************//
  // Colcolo della risultante delle forze
  // di gravitazione sul corpo celeste
  //************************************//
  public Vector2D getForce(ArrayList<CorpoCeleste> solarSystem){
    Vector2D ris = new Vector2D(0,0);
    for(CorpoCeleste pianeta : solarSystem){
      Vector2D r = new Vector2D(pianeta.getPos().diff(this.getPos()));
      double dist = r.mod();
      Vector2D dir = r.unit();
      if(dist != 0){
        double k = (Universal.G*m*pianeta.getMass())/(dist*dist);
        Vector2D f = new Vector2D(dir.multiply(k));
        ris = ris.sum(f);
      }
    }
    return ris;
  }

  private void evolve(double dt, Vector2D force){
    Vector2D a = new Vector2D(force.divide(m));
    v = v.sum(a.multiply(dt));
    x = x.sum(v.multiply(dt));
  }

  public void evolve(double dt, ArrayList<CorpoCeleste> solarSystem){
    evolve(dt,this.getForce(solarSystem));
  }

  public String toString(){
    return "Mass : "+m+"\n"
      + "Position : "+x+"\n"
      + "Velocity : "+v+"\n";
  }

}
