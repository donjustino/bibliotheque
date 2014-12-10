/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Livre;
import fr.unice.miage.ntdp.bibliotheque.Message;
import fr.unice.miage.ntdp.bibliotheque.Personne;
import fr.unice.miage.ntdp.bibliotheque.Pret;
import fr.unice.miage.ntdp.bibliotheque.PretStatus;
import fr.unice.miage.ntdp.bibliotheque.bean.AbstractFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("pret")
@Stateless
@XmlRootElement
public class PretRessource extends AbstractFacade<Pret> {
     @EJB
    private LivreRessource livreFacade;
    @EJB
    private PersonneRessource personneFacade;
    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    public PretRessource() {
        super(Pret.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    /*
    @GET
    @Produces({"application/json,application/xml"})
    public List<Pret> list() {
        return super.findAll();
    }*/
    
    @GET
    @Produces({"application/json,application/xml"})
    public Message list() {
        Message listMessage = new Message();
        listMessage.data = super.findAll();
        listMessage.status = true;
        listMessage.message = "ça marche";
        return listMessage;
    }
    

    @POST
    @Consumes({"application/json,application/xml"})
    @Override
    public void create(Pret c) {
        super.create(c);
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json,application/xml"})
    public Pret listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({"application/json,application/xml"})
    public void update(@PathParam("id") Long id, Pret c) {
        Pret ct = super.find(id);
        if (ct != null) {
            super.edit(c);
        }
    }
    
    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
        String json = "Delete";
        return json;
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
    public List<Pret> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }

    @GET
    @Path("chercheParLivreId/{livreId}")
    @Produces({"application/xml", "application/json"})
    public List<Pret> chercheParLivre(@PathParam("livreId") Long livreId) {
        Query query = em.createNamedQuery("findByLivreId");
        Livre livre = livreFacade.find(livreId);
        query.setParameter("livre", livre);
        List<Pret> prets = query.getResultList();
        return prets;
    }

    @GET
    @Path("chercherParIdUtilsateur/{userId}")
    @Produces({"application/xml", "application/json"})
    public List<Pret> chercherParIdUtilsateur(@PathParam("userId") Long userId) {
        Query query = em.createNamedQuery("findByUserId");
        Personne user = personneFacade.find(userId);
        query.setParameter("user", user);
        List<Pret> prets = query.getResultList();
        return prets;
    }

    @PUT
    @Path("{id}/statutPret/{statutpret}")
    @Produces({"application/json"})
    public Pret setStatusPret(@PathParam("statutpret") Integer statut, @PathParam("id") Long id) {
        Pret p = super.find(id);
        p.setStatus(PretStatus.values()[statut]);
        return p;
    }

}
