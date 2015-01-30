/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Members;
import entities.Products;
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
public class MembersFacade extends AbstractFacade<Members> {
    @PersistenceContext(unitName = "OutgoingsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MembersFacade() {
        super(Members.class);
    }
    
    public List<Members> getDescFromID(BigDecimal memberid) {  
           List<Members> Out;  
           Out = em.createNamedQuery("Members.findByMemberid").setParameter("memberid", memberid).getResultList();  
           return Out;  
      }
    
}
