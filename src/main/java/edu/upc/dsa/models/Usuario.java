package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

import java.util.List;
import java.util.ArrayList;


public class Usuario {

    private String id;
    private String nombre;

    private List<Seguimiento> seguimientos;

    public Usuario(){
        this.id = RandomUtils.getId();
    }
    public Usuario( String nombre){
        this();
        this.nombre = nombre;
        this.seguimientos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", seguimientos=" + seguimientos +
                '}';
    }



}
