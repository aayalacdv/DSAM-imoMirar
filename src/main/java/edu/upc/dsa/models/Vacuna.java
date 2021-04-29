package edu.upc.dsa.models;

public class Vacuna {
    String id;
    String idUsuario;
    String fechaAplicación;
    int numVacunas;


    public Vacuna(){};
    public Vacuna(String id, String idUsuario, String fechaAplicación){
        this();
        this.id = id;
        this.idUsuario = idUsuario;
        this.fechaAplicación = fechaAplicación;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaAplicación() {
        return fechaAplicación;
    }

    public void setFechaAplicación(String fechaAplicación) {
        this.fechaAplicación = fechaAplicación;
    }

    public int getNumVacunas() {
        return numVacunas;
    }

    public void setNumVacunas(int numVacunas) {
        this.numVacunas = numVacunas;
    }

    @Override
    public String toString() {
        return "Vacuna{" +
                "id='" + id + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", fechaAplicación='" + fechaAplicación + '\'' +
                ", numVacunas=" + numVacunas +
                '}';
    }
}
