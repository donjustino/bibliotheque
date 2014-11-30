/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.AccountStatus;
import fr.unice.miage.ntdp.bibliotheque.Users;
import fr.unice.miage.ntdp.bibliotheque.bean.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Justin
 */
@Path("users")
@Stateless
@XmlRootElement
public class UsersRessource extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    public UsersRessource() {
        super(Users.class);
}

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
     @GET
    @Produces({"application/json,application/xml"})
    public List<Users> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/json,application/xml"})
    @Override
    public void create(Users c) {
        String output = "POST:Jersey say : ";
        Response.status(204).entity(output).build();
        super.create(c);
    }
    
    @GET
    @Path("/{id}")
    @Produces({"application/json,application/xml"})
    public Users listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({"application/json,application/xml"})
    public void update(@PathParam("id") Long id, Users c){
        Users ct = super.find(id);
        if(ct != null){
            super.edit(c);
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Consumes("text/plain")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("/count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("/{min}/{max}")
    @Produces({"application/json,application/xml"})
    public List<Users> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
    
    @PUT
    @Path("{id}/setStatusById/{statutCompteId}")
    @Produces({"application/xml", "application/json"})
    public Users setNewStatus(@PathParam("statutCompteId") String statutCompteId, @PathParam("id") Long id) {
        
        Users user = super.find(id);
        user.setStatutCompte(AccountStatus.valueOf(statutCompteId));
       
       return user;
    }
}
