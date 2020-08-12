package com.restful.services;

import com.restful.entidades.Personal;
import com.restful.session.PersonalFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("Personal")

public class personalRest {
    
    @EJB
    private PersonalFacade personalFacade;
    
    
    //Traer todos los Datos de la BDD
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Personal>findAll(){
        return personalFacade.findAll();
    }  
    
    //Traer un ID especifico
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   @Path("{id}")   
   public Personal findByID(@PathParam("id")Integer id){
       return personalFacade.find(id);    
   }
   
   //Insertar 
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Personal personal) {
        personalFacade.create(personal);   
        String message = "Insertado Correctamente !!";
        return Response.ok(message).build();
        
    }
    
    //Eliminar
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response remove(@PathParam("id") Integer id) {
        personalFacade.remove(personalFacade.find(id));
        String message = "Eliminado Correctamente !!";
        return Response.ok(message).build();
    }
    
    //ACTUALIZAR
    @PUT
    @Consumes ({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response edit(@PathParam("id")Integer id, Personal personal) {
        if (personalFacade.find(id) != null) {
            personal.setId(id);
            personalFacade.edit(personal); 
        }
        String message = "Actualizado Correctamente !!";
        return Response.ok(message).build();
    }
    
   
}
