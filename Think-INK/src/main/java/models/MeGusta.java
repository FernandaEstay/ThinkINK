/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "ME_GUSTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeGusta.findAll", query = "SELECT m FROM MeGusta m"),
    @NamedQuery(name = "MeGusta.findByIdMeGusta", query = "SELECT m FROM MeGusta m WHERE m.idMeGusta = :idMeGusta"),
    @NamedQuery(name = "MeGusta.findByFecha", query = "SELECT m FROM MeGusta m WHERE m.fecha = :fecha")})
public class MeGusta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ME_GUSTA")
    private Integer idMeGusta;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")
    @ManyToOne(optional = false)
    private Foto idFoto;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public MeGusta() {
    }

    public MeGusta(Integer idMeGusta) {
        this.idMeGusta = idMeGusta;
    }

    public Integer getIdMeGusta() {
        return idMeGusta;
    }

    public void setIdMeGusta(Integer idMeGusta) {
        this.idMeGusta = idMeGusta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Foto getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Foto idFoto) {
        this.idFoto = idFoto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMeGusta != null ? idMeGusta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeGusta)) {
            return false;
        }
        MeGusta other = (MeGusta) object;
        if ((this.idMeGusta == null && other.idMeGusta != null) || (this.idMeGusta != null && !this.idMeGusta.equals(other.idMeGusta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.MeGusta[ idMeGusta=" + idMeGusta + " ]";
    }
    
}
