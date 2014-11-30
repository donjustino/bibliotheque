/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque.bean;

import fr.unice.miage.ntdp.bibliotheque.Pret;
import fr.unice.miage.ntdp.bibliotheque.PretStatus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author edou
 */
@Stateless
public class PretFacade extends AbstractFacade<Pret> {
    @PersistenceContext(unitName = "BibliothequePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PretFacade() {
        super(Pret.class);
    }
   
}
