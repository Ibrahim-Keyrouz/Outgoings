/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bk-laptop
 */
@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByPrdid", query = "SELECT p FROM Products p WHERE p.prdid = :prdid"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRDID")
    private BigDecimal prdid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prdid")
    private Collection<Outgoings> outgoingsCollection;

    public Products() {
    }

    public Products(BigDecimal prdid) {
        this.prdid = prdid;
    }

    public Products(BigDecimal prdid, String description) {
        this.prdid = prdid;
        this.description = description;
    }

    public BigDecimal getPrdid() {
        return prdid;
    }

    public void setPrdid(BigDecimal prdid) {
        this.prdid = prdid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Outgoings> getOutgoingsCollection() {
        return outgoingsCollection;
    }

    public void setOutgoingsCollection(Collection<Outgoings> outgoingsCollection) {
        this.outgoingsCollection = outgoingsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prdid != null ? prdid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.prdid == null && other.prdid != null) || (this.prdid != null && !this.prdid.equals(other.prdid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Products[ prdid=" + prdid + " ]";
    }
    
}
