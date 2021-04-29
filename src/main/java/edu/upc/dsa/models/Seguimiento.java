package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

public class Seguimiento {
    private String id;
    private String fecha;
    private String comentario;
    private String userID;

    public String getUserID() {

        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Seguimiento(){
        this.id = RandomUtils.getId();
    }
    public Seguimiento ( String fecha, String comentario){
        this();
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Seguimiento{" +
                "id='" + id + '\'' +
                ", fecha='" + fecha + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
