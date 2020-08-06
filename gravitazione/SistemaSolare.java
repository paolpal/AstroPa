package gravitazione;

import java.util.*;
import gravitazione.util.*;

public class SistemaSolare {
  private ArrayList<CorpoCeleste> system;
  private double dt;
  private double tMax;

  public SistemaSolare(){
    system = new ArrayList<CorpoCeleste>();
    dt = 0;
    tMax = 0;
  }

  public void addCorpo(CorpoCeleste corpo){
    system.add(corpo);
  }

  public void setdt(double deltaTime){
    dt = deltaTime;
  }

  public void setTMax(double tempoMax){
    tMax = tempoMax;
  }

  public ArrayList<CorpoCeleste> getSystem(){
    return system;
  }

  public void evolve(double dt){
    for(CorpoCeleste corpo : system){
      corpo.evolve(dt,system);
    }
  }

  public void evolve(){
    double t = 0;
    while(t<tMax){
      evolve(dt);
      //***** OUT PUT ******//
      System.out.println("//********************//");
      System.out.println("//   "+t+"   //");
      System.out.println("//********************//");
      for(CorpoCeleste corpo : system){
        System.out.println(corpo);
      }
      t+=dt;
    }
  }

  public static void main(String[] args) {
    SistemaSolare ss = new SistemaSolare();
    Vector2D pos1 = new Vector2D(100,0);
    Vector2D pos2 = new Vector2D(-100,0);
    Vector2D pos3 = new Vector2D(0,100);
    Vector2D pos4 = new Vector2D(0,-100);
    CorpoCeleste C1 = new CorpoCeleste(100,pos1);
    CorpoCeleste C2 = new CorpoCeleste(100,pos2);
    CorpoCeleste C3 = new CorpoCeleste(100,pos3);
    CorpoCeleste C4 = new CorpoCeleste(100,pos4);
    ss.setTMax(1e8);
    ss.setdt(1e3);
    ss.addCorpo(C1);
    ss.addCorpo(C2);
    ss.addCorpo(C3);
    ss.addCorpo(C4);
    ss.evolve();
  }
}
