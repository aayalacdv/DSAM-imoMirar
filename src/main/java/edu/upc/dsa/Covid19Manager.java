package edu.upc.dsa;

import java.util.List;

import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;

public interface Covid19Manager {

    public List<Vacuna> vacunasAlfabeticamente();
    public void aplicarVacuna(String idVacuna,String nombrePersona, String fecha) throws Exception;
    public List<String> vacunasPorCantidad();
    public void hacerSeguimiento(String userId, String fecha, String comentario);
    public List<Seguimiento> seguimientoUsuario(String idUsuario);

}
