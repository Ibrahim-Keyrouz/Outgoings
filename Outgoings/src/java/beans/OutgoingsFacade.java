/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Outgoings;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bk-laptop
 */
@Stateless
public class OutgoingsFacade extends AbstractFacade<Outgoings> {
    @PersistenceContext(unitName = "OutgoingsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OutgoingsFacade() {
        super(Outgoings.class);
    }
    
     public List<Outgoings> getOutgoingsFromMember(BigDecimal memberid) {  
           List<Outgoings> Out;  
           Out = em.createNamedQuery("Outgoings.findbyMember").setParameter("theId", memberid).getResultList();  
           return Out;  
      }  
    
}
