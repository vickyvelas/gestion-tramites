/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.beans;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julie
 */
public class NodoPantalla {
   int codigo;
   String nombre="";
   double xpos=0;
   double ypos=0;
   List<Integer> destinos= new ArrayList<Integer>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getXpos() {
        return xpos;
    }

    public void setXpos(double xpos) {
        this.xpos = xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public void setYpos(double ypos) {
        this.ypos = ypos;
    }
    
    public void addDestino(int destino) {
        this.destinos.add(destino);
    }
   
}
