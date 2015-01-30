/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
public class ProductsFacade extends AbstractFacade<Products> {
    @PersistenceContext(unitName = "OutgoingsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
    
     public List<Products> getDescFromID(BigDecimal prdid) {  
           List<Products> Out;  
           Out = em.createNamedQuery("Products.findByPrdid").setParameter("prdid", prdid).getResultList();  
           return Out;  
      }
}
