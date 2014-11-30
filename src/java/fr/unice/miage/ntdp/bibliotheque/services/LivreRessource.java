package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Categorie;
import fr.unice.miage.ntdp.bibliotheque.Livre;
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

@Path("livre")
@Stateless
@XmlRootElement
public class LivreRessource extends AbstractFacade<Livre> {

    @EJB
    private CategorieRessource categorieRessource;
    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivreRessource() {
        super(Livre.class);
    }

    @GET
    @Produces({"application/json,application/xml"})
    public List<Livre> list() {
        return super.findAll();
    }

    @POST
    @Consumes({"application/json,application/xml"})
    @Override
    public void create(Livre c) {
        String output = "POST:Jersey say : ";
        Response.status(204).entity(output).build();
        super.create(c);
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json,application/xml"})
    public Livre listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({"application/json,application/xml"})
    public void update(@PathParam("id") Long id, Livre c) {
        Livre ct = super.find(id);
        if (ct != null) {
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
    public List<Livre> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }
    @GET
    @Path("search/{id}")
    @Produces({"application/json"})
    public List<Livre> chercherLivreParCategorie(@PathParam("id") long id) {
      
        Query q = em.createNamedQuery("chercherLivreParCat");
        System.out.println(id);
        q.setParameter("categorie", id);
        return q.getResultList();
    }
    

}
