/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.unice.miage.ntdp.bibliotheque;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edou
 */
@Entity
@XmlRootElement(name = "pret")
@NamedQueries({
@NamedQuery(
    name="findByLivreId",
    query="SELECT c FROM Pret c WHERE c.livre = :livre"
),
    @NamedQuery(
    name="findByUserId",
    query="SELECT p FROM Pret p WHERE p.user = :user"
),
})
public class Pret implements Serializable {
    @Enumerated(EnumType.STRING)
    private PretStatus status;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Livre livre; 
    @ManyToOne
    private Users user; 
    private Date debut; 
    private int Duree; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pret)) {
            return false;
        }
        Pret other = (Pret) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pret[ id=" + id + " ]";
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int Duree) {
        this.Duree = Duree;
    }
    
    public void setStatus(PretStatus status) {
        this.status = status;
    }

   
    
}
