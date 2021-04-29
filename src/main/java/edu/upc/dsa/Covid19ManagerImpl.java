package edu.upc.dsa;

import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Usuario;
import edu.upc.dsa.models.Vacuna;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Covid19ManagerImpl implements Covid19Manager {

    HashMap<String, Usuario> usuarios;
    List<Vacuna> vacunasList;
    String[] marcasVacunas;

    private static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);


    private static Covid19ManagerImpl manager;
    public static Covid19ManagerImpl getInstance(){
        if(manager == null){
            manager = new Covid19ManagerImpl();
        }
        return manager;
    }

    private Covid19ManagerImpl (){
        this.usuarios = new HashMap<>();
        this.vacunasList = new ArrayList<>();
        this.marcasVacunas = new String[100];
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Vacuna> getVacunasList() {
        return vacunasList;
    }

    public void setVacunasList(List<Vacuna> vacunasList) {
        this.vacunasList = vacunasList;
    }

    public String[] getMarcasVacunas() {
        return marcasVacunas;
    }

    public void setMarcasVacunas(String[] marcasVacunas) {
        this.marcasVacunas = marcasVacunas;
    }

    //complementarias
    public Usuario getUsuarioById(String id){
        return this.usuarios.get(id);
    }



    @Override
    public List<Vacuna> vacunasAlfabeticamente() {
        this.vacunasList.sort((Vacuna a, Vacuna b)->{
           int cmp = a.getId().compareTo(b.getId());
           if (cmp == 0)
               cmp = a.getFechaAplicación().compareTo(b.getFechaAplicación());
           return cmp;
        });
       return this.vacunasList;
    }

    @Override
    public void aplicarVacuna(String idVacuna,  String nombrePersona, String fecha) throws Exception{
        //miramos que la vacuna se encuentre dentro de las marcas
        boolean found = false;
       for(String s: this.marcasVacunas){
          if ( idVacuna.equals(s) ) found = true;

       }
       if(!found){
           throw new Exception("vacuna no encontrada");
       }
       else{
           //Creamos una vacuna nueva y le metemos la fecha de aplicación y también
           logger.info("Aplicando vacuna");
           logger.debug(this.vacunasList);
           logger.debug(this.usuarios);
           Usuario u =  new Usuario(nombrePersona);
           Vacuna v = new Vacuna(idVacuna, u.getId(), fecha);
           //cantidad de vacuans aplicadas
           int n = 0 ;
           for( Vacuna a: this.vacunasList){
               if(v.getId().equals(a.getId())){
                   n= a.getNumVacunas();
               }
           }
           n = n +1 ;
           //acutalizamos el número de vacunas
           this.vacunasList.add(v);
           for( Vacuna a: this.vacunasList){
               if(v.getId().equals(a.getId())){
                   a.setNumVacunas(n);
               }
           }
           //admitiendo que ul usuario no ha de tener más de una vacuna:
           this.usuarios.put(u.getId(),u);

           logger.info("Vacuna aplicada");
           logger.debug(this.vacunasList);
           logger.debug(this.usuarios);

       }
    }

    @Override
    public List<String> vacunasPorCantidad() {
        this.vacunasList.sort((Vacuna a, Vacuna b) -> Integer.compare(a.getNumVacunas(),b.getNumVacunas()));
        Collections.reverse(this.vacunasList);
        List<String > marcas = new ArrayList<>();
        for(Vacuna v : this.vacunasList){
            marcas.add(v.getId());
        }
        return marcas;
    }

    @Override
    public void hacerSeguimiento(String userId, String fecha, String comentario) {
        logger.info("haciendo seguimiento ");
        logger.debug(getUsuarioById(userId).getSeguimientos());
        logger.debug(this.usuarios);
        Seguimiento seg = new Seguimiento(fecha,comentario);
        //meterlo en la lista del usuario
        Usuario u = getUsuarioById(userId);
        seg.setUserID(u.getId());
        u.getSeguimientos().add(seg);
        //actualizar hasmap de usuarios
        this.usuarios.put(u.getId(),u);
        logger.info("seguimiento completado");
        logger.debug(getUsuarioById(userId).getSeguimientos());
        logger.debug(this.usuarios);

    }

    @Override
    public List<Seguimiento> seguimientoUsuario(String idUsuario) {
        return getUsuarioById(idUsuario).getSeguimientos();
    }


    public void setUpResources(){
        //crear usuarios
        Usuario u1 = new Usuario("axel");
        Usuario u2 = new Usuario("alex");
        Usuario u3 = new Usuario("marc");
        u1.setId("1234");
        this.usuarios.put(u1.getId(), u1);
        this.usuarios.put(u2.getId(), u2);
        this.usuarios.put(u3.getId(),u3);
        //crear marcas
        String m1 = "rusa";
        String m2 = "cubana";
        String m3 = "holandesa";

        this.marcasVacunas[0] = m1;
        this.marcasVacunas[1] = m2;
        this.marcasVacunas[2] = m3;
        //crear vacunas

        Vacuna v1 = new Vacuna(m1,u1.getId(),"2021/5/23");
        Vacuna v2 = new Vacuna(m2,u2.getId(),"2020/6/18");
        Vacuna v3 = new Vacuna(m2,u3.getId(),"2020/5/18");

        v1.setNumVacunas(1);
        v2.setNumVacunas(2);
        v3.setNumVacunas(2);

        this.vacunasList.add(v1);
        this.vacunasList.add(v2);
        this.vacunasList.add(v3);

    }

    public void tearDownResources(){
        setUsuarios(new HashMap<>());
        setVacunasList(new ArrayList<>());
        setMarcasVacunas(new String[100]);

    }
}
