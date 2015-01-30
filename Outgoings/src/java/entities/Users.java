/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.security.MessageDigest;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bk-laptop
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserid", query = "SELECT u FROM Users u WHERE u.userid = :userid"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "DESCRIPTION", referencedColumnName = "DESCRIPTION")
    @ManyToOne(optional = false)
    private Groups description;

    public Users() {
    }

    public Users(String userid) {
        this.userid = userid;
    }

    public Users(String userid, String name, String password) {
        this.userid = userid;
        this.name = name;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

   

    public Groups getDescription() {
        return description;
    }

    public void setDescription(Groups description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    
     public void setPassword(String password) {
        
        
       this.password = this.sha256(password);
        //this.password = password;
    }

     private String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();
            
            for (int i = 0 ; i <hash.length;i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1 ) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (Exception ex) {
            throw new RuntimeException(ex);
            
            }
        }
    @Override
    public String toString() {
        return "entities.Users[ userid=" + userid + " ]";
    }
    
}
