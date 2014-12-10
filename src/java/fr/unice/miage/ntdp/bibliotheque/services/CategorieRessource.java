package fr.unice.miage.ntdp.bibliotheque.services;

import fr.unice.miage.ntdp.bibliotheque.Categorie;
import fr.unice.miage.ntdp.bibliotheque.Message;
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
import javax.xml.bind.annotation.XmlRootElement;

@Path("categorie")
@Stateless
@XmlRootElement
public class CategorieRessource extends AbstractFacade<Categorie> {

    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieRessource() {
        super(Categorie.class);
    }

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
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Categorie entity) {
        super.create(entity);
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json,application/xml"})
    public Categorie listbyId(@PathParam("id") Long id) {
        return super.find(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({"application/json,application/xml"})
    public void update(@PathParam("id") Long id, Categorie c) {
        Categorie ct = super.find(id);
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
    public List<Categorie> findByRange(@PathParam("min") Integer min, @PathParam("max") Integer max) {
        return super.findRange(new int[]{min, max});
    }

}
