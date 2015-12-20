/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "HASHTAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hashtag.findAll", query = "SELECT h FROM Hashtag h"),
    @NamedQuery(name = "Hashtag.findByIdHashtag", query = "SELECT h FROM Hashtag h WHERE h.idHashtag = :idHashtag"),
    @NamedQuery(name = "Hashtag.findByNombre", query = "SELECT h FROM Hashtag h WHERE h.nombre = :nombre"),
    @NamedQuery(name = "Hashtag.findByCantidad", query = "SELECT h FROM Hashtag h WHERE h.cantidad = :cantidad"),
    @NamedQuery(name = "Hashtag.findByFecha", query = "SELECT h FROM Hashtag h WHERE h.fecha = :fecha")})
public class Hashtag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HASHTAG")
    private Integer idHashtag;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHashtag")
    private Collection<HashtagUsuarioFoto> hashtagUsuarioFotoCollection;

    public Hashtag() {
    }

    public Hashtag(Integer idHashtag) {
        this.idHashtag = idHashtag;
    }

    public Integer getIdHashtag() {
        return idHashtag;
    }

    public void setIdHashtag(Integer idHashtag) {
        this.idHashtag = idHashtag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public Collection<HashtagUsuarioFoto> getHashtagUsuarioFotoCollection() {
        return hashtagUsuarioFotoCollection;
    }

    public void setHashtagUsuarioFotoCollection(Collection<HashtagUsuarioFoto> hashtagUsuarioFotoCollection) {
        this.hashtagUsuarioFotoCollection = hashtagUsuarioFotoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHashtag != null ? idHashtag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hashtag)) {
            return false;
        }
        Hashtag other = (Hashtag) object;
        if ((this.idHashtag == null && other.idHashtag != null) || (this.idHashtag != null && !this.idHashtag.equals(other.idHashtag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Hashtag[ idHashtag=" + idHashtag + " ]";
    }
    
}
