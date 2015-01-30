/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "MEMBERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Members.findAll", query = "SELECT m FROM Members m"),
    @NamedQuery(name = "Members.findByMemberid", query = "SELECT m FROM Members m WHERE m.memberid = :memberid"),
    @NamedQuery(name = "Members.findByDescription", query = "SELECT m FROM Members m WHERE m.description = :description"),
    @NamedQuery(name = "Members.findByAge", query = "SELECT m FROM Members m WHERE m.age = :age"),
    @NamedQuery(name = "Members.findBySexe", query = "SELECT m FROM Members m WHERE m.sexe = :sexe")})
public class Members implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEMBERID")
    private BigDecimal memberid;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "AGE")
    private BigInteger age;
    @Size(max = 1)
    @Column(name = "SEXE")
    private String sexe;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberid")
    private Collection<Outgoings> outgoingsCollection;
    
    public Members() {
    }

    public Members(BigDecimal memberid) {
        this.memberid = memberid;
    }

    public BigDecimal getMemberid() {
        return memberid;
    }

    public void setMemberid(BigDecimal memberid) {
         
         this.memberid = memberid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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
        hash += (memberid != null ? memberid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Members)) {
            return false;
        }
        Members other = (Members) object;
        if ((this.memberid == null && other.memberid != null) || (this.memberid != null && !this.memberid.equals(other.memberid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Members[ memberid=" + memberid + " ]";
    }
    
    
   
}
