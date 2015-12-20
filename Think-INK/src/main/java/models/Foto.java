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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FOTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foto.findAll", query = "SELECT f FROM Foto f"),
    @NamedQuery(name = "Foto.findByIdFoto", query = "SELECT f FROM Foto f WHERE f.idFoto = :idFoto"),
    @NamedQuery(name = "Foto.findByImagen", query = "SELECT f FROM Foto f WHERE f.imagen = :imagen"),
    @NamedQuery(name = "Foto.findByFechaSubida", query = "SELECT f FROM Foto f WHERE f.fechaSubida = :fechaSubida"),
    @NamedQuery(name = "Foto.findByCantMeGusta", query = "SELECT f FROM Foto f WHERE f.cantMeGusta = :cantMeGusta")})
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FOTO")
    private Integer idFoto;
    @Size(max = 1024)
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "FECHA_SUBIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubida;
    @Column(name = "CANT_ME_GUSTA")
    private BigInteger cantMeGusta;
    @JoinColumn(name = "ID_GALERIA", referencedColumnName = "ID_GALERIA")
    @ManyToOne(optional = false)
    private Galeria idGaleria;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFoto")
    private Collection<HashtagUsuarioFoto> hashtagUsuarioFotoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFoto")
    private Collection<MeGusta> meGustaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFoto")
    private Collection<Etiqueta> etiquetaCollection;

    public Foto() {
    }

    public Foto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public BigInteger getCantMeGusta() {
        return cantMeGusta;
    }

    public void setCantMeGusta(BigInteger cantMeGusta) {
        this.cantMeGusta = cantMeGusta;
    }

    public Galeria getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(Galeria idGaleria) {
        this.idGaleria = idGaleria;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<HashtagUsuarioFoto> getHashtagUsuarioFotoCollection() {
        return hashtagUsuarioFotoCollection;
    }

    public void setHashtagUsuarioFotoCollection(Collection<HashtagUsuarioFoto> hashtagUsuarioFotoCollection) {
        this.hashtagUsuarioFotoCollection = hashtagUsuarioFotoCollection;
    }

    @XmlTransient
    public Collection<MeGusta> getMeGustaCollection() {
        return meGustaCollection;
    }

    public void setMeGustaCollection(Collection<MeGusta> meGustaCollection) {
        this.meGustaCollection = meGustaCollection;
    }

    @XmlTransient
    public Collection<Etiqueta> getEtiquetaCollection() {
        return etiquetaCollection;
    }

    public void setEtiquetaCollection(Collection<Etiqueta> etiquetaCollection) {
        this.etiquetaCollection = etiquetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFoto != null ? idFoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foto)) {
            return false;
        }
        Foto other = (Foto) object;
        if ((this.idFoto == null && other.idFoto != null) || (this.idFoto != null && !this.idFoto.equals(other.idFoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Foto[ idFoto=" + idFoto + " ]";
    }
    
}
