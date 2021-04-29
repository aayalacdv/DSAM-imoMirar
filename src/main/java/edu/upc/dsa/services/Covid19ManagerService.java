package edu.upc.dsa.services;

import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Seguimiento;
import edu.upc.dsa.models.Vacuna;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/covid", description = "ENDPOINT COVID MANAGER")
@Path("/covid")
public class Covid19ManagerService {
    private Covid19ManagerImpl manager;

    public Covid19ManagerService (){
        manager = Covid19ManagerImpl.getInstance();
        if(manager.getUsuarios().size() == 0 ){
            manager.setUpResources();
        }
    }

    @GET
    @ApiOperation( value = "/vacuna " , notes = "aplicar vacuna" )
    @ApiResponses( value = {
            @ApiResponse( code = 404, message = " Not found " ),
            @ApiResponse( code = 201, message = "Operation completed "),
     })
     @Path("/vacuna/{idPersona}/{idVacuna}/{fecha}")
     public Response vacunarPersona(@PathParam("idPersona") String idPersona, @PathParam("idVacuna") String idVacuna, @PathParam("fecha") String fecha ){
        try{
            manager.aplicarVacuna(idVacuna,idPersona,fecha);
        }catch(Exception e ){
            return Response.status(404).build();
        }

        return Response.status(201).build();

     }

     @GET
     @ApiOperation( value = "obtener vacunas " , notes = "vacunas ordenadas" )
     @ApiResponses( value = {
             @ApiResponse( code = 201, message = "Operation completed ", response = Vacuna.class, responseContainer = "List"),
      })
      @Path("/vacunasorden")
      @Produces(MediaType.APPLICATION_JSON)
      public Response  listadoVacunas(){

         List<Vacuna> l = manager.vacunasAlfabeticamente();
         GenericEntity<List<Vacuna>> entity = new GenericEntity<List<Vacuna>>(l){};
         return Response.status(201).entity(entity).build();

      }

    @GET
    @ApiOperation( value = "obtener vacunas por número " , notes = "vacunas ordenadas" )
    @ApiResponses( value = {
            @ApiResponse( code = 201, message = "Operation completed ",  responseContainer = "List"),
    })

    @Path("/vacunasnum")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  listadoVacunasPorNumero(){

        List<String> l = manager.vacunasPorCantidad();
        GenericEntity<List<String>> entity = new GenericEntity<List<String>>(l){};
        return Response.status(201).entity(entity).build();

    }


    @GET
    @ApiOperation( value = "Seguimientos " , notes = "Seguimientos" )
    @ApiResponses( value = {
            @ApiResponse( code = 201, message = "Operation completed ",  responseContainer = "List"),
    })

    @Path("/seguimiento/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response  listadoSeguimientos(@PathParam("id") String id){

        List<Seguimiento> l = manager.seguimientoUsuario(id);
        GenericEntity<List<Seguimiento>> entity = new GenericEntity<List<Seguimiento>>(l){};
        return Response.status(201).entity(entity).build();

    }

    @POST
    @ApiOperation(value = "poner seguimiento ", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Seguimiento.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/seguimiento")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Seguimiento seg) {

        if(seg.getUserID() == null )
            return Response.status(500).entity(seg).build();
        manager.hacerSeguimiento(seg.getUserID(),seg.getFecha(),seg.getComentario());
        return Response.status(201).entity(seg).build();
    }





























}
