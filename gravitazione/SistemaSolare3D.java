package gravitazione;

import java.util.*;
import gravitazione.util.*;

public class SistemaSolare3D {
  private ArrayList<CorpoCeleste3D> system;
  private double dt;
  private double tMax;

  public SistemaSolare3D(){
    system = new ArrayList<CorpoCeleste3D>();
    dt = 0;
    tMax = 0;
  }

  public void addCorpo(CorpoCeleste3D corpo){
    system.add(corpo);
  }

  public void setdt(double deltaTime){
    dt = deltaTime;
  }

  public void setTMax(double tempoMax){
    tMax = tempoMax;
  }

  public ArrayList<CorpoCeleste3D> getSystem(){
    return system;
  }

  public void evolve(double dt){
    for(CorpoCeleste3D corpo : system){
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
      for(CorpoCeleste3D corpo : system){
        System.out.println(corpo);
      }
      t+=dt;
    }
  }
}
