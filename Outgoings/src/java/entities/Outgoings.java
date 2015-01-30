/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bk-laptop
 */
@Entity
@Table(name = "OUTGOINGS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Outgoings.findbyMember", query = "select out from Outgoings as out where out.memberid.memberid = :theId order by out.trsdate"),
    @NamedQuery(name = "Outgoings.findAll", query = "SELECT o FROM Outgoings o"),
    @NamedQuery(name = "Outgoings.findByDocid", query = "SELECT o FROM Outgoings o WHERE o.docid = :docid"),
    @NamedQuery(name = "Outgoings.findByTrsdate", query = "SELECT o FROM Outgoings o WHERE o.trsdate = :trsdate"),
    @NamedQuery(name = "Outgoings.findByQty", query = "SELECT o FROM Outgoings o WHERE o.qty = :qty"),
    @NamedQuery(name = "Outgoings.findByTotal", query = "SELECT o FROM Outgoings o WHERE o.total = :total")})
public class Outgoings implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOCID")
    private BigDecimal docid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRSDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QTY")
    private BigInteger qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private BigInteger total;
    @JoinColumn(name = "PRDID", referencedColumnName = "PRDID")
    @ManyToOne(optional = false)
    private Products prdid;
    @JoinColumn(name = "MEMBERID", referencedColumnName = "MEMBERID")
    @ManyToOne(optional = false)
    private Members memberid;

    public Outgoings() {
    }

    public Outgoings(BigDecimal docid) {
        this.docid = docid;
    }

    public Outgoings(BigDecimal docid, Date trsdate, BigInteger qty, BigInteger total) {
        this.docid = docid;
        this.trsdate = trsdate;
        this.qty = qty;
        this.total = total;
    }

    public BigDecimal getDocid() {
        return docid;
    }

    public void setDocid(BigDecimal docid) {
        this.docid = docid;
    }

    public Date getTrsdate() {
        return trsdate;
    }

    public void setTrsdate(Date trsdate) {
        this.trsdate = trsdate;
    }

    public BigInteger getQty() {
        return qty;
    }

    public void setQty(BigInteger qty) {
        this.qty = qty;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public Products getPrdid() {
        return prdid;
    }

    public void setPrdid(Products prdid) {
        this.prdid = prdid;
    }

    public Members getMemberid() {
        return memberid;
    }

    public void setMemberid(Members memberid) {
        this.memberid = memberid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docid != null ? docid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Outgoings)) {
            return false;
        }
        Outgoings other = (Outgoings) object;
        if ((this.docid == null && other.docid != null) || (this.docid != null && !this.docid.equals(other.docid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Outgoings[ docid=" + docid + " ]";
    }
    
}
