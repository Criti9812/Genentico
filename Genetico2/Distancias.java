package Genetico2;

import java.awt.*;
import javax.swing.*;

public class Distancias implements Comparable<Distancias>{
  private String cadena;
  private float distancia;

  public Distancias(String cadena, float distancia){
      this.cadena=cadena;
      this.distancia=distancia;
  }

  public String getCad(){
      return cadena;
  }

  public float getDistancia(){
      return distancia;
  }

  public int compareTo(Distancias distancia2) { 
    return Double.compare(this.distancia, distancia2.distancia);
}

}