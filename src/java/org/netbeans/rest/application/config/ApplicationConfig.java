/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Application;

/**
 *
 * @author Justin
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
      
            Class jsonProvider;
        try {
            jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
             resources.add(jsonProvider);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.AuteurRessource.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.CategorieRessource.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.LivreRessource.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.PersonneRessource.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.PretRessource.class);
        resources.add(fr.unice.miage.ntdp.bibliotheque.services.UsersRessource.class);
        resources.add(org.netbeans.rest.application.config.RequestFilter.class);
    }
    
}
