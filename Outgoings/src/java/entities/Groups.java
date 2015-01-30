/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
@Table(name = "GROUPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByGroupid", query = "SELECT g FROM Groups g WHERE g.groupid = :groupid"),
    @NamedQuery(name = "Groups.findByDescription", query = "SELECT g FROM Groups g WHERE g.description = :description")})
public class Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "GROUPID")
    private BigInteger groupid;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "description")
    private Collection<Users> usersCollection;

    public Groups() {
    }

    public Groups(String description) {
        this.description = description;
    }

    public BigInteger getGroupid() {
        return groupid;
    }

    public void setGroupid(BigInteger groupid) {
        this.groupid = groupid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (description != null ? description.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.description == null && other.description != null) || (this.description != null && !this.description.equals(other.description))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groups[ description=" + description + " ]";
    }
    
}
