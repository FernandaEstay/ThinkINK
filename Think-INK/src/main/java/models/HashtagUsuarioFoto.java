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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "HASHTAG_USUARIO_FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HashtagUsuarioFoto.findAll", query = "SELECT h FROM HashtagUsuarioFoto h"),
    @NamedQuery(name = "HashtagUsuarioFoto.findByIdHashtagUsuarioFoto", query = "SELECT h FROM HashtagUsuarioFoto h WHERE h.idHashtagUsuarioFoto = :idHashtagUsuarioFoto"),
    @NamedQuery(name = "HashtagUsuarioFoto.findByFechaPublicacion", query = "SELECT h FROM HashtagUsuarioFoto h WHERE h.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "HashtagUsuarioFoto.findByNombre", query = "SELECT h FROM HashtagUsuarioFoto h WHERE h.nombre = :nombre")})
public class HashtagUsuarioFoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HASHTAG_USUARIO_FOTO")
    private Integer idHashtagUsuarioFoto;
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "ID_FOTO", referencedColumnName = "ID_FOTO")
    @ManyToOne(optional = false)
    private Foto idFoto;
    @JoinColumn(name = "ID_HASHTAG", referencedColumnName = "ID_HASHTAG")
    @ManyToOne(optional = false)
    private Hashtag idHashtag;

    public HashtagUsuarioFoto() {
    }

    public HashtagUsuarioFoto(Integer idHashtagUsuarioFoto) {
        this.idHashtagUsuarioFoto = idHashtagUsuarioFoto;
    }

    public Integer getIdHashtagUsuarioFoto() {
        return idHashtagUsuarioFoto;
    }

    public void setIdHashtagUsuarioFoto(Integer idHashtagUsuarioFoto) {
        this.idHashtagUsuarioFoto = idHashtagUsuarioFoto;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Foto getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Foto idFoto) {
        this.idFoto = idFoto;
    }

    public Hashtag getIdHashtag() {
        return idHashtag;
    }

    public void setIdHashtag(Hashtag idHashtag) {
        this.idHashtag = idHashtag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHashtagUsuarioFoto != null ? idHashtagUsuarioFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HashtagUsuarioFoto)) {
            return false;
        }
        HashtagUsuarioFoto other = (HashtagUsuarioFoto) object;
        if ((this.idHashtagUsuarioFoto == null && other.idHashtagUsuarioFoto != null) || (this.idHashtagUsuarioFoto != null && !this.idHashtagUsuarioFoto.equals(other.idHashtagUsuarioFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.HashtagUsuarioFoto[ idHashtagUsuarioFoto=" + idHashtagUsuarioFoto + " ]";
    }
    
}
